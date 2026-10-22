package com.mapcontrol.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.provider.Settings;

import com.mapcontrol.service.BootReceiver;

/**
 * Aynı açılışta karşılama ve ana ekran bir kez açılır.
 * Açılış kimliği, platformun açılış sayacından okunur.
 */
public final class BootSession {

    private static final String KEY_BOOT = "bootGeneration";
    private static final String KEY_WELCOME = "welcomeGeneration";
    private static final String KEY_UI = "uiGeneration";
    private static final String KEY_UI_PENDING = "uiLaunchRequested";
    private static volatile String fallbackBootId;

    private BootSession() {
    }

    public static void noteServiceStart(Context context) {
        SharedPreferences prefs = prefs(context);
        String current = currentId(context);
        String stored = prefs.getString(KEY_BOOT, null);
        SharedPreferences.Editor editor = prefs.edit().putString(KEY_BOOT, current);
        if (stored != null && !stored.equals(current)) {
            editor.remove(KEY_WELCOME).remove(KEY_UI);
        }
        editor.apply();
    }

    public static boolean isWelcomeShown(Context context) {
        return currentId(context).equals(prefs(context).getString(KEY_WELCOME, null));
    }

    public static void markWelcomeShown(Context context) {
        prefs(context).edit().putString(KEY_WELCOME, currentId(context)).apply();
    }

    public static void clearWelcomeShown(Context context) {
        prefs(context).edit().remove(KEY_WELCOME).apply();
    }

    public static void setUiPending(Context context, boolean pending) {
        prefs(context).edit().putBoolean(KEY_UI_PENDING, pending).commit();
    }

    public static boolean isUiPending(Context context) {
        return prefs(context).getBoolean(KEY_UI_PENDING, false);
    }

    public static boolean isUiLaunched(Context context) {
        return currentId(context).equals(prefs(context).getString(KEY_UI, null));
    }

    public static void markUiLaunched(Context context) {
        prefs(context).edit().putString(KEY_UI, currentId(context)).apply();
    }

    private static String currentId(Context context) {
        try {
            int count = Settings.Global.getInt(
                    context.getContentResolver(), Settings.Global.BOOT_COUNT, -1);
            if (count >= 0) {
                return "boot-" + count;
            }
        } catch (Exception ignored) {
        }
        synchronized (BootSession.class) {
            if (fallbackBootId == null) {
                try (java.io.BufferedReader reader = new java.io.BufferedReader(
                        new java.io.FileReader("/proc/sys/kernel/random/boot_id"))) {
                    String id = reader.readLine();
                    if (id != null && id.matches("[a-fA-F0-9-]{36}")) fallbackBootId = "kernel-" + id;
                } catch (java.io.IOException ignored) { }
                if (fallbackBootId == null) {
                    long estimate = System.currentTimeMillis() - SystemClock.elapsedRealtime();
                    long stored = prefs(context).getLong("fallbackBootWallTime", Long.MIN_VALUE);
                    long bootTime = stored != Long.MIN_VALUE && Math.abs(estimate - stored) < 5000L ? stored : estimate;
                    prefs(context).edit().putLong("fallbackBootWallTime", bootTime).apply();
                    fallbackBootId = "wall-" + bootTime;
                }
            }
            return fallbackBootId;
        }
    }

    private static SharedPreferences prefs(Context context) {
        return context.getApplicationContext()
                .getSharedPreferences(BootReceiver.PREFS_NAME, Context.MODE_PRIVATE);
    }
}
