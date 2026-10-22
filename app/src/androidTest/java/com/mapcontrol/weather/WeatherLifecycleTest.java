package com.mapcontrol.weather;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class WeatherLifecycleTest {
    private Context noLocationContext() {
        Context base = InstrumentationRegistry.getInstrumentation().getTargetContext();
        return new ContextWrapper(base) {
            @Override public Context getApplicationContext() { return this; }
            @Override public int checkSelfPermission(String permission) { return PackageManager.PERMISSION_DENIED; }
            @Override public SharedPreferences getSharedPreferences(String name, int mode) {
                return super.getSharedPreferences("weather_lifecycle_test", mode);
            }
        };
    }

    @Test public void legacyFixedCityCacheIsNotDisplayedWithoutVehicleFix() throws Exception {
        Context context = noLocationContext();
        context.getSharedPreferences("test", 0).edit().clear()
                .putString("open_meteo_weather_cache", "{\"current_weather\":{\"temperature\":30}}")
                .putLong("open_meteo_weather_cached_at", System.currentTimeMillis()).commit();
        OpenMeteoWeatherClient client = new OpenMeteoWeatherClient(context);
        CountDownLatch failed = new CountDownLatch(1);
        AtomicInteger successes = new AtomicInteger();
        try {
            assertNull(client.getLastSnapshot());
            InstrumentationRegistry.getInstrumentation().runOnMainSync(() -> {
                client.setListener(new OpenMeteoWeatherClient.Listener() {
                    @Override public void onWeatherUpdated(OpenMeteoWeatherSnapshot snapshot) { successes.incrementAndGet(); }
                    @Override public void onWeatherFailed() { failed.countDown(); }
                });
                client.start();
            });
            assertTrue(failed.await(5, TimeUnit.SECONDS));
            assertEquals(0, successes.get());
            assertNull(client.getLastSnapshot());
        } finally {
            InstrumentationRegistry.getInstrumentation().runOnMainSync(client::destroy);
            context.getSharedPreferences("test", 0).edit().clear().commit();
        }
    }

    @Test public void destroyPreventsQueuedCallbacksAndRestart() {
        OpenMeteoWeatherClient client = new OpenMeteoWeatherClient(noLocationContext());
        AtomicInteger callbacks = new AtomicInteger();
        InstrumentationRegistry.getInstrumentation().runOnMainSync(() -> {
            client.setListener(new OpenMeteoWeatherClient.Listener() {
                @Override public void onWeatherUpdated(OpenMeteoWeatherSnapshot snapshot) { callbacks.incrementAndGet(); }
                @Override public void onWeatherFailed() { callbacks.incrementAndGet(); }
            });
            client.start();
            client.destroy();
            client.start();
        });
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
        assertEquals(0, callbacks.get());
    }
}
