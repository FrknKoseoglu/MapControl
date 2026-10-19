package com.mapcontrol.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.XmlResourceParser;
import com.mapcontrol.R;
import com.mapcontrol.ui.activity.MainActivity;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.xmlpull.v1.XmlPullParser;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class AccessibilityEntryPointTest {
    @Test public void configuredSettingsActivityResolvesToTheRealActivity() throws Exception {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        try (XmlResourceParser parser = context.getResources().getXml(R.xml.accessibility_service_config)) {
            while (parser.next() != XmlPullParser.START_TAG) { }
            String name = parser.getAttributeValue("http://schemas.android.com/apk/res/android", "settingsActivity");
            assertEquals(MainActivity.class.getName(), name);
            assertNotNull(context.getPackageManager().getActivityInfo(
                    new ComponentName(context.getPackageName(), name), PackageManager.GET_META_DATA));
        }
    }
}
