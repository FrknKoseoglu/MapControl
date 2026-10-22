package com.mapcontrol.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class PassiveDiagnosticsTest {
    private Context permission(int result) {
        return new ContextWrapper(InstrumentationRegistry.getInstrumentation().getTargetContext()) {
            @Override public int checkPermission(String permission, int pid, int uid) { return result; }
            @Override public Object getSystemService(String name) { throw new AssertionError("No hardware service should be requested"); }
        };
    }

    @Test public void grantedMicrophoneIsReportedAsNotHardwareTested() {
        PermissionProbe.Result result = PermissionProbe.probeMic(permission(PackageManager.PERMISSION_GRANTED));
        assertEquals(PermissionProbe.Result.Status.SKIP, result.status);
        assertEquals("RECORD_AUDIO", result.name);
        assertFalse(result.detail.isEmpty());
    }

    @Test public void missingMicrophonePermissionIsReportedWithoutOpeningIt() {
        assertEquals(PermissionProbe.Result.Status.FAIL,
                PermissionProbe.probeMic(permission(PackageManager.PERMISSION_DENIED)).status);
    }
}
