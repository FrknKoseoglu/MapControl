package com.mapcontrol.vehicle;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.TextView;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.desaysv.ivi.extra.project.carinfo.NewEnergyID;
import com.desaysv.ivi.extra.project.carinfo.proxy.CarInfoProxy;
import com.desaysv.ivi.extra.project.carinfo.proxy.Constants;
import com.desaysv.ivi.extra.project.carinfo.proxy.IServiceConnectListener;
import com.desaysv.ivi.vdb.IVDBusNotify;
import com.desaysv.ivi.vdb.event.VDEvent;
import com.desaysv.ivi.vdb.event.id.carinfo.VDEventCarInfo;
import com.mapcontrol.R;
import com.mapcontrol.ui.HeaderStatusMonitor;
import com.mapcontrol.ui.builder.TopBarBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class VehicleMetricsLiveHeaderTest {
    static class FakeCar extends CarInfoProxy {
        volatile boolean connected;
        volatile int[] mode = {0};
        volatile IServiceConnectListener listener;
        volatile IVDBusNotify.Stub notify;
        final AtomicInteger subscriptions = new AtomicInteger(), unsubscriptions = new AtomicInteger();
        final AtomicInteger reads = new AtomicInteger(), mainThreadIo = new AtomicInteger();
        void io() { if (Looper.myLooper() == Looper.getMainLooper()) mainThreadIo.incrementAndGet(); }
        @Override public void init(Context context) { io(); }
        @Override public boolean isServiceConnnected() { return connected; }
        @Override public void regServiceConnectListener(IServiceConnectListener l) { listener = l; }
        @Override public void unRegServiceConnectListener(IServiceConnectListener l) { if (listener == l) listener = null; }
        @Override public void regCallBack(int module, IVDBusNotify.Stub stub, int[] ids) {
            io(); subscriptions.incrementAndGet(); notify = stub;
        }
        @Override public void unRegCallBack(int module, IVDBusNotify.Stub stub) {
            io(); unsubscriptions.incrementAndGet();
        }
        @Override public int[] getItemValuesOrNull(int module, int id) {
            io(); reads.incrementAndGet();
            return module == VDEventCarInfo.MODULE_NEW_ENERGY && id == NewEnergyID.ID_DRIVE_MODE ? mode : null;
        }
        void connect(boolean value) {
            connected = value;
            IServiceConnectListener l = listener;
            if (l != null) l.onServiceConnectedChanged(value ? 1 : 0);
        }
        void emitMode(int value) throws Exception {
            mode = new int[]{value};
            Bundle payload = new Bundle();
            payload.putInt(Constants.CMD_ID, NewEnergyID.ID_DRIVE_MODE);
            payload.putIntArray(Constants.VALUE, mode);
            notify.onVDBusNotify(new VDEvent(VDEventCarInfo.MODULE_NEW_ENERGY, payload));
        }
    }
    private Context context() { return InstrumentationRegistry.getInstrumentation().getTargetContext(); }
    private VehicleMetricsSnapshot await(BlockingQueue<VehicleMetricsSnapshot> queue,
            Predicate<VehicleMetricsSnapshot> match) throws Exception {
        long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(6);
        while (System.nanoTime() < deadline) {
            VehicleMetricsSnapshot next = queue.poll(100, TimeUnit.MILLISECONDS);
            if (next != null && match.test(next)) return next;
        }
        throw new AssertionError("Expected vehicle update was not delivered");
    }
    @Test public void lateConnectionPollingCallbacksAndReconnectRecoverActualMode() throws Exception {
        FakeCar car = new FakeCar();
        VehicleMetricsRepository repo = new VehicleMetricsRepository(context(), car);
        BlockingQueue<VehicleMetricsSnapshot> updates = new LinkedBlockingQueue<>();
        VehicleMetricsRepository.Listener listener = updates::add;
        try {
            repo.addListener(listener);
            await(updates, s -> !s.connected);
            car.connect(true);
            await(updates, s -> s.connected && s.driveMode == 0);
            assertEquals(3, car.subscriptions.get());
            car.mode = new int[]{2}; // No OEM callback: periodic fresh reads must still work.
            repo.refresh();
            await(updates, s -> s.driveMode == 2);
            car.emitMode(1);
            await(updates, s -> s.driveMode == 1);
            car.connect(false);
            await(updates, s -> !s.connected && !s.hasDriveMode());
            car.mode = new int[]{3}; car.connect(true);
            await(updates, s -> s.connected && s.driveMode == 3);
            assertEquals(6, car.subscriptions.get());
            car.mode = null; repo.refresh();
            await(updates, s -> s.connected && !s.hasDriveMode());
            assertEquals(0, car.mainThreadIo.get());
            repo.removeListener(listener);
            long end = System.nanoTime() + TimeUnit.SECONDS.toNanos(3);
            while (car.unsubscriptions.get() < 6 && System.nanoTime() < end) Thread.sleep(20);
            assertEquals(6, car.unsubscriptions.get());
            assertNull(car.listener);
            int reads = car.reads.get(); repo.refresh(); Thread.sleep(100);
            assertEquals(reads, car.reads.get());
        } finally { repo.release(); }
    }
    @Test public void visibleHeaderPollsFreshModeWithoutCategoryChange() throws Exception {
        FakeCar car = new FakeCar(); car.connected = true;
        VehicleMetricsRepository repo = new VehicleMetricsRepository(context(), car);
        HeaderStatusMonitor[] monitor = new HeaderStatusMonitor[1];
        View[] header = new View[1];
        try {
            InstrumentationRegistry.getInstrumentation().runOnMainSync(() -> {
                TopBarBuilder builder = new TopBarBuilder(new ContextThemeWrapper(context(), R.style.Theme_MapControl),
                        new TopBarBuilder.TopBarCallback() {
                            public void onLogTabToggle(boolean show) { }
                            public void log(String message) { }
                        });
                header[0] = builder.build();
                monitor[0] = new HeaderStatusMonitor(context(), builder, repo, () -> false);
                monitor[0].start();
            });
            awaitLabel(header[0], "ECO");
            car.mode = new int[]{2};
            awaitLabel(header[0], "SPORT");
            car.connect(false);
            awaitLabel(header[0], "—");
            assertEquals(0, car.mainThreadIo.get());
        } finally {
            InstrumentationRegistry.getInstrumentation().runOnMainSync(() -> {
                if (monitor[0] != null) monitor[0].stop();
            });
            repo.release();
        }
    }
    private void awaitLabel(View header, String expected) throws Exception {
        long end = System.nanoTime() + TimeUnit.SECONDS.toNanos(6);
        String[] label = {""};
        while (System.nanoTime() < end) {
            InstrumentationRegistry.getInstrumentation().runOnMainSync(() ->
                    label[0] = ((TextView) header.findViewWithTag("header_mode_value")).getText().toString());
            if (expected.equals(label[0])) return;
            Thread.sleep(50);
        }
        assertEquals(expected, label[0]);
    }
}
