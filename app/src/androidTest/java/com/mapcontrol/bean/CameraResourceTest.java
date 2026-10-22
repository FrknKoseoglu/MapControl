package com.mapcontrol.bean;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class CameraResourceTest {
    @Test public void releasingCameraBeansStopsTheirHandlerThreads() throws Exception {
        int before = cameraThreads();
        for (int i = 0; i < 12; i++) {
            CameraBean bean = new CameraBean("DMS", null, null);
            bean.release();
            bean.release();
        }
        long deadline = android.os.SystemClock.elapsedRealtime() + 3000;
        while (cameraThreads() > before && android.os.SystemClock.elapsedRealtime() < deadline) Thread.sleep(20);
        assertTrue("Released camera callbacks must not retain live threads", cameraThreads() <= before);
    }

    private int cameraThreads() {
        int count = 0;
        for (Thread thread : Thread.getAllStackTraces().keySet()) {
            if (thread.isAlive() && "CameraBean".equals(thread.getName())) count++;
        }
        return count;
    }
}
