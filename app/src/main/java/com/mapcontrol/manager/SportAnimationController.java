package com.mapcontrol.manager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import com.desaysv.ivi.extra.project.carinfo.NewEnergyID;
import com.desaysv.ivi.extra.project.carinfo.proxy.CarInfoProxy;
import com.desaysv.ivi.vdb.event.id.carinfo.VDEventCarInfo;
import com.mapcontrol.util.TargetPackageStore;
import com.mapcontrol.vehicle.SportAnimationPolicy;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.LongSupplier;

/** Optional, bounded OEM window dismissal. Does not change vehicle mode or disable OEM packages. */
public final class SportAnimationController {
    public static final String PREF_ENABLED = "hideSportAnimation";
    public static final String OEM_PACKAGE = "com.desaysv.vehiclesetting";
    public static final String ACTION_CLOSE = "com.desaysv.systemui.ACTION_DIALOG_CLOSE";
    interface ModeSource { Reading read(); }
    static final class Reading {
        final boolean connected;
        final int mode;
        Reading(boolean connected, int mode) { this.connected = connected; this.mode = mode; }
    }
    private final Context context;
    private final SharedPreferences prefs;
    private final ScheduledExecutorService scheduler;
    private final ModeSource source;
    private final LongSupplier clock;
    private final Consumer<String> log;
    private final SportAnimationPolicy policy = new SportAnimationPolicy();
    private ScheduledFuture<?> pollTask;
    private volatile boolean released;
    private final AtomicInteger preferenceGeneration = new AtomicInteger();
    private int observedGeneration;
    private final SharedPreferences.OnSharedPreferenceChangeListener prefListener = (preferences, key) -> {
        if (key == null || PREF_ENABLED.equals(key)) preferenceGeneration.incrementAndGet();
    };

    public SportAnimationController(Context context, ScheduledExecutorService scheduler, Consumer<String> log) {
        this(context, scheduler, log, () -> {
            try {
                CarInfoProxy proxy = CarInfoProxy.getInstance();
                if (proxy == null || !proxy.isServiceConnnected()) return new Reading(false, -1);
                int[] values = proxy.getItemValuesOrNull(VDEventCarInfo.MODULE_NEW_ENERGY, NewEnergyID.ID_DRIVE_MODE);
                return new Reading(true, values != null && values.length > 0 ? values[0] : -1);
            } catch (Throwable t) {
                return new Reading(false, -1);
            }
        }, SystemClock::elapsedRealtime);
    }

    SportAnimationController(Context context, ScheduledExecutorService scheduler, Consumer<String> log,
            ModeSource source, LongSupplier clock) {
        this.context = context != null ? context.getApplicationContext() : null;
        this.prefs = this.context != null ? TargetPackageStore.prefs(this.context) : null;
        this.scheduler = scheduler;
        this.log = log;
        this.source = source;
        this.clock = clock;
        if (prefs != null) {
            try {
                prefs.registerOnSharedPreferenceChangeListener(prefListener);
            } catch (Throwable ignored) {}
        }
    }

    public void start() {
        if (released || pollTask != null || scheduler == null || scheduler.isShutdown()) return;
        try {
            // Uses the service's existing worker. With the option off, no extra vehicle reads occur.
            pollTask = scheduler.scheduleWithFixedDelay(this::poll, 0, 500, TimeUnit.MILLISECONDS);
        } catch (Throwable t) {
            if (log != null) {
                try {
                    log.accept("SportAnimationController başlatılamadı: " + t.getMessage());
                } catch (Throwable ignored) {}
            }
        }
    }

    void poll() {
        if (released) return;
        try {
            int generation = preferenceGeneration.get();
            if (generation != observedGeneration) {
                policy.reset();
                observedGeneration = generation;
            }
            if (prefs == null || !prefs.getBoolean(PREF_ENABLED, false)) {
                policy.reset();
                return;
            }
            if (source == null) return;
            Reading reading = source.read();
            if (reading == null || !reading.connected) {
                policy.reset();
                return;
            }
            long now = clock != null ? clock.getAsLong() : SystemClock.elapsedRealtime();
            if (!policy.observe(true, reading.connected, reading.mode, now)) return;
            if (released || generation != preferenceGeneration.get()
                    || prefs == null || !prefs.getBoolean(PREF_ENABLED, false)) return;
            if (context != null) {
                Intent request = new Intent(ACTION_CLOSE).setPackage(OEM_PACKAGE);
                try {
                    context.sendBroadcast(request);
                    if (log != null) {
                        log.accept("Sport animasyonu: OEM pencere kapatma isteği gönderildi");
                    }
                } catch (Throwable broadcastError) {
                    if (log != null) {
                        log.accept("Sport animasyonu yayın gönderilemedi: " + broadcastError.getMessage());
                    }
                }
            }
        } catch (Throwable unavailable) {
            try {
                policy.reset();
            } catch (Throwable ignored) {}
            if (log != null) {
                try {
                    log.accept("Sport animasyonu kontrolü uygulanamadı: " + (unavailable.getMessage() != null ? unavailable.getMessage() : unavailable.getClass().getSimpleName()));
                } catch (Throwable ignored) {}
            }
        }
    }

    public void release() {
        released = true;
        if (prefs != null) {
            try {
                prefs.unregisterOnSharedPreferenceChangeListener(prefListener);
            } catch (Throwable ignored) {}
        }
        if (pollTask != null) {
            try {
                pollTask.cancel(false);
            } catch (Throwable ignored) {}
            pollTask = null;
        }
        try {
            policy.reset();
        } catch (Throwable ignored) {}
    }
}
