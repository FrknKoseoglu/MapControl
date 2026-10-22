package com.mapcontrol.ui.builder;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.graphics.ColorUtils;
import com.mapcontrol.R;
import com.mapcontrol.ui.HeaderStatusSink;
import com.mapcontrol.ui.theme.UiStyles;
import com.mapcontrol.vehicle.DriveModeStyle;

/** Shared page title and live status strip. Page actions belong inside their own page. */
public class TopBarBuilder implements HeaderStatusSink {
    public enum WifiState { CONNECTED, DISCONNECTED, OFF, UNKNOWN }

    public interface TopBarCallback {
        void onLogTabToggle(boolean show);
        void log(String message);
        default void onStatusSelected(int tabIndex) {}
    }

    private final Context context;
    private final TopBarCallback callback;
    private final Handler titleClickHandler = new Handler(Looper.getMainLooper());
    private final Runnable titleClickReset = () -> titleClickCount = 0;
    private int titleClickCount;
    private boolean isLogTabVisible;
    private LinearLayout topBar;
    private TextView titleView;
    private AppCompatImageButton launcherBackButton;
    private View topBarDivider;
    private StatusChip wifiChip, modeChip, phoneChip, projectionChip, soundChip;
    private WifiState wifiState = WifiState.UNKNOWN;
    private boolean vehicleConnected;
    private int driveMode = Integer.MIN_VALUE;
    private boolean serverRunning, projectionActive;
    private boolean soundBoostActive;
    private int soundGainMb;

    public TopBarBuilder(Context context, TopBarCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    public LinearLayout build() {
        topBar = new LinearLayout(context);
        topBar.setTag("shared_header");
        topBar.setOrientation(LinearLayout.VERTICAL);
        HeaderRow row = new HeaderRow();
        row.setPadding(dp(18), dp(8), dp(14), dp(8));
        row.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout titleArea = new LinearLayout(context);
        titleArea.setGravity(Gravity.CENTER_VERTICAL);
        row.addView(titleArea);

        titleArea.setOrientation(LinearLayout.HORIZONTAL);

        launcherBackButton = new AppCompatImageButton(context);
        launcherBackButton.setVisibility(View.GONE);
        launcherBackButton.setContentDescription(context.getString(R.string.header_back_home));
        launcherBackButton.setPadding(dp(8), dp(8), dp(8), dp(8));
        launcherBackButton.setImageResource(R.drawable.ic_vehicle_back);
        launcherBackButton.setFocusable(true);
        launcherBackButton.setClickable(true);
        LinearLayout.LayoutParams backParams = new LinearLayout.LayoutParams(dp(36), dp(36));
        backParams.setMarginEnd(dp(8));
        titleArea.addView(launcherBackButton, backParams);

        titleView = new TextView(context);
        titleView.setTag("header_title");
        titleView.setText(R.string.side_rail_wifi);
        UiStyles.setTextSize(titleView, R.dimen.text_size_medium);
        titleView.setTypeface(null, Typeface.BOLD);
        titleView.setGravity(Gravity.CENTER_VERTICAL);
        titleView.setSingleLine(true);
        titleView.setMinHeight(dp(38));
        titleView.setClickable(true);
        titleView.setFocusable(true);
        titleArea.addView(titleView, new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.MATCH_PARENT, 1));

        StatusGrid statuses = new StatusGrid();
        row.addView(statuses);
        wifiChip = new StatusChip("header_wifi", R.string.header_wifi, R.drawable.ic_mdi_wifi, 0);
        modeChip = new StatusChip("header_mode", R.string.header_mode, R.drawable.ic_mdi_car, 6);
        phoneChip = new StatusChip("header_phone", R.string.header_phone, R.drawable.ic_mdi_cellphone, 1);
        projectionChip = new StatusChip("header_projection", R.string.header_projection,
                R.drawable.ic_hub_monitor, 3);
        soundChip = new StatusChip("header_sound", R.string.side_rail_equalizer,
                R.drawable.ic_mdi_volume_plus, 11);
        soundChip.setVisibility(View.GONE);
        statuses.addView(wifiChip);
        statuses.addView(modeChip);
        statuses.addView(phoneChip);
        statuses.addView(projectionChip);
        statuses.addView(soundChip);
        topBar.addView(row, new LinearLayout.LayoutParams(-1, -2));
        topBarDivider = new View(context);
        topBar.addView(topBarDivider, new LinearLayout.LayoutParams(-1, Math.max(1, dp(1))));

        titleView.setOnClickListener(v -> {
            titleClickCount++;
            titleClickHandler.removeCallbacks(titleClickReset);
            if (titleClickCount >= 3) {
                isLogTabVisible = !isLogTabVisible;
                callback.onLogTabToggle(isLogTabVisible);
                callback.log("Log sekmesi " + (isLogTabVisible ? "açıldı" : "kapatıldı"));
                titleClickCount = 0;
            } else titleClickHandler.postDelayed(titleClickReset, 1000);
        });
        reapplyTheme();
        return topBar;
    }

