package com.mapcontrol.weather;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mapcontrol.vehicle.VehicleLocationProvider;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

/** Weather for the vehicle's approximate position, using existing location permissions/fixes. */
public final class OpenMeteoWeatherClient {
    private static final String PREFS_NAME = "MapControlPrefs";
    private static final String KEY_CACHED_JSON = "open_meteo_weather_cache";
    private static final String KEY_CACHED_AT = "open_meteo_weather_cached_at";
    private static final String KEY_CACHED_POSITION = "open_meteo_weather_position";
    private static final long CACHE_TTL_MS = 15L * 60 * 1000;
    private static final long RETRY_MS = 60_000;

    public interface Listener {
        void onWeatherUpdated(@NonNull OpenMeteoWeatherSnapshot snapshot);
        void onWeatherFailed();
    }

    private final Context appContext;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final AtomicBoolean inFlight = new AtomicBoolean();
    private final Runnable refreshRunnable = this::fetchIfNeeded;
    private volatile boolean active;
    private volatile boolean destroyed;
    private volatile int generation;
    @Nullable private volatile HttpURLConnection connection;
    @Nullable private Listener listener;
    @Nullable private OpenMeteoWeatherSnapshot lastSnapshot;

    public OpenMeteoWeatherClient(@NonNull Context context) {
        appContext = context.getApplicationContext();
    }

    public void setListener(@Nullable Listener listener) {
        this.listener = listener;
        if (active && listener != null && lastSnapshot != null) listener.onWeatherUpdated(lastSnapshot);
    }

    public void start() {
        if (destroyed || active) return;
        active = true;
        generation++;
        fetchIfNeeded();
    }

    public void stop() {
        active = false;
        generation++;
        mainHandler.removeCallbacks(refreshRunnable);
        HttpURLConnection current = connection;
        if (current != null) current.disconnect();
    }

    public void destroy() {
        destroyed = true;
        stop();
        listener = null;
        executor.shutdownNow();
    }

    @Nullable public OpenMeteoWeatherSnapshot getLastSnapshot() { return lastSnapshot; }

    private boolean current(int request) { return active && !destroyed && request == generation; }

    private void fetchIfNeeded() {
        if (!active || destroyed || !inFlight.compareAndSet(false, true)) return;
        final int request = generation;
        try {
            executor.execute(() -> fetch(request));
        } catch (RejectedExecutionException stopped) {
            inFlight.set(false);
        }
    }

    private void fetch(int request) {
        HttpURLConnection http = null;
        long delay = RETRY_MS;
        OpenMeteoWeatherSnapshot result = null;
        try {
            if (!current(request)) return;
            Location fix = VehicleLocationProvider.lastKnown(appContext, 10L * 60 * 1000, 25_000);
            if (fix == null) return;
            // Explicit product choice: only two decimal places leave the vehicle for Open-Meteo.
            String position = String.format(Locale.US, "latitude=%.2f&longitude=%.2f",
                    fix.getLatitude(), fix.getLongitude());
            long age = System.currentTimeMillis() - prefs().getLong(KEY_CACHED_AT, 0);
            if (position.equals(prefs().getString(KEY_CACHED_POSITION, null)) && age >= 0 && age < CACHE_TTL_MS) {
                result = parse(prefs().getString(KEY_CACHED_JSON, ""));
                if (result != null) {
                    delay = Math.max(RETRY_MS, CACHE_TTL_MS - age);
                    return;
                }
            }
            if (!current(request)) return;
            http = (HttpURLConnection) new URL("https://api.open-meteo.com/v1/forecast?"
                    + position + "&current_weather=true").openConnection();
            connection = http;
            if (!current(request)) return;
            http.setConnectTimeout(10_000);
            http.setReadTimeout(10_000);
            if (http.getResponseCode() != HttpURLConnection.HTTP_OK) return;
            ByteArrayOutputStream body = new ByteArrayOutputStream();
            try (InputStream input = http.getInputStream()) {
                byte[] buffer = new byte[4096];
                for (int count; (count = input.read(buffer)) != -1;) {
                    if (body.size() + count > 64 * 1024) return;
                    body.write(buffer, 0, count);
                }
            }
            String json = new String(body.toByteArray(), StandardCharsets.UTF_8);
            result = parse(json);
            if (result != null && current(request)) {
                prefs().edit().putString(KEY_CACHED_JSON, json).putString(KEY_CACHED_POSITION, position)
                        .putLong(KEY_CACHED_AT, System.currentTimeMillis()).apply();
                delay = CACHE_TTL_MS;
            }
        } catch (Exception unavailable) {
            // No fabricated city or stale temperature when GPS/network is unavailable.
        } finally {
            if (http != null) http.disconnect();
            if (connection == http) connection = null;
            inFlight.set(false);
            final OpenMeteoWeatherSnapshot snapshot = result;
            final long nextDelay = delay;
            mainHandler.post(() -> {
                if (current(request)) {
                    lastSnapshot = snapshot;
                    if (listener != null) {
                        if (snapshot != null) listener.onWeatherUpdated(snapshot);
                        else listener.onWeatherFailed();
                    }
                    mainHandler.removeCallbacks(refreshRunnable);
                    mainHandler.postDelayed(refreshRunnable, nextDelay);
                } else if (active && !destroyed) {
                    fetchIfNeeded();
                }
            });
        }
    }

    @Nullable private OpenMeteoWeatherSnapshot parse(String json) {
        try {
            JSONObject current = new JSONObject(json).getJSONObject("current_weather");
            float temperature = (float) current.getDouble("temperature");
            float wind = (float) current.getDouble("windspeed");
            if (Float.isNaN(temperature) || Float.isInfinite(temperature)
                    || Float.isNaN(wind) || Float.isInfinite(wind) || wind < 0) return null;
            return new OpenMeteoWeatherSnapshot(temperature, wind, current.getInt("winddirection"),
                    current.getInt("weathercode"), current.getInt("is_day") == 1);
        } catch (Exception invalid) {
            return null;
        }
    }

    private SharedPreferences prefs() { return appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); }
}
