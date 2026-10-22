package com.mapcontrol.vehicle;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.desaysv.ivi.extra.project.carinfo.CarSettingID;
import com.desaysv.ivi.extra.project.carinfo.NewEnergyID;
import com.desaysv.ivi.extra.project.carinfo.ReadOnlyID;
import com.desaysv.ivi.extra.project.carinfo.proxy.CarInfoProxy;
import com.desaysv.ivi.extra.project.carinfo.proxy.Constants;
import com.desaysv.ivi.extra.project.carinfo.proxy.IServiceConnectListener;
import com.desaysv.ivi.vdb.IVDBusNotify;
import com.desaysv.ivi.vdb.event.VDEvent;
import com.desaysv.ivi.vdb.event.id.carinfo.VDEventCarInfo;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CarInfoProxy + VDBus üzerinden araç metriklerini paylaşımlı olarak sunar.
 * {@link com.mapcontrol.ui.builder.VehicleInfoTabBuilder} ve launcher dashboard aynı kaynağı kullanır.
 */
public final class VehicleMetricsRepository {

    public interface Listener {
        void onMetricsUpdated(VehicleMetricsSnapshot snapshot);
    }

    private static final int MODULE_READONLY = VDEventCarInfo.MODULE_READONLY_INFO;
    private static final int MODULE_SETTING = VDEventCarInfo.MODULE_CAR_SETTING;
    private static final int MODULE_NEW_ENERGY = VDEventCarInfo.MODULE_NEW_ENERGY;

    static final int[] SUBSCRIBED_READONLY_IDS = {
            ReadOnlyID.ID_GRAND_TOTAL_KM,
            ReadOnlyID.ID_TRIP,
            ReadOnlyID.ID_GRAND_TOTAL_KM_AFTER_CLEAR,
            ReadOnlyID.ID_GRAND_TOTAL_KM_AFTER_RUNNING,
            ReadOnlyID.ID_FUEL_PERCENT,
            ReadOnlyID.ID_ENDURANCE_KM,
            ReadOnlyID.ID_TOTAL_RANGE,
            ReadOnlyID.ID_SUM_FUEL,
            ReadOnlyID.ID_LOW_FUEL_WARNING,
            ReadOnlyID.ID_AVG_FUEL_CONS,
            ReadOnlyID.ID_AVERAGE_FUEL_CONS_AFTER_RUNNING,
            ReadOnlyID.ID_ENGINE_RPM,
            ReadOnlyID.ID_WATER_TEMPERATURE,
            ReadOnlyID.ID_IBS_VOLTAGE,
            ReadOnlyID.ID_CAR_SPEED,
            ReadOnlyID.ID_SPEED_GAUGE_DISPLAY,
            ReadOnlyID.ID_GEARBOX_STATE,
            ReadOnlyID.ID_SYSTEM_POWER_MODE,
            ReadOnlyID.ID_AVG_SPEED,
            // Kapı / bagaj (OEM: 1=açık, 0=kapalı)
            ReadOnlyID.ID_DRIVE_DOOR_STATE,
            ReadOnlyID.ID_PASSENGER_DOOR_STATE,
            ReadOnlyID.ID_LFFT_BEHIND_DOOR_STATE,
            ReadOnlyID.ID_RIGHT_BEHIND_DOOR_STATE,
            ReadOnlyID.ID_TRUNK_STATE,
    };

    /** Cam tavan — MODULE_CAR_SETTING (SPF = sunroof). */
    static final int[] SUBSCRIBED_SETTING_IDS = {
            CarSettingID.ID_CAR_SPF_OPERATE,
            CarSettingID.ID_CAR_SPF_POSI,
    };

    /** Sürüş modu — MODULE_NEW_ENERGY. */
    static final int[] SUBSCRIBED_NEW_ENERGY_IDS = {
            NewEnergyID.ID_DRIVE_MODE,
    };

    private final Context appContext;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final ExecutorService ioExecutor = Executors.newSingleThreadExecutor();
    private final Set<Listener> listeners = new CopyOnWriteArraySet<>();
    private final VehicleMetricsCache cache = new VehicleMetricsCache();

    private final CarInfoProxy proxy;
    private final AtomicBoolean refreshPending = new AtomicBoolean();
    private final AtomicInteger connectionGeneration = new AtomicInteger();
    private int registeredGeneration = -1;
    private volatile boolean released;
    private final IServiceConnectListener connectionListener = state -> {
        connectionGeneration.incrementAndGet();
        refresh();
    };

    private boolean callbacksRegistered;
    private volatile boolean started;

    private final IVDBusNotify.Stub carInfoNotifyStub = new IVDBusNotify.Stub() {
        @Override
        public void onVDBusNotify(VDEvent event) {
            if (!started || event == null || event.getPayload() == null) {
                return;
            }
            int module = event.getId();
            if (module != MODULE_READONLY
                    && module != MODULE_SETTING
                    && module != MODULE_NEW_ENERGY) {
                return;
            }
            int itemId = event.getPayload().getInt(Constants.CMD_ID, -1);
            int[] values = event.getPayload().getIntArray(Constants.VALUE);
            if (values == null || values.length == 0) {
                return;
            }
            int generation = connectionGeneration.get();
            enqueue(() -> {
                if (started && generation == connectionGeneration.get() && isCarInfoConnected()
                        && cache.update(module, itemId, values)) notifyListeners();
            });
        }
    };