    public void setWifiState(WifiState state) {
        if (state == wifiState) return;
        wifiState = state;
        refreshWifi();
    }

    public void setDriveMode(boolean connected, int mode) {
        if (!DriveModeStyle.isThemed(mode)) {
            int prefMode = context.getSharedPreferences("MapControlPrefs", Context.MODE_PRIVATE)
                    .getInt("driveModeSetting", -1);
            if (DriveModeStyle.isThemed(prefMode)) {
                mode = prefMode;
            }
        }
        if (connected == vehicleConnected && mode == driveMode) return;
        vehicleConnected = connected;
        driveMode = mode;
        refreshMode();
    }

    public void setServerRunning(boolean running) {
        if (serverRunning == running) return;
        serverRunning = running;
        refreshPhone();
    }

    public void setProjectionActive(boolean active) {
        if (projectionActive == active) return;
        projectionActive = active;
        refreshProjection();
    }

    @Override
    public void setSoundBoost(boolean active, int gainMb) {
        if (soundBoostActive == active && soundGainMb == gainMb) return;
        soundBoostActive = active;
        soundGainMb = gainMb;
        refreshSound();
    }

    public void setTitle(int titleRes) {
        if (titleView == null) return;
        String title = context.getString(titleRes);
        if (!android.text.TextUtils.equals(titleView.getText(), title)) titleView.setText(title);
    }

    private void refreshStatuses() {
        refreshWifi();
        refreshMode();
        refreshPhone();
        refreshProjection();
        refreshSound();
    }

    private void refreshWifi() {
        if (wifiChip == null) return;
        int label = R.string.header_unknown;
        if (wifiState == WifiState.CONNECTED) label = R.string.header_connected;
        else if (wifiState == WifiState.DISCONNECTED) label = R.string.header_disconnected;
        else if (wifiState == WifiState.OFF) label = R.string.header_off;
        wifiChip.bind(context.getString(label), UiStyles.color(context,
                wifiState == WifiState.CONNECTED ? R.color.statusSuccess
                : wifiState == WifiState.DISCONNECTED ? R.color.statusWarning : R.color.textSecondary));
    }

    private void refreshMode() {
        if (modeChip == null) return;
        int mode = driveMode;
        if (!DriveModeStyle.isThemed(mode)) {
            int prefMode = context.getSharedPreferences("MapControlPrefs", Context.MODE_PRIVATE)
                    .getInt("driveModeSetting", -1);
            if (DriveModeStyle.isThemed(prefMode)) {
                mode = prefMode;
            }
        }
        boolean knownMode = DriveModeStyle.isThemed(mode);
        modeChip.bind(knownMode ? DriveModeStyle.shortLabel(mode) : "—",
                knownMode ? DriveModeStyle.accentColor(context, mode)
                        : UiStyles.color(context, R.color.textSecondary));
    }

    private void refreshPhone() {
        if (phoneChip == null) return;
        phoneChip.bind(context.getString(serverRunning ? R.string.header_running : R.string.header_stopped),
                UiStyles.color(context, serverRunning ? R.color.statusSuccess : R.color.textSecondary));
    }

    private void refreshProjection() {
        if (projectionChip == null) return;
        projectionChip.bind(context.getString(projectionActive ? R.string.header_active : R.string.header_off),
                UiStyles.color(context, projectionActive ? R.color.statusSuccess : R.color.textSecondary));
    }

    private void refreshSound() {
        if (soundChip == null) return;
        if (soundBoostActive && soundGainMb > 0) {
            soundChip.setVisibility(View.VISIBLE);
            String val = String.format(java.util.Locale.US, "+%.1f dB", soundGainMb / 100.0f);
            soundChip.bind(val, UiStyles.color(context, R.color.oemAccent));
        } else {
            soundChip.setVisibility(View.GONE);
        }
    }

