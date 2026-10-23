package com.mapcontrol.manager;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.desaysv.ivi.extra.project.carinfo.proxy.Constants;
import com.desaysv.ivi.vdb.event.VDEvent;
import com.mapcontrol.util.TargetPackageStore;
import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class SportAnimationDiagnosticsTest {
    @Test public void realCardLongPressOpensReportAndCopyReturnsTheDiagnosticText() throws Exception {
        android.app.Instrumentation instrumentation=InstrumentationRegistry.getInstrumentation();
        Context context=instrumentation.getTargetContext();
        SharedPreferences prefs=TargetPackageStore.prefs(context);
        java.util.Map<String,?> before=prefs.getAll();
        com.mapcontrol.ui.activity.MainActivity activity=null;
        try {
            prefs.edit().putBoolean("disclaimerAccepted",true).putBoolean("appManagementDisclaimerAccepted",true)
                    .putBoolean("onboardingCompleted",true).putBoolean("launcherModeEnabled",false)
                    .putBoolean("floatingBackButtonEnabled",false).putBoolean("bootAutoStart",false)
                    .putBoolean(SportAnimationController.PREF_ENABLED,false).commit();
            SportAnimationDiagnostics.note(context,"PENCERE","UI_REPORT_COPY_TEST");
            activity=(com.mapcontrol.ui.activity.MainActivity)instrumentation.startActivitySync(
                    new android.content.Intent(context,com.mapcontrol.ui.activity.MainActivity.class)
                            .addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK));
            com.mapcontrol.ui.activity.MainActivity host=activity;
            instrumentation.runOnMainSync(()->{
                try{
                    java.lang.reflect.Method method=com.mapcontrol.ui.activity.MainActivity.class.getDeclaredMethod("switchTab",int.class);
                    method.setAccessible(true);method.invoke(host,6);
                    android.view.View card=host.getWindow().getDecorView().findViewWithTag("sport_animation_setting");
                    assertNotNull(card);assertTrue(card.performLongClick());
                }catch(Exception failure){throw new AssertionError(failure);}
            });
            instrumentation.waitForIdleSync();
            instrumentation.runOnMainSync(()->{
                android.widget.Button copy=null;
                for(android.view.View root:android.view.inspector.WindowInspector.getGlobalWindowViews()){
                    android.widget.Button found=findCopy(root,host.getString(com.mapcontrol.R.string.super_mode_diagnostics_copy));
                    if(found!=null)copy=found;
                }
                assertNotNull("diagnostic dialog Copy button",copy);copy.performClick();
            });
            instrumentation.waitForIdleSync();
            instrumentation.runOnMainSync(()->{
                android.content.ClipboardManager clipboard=(android.content.ClipboardManager)host.getSystemService(Context.CLIPBOARD_SERVICE);
                assertNotNull(clipboard.getPrimaryClip());
                String text=clipboard.getPrimaryClip().getItemAt(0).coerceToText(host).toString();
                assertTrue("Copied report: "+text,text.contains("UI_REPORT_COPY_TEST"));
                assertTrue(text.contains(context.getPackageName()));
                assertFalse(prefs.getBoolean(SportAnimationController.PREF_ENABLED,true));
            });
        }finally{
            if(activity!=null){com.mapcontrol.ui.activity.MainActivity host=activity;instrumentation.runOnMainSync(host::finish);}
            SharedPreferences.Editor restore=prefs.edit().clear();
            for(java.util.Map.Entry<String,?> entry:before.entrySet()){
                Object v=entry.getValue();String k=entry.getKey();
                if(v instanceof Boolean)restore.putBoolean(k,(Boolean)v);
                else if(v instanceof Integer)restore.putInt(k,(Integer)v);
                else if(v instanceof Long)restore.putLong(k,(Long)v);
                else if(v instanceof Float)restore.putFloat(k,(Float)v);
                else if(v instanceof String)restore.putString(k,(String)v);
                else if(v instanceof java.util.Set)restore.putStringSet(k,new java.util.HashSet<>((java.util.Set<String>)v));
            }restore.commit();
        }
    }
    private static android.widget.Button findCopy(android.view.View view,String text){
        if(view instanceof android.widget.Button && text.contentEquals(((android.widget.Button)view).getText()))return(android.widget.Button)view;
        if(view instanceof android.view.ViewGroup){android.view.ViewGroup group=(android.view.ViewGroup)view;
            for(int i=0;i<group.getChildCount();i++){android.widget.Button found=findCopy(group.getChildAt(i),text);if(found!=null)return found;}}
        return null;
    }

    private Context context(){return new ContextWrapper(InstrumentationRegistry.getInstrumentation().getTargetContext()){
        @Override public Context getApplicationContext(){return this;}
        @Override public SharedPreferences getSharedPreferences(String name,int flags){return super.getSharedPreferences("sport_diagnostics_test_"+name,flags);}
    };}
    @Test public void anotherVariantCannotSatisfyTheOwnAccessibilityPermission() {
        assertFalse(SportAnimationController.matchesOwnAccessibilityService("com.mapcontrol.testfk",
                "com.mapcontrol/com.mapcontrol.service.GlobalBackService"));
        assertFalse(SportAnimationController.matchesOwnAccessibilityService("com.mapcontrol.testfk",
                "com.mapcontrol.vnoisy/com.mapcontrol.service.GlobalBackService"));
        assertTrue(SportAnimationController.matchesOwnAccessibilityService("com.mapcontrol.testfk",
                "other.app/.Service:com.mapcontrol.testfk/com.mapcontrol.service.GlobalBackService"));
        assertTrue(SportAnimationController.matchesOwnAccessibilityService("com.mapcontrol",
                "com.mapcontrol/.service.GlobalBackService"));
        assertFalse(SportAnimationController.matchesOwnAccessibilityService("com.mapcontrol.testfk",
                "com.mapcontrol.testfk/.service.GlobalBackService"));
        assertFalse(SportAnimationController.matchesOwnAccessibilityService("com.mapcontrol.testfk",null));
    }

    @Test public void boundedReportSurvivesNewContextAndPreservesUnrelatedPreferences() throws Exception {
        Context c=context();SharedPreferences prefs=TargetPackageStore.prefs(c);
        SharedPreferences diagnostics=c.getSharedPreferences(SportAnimationDiagnostics.PREFS_NAME,0);
        diagnostics.edit().clear().commit();
        prefs.edit().clear().putInt("driveModeSetting",1).putString("privateUnrelated","MUST_NOT_EXPORT").commit();
        try{
            for(int i=0;i<50;i++)SportAnimationDiagnostics.note(c,"PENCERE","sample="+i);
            JSONArray entries=new JSONArray(diagnostics.getString("sportAnimationDiagnostics","[]"));
            assertEquals(32,entries.length());assertTrue(entries.optString(0).contains("sample=18"));
            String report=SportAnimationDiagnostics.report(context());
            assertTrue(report.contains("sample=49"));assertFalse(report.contains("MUST_NOT_EXPORT"));
            assertEquals(1,prefs.getInt("driveModeSetting",-1));
        }finally{prefs.edit().clear().commit();diagnostics.edit().clear().commit();}
    }
    @Test public void repeatedStageSnapshotsAreDeduplicatedAndOversizedEntriesClipped() throws Exception {
        Context c=context();SharedPreferences prefs=c.getSharedPreferences(SportAnimationDiagnostics.PREFS_NAME,0);prefs.edit().clear().commit();
        try{
            SportAnimationDiagnostics.note(c,"PENCERE","windows=1");
            SportAnimationDiagnostics.note(c,"MOD","reply=null");
            SportAnimationDiagnostics.note(c,"PENCERE","windows=1");
            assertEquals(2,new JSONArray(prefs.getString("sportAnimationDiagnostics","[]")).length());
            StringBuilder large=new StringBuilder();for(int i=0;i<1000;i++)large.append('x');
            SportAnimationDiagnostics.note(c,"MOD",large.toString());
            JSONArray entries=new JSONArray(prefs.getString("sportAnimationDiagnostics","[]"));
            assertTrue(entries.optString(2).length()<680);
            SportAnimationDiagnostics.begin(c,"new service session");
            SportAnimationDiagnostics.note(c,"PENCERE","windows=1");
            assertEquals(5,new JSONArray(prefs.getString("sportAnimationDiagnostics","[]")).length());
        }finally{prefs.edit().clear().commit();}
    }
    @Test public void readDiagnosticsExplainMissingAndRejectedRepliesWithoutChangingAcceptance() {
        List<String> notes=new ArrayList<>();
        assertEquals(-1,SportAnimationController.readMode(q->null,notes::add));assertTrue(notes.get(0).contains("yanıt=null"));
        Bundle data=new Bundle();data.putInt(Constants.CMD_ID,5);data.putIntArray(Constants.VALUE,new int[]{2});
        assertEquals(-1,SportAnimationController.readMode(q->new VDEvent(327682,data),notes::add));
        assertTrue(notes.get(1).contains("cmd=5"));
        try{SportAnimationController.readMode(q->{throw new SecurityException();},notes::add);fail();}
        catch(SecurityException expected){assertTrue(notes.get(2).contains("SecurityException"));}
    }
}
