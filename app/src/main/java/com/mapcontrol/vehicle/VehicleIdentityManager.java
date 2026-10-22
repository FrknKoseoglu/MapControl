package com.mapcontrol.vehicle;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.desaysv.ivi.extra.project.carinfo.DiagnosisID;
import com.desaysv.ivi.extra.project.carinfo.ReadOnlyID;
import com.desaysv.ivi.extra.project.carinfo.proxy.CarInfoProxy;
import com.desaysv.ivi.vdb.event.id.carinfo.VDEventCarInfo;
import com.mapcontrol.vehicle.material.MaterialVehiclePackageResolver;
import com.mapcontrol.vehicle.material.MaterialVehiclePreferences;
import com.mapcontrol.vehicle.material.OemEolConfigReader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Türkiye pazarı için araç kimliğini (Ticari Model, Model Yılı, Yazılım Sürümü, VIN)
 * JSON kataloğu, EOL yapılandırması ve sistem özelliklerini harmanlayarak çözümler.
 */
public final class VehicleIdentityManager {

    private static final String TAG = "VehicleIdentityMgr";
    private static final String ASSET_CATALOG = "vehicle_models_tr.json";

    public interface OnIdentityLoadedListener {
        void onIdentityLoaded(@NonNull VehicleIdentity identity);
    }

    public static final class ModelCatalogEntry {
        public final String id;
        public final String marketName;
        public final String platformCode;
        public final List<Integer> modelCodes;
        public final int powerType;
        public final String materialPackage;
        public final String description;

        public ModelCatalogEntry(String id, String marketName, String platformCode,
                List<Integer> modelCodes, int powerType, String materialPackage, String description) {
            this.id = id;
            this.marketName = marketName;
            this.platformCode = platformCode;
            this.modelCodes = modelCodes;
            this.powerType = powerType;
            this.materialPackage = materialPackage;
            this.description = description;
        }
    }

    private static volatile VehicleIdentityManager instance;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    private List<ModelCatalogEntry> cachedCatalog;
    private String cachedFallbackName = "-";
    private String cachedFallbackDesc = "";
    private volatile VehicleIdentity cachedIdentity;

    private VehicleIdentityManager() {
    }

    public static VehicleIdentityManager getInstance() {
        if (instance == null) {
            synchronized (VehicleIdentityManager.class) {
                if (instance == null) {
                    instance = new VehicleIdentityManager();
                }
            }
        }
        return instance;
    }

    /**
     * Kimlik bilgisini asenkron olarak çözümler ve UI thread üzerinde listener'a iletir.
     */
    public void resolveIdentityAsync(@NonNull Context context, @NonNull OnIdentityLoadedListener listener) {
        if (cachedIdentity != null) {
            listener.onIdentityLoaded(cachedIdentity);
        }
        Context app = context.getApplicationContext();
        executor.execute(() -> {
            VehicleIdentity resolved = resolveIdentitySync(app);
            cachedIdentity = resolved;
            mainHandler.post(() -> listener.onIdentityLoaded(resolved));
        });
    }

    /**
     * Senkron çözümleme (arkaplan thread veya testler için).
     */
    @NonNull
    public VehicleIdentity resolveIdentitySync(@NonNull Context context) {
        ensureCatalogLoaded(context);

        // 1. Yazılım sürümü
        String swVersion = resolveSoftwareVersion();

        // 2. VIN ve Model Yılı
        String vin = resolveVin(context);
        String modelYear = decodeModelYear(vin, swVersion);

        // 3. EOL ve Platform Bilgileri
        MaterialVehiclePackageResolver.Resolution resolution =
                MaterialVehiclePackageResolver.resolve(context);
        OemEolConfigReader eol = new OemEolConfigReader();
        eol.load(context);

        int modelCode = eol.getModelCode();
        int powerType = eol.getPowerType();
        String platformLabel = resolution.platformLabel;
        String packageName = resolution.packageName;

        // Kullanıcı ayarlarından manuel seçim varsa dikkate al
        MaterialVehiclePreferences.Selection manualSelection =
                MaterialVehiclePreferences.getSelection(context);

        // 4. Katalogdan en uygun modeli eşleştir
        ModelMatch match = matchModel(manualSelection, packageName, platformLabel,
                modelCode, powerType, swVersion);

        return new VehicleIdentity(
                match.marketName,
                match.platformCode != null ? match.platformCode : (platformLabel != null ? platformLabel : "GENEL"),
                modelYear,
                swVersion,
                vin,
                match.description
        );
    }

