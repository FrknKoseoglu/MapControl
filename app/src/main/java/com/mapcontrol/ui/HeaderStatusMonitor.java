package com.mapcontrol.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;

import com.mapcontrol.ui.builder.TopBarBuilder;
import com.mapcontrol.util.ClusterNavigationState;
import com.mapcontrol.vehicle.VehicleMetricsRepository;
import com.mapcontrol.vehicle.VehicleMetricsSnapshot;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BooleanSupplier;

/** Reads cached/service state while the activity is visible, including missed background changes. */
public final class HeaderStatusMonitor {
    private final Context context;
    private final List<HeaderStatusSink> sinks = new CopyOnWriteArrayList<>();
    private final MetricsSource metrics;
    private final BooleanSupplier serverRunning;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private boolean started;
    private final VehicleMetricsRepository.Listener metricsListener = this::bindMetrics;
    // Run after the current UI event (and its existing vehicle command) has completed.
    private final Runnable refreshMetricsTask;

    interface MetricsSource {
        VehicleMetricsSnapshot currentSnapshot();
        void addListener(VehicleMetricsRepository.Listener listener);
        void removeListener(VehicleMetricsRepository.Listener listener);
        void refresh();
    }

    private final Runnable refreshTask = new Runnable() {
        @Override public void run() {
            if (!started) return;
            refresh();
            handler.postDelayed(this, 2000);
        }
    };

    public HeaderStatusMonitor(Context context, HeaderStatusSink initialSink, VehicleMetricsRepository metrics,
            BooleanSupplier serverRunning) {
        this(context, initialSink, new MetricsSource() {
            public VehicleMetricsSnapshot currentSnapshot() { return metrics.currentSnapshot(); }
            public void addListener(VehicleMetricsRepository.Listener listener) { metrics.addListener(listener); }
            public void removeListener(VehicleMetricsRepository.Listener listener) { metrics.removeListener(listener); }
            public void refresh() { metrics.refresh(); }
        }, serverRunning);
    }

    HeaderStatusMonitor(Context context, HeaderStatusSink initialSink, MetricsSource metrics,
            BooleanSupplier serverRunning) {
        this.context = context.getApplicationContext();
        if (initialSink != null) {
            this.sinks.add(initialSink);
        }
        this.metrics = metrics;
        this.serverRunning = serverRunning;
        refreshMetricsTask = () -> {
            if (started) metrics.refresh();
        };
    }

    public void addSink(HeaderStatusSink sink) {
        if (sink != null && !sinks.contains(sink)) {
            sinks.add(sink);
            sink.setWifiState(readWifiState());
            sink.setServerRunning(serverRunning.getAsBoolean());
            sink.setProjectionActive(ClusterNavigationState.getLastKnownOpen());
            VehicleMetricsSnapshot snapshot = metrics.currentSnapshot();
            int mode = snapshot != null ? snapshot.driveMode : Integer.MIN_VALUE;
            sink.setDriveMode(snapshot != null && snapshot.connected, mode);
            sink.setSoundBoost(readBoostActive(),
                    readGainMb());
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
        handler.removeCallbacks(refreshMetricsTask);
        metrics.removeListener(metricsListener);
    }

    public void refresh() {
        TopBarBuilder.WifiState wifiState = readWifiState();
        boolean running = serverRunning.getAsBoolean();
        boolean projectionActive = ClusterNavigationState.getLastKnownOpen();
        VehicleMetricsSnapshot snapshot = metrics.currentSnapshot();
        boolean connected = snapshot != null && snapshot.connected;
        int mode = snapshot != null ? snapshot.driveMode : Integer.MIN_VALUE;
        boolean boostActive = readBoostActive();
        int gainMb = readGainMb();

        for (HeaderStatusSink sink : sinks) {
            sink.setWifiState(wifiState);
            sink.setServerRunning(running);
            sink.setProjectionActive(projectionActive);
            sink.setDriveMode(connected, mode);
            sink.setSoundBoost(boostActive, gainMb);
        }
        if (started) {
            // Coalesce requests in the same UI event and let selection handlers finish first.
            handler.removeCallbacks(refreshMetricsTask);
            handler.post(refreshMetricsTask);
        }
    }

    private void bindMetrics(VehicleMetricsSnapshot snapshot) {
        if (started && snapshot != null) {
            int mode = snapshot.driveMode;
            for (HeaderStatusSink sink : sinks) {
                sink.setDriveMode(snapshot.connected, mode);
            }
        }
    }

    private boolean readBoostActive() {
        return context.getSharedPreferences("MapControlPrefs", Context.MODE_PRIVATE)
                .getBoolean("equalizerEnabled", false) && readGainMb() > 0;
    }

    private int readGainMb() {
        return context.getSharedPreferences("MapControlPrefs", Context.MODE_PRIVATE)
                .getInt("equalizerGainMb", 0);
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
