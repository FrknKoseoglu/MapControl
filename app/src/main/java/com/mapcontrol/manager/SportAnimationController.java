package com.mapcontrol.manager;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import com.desaysv.ivi.extra.project.carinfo.NewEnergyID;
import com.desaysv.ivi.extra.project.carinfo.proxy.Constants;
import com.desaysv.ivi.vdb.client.VDBus;
import com.desaysv.ivi.vdb.event.VDEvent;
import com.desaysv.ivi.vdb.event.id.carinfo.VDEventCarInfo;
import com.mapcontrol.util.TargetPackageStore;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.LongSupplier;

/** Opt-in dismissal of the identified OEM Sport window, using its own Back control only. */
public final class SportAnimationController {
    public static final String PREF_ENABLED = "hideSportAnimation";
    public static final String OEM_PACKAGE = SportAnimationWindow.PACKAGE;
    private static final String TAG = "SportAnimation";
    private static final long RETRY_MS = 200;
    private static final long BURST_MS = 2000;
    private static final int MAX_CLICK_ATTEMPTS = 3;
    interface ModeSource { int read(); }
    interface Transport { VDEvent read(VDEvent request); }
    interface Dialog {
        int windowId();
        boolean close();
        void recycle();
    }
    interface Windows { Dialog find(); }
    private final SharedPreferences prefs;
    private final Handler main = new Handler(Looper.getMainLooper());
    private final Executor worker;
    private final ExecutorService ownedWorker;
    private final Windows windows;
    private final ModeSource source;
    private final LongSupplier clock;
    private final Consumer<String> log;
    private boolean released, started, inFlight;
    private int generation;
    private long deadline;
    private int lastWindow = -1, attempts;
    private String lastDiagnostic;
    private final Runnable scan = this::scan;
    private final SharedPreferences.OnSharedPreferenceChangeListener listener = (p, key) -> {
        if (key == null || PREF_ENABLED.equals(key)) main.post(() -> {
            generation++;
            lastWindow = -1; attempts = 0;
            main.removeCallbacks(scan);
            if (enabled()) requestScan();
        });
    };

    public SportAnimationController(AccessibilityService service) {
        this(service, () -> SportAnimationWindow.find(service), () -> readMode(request -> {
            VDBus bus = VDBus.getDefault();
            bus.init(service.getApplicationContext());
            // Do not gate a valid reply on CarInfoProxy's separately maintained connection flag.
            return bus.getOnce(request);
        }), Executors.newSingleThreadExecutor(), SystemClock::uptimeMillis,
                message -> Log.i(TAG, message), true);
    }

    SportAnimationController(Context context, Windows windows, ModeSource source, Executor worker,
            LongSupplier clock, Consumer<String> log, boolean ownsWorker) {
        this.prefs = TargetPackageStore.prefs(context);
        this.windows = windows; this.source = source; this.worker = worker;
        this.clock = clock; this.log = log;
        this.ownedWorker = ownsWorker ? (ExecutorService) worker : null;
    }

    public void start() {
        if (started || released) return;
        started = true;
        prefs.registerOnSharedPreferenceChangeListener(listener);
        if (enabled()) requestScan();
    }

    public void onWindowEvent(AccessibilityEvent event) {
        if (!enabled() || event == null) return;
        int type = event.getEventType();
        if (type != AccessibilityEvent.TYPE_WINDOWS_CHANGED
                && type != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
                && type != AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) return;
        CharSequence pkg = event.getPackageName();
        if (type != AccessibilityEvent.TYPE_WINDOWS_CHANGED
                && (pkg == null || !OEM_PACKAGE.contentEquals(pkg))) return;
        requestScan();
    }

    private boolean enabled() { return started && !released && prefs.getBoolean(PREF_ENABLED, false); }

    private void requestScan() {
        if (!enabled()) return;
        deadline = clock.getAsLong() + BURST_MS;
        main.removeCallbacks(scan);
        main.post(scan);
    }

    private void retry() {
        if (enabled() && clock.getAsLong() < deadline) {
            main.removeCallbacks(scan);
            main.postDelayed(scan, RETRY_MS);
        }
    }

    private void scan() {
        if (!enabled() || inFlight || clock.getAsLong() > deadline) return;
        try {
            Dialog dialog = windows.find();
            if (dialog == null) { retry(); return; }
            int id;
            try { id = dialog.windowId(); } finally { dialog.recycle(); }
            if (id < 0) return;
            if (id != lastWindow) { lastWindow = id; attempts = 0; }
            if (attempts >= MAX_CLICK_ATTEMPTS) return;
            int ticket = generation;
            inFlight = true;
            worker.execute(() -> {
                int mode;
                try { mode = source.read(); } catch (RuntimeException unavailable) { mode = -1; }
                int actualMode = mode;
                main.post(() -> finishRead(ticket, id, actualMode));
            });
        } catch (RuntimeException unavailable) {
            inFlight = false;
            diagnostic("Pencere okunamadı: " + unavailable.getClass().getSimpleName());
            retry();
        }
    }

    private void finishRead(int ticket, int windowId, int mode) {
        inFlight = false;
        if (!enabled()) return;
        if (ticket != generation) { retry(); return; }
        if (clock.getAsLong() > deadline) return;
        if (mode != 2) {
            diagnostic(mode < 0 ? "Mod verisi alınamadı; pencereye dokunulmadı"
                    : "Gerçek mod=" + mode + "; Sport dışı pencereye dokunulmadı");
            retry(); return;
        }
        try {
            // Reacquire after the asynchronous read: never click a stale node or replacement window.
            Dialog dialog = windows.find();
            if (dialog == null) { retry(); return; }
            try {
                if (dialog.windowId() != windowId) { retry(); return; }
                attempts++;
                boolean accepted = dialog.close();
                diagnostic(accepted ? "Sport penceresinin kendi geri düğmesi tetiklendi"
                        : "Sport penceresi kapatma isteğini kabul etmedi");
            } finally { dialog.recycle(); }
        } catch (RuntimeException unavailable) {
            diagnostic("Pencere kapatılamadı: " + unavailable.getClass().getSimpleName());
        }
        retry();
    }

    private void diagnostic(String message) {
        if (!message.equals(lastDiagnostic)) {
            lastDiagnostic = message;
            if (log != null) log.accept(message);
        }
    }

    static int readMode(Transport transport) {
        Bundle query = new Bundle();
        query.putInt(Constants.CMD_ID, NewEnergyID.ID_DRIVE_MODE);
        VDEvent reply = transport.read(new VDEvent(VDEventCarInfo.MODULE_NEW_ENERGY, query));
        Bundle payload = reply != null ? reply.getPayload() : null;
        if (reply == null || reply.getId() != VDEventCarInfo.MODULE_NEW_ENERGY || payload == null) return -1;
        if (payload.containsKey(Constants.CMD_ID)
                && (!(payload.get(Constants.CMD_ID) instanceof Integer)
                    || payload.getInt(Constants.CMD_ID) != NewEnergyID.ID_DRIVE_MODE)) return -1;
        int[] values = payload.getIntArray(Constants.VALUE);
        if (values == null || values.length == 0) return -1;
        // Match OEM CarInfoProxy.getItemValue: the first integer is the item value.
        // Do not combine extra integers into a different mode (e.g. [0, 2] is ECO).
        int mode = values[0];
        return mode >= 0 && mode <= 7 && mode != 6 ? mode : -1;
    }

    public void release() {
        released = true; generation++;
        prefs.unregisterOnSharedPreferenceChangeListener(listener);
        main.removeCallbacks(scan);
        if (ownedWorker != null) ownedWorker.shutdownNow();
    }
}
