package com.mapcontrol.ui;

import android.app.Instrumentation;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.ContextThemeWrapper;
import android.widget.TextView;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.mapcontrol.R;
import com.mapcontrol.ui.widget.StatusWidgetsView;
import com.mapcontrol.vehicle.DriveModeStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class HeaderStatusMonitorTest {
    private Context context() {
        return new ContextThemeWrapper(InstrumentationRegistry.getInstrumentation().getTargetContext(),
                R.style.Theme_MapControl);
    }

    private static HeaderStatusMonitor.ModeReading snapshot(boolean connected, int mode) {
        return new HeaderStatusMonitor.ModeReading(connected, mode);
    }

    private static final class Source implements HeaderStatusMonitor.ModeSource {
        HeaderStatusMonitor.ModeReading current = snapshot(true, DriveModeStyle.NORMAL);
        HeaderStatusMonitor.ModeReading next = current;
        int refreshes;
        Runnable onRefresh = () -> {};
        final List<Consumer<HeaderStatusMonitor.ModeReading>> listeners = new ArrayList<>();
        public HeaderStatusMonitor.ModeReading currentReading() { return current; }
        public void addListener(Consumer<HeaderStatusMonitor.ModeReading> listener) {
            listeners.add(listener);
            listener.accept(current);
        }
        public void removeListener(Consumer<HeaderStatusMonitor.ModeReading> listener) { listeners.remove(listener); }
        public void refresh() {
            refreshes++;
            current = next;
            for (Consumer<HeaderStatusMonitor.ModeReading> listener : new ArrayList<>(listeners)) {
                listener.accept(current);
            }
            onRefresh.run();
        }
    }

    private void assertMode(StatusWidgetsView widget, String prefix, String text) {
        assertEquals(text, ((TextView) widget.findViewWithTag(prefix + "_mode_value")).getText().toString());
    }

    @Test public void selectionRefreshReadsAfterTheEventAndUpdatesEveryWidget() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        Context context = context();
        SharedPreferences prefs = context.getSharedPreferences("MapControlPrefs", Context.MODE_PRIVATE);
        boolean hadMode = prefs.contains("driveModeSetting");
        int savedMode = prefs.getInt("driveModeSetting", -1);
        Source source = new Source();
        HeaderStatusMonitor[] monitor = new HeaderStatusMonitor[1];
        StatusWidgetsView[] widgets = new StatusWidgetsView[3];
        try {
            instrumentation.runOnMainSync(() -> {
                widgets[0] = new StatusWidgetsView(context, "rail", tab -> {});
                widgets[1] = new StatusWidgetsView(context, "launcher", tab -> {});
                monitor[0] = new HeaderStatusMonitor(context, widgets[0], source, () -> false);
                monitor[0].addSink(widgets[1]);
                monitor[0].start();
            });
            instrumentation.waitForIdleSync();
            int[] before = new int[1];
            instrumentation.runOnMainSync(() -> {
                before[0] = source.refreshes;
                prefs.edit().putInt("driveModeSetting", DriveModeStyle.SPORT).commit();
                monitor[0].refresh();
                monitor[0].refresh();
                assertEquals("Read must wait until the selection event has finished", before[0], source.refreshes);
                assertMode(widgets[0], "rail", "NORMAL");
                assertMode(widgets[1], "launcher", "NORMAL");
                // The existing command runs later in the same event; the next read must see its reply.
                source.next = snapshot(true, DriveModeStyle.SPORT);
            });
            instrumentation.waitForIdleSync();
            instrumentation.runOnMainSync(() -> {
                assertEquals("Same-event refresh requests must be coalesced", before[0] + 1, source.refreshes);
                assertMode(widgets[0], "rail", "SPORT");
                assertMode(widgets[1], "launcher", "SPORT");
                widgets[2] = new StatusWidgetsView(context, "late", tab -> {});
                monitor[0].addSink(widgets[2]);
                assertMode(widgets[2], "late", "SPORT");
                source.next = snapshot(true, Integer.MIN_VALUE);
                monitor[0].refresh();
            });
            instrumentation.waitForIdleSync();
            instrumentation.runOnMainSync(() -> {
                assertMode(widgets[0], "rail", "—");
                assertMode(widgets[1], "launcher", "—");
                source.next = snapshot(false, DriveModeStyle.SPORT);
                monitor[0].refresh();
            });
            instrumentation.waitForIdleSync();
            instrumentation.runOnMainSync(() -> {
                assertMode(widgets[0], "rail", "—");
                source.next = snapshot(true, DriveModeStyle.SNOW);
                monitor[0].refresh();
            });
            instrumentation.waitForIdleSync();
            instrumentation.runOnMainSync(() -> {
                assertMode(widgets[0], "rail", "SNOW");
                assertMode(widgets[1], "launcher", "SNOW");
                assertMode(widgets[2], "late", "SNOW");
                assertEquals("Readout must not overwrite the saved choice", DriveModeStyle.SPORT,
                        prefs.getInt("driveModeSetting", -1));
            });
        } finally {
            instrumentation.runOnMainSync(() -> { if (monitor[0] != null) monitor[0].stop(); });
            if (hadMode) prefs.edit().putInt("driveModeSetting", savedMode).commit();
            else prefs.edit().remove("driveModeSetting").commit();
        }
    }

    @Test public void periodicRefreshPicksUpExternalModeChangesWithoutNavigation() throws Exception {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        Source source = new Source();
        HeaderStatusMonitor[] monitor = new HeaderStatusMonitor[1];
        StatusWidgetsView[] widget = new StatusWidgetsView[1];
        try {
            instrumentation.runOnMainSync(() -> {
                widget[0] = new StatusWidgetsView(context(), "live", tab -> {});
                monitor[0] = new HeaderStatusMonitor(context(), widget[0], source, () -> false);
                monitor[0].start();
            });
            instrumentation.waitForIdleSync();
            CountDownLatch changed = new CountDownLatch(1);
            instrumentation.runOnMainSync(() -> {
                source.next = snapshot(true, DriveModeStyle.ECO);
                source.onRefresh = () -> { if (source.current.driveMode == DriveModeStyle.ECO) changed.countDown(); };
            });
            assertTrue("Periodic UI refresh must request fresh vehicle state", changed.await(5, TimeUnit.SECONDS));
            instrumentation.runOnMainSync(() -> {
                assertMode(widget[0], "live", "ECO");
                assertTrue(source.refreshes >= 2);
            });
        } finally {
            instrumentation.runOnMainSync(() -> { if (monitor[0] != null) monitor[0].stop(); });
        }
    }

    @Test public void stoppingCancelsQueuedAndPeriodicReadsAndRestartRefreshesAgain() throws Exception {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        Source source = new Source();
        HeaderStatusMonitor[] monitor = new HeaderStatusMonitor[1];
        int[] before = new int[1];
        CountDownLatch readAfterStop = new CountDownLatch(1);
        try {
            instrumentation.runOnMainSync(() -> {
                monitor[0] = new HeaderStatusMonitor(context(), null, source, () -> false);
                monitor[0].start();
            });
            instrumentation.waitForIdleSync();
            instrumentation.runOnMainSync(() -> {
                before[0] = source.refreshes;
                source.onRefresh = readAfterStop::countDown;
                monitor[0].refresh();
                monitor[0].stop();
                assertTrue(source.listeners.isEmpty());
            });
            assertFalse("Hidden UI must not continue reading vehicle state", readAfterStop.await(2300, TimeUnit.MILLISECONDS));
            instrumentation.runOnMainSync(() -> {
                assertEquals(before[0], source.refreshes);
                source.onRefresh = () -> {};
                source.next = snapshot(true, DriveModeStyle.SPORT);
                monitor[0].start();
            });
            instrumentation.waitForIdleSync();
            instrumentation.runOnMainSync(() -> {
                assertTrue(source.refreshes > before[0]);
                assertEquals(DriveModeStyle.SPORT, source.current.driveMode);
            });
        } finally {
            instrumentation.runOnMainSync(() -> { if (monitor[0] != null) monitor[0].stop(); });
        }
    }
}
