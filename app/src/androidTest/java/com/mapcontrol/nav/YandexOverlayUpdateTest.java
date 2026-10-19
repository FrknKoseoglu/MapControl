package com.mapcontrol.nav;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.view.View;
import android.view.WindowManager;
import com.mapcontrol.util.RecordingWindowManager;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class YandexOverlayUpdateTest {
    @Test public void liveContentUpdatesKeepOneWindowAndWindowEventsCanStillRaiseIt() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(() -> {
            try {
                Context context = new ContextWrapper(InstrumentationRegistry.getInstrumentation().getTargetContext()) {
                    @Override public Context getApplicationContext() { return this; }
                    @Override public SharedPreferences getSharedPreferences(String name, int mode) {
                        return super.getSharedPreferences("yandex_window_test", mode);
                    }
                };
                Constructor<YandexClusterNavOverlay> constructor = YandexClusterNavOverlay.class.getDeclaredConstructor(Context.class);
                constructor.setAccessible(true);
                YandexClusterNavOverlay overlay = constructor.newInstance(context);
                RecordingWindowManager windows = new RecordingWindowManager();
                Method build = YandexClusterNavOverlay.class.getDeclaredMethod("buildOverlay", Context.class);
                build.setAccessible(true);
                View root = (View) build.invoke(overlay, context);
                set(overlay, "overlayRoot", root);
                set(overlay, "windowManager", windows.manager);
                set(overlay, "overlayLayoutParams", new WindowManager.LayoutParams());
                Field version = YandexClusterNavOverlay.class.getDeclaredField("LAYOUT_VERSION");
                version.setAccessible(true);
                set(overlay, "appliedLayoutVersion", version.get(null));
                Method apply = YandexClusterNavOverlay.class.getDeclaredMethod("applyOnMain", YandexNavSnapshot.class);
                apply.setAccessible(true);
                for (int i = 100; i > 50; i--) apply.invoke(overlay,
                        new YandexNavSnapshot(true, String.valueOf(i), "m", "Road", "5 km", "12:30", "10 min", null, null, null));
                assertEquals(0, windows.adds.get());
                assertEquals(0, windows.removes.get());
                Method raise = YandexClusterNavOverlay.class.getDeclaredMethod("raiseToTopOnMain", boolean.class);
                raise.setAccessible(true);
                raise.invoke(overlay, true);
                raise.invoke(overlay, true);
                assertEquals(1, windows.adds.get());
                assertEquals(1, windows.removes.get());
                apply.invoke(overlay, YandexNavSnapshot.inactive());
                assertEquals(2, windows.removes.get());
            } catch (Exception failure) { throw new AssertionError(failure); }
        });
    }

    private void set(Object target, String name, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(name);
        field.setAccessible(true);
        field.set(target, value);
    }
}
