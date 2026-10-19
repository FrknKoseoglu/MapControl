package com.mapcontrol.util;

import android.content.Context;
import android.view.View;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class OverlayDismissalTest {
    private Context context() { return InstrumentationRegistry.getInstrumentation().getTargetContext(); }
    private void onMain(Runnable action) { InstrumentationRegistry.getInstrumentation().runOnMainSync(action); }

    @Test public void repeatedPreparingWindowsAreAllRemoved() {
        RecordingWindowManager windows = new RecordingWindowManager();
        onMain(() -> {
            for (int i = 0; i < 30; i++) {
                put("preparingMessageView", new View(context()));
                put("preparingWindowManager", windows.manager);
                DisplayHelper.hidePreparingMessage();
                DisplayHelper.hidePreparingMessage();
                assertNull(get("preparingMessageView"));
                assertNull(get("preparingWindowManager"));
            }
        });
        assertEquals(30, windows.removes.get());
    }

    @Test public void oldFadeCannotRemoveOrClearAReplacementWindow() throws Exception {
        RecordingWindowManager old = new RecordingWindowManager();
        RecordingWindowManager replacement = new RecordingWindowManager();
        View[] views = new View[2];
        try {
            onMain(() -> {
                views[0] = new View(context()) { @Override public boolean isAttachedToWindow() { return true; } };
                views[1] = new View(context());
                put("preparingMessageView", views[0]);
                put("preparingWindowManager", old.manager);
                DisplayHelper.hidePreparingMessage();
                put("preparingMessageView", views[1]);
                put("preparingWindowManager", replacement.manager);
            });
            assertTrue("Fade fallback must release its owned window", old.firstRemoval.await(3, TimeUnit.SECONDS));
            onMain(() -> {
                assertSame(views[1], get("preparingMessageView"));
                assertSame(replacement.manager, get("preparingWindowManager"));
            });
            assertEquals(0, replacement.removes.get());
            assertSame(views[0], old.removed.get(0));
        } finally {
            onMain(DisplayHelper::hidePreparingMessage);
        }
        assertEquals(1, old.removes.get());
        assertEquals(1, replacement.removes.get());
    }

    @Test public void cancelledAnimationStillRemovesItsWindowExactlyOnce() throws Exception {
        RecordingWindowManager windows = new RecordingWindowManager();
        onMain(() -> {
            View view = new View(context()) { @Override public boolean isAttachedToWindow() { return true; } };
            OverlayDismissal.dismiss(windows.manager, view, 40);
            view.animate().cancel();
        });
        assertTrue(windows.firstRemoval.await(3, TimeUnit.SECONDS));
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
        assertEquals(1, windows.removes.get());
    }

    @Test public void bootSplashUsesTheSameOwnedRemovalPath() {
        RecordingWindowManager windows = new RecordingWindowManager();
        onMain(() -> {
            put("bootSplashView", new View(context()));
            put("bootSplashWindowManager", windows.manager);
            DisplayHelper.hideBootSplash();
            DisplayHelper.hideBootSplash();
            assertNull(get("bootSplashView"));
            assertFalse(DisplayHelper.isBootSplashShowing());
        });
        assertEquals(1, windows.removes.get());
    }

    @Test public void backgroundDismissalIsMarshalledToMain() throws Exception {
        RecordingWindowManager windows = new RecordingWindowManager();
        View[] view = new View[1];
        onMain(() -> view[0] = new View(context()));
        OverlayDismissal.dismiss(windows.manager, view[0], 0);
        assertTrue(windows.firstRemoval.await(3, TimeUnit.SECONDS));
        assertEquals(1, windows.removes.get());
    }

    private static Object get(String name) {
        try { Field field = DisplayHelper.class.getDeclaredField(name); field.setAccessible(true); return field.get(null); }
        catch (Exception error) { throw new AssertionError(error); }
    }
    private static void put(String name, Object value) {
        try { Field field = DisplayHelper.class.getDeclaredField(name); field.setAccessible(true); field.set(null, value); }
        catch (Exception error) { throw new AssertionError(error); }
    }
}
