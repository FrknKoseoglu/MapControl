package com.mapcontrol.manager;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.mapcontrol.util.TargetPackageStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class SportAnimationControllerTest {
    static class Harness extends ContextWrapper {
        final List<Intent> sent = new ArrayList<>();
        Harness() { super(InstrumentationRegistry.getInstrumentation().getTargetContext()); }
        @Override public Context getApplicationContext() { return this; }
        @Override public SharedPreferences getSharedPreferences(String name, int mode) {
            return super.getSharedPreferences("sport_animation_test_" + name, mode);
        }
        @Override public void sendBroadcast(Intent intent) { sent.add(intent); }
    }
    @Test public void optInUsesActualModeAndSendsOnlyOnePackageScopedRequest() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(() -> {
            Harness context = new Harness();
            SharedPreferences prefs = TargetPackageStore.prefs(context);
            prefs.edit().clear().putInt("driveModeSetting", 2).commit();
            ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();
            long[] now = {0};
            int[] mode = {1}, reads = {0};
            List<String> logs = new ArrayList<>();
            SportAnimationController controller = new SportAnimationController(context, worker, logs::add,
                    () -> { reads[0]++; return new SportAnimationController.Reading(true, mode[0]); }, () -> now[0]);
            try {
                controller.poll();
                assertEquals(0, reads[0]);
                assertTrue(context.sent.isEmpty());
                prefs.edit().putBoolean(SportAnimationController.PREF_ENABLED, true).commit();
                controller.poll();
                assertTrue(context.sent.isEmpty());
                mode[0] = 2; now[0] = 100; controller.poll();
                now[0] = 1000; controller.poll();
                controller.poll();
                assertEquals(1, context.sent.size());
                Intent request = context.sent.get(0);
                assertEquals("com.desaysv.systemui.ACTION_DIALOG_CLOSE", request.getAction());
                assertEquals("com.desaysv.vehiclesetting", request.getPackage());
                assertNull(request.getComponent());
                assertEquals(2, prefs.getInt("driveModeSetting", -1));
                assertEquals(1, logs.size());
                assertTrue(logs.get(0).contains("isteği"));
            } finally { controller.release(); worker.shutdownNow(); prefs.edit().clear().commit(); }
        });
    }
    @Test public void quickOffOnAndReleaseCancelPendingRequests() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(() -> {
            Harness context = new Harness();
            SharedPreferences prefs = TargetPackageStore.prefs(context);
            prefs.edit().clear().putBoolean(SportAnimationController.PREF_ENABLED, true).commit();
            ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();
            long[] now = {0}; int[] mode = {1};
            SportAnimationController controller = new SportAnimationController(context, worker, message -> {},
                    () -> new SportAnimationController.Reading(true, mode[0]), () -> now[0]);
            try {
                controller.poll(); mode[0] = 2; now[0] = 100; controller.poll();
                prefs.edit().putBoolean(SportAnimationController.PREF_ENABLED, false).commit();
                prefs.edit().putBoolean(SportAnimationController.PREF_ENABLED, true).commit();
                now[0] = 1000; controller.poll();
                assertTrue(context.sent.isEmpty());
                mode[0] = 1; controller.poll();
                mode[0] = 2; now[0] = 2000; controller.poll();
                controller.release(); now[0] = 3000; controller.poll();
                assertTrue(context.sent.isEmpty());
            } finally { controller.release(); worker.shutdownNow(); prefs.edit().clear().commit(); }
        });
    }
}
