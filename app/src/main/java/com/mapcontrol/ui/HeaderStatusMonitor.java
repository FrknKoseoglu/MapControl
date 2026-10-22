package com.mapcontrol.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;

import com.mapcontrol.audio.AudioEqualizerController;
import com.mapcontrol.ui.builder.TopBarBuilder;
import com.mapcontrol.util.ClusterNavigationState;
import com.mapcontrol.vehicle.DriveModeStyle;
import com.mapcontrol.vehicle.VehicleMetricsRepository;
import com.mapcontrol.vehicle.VehicleMetricsSnapshot;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BooleanSupplier;

/** Reads cached/service state while the activity is visible, including missed background changes. */
public final class HeaderStatusMonitor {
    private final Context context;
    private final List<HeaderStatusSink> sinks = new CopyOnWriteArrayList<>();
    private final VehicleMetricsRepository metrics;
    private final BooleanSupplier serverRunning;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private boolean started;
    private final VehicleMetricsRepository.Listener metricsListener = this::bindMetrics;
    private final Runnable refreshTask = new Runnable() {
        @Override public void run() {
            if (!started) return;
            refresh();
            metrics.refreshDriveMode(); // Retry late connections and modes missed by OEM callbacks, off the UI thread.
            handler.postDelayed(this, 2000);
        }
    };

    public HeaderStatusMonitor(Context context, HeaderStatusSink initialSink, VehicleMetricsRepository metrics,
            BooleanSupplier serverRunning) {
        this.context = context.getApplicationContext();
        if (initialSink != null) {
            this.sinks.add(initialSink);
        }
        this.metrics = metrics;
        this.serverRunning = serverRunning;
    }

    public void addSink(HeaderStatusSink sink) {
        if (sink != null && !sinks.contains(sink)) {
            sinks.add(sink);
            sink.setWifiState(readWifiState());
            sink.setServerRunning(serverRunning.getAsBoolean());
            sink.setProjectionActive(ClusterNavigationState.getLastKnownOpen());
            VehicleMetricsSnapshot snapshot = metrics.currentSnapshot();
            int mode = snapshot != null ? snapshot.driveMode : Integer.MIN_VALUE;
            if (!DriveModeStyle.isThemed(mode)) {
                int prefMode = context.getSharedPreferences("MapControlPrefs", Context.MODE_PRIVATE)
                        .getInt("driveModeSetting", -1);
                if (DriveModeStyle.isThemed(prefMode)) {
                    mode = prefMode;
                }
            }
            sink.setDriveMode(snapshot != null && snapshot.connected, mode);
            sink.setSoundBoost(AudioEqualizerController.isBoostActive(context),
                    AudioEqualizerController.getGainMb(context));
        }
    }

    public void removeSink(HeaderStatusSink sink) {
        if (sink != null) {
            sinks.remove(sink);
        }
    }

    public void start() {
        if (started) return;
        started = true;
        metrics.addListener(metricsListener);
        handler.post(refreshTask);
    }

    public void stop() {
        if (!started) return;
        started = false;
        handler.removeCallbacks(refreshTask);
        metrics.removeListener(metricsListener);
    }

    public void refresh() {
        TopBarBuilder.WifiState wifiState = readWifiState();
        boolean running = serverRunning.getAsBoolean();
        boolean projectionActive = ClusterNavigationState.getLastKnownOpen();
        VehicleMetricsSnapshot snapshot = metrics.currentSnapshot();
        boolean connected = snapshot != null && snapshot.connected;
        int mode = snapshot != null ? snapshot.driveMode : Integer.MIN_VALUE;
        if (!DriveModeStyle.isThemed(mode)) {
            int prefMode = context.getSharedPreferences("MapControlPrefs", Context.MODE_PRIVATE)
                    .getInt("driveModeSetting", -1);
            if (DriveModeStyle.isThemed(prefMode)) {
                mode = prefMode;
            }
        }
        boolean boostActive = AudioEqualizerController.isBoostActive(context);
        int gainMb = AudioEqualizerController.getGainMb(context);

        for (HeaderStatusSink sink : sinks) {
            sink.setWifiState(wifiState);
            sink.setServerRunning(running);
            sink.setProjectionActive(projectionActive);
            sink.setDriveMode(connected, mode);
            sink.setSoundBoost(boostActive, gainMb);
        }
    }

    private void bindMetrics(VehicleMetricsSnapshot snapshot) {
        if (started && snapshot != null) {
            int mode = snapshot.driveMode;
            if (!DriveModeStyle.isThemed(mode)) {
                int prefMode = context.getSharedPreferences("MapControlPrefs", Context.MODE_PRIVATE)
                        .getInt("driveModeSetting", -1);
                if (DriveModeStyle.isThemed(prefMode)) {
                    mode = prefMode;
                }
            }
            for (HeaderStatusSink sink : sinks) {
                sink.setDriveMode(snapshot.connected, mode);
            }
        }
    }

    private TopBarBuilder.WifiState readWifiState() {
        try {
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            if (wifi == null) return TopBarBuilder.WifiState.UNKNOWN;
            if (!wifi.isWifiEnabled()) return TopBarBuilder.WifiState.OFF;
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity == null) return TopBarBuilder.WifiState.UNKNOWN;
            for (Network network : connectivity.getAllNetworks()) {
                NetworkCapabilities caps = connectivity.getNetworkCapabilities(network);
                if (caps != null && caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return TopBarBuilder.WifiState.CONNECTED;
                }
            }
            return TopBarBuilder.WifiState.DISCONNECTED;
        } catch (RuntimeException unavailable) {
            return TopBarBuilder.WifiState.UNKNOWN;
        }
    }
}
