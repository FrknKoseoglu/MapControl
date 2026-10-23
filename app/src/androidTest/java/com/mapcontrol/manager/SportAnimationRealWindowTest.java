package com.mapcontrol.manager;

import android.app.UiAutomation;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.accessibilityservice.AccessibilityServiceInfo;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.mapcontrol.util.TargetPackageStore;
import java.util.ArrayDeque;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

/** Requires the isolated emulator fixture built by scripts/tests/build_sport_window_fixture.py. */
@RunWith(AndroidJUnit4.class)
public class SportAnimationRealWindowTest {
    private static void main(Runnable r){InstrumentationRegistry.getInstrumentation().runOnMainSync(r);}
    private static void idle(){InstrumentationRegistry.getInstrumentation().waitForIdleSync();}
    private Context context;
    private int value(String column){
        try(Cursor c=context.getContentResolver().query(Uri.parse("content://com.mapcontrol.sportfixture/state"),null,null,null,null)){
            assertNotNull(c);assertTrue(c.moveToFirst());return c.getInt(c.getColumnIndexOrThrow(column));
        }
    }
    private void scenario(int mode) throws Exception {
        assumeTrue(Build.FINGERPRINT.contains("generic") || Build.FINGERPRINT.contains("emulator"));
        context=InstrumentationRegistry.getInstrumentation().getTargetContext();
        assumeNotNull(context.getPackageManager().resolveContentProvider("com.mapcontrol.sportfixture",0));
        UiAutomation automation=InstrumentationRegistry.getInstrumentation().getUiAutomation();
        AccessibilityServiceInfo original=automation.getServiceInfo();
        AccessibilityServiceInfo info=automation.getServiceInfo();
        info.flags|=AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS|AccessibilityServiceInfo.FLAG_RETRIEVE_INTERACTIVE_WINDOWS
                |AccessibilityServiceInfo.FLAG_INCLUDE_NOT_IMPORTANT_VIEWS;
        automation.setServiceInfo(info);
        Context isolated=new ContextWrapper(context){
            @Override public Context getApplicationContext(){return this;}
            @Override public SharedPreferences getSharedPreferences(String name,int flags){return super.getSharedPreferences("sport_real_window_"+name,flags);}
            @Override public void sendBroadcast(Intent intent){throw new AssertionError("No broadcasts");}
        };
        SharedPreferences prefs=TargetPackageStore.prefs(isolated);
        ArrayDeque<Runnable> reads=new ArrayDeque<>();
        SportAnimationController controller=new SportAnimationController(isolated,
                () -> SportAnimationWindow.findInWindows(automation.getWindows()),() -> value("mode"),reads::add,
                SystemClock::uptimeMillis,message -> {},false);
        try {
            int token=(int)SystemClock.uptimeMillis();
            context.startActivity(new Intent().setClassName("com.desaysv.vehiclesetting","com.desaysv.vehiclesetting.FixtureActivity")
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK).putExtra("mode",mode).putExtra("token",token));
            automation.waitForIdle(200,3000);
            long end=SystemClock.uptimeMillis()+5000;
            boolean found=false;
            while(SystemClock.uptimeMillis()<end){
                if(value("token")!=token){SystemClock.sleep(25);continue;}
                SportAnimationController.Dialog d=SportAnimationWindow.findInWindows(automation.getWindows());
                if(d!=null){d.recycle();found=true;break;}
                SystemClock.sleep(50);
            }
            assertTrue("Real Android node tree must match the OEM resource IDs",found);
            main(() -> {prefs.edit().clear().putBoolean(SportAnimationController.PREF_ENABLED,true).commit();controller.start();});idle();
            end=SystemClock.uptimeMillis()+3000;
            while(reads.isEmpty()&&SystemClock.uptimeMillis()<end){SystemClock.sleep(25);idle();}
            assertFalse(reads.isEmpty());reads.remove().run();idle();
            end=SystemClock.uptimeMillis()+2000;
            while(mode==2&&value("closes")==0&&SystemClock.uptimeMillis()<end)SystemClock.sleep(25);
            assertEquals(mode==2?1:0,value("closes"));
            assertEquals("Window close must preserve fixture drive mode",mode,value("mode"));
        } finally {
            main(() -> {controller.release();prefs.edit().clear().commit();});
            context.startActivity(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            automation.waitForIdle(200,3000);
            automation.setServiceInfo(original);
        }
    }
    @Test public void realSportWindowBackClickClosesOnlyDialogAndPreservesMode() throws Exception {scenario(2);}
    @Test public void realNormalWindowIsLeftOpen() throws Exception {scenario(1);}
}