    private static final class ModelMatch {
        final String marketName;
        final String platformCode;
        final String description;

        ModelMatch(String marketName, String platformCode, String description) {
            this.marketName = marketName;
            this.platformCode = platformCode;
            this.description = description;
        }
    }

    @NonNull
    private ModelMatch matchModel(@Nullable MaterialVehiclePreferences.Selection manual,
            @Nullable String resolvedPkg, @Nullable String platformLabel,
            int modelCode, int powerType, @NonNull String swVersion) {

        String targetPkg = manual != null ? manual.packageName : resolvedPkg;

        // 1. Paket adına göre eşleştir (örn. com.desaysv.materialvehiclet19fl)
        if (targetPkg != null && cachedCatalog != null) {
            for (ModelCatalogEntry entry : cachedCatalog) {
                if (entry.materialPackage != null && !entry.materialPackage.isEmpty()
                        && targetPkg.equalsIgnoreCase(entry.materialPackage)) {
                    return new ModelMatch(entry.marketName, entry.platformCode, entry.description);
                }
            }
        }

        // 2. Platform koduna göre eşleştir (T19FL, T19C, T18FL3, T1EFL vb.)
        if (platformLabel != null && cachedCatalog != null) {
            for (ModelCatalogEntry entry : cachedCatalog) {
                if (entry.platformCode != null && entry.platformCode.equalsIgnoreCase(platformLabel)) {
                    return new ModelMatch(entry.marketName, entry.platformCode, entry.description);
                }
            }
        }

        // 3. modelCode ve powerType kombinasyonuna göre eşleştir
        if (modelCode > 0 && cachedCatalog != null) {
            for (ModelCatalogEntry entry : cachedCatalog) {
                if (entry.modelCodes != null && entry.modelCodes.contains(modelCode)) {
                    if (entry.powerType == powerType) {
                        return new ModelMatch(entry.marketName, entry.platformCode, entry.description);
                    }
                }
            }
        }

        // 4. Yazılım sürüm metni veya fingerprint içerisinden sezgisel eşleştirme
        String swLower = (swVersion + " " + Build.FINGERPRINT + " " + Build.DEVICE + " " + Build.BOARD).toLowerCase(Locale.ROOT);
        if (swLower.contains("t19fl") || (swLower.contains("t19c") && swLower.contains("msmnile"))) {
            return new ModelMatch("Chery Omoda 5 Pro", "T19FL", "1.6 TGDI Benzinli (Makyajlı Kasa / 8155)");
        }
        if (swLower.contains("t19c")) {
            return new ModelMatch("Chery Omoda 5", "T19C", "1.6 TGDI Benzinli (İlk Kasa)");
        }
        if (swLower.contains("t1efl")) {
            return new ModelMatch("Chery Tiggo 7 Pro Max", "T1EFL", "1.6 TGDI Benzinli (Makyajlı Kasa)");
        }
        if (swLower.contains("t1e")) {
            return new ModelMatch("Chery Tiggo 7 Pro", "T1E", "1.6 TGDI Benzinli (İlk Kasa)");
        }
        if (swLower.contains("t18fl3") || swLower.contains("t18fl")) {
            return new ModelMatch("Chery Tiggo 8 Pro Max", "T18FL3", "1.6 TGDI Benzinli / AWD (Makyajlı Kasa)");
        }
        if (swLower.contains("t18")) {
            return new ModelMatch("Chery Tiggo 8 Pro", "T18", "1.6 TGDI Benzinli (İlk Kasa)");
        }
        if (swLower.contains("t26")) {
            return new ModelMatch("Chery Tiggo 9 / Jaecoo 8", "T26", "D-SUV Segmenti");
        }
        if (swLower.contains("t1ej") || swLower.contains("tj-1")) {
            return new ModelMatch("Jaecoo 7", "T1EJ", "1.6 TGDI Benzinli / AWD");
        }
        if (swLower.contains("m1e")) {
            return new ModelMatch("Chery Arrizo 8", "M1E", "Sedan");
        }

        return new ModelMatch(cachedFallbackName, platformLabel != null ? platformLabel : "-", cachedFallbackDesc);
    }

