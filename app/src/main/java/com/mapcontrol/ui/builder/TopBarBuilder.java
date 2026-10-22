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
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import com.mapcontrol.BuildConfig;
import com.mapcontrol.ui.widget.StatusWidgetsView;
import androidx.appcompat.widget.AppCompatImageButton;
import com.mapcontrol.R;
import com.mapcontrol.ui.HeaderStatusSink;
import com.mapcontrol.ui.theme.UiStyles;

/** Page title/back and passive branding. Live status widgets are hosted separately. */
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
    private TextView brandView;
    private AppCompatImageButton launcherBackButton;
    private View topBarDivider;
    private final StatusWidgetsView statusWidgets;

    public TopBarBuilder(Context context, TopBarCallback callback) {
        this.context = context;
        this.callback = callback;
        statusWidgets = new StatusWidgetsView(context, "header", callback::onStatusSelected);
    }

    public LinearLayout build() {
        topBar = new LinearLayout(context);
        topBar.setTag("shared_header");
        topBar.setOrientation(LinearLayout.VERTICAL);
        HeaderRow row = new HeaderRow();
        row.setPadding(dp(20), dp(16), dp(16), dp(16));
        LinearLayout titleArea = new LinearLayout(context);
        titleArea.setGravity(Gravity.CENTER_VERTICAL);

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
        titleView.setMinHeight(dp(48));
        titleView.setClickable(true);
        titleView.setFocusable(true);
        titleArea.addView(titleView, new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.MATCH_PARENT, 1));

        row.addView(titleArea);

        brandView = new TextView(context);
        brandView.setTag("header_brand");
        SpannableString brand = new SpannableString("MapControl by vNoisy (" + BuildConfig.VERSION_NAME + ")");
        int author = brand.toString().indexOf("vNoisy");
        brand.setSpan(new StyleSpan(Typeface.ITALIC), author, author + "vNoisy".length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        int version = brand.toString().indexOf('(');
        brand.setSpan(new RelativeSizeSpan(.82f), version, brand.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        brandView.setText(brand);
        UiStyles.setTextSize(brandView, R.dimen.text_size_normal);
        brandView.setTypeface(null, Typeface.BOLD);
        brandView.setGravity(Gravity.RIGHT | Gravity.TOP);
        brandView.setTextDirection(View.TEXT_DIRECTION_LTR);
        brandView.setClickable(false);
        brandView.setFocusable(false);
        row.addView(brandView);
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

    public StatusWidgetsView getStatusWidgets() { return statusWidgets; }
    @Override public void setWifiState(WifiState state) { statusWidgets.setWifiState(state); }
    @Override public void setDriveMode(boolean connected, int mode) { statusWidgets.setDriveMode(connected, mode); }
    @Override public void setServerRunning(boolean running) { statusWidgets.setServerRunning(running); }
    @Override public void setProjectionActive(boolean active) { statusWidgets.setProjectionActive(active); }
    @Override public void setSoundBoost(boolean active, int gainMb) { statusWidgets.setSoundBoost(active, gainMb); }

    public void setTitle(int titleRes) {
        if (titleView == null) return;
        String title = context.getString(titleRes);
        if (!android.text.TextUtils.equals(titleView.getText(), title)) titleView.setText(title);
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
        if (brandView != null) brandView.setTextColor(UiStyles.color(context, R.color.textSecondary));
        if (launcherBackButton != null) {
            UiStyles.setGlassCardBackground(launcherBackButton);
            launcherBackButton.setImageTintList(ColorStateList.valueOf(UiStyles.color(context, R.color.textPrimary)));
        }
        if (topBarDivider != null) UiStyles.setBackgroundRes(topBarDivider, R.drawable.bg_topbar_divider);
        statusWidgets.reapplyTheme();
    }

    private int dp(float value) { return Math.round(value * context.getResources().getDisplayMetrics().density); }

    /** Physical LHD corners stay fixed, including in RTL locales and narrow windows. */
    private final class HeaderRow extends ViewGroup {
        private boolean titleBelow;
        private int titleWidth, brandWidth, topHeight;

        HeaderRow() { super(context); }

        @Override protected void onMeasure(int widthSpec, int heightSpec) {
            int width = MeasureSpec.getSize(widthSpec);
            int available = Math.max(0, width - getPaddingLeft() - getPaddingRight());
            int gap = dp(16);
            int half = Math.max(0, (available - gap) / 2);
            View title = getChildAt(0), brand = getChildAt(1);
            brand.measure(MeasureSpec.makeMeasureSpec(half, MeasureSpec.AT_MOST),
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            brandWidth = brand.getMeasuredWidth();
            int middle = Math.max(0, available - brandWidth - gap);
            titleBelow = middle < dp(360 * Math.max(1f,
                    getResources().getConfiguration().fontScale));
            titleWidth = titleBelow ? available : middle;
            title.measure(MeasureSpec.makeMeasureSpec(titleWidth, MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            topHeight = brand.getMeasuredHeight();
            if (!titleBelow) topHeight = Math.max(topHeight, title.getMeasuredHeight());
            int height = getPaddingTop() + topHeight + getPaddingBottom();
            if (titleBelow) height += dp(12) + title.getMeasuredHeight();
            setMeasuredDimension(width, resolveSize(height, heightSpec));
        }

        @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
            int left = getPaddingLeft(), top = getPaddingTop();
            View title = getChildAt(0), brand = getChildAt(1);
            int brandLeft = getWidth() - getPaddingRight() - brandWidth;
            brand.layout(brandLeft, top, brandLeft + brandWidth, top + brand.getMeasuredHeight());
            int titleLeft = left;
            int titleTop = titleBelow ? top + topHeight + dp(12)
                    : top + (topHeight - title.getMeasuredHeight()) / 2;
            title.layout(titleLeft, titleTop, titleLeft + titleWidth,
                    titleTop + title.getMeasuredHeight());
        }
    }

}
