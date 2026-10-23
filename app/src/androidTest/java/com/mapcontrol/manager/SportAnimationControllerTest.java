package com.mapcontrol.manager;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.accessibility.AccessibilityEvent;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.desaysv.ivi.extra.project.carinfo.NewEnergyID;
import com.desaysv.ivi.extra.project.carinfo.proxy.Constants;
import com.desaysv.ivi.vdb.event.VDEvent;
import com.desaysv.ivi.vdb.event.id.carinfo.VDEventCarInfo;
import com.mapcontrol.util.TargetPackageStore;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class SportAnimationControllerTest {
    private static void main(Runnable task) { InstrumentationRegistry.getInstrumentation().runOnMainSync(task); }
    private static void idle() { InstrumentationRegistry.getInstrumentation().waitForIdleSync(); }
    static final class Harness extends ContextWrapper implements AutoCloseable {
        final ArrayDeque<Runnable> reads = new ArrayDeque<>();
        final List<String> logs = new ArrayList<>();
        final SharedPreferences prefs;
        final SportAnimationController controller;
        int mode = 2, id = 10, clicks, modeReads;
        boolean present = true, accepted = true;
        long now;
        Harness(boolean enabled) {
            super(InstrumentationRegistry.getInstrumentation().getTargetContext());
            prefs = TargetPackageStore.prefs(this);
            main(() -> prefs.edit().clear().putBoolean(SportAnimationController.PREF_ENABLED, enabled)
                    .putInt("driveModeSetting", 1).commit());
            controller = new SportAnimationController(this, () -> !present ? null : new SportAnimationController.Dialog() {
                final int window = id;
                public int windowId() { return window; }
                public boolean close() { clicks++; if (accepted) present = false; return accepted; }
                public void recycle() {}
            }, () -> { modeReads++; return mode; }, reads::add, () -> now, logs::add, false);
        }
        @Override public Context getApplicationContext() { return this; }
        @Override public SharedPreferences getSharedPreferences(String name, int mode) {
            return super.getSharedPreferences("sport_animation_test_" + name, mode);
        }
        @Override public void sendBroadcast(Intent intent) { throw new AssertionError("No generic close broadcasts allowed"); }
        void start() { main(controller::start); idle(); }
        void event() {
            main(() -> {
                AccessibilityEvent event = AccessibilityEvent.obtain(AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED);
                event.setPackageName(SportAnimationController.OEM_PACKAGE);
                controller.onWindowEvent(event); event.recycle();
            }); idle();
        }
        void reply() { assertFalse("expected asynchronous read", reads.isEmpty()); reads.remove().run(); idle(); }
        public void close() { main(() -> { controller.release(); prefs.edit().clear().commit(); }); idle(); }
    }

    @Test public void visibleSportClosesOnStartupWithoutModeTransitionOrChangingSavedMode() {
        try (Harness h = new Harness(true)) {
            h.start(); h.reply();
            assertEquals(1, h.clicks);
            assertEquals(1, h.prefs.getInt("driveModeSetting", -1));
            assertEquals(1, h.modeReads);
        }
    }
    @Test public void disabledDoesNotReadWindowsModeOrClick() {
        try (Harness h = new Harness(false)) {
            h.start(); h.event();
            assertTrue(h.reads.isEmpty()); assertEquals(0,h.clicks);
        }
    }
    @Test public void enablingWhileAlreadySportFindsExistingWindow() {
        try (Harness h = new Harness(false)) {
            h.start(); main(() -> h.prefs.edit().putBoolean(SportAnimationController.PREF_ENABLED,true).commit());
            idle(); h.reply(); assertEquals(1,h.clicks);
        }
    }
    @Test public void lateWindowEventTriggersAfterInitiallyMissingWindow() {
        try (Harness h = new Harness(true)) {
            h.present=false; h.start(); assertTrue(h.reads.isEmpty());
            h.present=true; h.event(); h.reply(); assertEquals(1,h.clicks);
        }
    }
    @Test public void missingAndNonSportReadNeverClickRegardlessOfSavedChoice() {
        for (int mode : new int[]{-1,0,1,3,4,5,7}) try (Harness h = new Harness(true)) {
            h.mode=mode;
            main(() -> h.prefs.edit().putInt("driveModeSetting",2).commit());
            h.start(); h.reply(); assertEquals("mode="+mode,0,h.clicks);
        }
    }
    @Test public void replacedWindowIsNotClickedByStaleRead() {
        try (Harness h = new Harness(true)) {
            h.start(); h.id=11; h.reply(); assertEquals(0,h.clicks);
            h.event(); h.reply(); assertEquals(1,h.clicks);
        }
    }
    @Test public void disableAndReleaseCancelInFlightRead() {
        try (Harness h = new Harness(true)) {
            h.start(); main(() -> h.prefs.edit().putBoolean(SportAnimationController.PREF_ENABLED,false).commit());
            idle(); h.reply(); assertEquals(0,h.clicks);
        }
        try (Harness h = new Harness(true)) {
            h.start(); main(h.controller::release); h.reply(); assertEquals(0,h.clicks);
        }
    }
    @Test public void offOnInvalidatesTheOlderReadAndNeedsFreshWindowAndMode() {
        try (Harness h = new Harness(true)) {
            h.start();
            main(() -> { h.prefs.edit().putBoolean(SportAnimationController.PREF_ENABLED,false).commit();
                h.prefs.edit().putBoolean(SportAnimationController.PREF_ENABLED,true).commit(); });
            idle(); h.reply(); assertEquals(0,h.clicks);
            h.event(); h.reply(); assertEquals(1,h.clicks);
        }
    }
    @Test public void rejectedClicksAreBoundedAndRetryUsesFreshMode() {
        try (Harness h = new Harness(true)) {
            h.accepted=false; h.start(); h.reply();
            for (int i=0;i<8;i++) { h.event(); if (!h.reads.isEmpty()) h.reply(); }
            assertEquals(3,h.clicks);
            assertEquals(3,h.modeReads);
        }
    }
    @Test public void elapsedBurstDoesNotClickAfterSlowModeRead() {
        try (Harness h = new Harness(true)) {
            h.start(); h.now=3000; h.reply(); assertEquals(0,h.clicks);
        }
    }
    @Test public void readTransportChecksChannelCommandAndValuesWithoutWriting() {
        assertEquals(2, SportAnimationController.readMode(request -> {
            assertEquals(VDEventCarInfo.MODULE_NEW_ENERGY, request.getId());
            assertEquals(NewEnergyID.ID_DRIVE_MODE, request.getPayload().getInt(Constants.CMD_ID));
            assertFalse(request.getPayload().containsKey(Constants.VALUE));
            return reply(VDEventCarInfo.MODULE_NEW_ENERGY,4,new int[]{2});
        }));
        assertEquals(0,SportAnimationController.readMode(q -> reply(VDEventCarInfo.MODULE_NEW_ENERGY,4,new int[]{0,2})));
        assertEquals(2,SportAnimationController.readMode(q -> reply(VDEventCarInfo.MODULE_NEW_ENERGY,4,new int[]{2,0})));
        VDEvent malformed=reply(VDEventCarInfo.MODULE_NEW_ENERGY,4,new int[]{2});
        malformed.getPayload().putString(Constants.CMD_ID,"4");
        assertEquals(-1,SportAnimationController.readMode(q -> malformed));
        assertEquals(-1,SportAnimationController.readMode(q -> null));
        assertEquals(-1,SportAnimationController.readMode(q -> reply(327684,4,new int[]{2})));
        assertEquals(-1,SportAnimationController.readMode(q -> reply(VDEventCarInfo.MODULE_NEW_ENERGY,5,new int[]{2})));
        for (int[] bad : new int[][]{null,{}, {-1},{6},{8}})
            assertEquals(-1,SportAnimationController.readMode(q -> reply(VDEventCarInfo.MODULE_NEW_ENERGY,4,bad)));
    }
    private static VDEvent reply(int channel,int command,int[] values) {
        Bundle data=new Bundle(); data.putInt(Constants.CMD_ID,command); data.putIntArray(Constants.VALUE,values);
        return new VDEvent(channel,data);
    }
}