    /**
     * Araç Yazılım / OS derleme sürümünü okur.
     */
    @NonNull
    public static String resolveSoftwareVersion() {
        if (Build.DISPLAY != null && !Build.DISPLAY.trim().isEmpty() && !Build.DISPLAY.equalsIgnoreCase("unknown")) {
            return Build.DISPLAY.trim();
        }
        String sysProp = readSystemProperty("ro.build.display.id");
        if (!sysProp.isEmpty()) {
            return sysProp;
        }
        if (Build.FINGERPRINT != null && !Build.FINGERPRINT.trim().isEmpty()) {
            return Build.FINGERPRINT.trim();
        }
        return "Bilinmiyor";
    }

    /**
     * VDBus veya sistem özellikleri üzerinden VIN (Şasi Numarası) okur.
     */
    @Nullable
    public static String resolveVin(Context context) {
        // 1. CarInfoProxy üzerinden dene (ReadOnlyID.ID_VIN = 9)
        try {
            CarInfoProxy proxy = CarInfoProxy.getInstance();
            if (proxy != null) {
                int[] values = proxy.getItemValuesOrNull(VDEventCarInfo.MODULE_READONLY_INFO, ReadOnlyID.ID_VIN);
                String vin = parseVinFromIntArray(values);
                if (vin != null && isValidVin(vin)) {
                    return vin;
                }

                // 2. DiagnosisID.ID_VIN = 16 (MODULE_DOANOSE = 327687)
                values = proxy.getItemValuesOrNull(VDEventCarInfo.MODULE_DOANOSE, DiagnosisID.ID_VIN);
                vin = parseVinFromIntArray(values);
                if (vin != null && isValidVin(vin)) {
                    return vin;
                }
            }
        } catch (Throwable t) {
            Log.w(TAG, "CarInfoProxy VIN okunamadı", t);
        }

        // 3. Android SystemProperties üzerinden dene
        String[] propKeys = {
                "persist.sys.vin",
                "ro.vin",
                "vehicle.persist.vin",
                "ro.boot.serialno",
                "ro.serialno"
        };
        for (String key : propKeys) {
            String val = readSystemProperty(key);
            if (isValidVin(val)) {
                return val.toUpperCase(Locale.ROOT);
            }
        }

        return null;
    }

    /**
     * ISO 3779 standardına göre VIN 10. karakterinden model yılını çözer.
     */
    @NonNull
    public static String decodeModelYear(@Nullable String vin, @Nullable String fallbackContext) {
        if (vin != null && vin.length() >= 10) {
            String decoded = decodeVinYearChar(vin.charAt(9));
            if (decoded != null) {
                return decoded;
            }
        }

        // VIN bulunamadıysa derleme metninden yıl ayıkla (örn. ..._250408_... veya 20250408)
        if (fallbackContext != null) {
            Pattern p4 = Pattern.compile("202[0-9]");
            Matcher m4 = p4.matcher(fallbackContext);
            if (m4.find()) {
                return m4.group();
            }

            Pattern p2 = Pattern.compile("_2([3-9])(0[1-9]|1[0-2])[0-3][0-9]_");
            Matcher m2 = p2.matcher(fallbackContext);
            if (m2.find()) {
                return "202" + m2.group(1);
            }
        }

        // Son çare sistem derleme zamanından türet
        if (Build.TIME > 0) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(Build.TIME);
            int year = cal.get(Calendar.YEAR);
            if (year >= 2020 && year <= 2035) {
                return String.valueOf(year);
            }
        }