    public VehicleMetricsRepository(Context context) {
        this(context, CarInfoProxy.getInstance());
    }

    VehicleMetricsRepository(Context context, CarInfoProxy proxy) {
        this.appContext = context.getApplicationContext();
        this.proxy = proxy;
    }

    public void addListener(Listener listener) {
        if (listener == null || released) {
            return;
        }
        listeners.add(listener);
        if (!started) {
            start();
        } else {
            mainHandler.post(() -> listener.onMetricsUpdated(currentSnapshot()));
        }
    }

    public void removeListener(Listener listener) {
        if (listener == null) {
            return;
        }
        listeners.remove(listener);
        if (listeners.isEmpty()) {
            stop();
        }
    }

    public void refresh() { refresh(false); }

    /** One periodic read for the header; load all metrics only when connecting or reconnecting. */
    public void refreshDriveMode() { refresh(true); }

    private void refresh(boolean modeOnly) {
        if (!started || released || !refreshPending.compareAndSet(false, true)) return;
        enqueue(() -> {
            try {
                if (!started) return;
                if (!isCarInfoConnected()) {
                    unregisterCallbacks();
                    cache.clear();
                    proxy.init(appContext);
                }
                if (isCarInfoConnected()) {
                    boolean fullSnapshot = !modeOnly || !callbacksRegistered
                            || registeredGeneration != connectionGeneration.get();
                    registerCallbacksIfNeeded();
                    if (fullSnapshot) loadSnapshotFromBus();
                    else loadDriveModeFromBus();
                }
                if (!isCarInfoConnected()) cache.clear();
            } catch (RuntimeException unavailable) {
                cache.clear();
                unregisterCallbacks();
            } finally {
                refreshPending.set(false);
                if (started) notifyListeners();
            }
        });
    }

    public void release() {
        listeners.clear();
        stop();
        released = true;
        // Let queued callback cleanup finish on its owning worker.
        ioExecutor.shutdown();
    }

    private void enqueue(Runnable task) {
        if (released) return;
        try { ioExecutor.execute(task); }
        catch (java.util.concurrent.RejectedExecutionException ignored) { }
    }

    public VehicleMetricsSnapshot currentSnapshot() {
        return VehicleMetricsSnapshot.from(cache, isCarInfoConnected());
    }

    public int getCombined(int readOnlyId) {
        return cache.getCombined(readOnlyId);
    }

    public boolean isConnected() {
        return isCarInfoConnected();
    }

    private void start() {
        if (started || released) return;
        started = true;
        proxy.regServiceConnectListener(connectionListener);
        refresh();
    }

    private void stop() {
        started = false;
        proxy.unRegServiceConnectListener(connectionListener);
        enqueue(() -> {
            if (!started) {
                unregisterCallbacks();
                cache.clear();
            }
        });
    }

    private void notifyListeners() {
        if (listeners.isEmpty()) {
            return;
        }
        VehicleMetricsSnapshot snapshot = currentSnapshot();
        mainHandler.post(() -> {
            for (Listener listener : listeners) {
                listener.onMetricsUpdated(snapshot);
            }
        });
    }

    private boolean isCarInfoConnected() {
        return proxy.isServiceConnnected();
    }

    private void registerCallbacksIfNeeded() {
        int generation = connectionGeneration.get();
        if (!isCarInfoConnected()) return;
        if (callbacksRegistered && registeredGeneration == generation) return;
        unregisterCallbacks();
        cache.clear();
        callbacksRegistered = true; // Also clean up partially successful subscriptions on failure.
        registeredGeneration = generation;
        proxy.regCallBack(MODULE_READONLY, carInfoNotifyStub, SUBSCRIBED_READONLY_IDS);
        proxy.regCallBack(MODULE_SETTING, carInfoNotifyStub, SUBSCRIBED_SETTING_IDS);
        proxy.regCallBack(MODULE_NEW_ENERGY, carInfoNotifyStub, SUBSCRIBED_NEW_ENERGY_IDS);
    }

    private void unregisterCallbacks() {
        if (!callbacksRegistered) {
            return;
        }
        try {
            proxy.unRegCallBack(MODULE_READONLY, carInfoNotifyStub);
            proxy.unRegCallBack(MODULE_SETTING, carInfoNotifyStub);
            proxy.unRegCallBack(MODULE_NEW_ENERGY, carInfoNotifyStub);
        } catch (Throwable ignored) {
        }
        callbacksRegistered = false;
    }

    private void loadSnapshotFromBus() {
        if (!isCarInfoConnected()) {
            cache.clear();
            return;
        }
        for (int id : SUBSCRIBED_READONLY_IDS) {
            int[] values = proxy.getItemValuesOrNull(MODULE_READONLY, id);
            if (values != null && values.length > 0) {
                cache.put(MODULE_READONLY, id, values);
            }
        }
        for (int id : SUBSCRIBED_SETTING_IDS) {
            int[] values = proxy.getItemValuesOrNull(MODULE_SETTING, id);
            if (values != null && values.length > 0) {
                cache.put(MODULE_SETTING, id, values);
            }
        }
        loadDriveModeFromBus();
    }

    private void loadDriveModeFromBus() {
        for (int id : SUBSCRIBED_NEW_ENERGY_IDS) {
            int[] values = proxy.getItemValuesOrNull(MODULE_NEW_ENERGY, id);
            // No answer is unknown; never retain a stale mode or substitute the startup preference.
            cache.put(MODULE_NEW_ENERGY, id, values);
        }
    }
}
