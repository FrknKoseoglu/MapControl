package com.mapcontrol.ui.builder;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.mapcontrol.R;
import com.mapcontrol.ui.theme.UiStyles;
import com.mapcontrol.vehicle.DriveModeStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class SharedHeaderTest {
    private Context context(String language) {
        Context base = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Configuration config = new Configuration(base.getResources().getConfiguration());
        config.setLocale(new Locale(language));
        return new ContextThemeWrapper(base.createConfigurationContext(config), R.style.Theme_MapControl);
    }
    private TopBarBuilder builder(Context context, AtomicInteger selected) {
        return new TopBarBuilder(context, new TopBarBuilder.TopBarCallback() {
            public void onLogTabToggle(boolean show) {}
            public void log(String message) {}
            public void onStatusSelected(int tab) { selected.set(tab); }
        });
    }
    private void layout(View view, Context context, int widthDp) {
        int width = Math.round(widthDp * context.getResources().getDisplayMetrics().density);
        view.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST));
        view.layout(0, 0, width, view.getMeasuredHeight());
    }
    private void assertTextFits(View view) {
        if (view instanceof TextView) {
            TextView text = (TextView) view;
            assertTrue(text.getText() + " does not fit its status cell",
                    text.getPaint().measureText(text.getText().toString()) <=
                            text.getWidth() - text.getCompoundPaddingLeft() - text.getCompoundPaddingRight() + 1);
        }
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                if (group.getChildAt(i).getVisibility() != View.GONE) assertTextFits(group.getChildAt(i));
            }
        }
    }

    @Test public void titlesAndLiveStatesKeepTheSameHeaderHeightWithoutClipping() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(() -> {
            for (String language : new String[]{"tr", "en"}) {
                Context context = context(language);
                TopBarBuilder builder = builder(context, new AtomicInteger());
                View header = builder.build();
                layout(header, context, 1400);
                int height = header.getHeight();
                for (int title : new int[]{R.string.side_rail_wifi, R.string.side_rail_apps,
                        R.string.side_rail_web, R.string.side_rail_drive_mode, R.string.side_rail_vehicle_info,
                        R.string.side_rail_projection, R.string.side_rail_settings, R.string.side_rail_welcome_sound,
                        R.string.side_rail_equalizer, R.string.topbar_vehicle_home}) {
                    builder.getTitleView().setText(title);
                    builder.setWifiState(TopBarBuilder.WifiState.DISCONNECTED);
                    builder.setDriveMode(true, DriveModeStyle.NORMAL);
                    builder.setServerRunning(true);
                    builder.setProjectionActive(true);
                    layout(header, context, 1400);
                    assertEquals(height, header.getHeight());
                    assertTextFits(header);
                }
                for (int width : new int[]{720, 400, 1400}) {
                    layout(header, context, width);
                    assertTextFits(header);
                    assertTrue(header.findViewWithTag("header_projection").getWidth() > 0);
                }
            }
        });
    }

    @Test public void disconnectClearsActualModeAndRebuildPreservesOtherStatusValues() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(() -> {
            Context context = context("tr");
            TopBarBuilder builder = builder(context, new AtomicInteger());
            View header = builder.build();
            builder.setDriveMode(true, DriveModeStyle.ECO);
            assertEquals("ECO", ((TextView) header.findViewWithTag("header_mode_value")).getText().toString());
            builder.setDriveMode(false, DriveModeStyle.ECO);
            assertEquals("—", ((TextView) header.findViewWithTag("header_mode_value")).getText().toString());
            builder.setDriveMode(true, 6);
            assertEquals("—", ((TextView) header.findViewWithTag("header_mode_value")).getText().toString());
            builder.setDriveMode(true, DriveModeStyle.SNOW);
            builder.setServerRunning(true);
            builder.setProjectionActive(true);
            builder.setWifiState(TopBarBuilder.WifiState.CONNECTED);
            header = builder.build();
            assertEquals("SNOW", ((TextView) header.findViewWithTag("header_mode_value")).getText().toString());
            assertEquals(context.getString(R.string.header_running),
                    ((TextView) header.findViewWithTag("header_phone_value")).getText().toString());
            assertEquals(context.getString(R.string.header_active),
                    ((TextView) header.findViewWithTag("header_projection_value")).getText().toString());
            Configuration night = new Configuration(context.getResources().getConfiguration());
            night.uiMode = (night.uiMode & ~Configuration.UI_MODE_NIGHT_MASK) | Configuration.UI_MODE_NIGHT_YES;
            try {
                UiStyles.setUiModeOverride(night);
                builder.reapplyTheme();
                TextView value = header.findViewWithTag("header_mode_value");
                assertEquals("SNOW", value.getText().toString());
                assertEquals(DriveModeStyle.accentColor(context, DriveModeStyle.SNOW), value.getCurrentTextColor());
            } finally { UiStyles.setUiModeOverride(null); }
        });
    }

    @Test public void statusIndicatorsNavigateWithoutChangingTheirState() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(() -> {
            Context context = context("tr");
            AtomicInteger selected = new AtomicInteger(-1);
            TopBarBuilder builder = builder(context, selected);
            View header = builder.build();
            String[] tags = {"header_wifi", "header_mode", "header_phone", "header_projection"};
            int[] tabs = {0, 6, 1, 3};
            for (int i = 0; i < tags.length; i++) {
                header.findViewWithTag(tags[i]).performClick();
                assertEquals(tabs[i], selected.get());
            }
            assertEquals(context.getString(R.string.header_stopped),
                    ((TextView) header.findViewWithTag("header_phone_value")).getText().toString());
        });
    }
    @Test public void titleAndOneStatusLeaveOtherIndicatorsUntouched() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(() -> {
            Context context = context("tr");
            TopBarBuilder builder = builder(context, new AtomicInteger());
            View header = builder.build();
            View wifi = header.findViewWithTag("header_wifi");
            View mode = header.findViewWithTag("header_mode");
            View phone = header.findViewWithTag("header_phone");
            View projection = header.findViewWithTag("header_projection");
            Object wifiBackground = wifi.getBackground(), modeBackground = mode.getBackground();
            Object phoneBackground = phone.getBackground(), projectionBackground = projection.getBackground();
            builder.setTitle(R.string.side_rail_vehicle_info);
            assertSame(wifiBackground, wifi.getBackground());
            assertSame(modeBackground, mode.getBackground());
            assertSame(phoneBackground, phone.getBackground());
            assertSame(projectionBackground, projection.getBackground());
            builder.setDriveMode(true, DriveModeStyle.SPORT);
            assertNotSame(modeBackground, mode.getBackground());
            assertSame(wifiBackground, wifi.getBackground());
            assertSame(phoneBackground, phone.getBackground());
            assertSame(projectionBackground, projection.getBackground());
            modeBackground = mode.getBackground();
            builder.setDriveMode(true, DriveModeStyle.SPORT);
            assertSame(modeBackground, mode.getBackground());
            builder.setWifiState(TopBarBuilder.WifiState.CONNECTED);
            assertSame(modeBackground, mode.getBackground());
            assertSame(phoneBackground, phone.getBackground());
            assertSame(projectionBackground, projection.getBackground());
        });
    }

}