    public void setLauncherBackButtonVisible(boolean visible) {
        if (launcherBackButton != null && (launcherBackButton.getVisibility() == View.VISIBLE) != visible)
            launcherBackButton.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
    public void setTopBarVisible(boolean visible) {
        if (topBar != null && (topBar.getVisibility() == View.VISIBLE) != visible)
            topBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
    public void setLauncherBackButtonListener(View.OnClickListener listener) {
        if (launcherBackButton != null) launcherBackButton.setOnClickListener(listener);
    }
    public TextView getTitleView() { return titleView; }
    public void release() { titleClickHandler.removeCallbacksAndMessages(null); }

    public void reapplyTheme() {
        if (topBar != null) {
            float r = 16f * context.getResources().getDisplayMetrics().density;
            GradientDrawable bg = new GradientDrawable();
            bg.setColor(UiStyles.color(context, R.color.surfaceBar));
            bg.setCornerRadii(new float[]{r, r, 0, 0, 0, 0, r, r});
            topBar.setBackground(bg);
        }
        if (titleView != null) titleView.setTextColor(UiStyles.color(context, R.color.textPrimary));
        if (launcherBackButton != null) {
            UiStyles.setGlassCardBackground(launcherBackButton);
            launcherBackButton.setImageTintList(ColorStateList.valueOf(UiStyles.color(context, R.color.textPrimary)));
        }
        if (topBarDivider != null) UiStyles.setBackgroundRes(topBarDivider, R.drawable.bg_topbar_divider);
        refreshStatuses();
    }

    private int statusCellHeight() {
        return dp(50 * Math.max(1f, context.getResources().getConfiguration().fontScale));
    }

    private int dp(float value) { return Math.round(value * context.getResources().getDisplayMetrics().density); }

    private final class StatusChip extends LinearLayout {
        private final AppCompatImageView icon;
        private final TextView label, value;
        private final int labelRes;
        StatusChip(String tag, int labelRes, int iconRes, int tabIndex) {
            super(context);
            this.labelRes = labelRes;
            setTag(tag);
            setGravity(Gravity.CENTER_VERTICAL);
            setPadding(dp(8), dp(4), dp(8), dp(4));
            setFocusable(true);
            setOnClickListener(v -> callback.onStatusSelected(tabIndex));
            icon = new AppCompatImageView(context);
            icon.setImageResource(iconRes);
            addView(icon, new LinearLayout.LayoutParams(dp(20), dp(20)));
            LinearLayout texts = new LinearLayout(context);
            texts.setOrientation(VERTICAL);
            texts.setGravity(Gravity.CENTER_VERTICAL);
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(0, -2, 1);
            textParams.setMarginStart(dp(6));
            addView(texts, textParams);
            label = new TextView(context);
            label.setText(labelRes);
            label.setSingleLine(true);
            label.setIncludeFontPadding(false);
            UiStyles.setTextSize(label, R.dimen.text_size_tiny);
            texts.addView(label);
            value = new TextView(context);
            value.setTag(tag + "_value");
            value.setSingleLine(true);
            value.setTypeface(null, Typeface.BOLD);
            value.setIncludeFontPadding(false);
            UiStyles.setTextSize(value, R.dimen.text_size_small);
            texts.addView(value);
        }
        void bind(String text, int accent) {
            label.setTextColor(UiStyles.color(context, R.color.textSecondary));
            value.setText(text);
            value.setTextColor(accent);
            icon.setImageTintList(ColorStateList.valueOf(accent));
            GradientDrawable background = new GradientDrawable();
            background.setCornerRadius(dp(10));
            background.setColor(ColorUtils.blendARGB(UiStyles.color(context, R.color.surfaceCardInner), accent, .07f));
            setBackground(background);
            setContentDescription(labelRes == R.string.header_phone
                    ? context.getString(R.string.header_phone_description, text)
                    : context.getString(labelRes) + ": " + text);
        }
    }

    /** One fixed row on the IVI; title and statuses stack when the window is narrow. */
    private final class HeaderRow extends LinearLayout {
        private Boolean lastStacked;
        HeaderRow() { super(context); }
        @Override protected void onMeasure(int widthSpec, int heightSpec) {
            boolean stacked = MeasureSpec.getSize(widthSpec) - getPaddingLeft() - getPaddingRight() < dp(900);
            if (lastStacked == null || lastStacked != stacked) {
                lastStacked = stacked;
                setOrientation(stacked ? VERTICAL : HORIZONTAL);
                getChildAt(0).setLayoutParams(new LinearLayout.LayoutParams(stacked ? -1 : 0,
                    -2, stacked ? 0 : 1));
                getChildAt(1).setLayoutParams(new LinearLayout.LayoutParams(stacked ? -1 : dp(660), -2));
            }
            super.onMeasure(widthSpec, heightSpec);
        }
    }

    private final class StatusGrid extends ViewGroup {
        private int columns, cellWidth;
        StatusGrid() { super(context); }
        @Override protected void onMeasure(int widthSpec, int heightSpec) {
            int width = MeasureSpec.getSize(widthSpec);
            int visibleCount = 0;
            for (int i = 0; i < getChildCount(); i++) {
                if (getChildAt(i).getVisibility() != View.GONE) visibleCount++;
            }
            if (visibleCount == 0) {
                setMeasuredDimension(width, 0);
                return;
            }
            int maxPossibleCols = Math.max(1, (width + dp(8)) / dp(110));
            columns = Math.min(visibleCount, Math.max(2, maxPossibleCols));
            cellWidth = Math.max(0, (width - dp(8) * (columns - 1)) / columns);
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                if (child.getVisibility() == View.GONE) continue;
                child.measure(
                        MeasureSpec.makeMeasureSpec(cellWidth, MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec(statusCellHeight(), MeasureSpec.EXACTLY));
            }
            int rows = (visibleCount + columns - 1) / columns;
            int height = rows * statusCellHeight() + (rows - 1) * dp(8);
            setMeasuredDimension(width, height);
        }
        @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
            int visibleIdx = 0;
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                if (child.getVisibility() == View.GONE) continue;
                int x = (visibleIdx % columns) * (cellWidth + dp(8));
                int y = (visibleIdx / columns) * (statusCellHeight() + dp(8));
                child.layout(x, y, x + cellWidth, y + statusCellHeight());
                visibleIdx++;
            }
        }
    }
}