        return "—";
    }

    /**
     * ISO 3779 standardı Model Yılı tablosu.
     */
    @Nullable
    public static String decodeVinYearChar(char c) {
        char upper = Character.toUpperCase(c);
        switch (upper) {
            case 'A': return "2010";
            case 'B': return "2011";
            case 'C': return "2012";
            case 'D': return "2013";
            case 'E': return "2014";
            case 'F': return "2015";
            case 'G': return "2016";
            case 'H': return "2017";
            case 'J': return "2018";
            case 'K': return "2019";
            case 'L': return "2020";
            case 'M': return "2021";
            case 'N': return "2022";
            case 'P': return "2023";
            case 'R': return "2024";
            case 'S': return "2025";
            case 'T': return "2026";
            case 'V': return "2027";
            case 'W': return "2028";
            case 'X': return "2029";
            case 'Y': return "2030";
            case '1': return "2031";
            case '2': return "2032";
            case '3': return "2033";
            case '4': return "2034";
            case '5': return "2035";
            case '6': return "2036";
            case '7': return "2037";
            case '8': return "2038";
            case '9': return "2039";
            default: return null;
        }
    }

    @Nullable
    public static String parseVinFromIntArray(@Nullable int[] values) {
        if (values == null || values.length < 10) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int v : values) {
            if (v >= 32 && v <= 126) {
                char c = (char) v;
                if (Character.isLetterOrDigit(c)) {
                    sb.append(Character.toUpperCase(c));
                }
            }
        }
        String result = sb.toString();
        return result.length() >= 10 ? result : null;
    }

    public static boolean isValidVin(@Nullable String vin) {
        if (vin == null) return false;
        String trimmed = vin.trim().toUpperCase(Locale.ROOT);
        // Standart şasi numarası 17 karakterdir, I, O, Q harfleri bulunmaz
        return trimmed.length() == 17 && trimmed.matches("[A-HJ-NPR-Z0-9]{17}");
    }

    private synchronized void ensureCatalogLoaded(Context context) {
        if (cachedCatalog != null) {
            return;
        }
        cachedCatalog = new ArrayList<>();
        try (InputStream is = context.getAssets().open(ASSET_CATALOG);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject root = new JSONObject(sb.toString());
            JSONArray models = root.optJSONArray("models");
            if (models != null) {
                for (int i = 0; i < models.length(); i++) {
                    JSONObject item = models.getJSONObject(i);
                    String id = item.optString("id");
                    String marketName = item.optString("market_name");
                    String platformCode = item.optString("platform_code");
                    int powerType = item.optInt("power_type", 0);
                    String materialPkg = item.optString("material_package");
                    String desc = item.optString("description");

                    List<Integer> codes = new ArrayList<>();
                    JSONArray codeArr = item.optJSONArray("model_codes");
                    if (codeArr != null) {
                        for (int j = 0; j < codeArr.length(); j++) {
                            codes.add(codeArr.getInt(j));
                        }
                    }

                    cachedCatalog.add(new ModelCatalogEntry(
                            id, marketName, platformCode, codes, powerType, materialPkg, desc
                    ));
                }
            }

            JSONObject fallback = root.optJSONObject("fallback");
            if (fallback != null) {
                cachedFallbackName = fallback.optString("market_name", cachedFallbackName);
                cachedFallbackDesc = fallback.optString("description", cachedFallbackDesc);
            }
            Log.i(TAG, "Araç modelleri kataloğu yüklendi: " + cachedCatalog.size() + " model.");
        } catch (Exception e) {
            Log.e(TAG, "Araç modelleri kataloğu okunamadı", e);
        }
    }

    private static String readSystemProperty(String key) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method get = cls.getMethod("get", String.class, String.class);
            Object value = get.invoke(null, key, "");
            return value != null ? value.toString().trim() : "";
        } catch (ReflectiveOperationException e) {
            return "";
        }
    }
}
