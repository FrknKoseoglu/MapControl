package com.mapcontrol.audio;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.lang.reflect.Field;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class EqualizerDisabledTest {
    private Context context;
    @Before public void setup() {
        context = new ContextWrapper(InstrumentationRegistry.getInstrumentation().getTargetContext()) {
            @Override public SharedPreferences getSharedPreferences(String name, int mode) {
                return super.getSharedPreferences("equalizer_disabled_test", mode);
            }
        };
        context.getSharedPreferences("test", 0).edit().clear().commit();
        AudioEqualizerController.release();
    }
    @After public void cleanup() {
        AudioEqualizerController.release();
        context.getSharedPreferences("test", 0).edit().clear().commit();
    }

    @Test public void readingDisabledStateKeepsBandControlsWithoutOpeningEffect() throws Exception {
        AudioEqualizerController.State state = AudioEqualizerController.read(context);
        assertFalse(state.enabled);
        assertFalse(state.active);
        assertEquals(5, state.centerHz.length);
        assertNull(effect());
    }

    @Test public void savingDisabledSettingsDoesNotOpenTheGlobalMix() throws Exception {
        AudioEqualizerController.update(context, false, 300, new int[]{100, 200, -100, 0, 0}, "custom");
        AudioEqualizerController.State state = AudioEqualizerController.read(context);
        assertFalse(state.enabled);
        assertEquals(300, state.gainMb);
        assertArrayEquals(new int[]{100, 200, -100, 0, 0}, state.bands);
        assertNull(effect());
    }

    @Test public void disabledKeeperNeverInstantiatesAnEffect() throws Exception {
        AudioEqualizerController.applySaved(context);
        AudioEqualizerController.ensureApplied(context);
        assertNull(effect());
    }

    private Object effect() throws Exception {
        Field field = AudioEqualizerController.class.getDeclaredField("dynamics");
        field.setAccessible(true);
        return field.get(null);
    }
}
