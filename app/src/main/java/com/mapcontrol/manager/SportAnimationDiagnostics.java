package com.mapcontrol.manager;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import com.mapcontrol.BuildConfig;
import com.mapcontrol.R;
import com.mapcontrol.service.GlobalBackService;
import com.mapcontrol.util.TargetPackageStore;
import org.json.JSONArray;

/** Bounded, feature-only diagnostics. No screen text, vehicle writes, or generic logs are collected. */
public final class SportAnimationDiagnostics {
    static final String PREFS_NAME="SuperModeDiagnostics";
    private static SharedPreferences store(Context context) {
        return context.getApplicationContext().getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
    }
    private static final String JOURNAL = "sportAnimationDiagnostics";
    private static final String PREFIX = "sportAnimationDiagnosticStage_";
    private static final int LIMIT = 32;
    private SportAnimationDiagnostics() {}

    static synchronized void begin(Context context, String serviceInfo) {
        try {
            SharedPreferences prefs = store(context);
            SharedPreferences.Editor editor = prefs.edit();
            for (String stage : new String[]{"PENCERE","MOD","OLAY","AKIS","SERVIS"}) editor.remove(PREFIX+stage);
            editor.apply();
            note(context,"SERVIS",serviceInfo);
        } catch (RuntimeException ignored) { /* Diagnostics must never break accessibility. */ }
    }

    static synchronized void note(Context context, String stage, String message) {
        try {
            SharedPreferences prefs = store(context);
            String bounded = message == null ? "null" : message.replace('\n',' ').replace('\r',' ');
            if (bounded.length() > 600) bounded = bounded.substring(0,600);
            if (bounded.equals(prefs.getString(PREFIX+stage,""))) return;
            JSONArray old;
            try { old = new JSONArray(prefs.getString(JOURNAL,"[]")); }
            catch (Exception malformed) { old = new JSONArray(); }
            JSONArray entries = new JSONArray();
            for (int i=Math.max(0,old.length()-(LIMIT-1));i<old.length();i++) entries.put(old.optString(i));
            String line = SystemClock.uptimeMillis()+"ms ["+stage+"] "+bounded;
            entries.put(line);
            prefs.edit().putString(JOURNAL,entries.toString()).putString(PREFIX+stage,bounded).apply();
            Log.i("SportAnimation",line);
        } catch (RuntimeException ignored) { /* Feature behavior does not depend on recording. */ }
    }

    public static String report(Context context) {
        SharedPreferences prefs = TargetPackageStore.prefs(context);
        StringBuilder report = new StringBuilder("Super Mod tanı kaydı\n");
        report.append("Paket: ").append(context.getPackageName()).append("\n");
        report.append("Sürüm: ").append(BuildConfig.VERSION_NAME).append(" / ").append(BuildConfig.VERSION_CODE).append("\n");
        report.append("Android API: ").append(Build.VERSION.SDK_INT).append("\n");
        report.append("Gizleme seçeneği: ").append(prefs.getBoolean(SportAnimationController.PREF_ENABLED,false)).append("\n");
        report.append("Erişilebilirlik kayıtlı: ").append(SportAnimationController.isAccessibilityRegistered(context)).append("\n");
        report.append("Erişilebilirlik bağlı: ").append(GlobalBackService.isServiceEnabled()).append("\n\n");
        try {
            JSONArray entries = new JSONArray(store(context).getString(JOURNAL,"[]"));
            if (entries.length()==0) report.append(context.getString(R.string.super_mode_diagnostics_empty));
            for (int i=0;i<entries.length();i++) report.append(entries.optString(i)).append("\n");
        } catch (Exception malformed) { report.append(context.getString(R.string.super_mode_diagnostics_empty)); }
        return report.toString();
    }

    public static void show(Context context) {
        String report = report(context);
        new AlertDialog.Builder(context).setTitle(R.string.super_mode_diagnostics_title).setMessage(report)
                .setNegativeButton(android.R.string.cancel,null)
                .setPositiveButton(R.string.super_mode_diagnostics_copy,(dialog,which) -> {
                    ClipboardManager clipboard=(ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                    if(clipboard!=null) clipboard.setPrimaryClip(ClipData.newPlainText("Super Mod",report));
                }).show();
    }
}
