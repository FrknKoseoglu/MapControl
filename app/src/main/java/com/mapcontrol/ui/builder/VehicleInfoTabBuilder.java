package com.mapcontrol.ui.builder;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.desaysv.ivi.extra.project.carinfo.ReadOnlyID;
import com.mapcontrol.R;
import com.mapcontrol.ui.theme.FeatureGridLayout;
import com.mapcontrol.ui.theme.UiStyles;
import com.mapcontrol.vehicle.VehicleIdentity;
import com.mapcontrol.vehicle.VehicleIdentityManager;
import com.mapcontrol.vehicle.VehicleMetricsFormatter;
import com.mapcontrol.vehicle.VehicleMetricsRepository;
import com.mapcontrol.vehicle.VehicleMetricsSnapshot;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Function;

/** Read-only vehicle dashboard. Stable views are updated without rebuilding the cards. */
public class VehicleInfoTabBuilder implements VehicleMetricsRepository.Listener {
    public interface VehicleInfoCallback { void log(String message); }

    public enum Category {
        ALL(R.string.vehicle_info_cat_all),
        GENERAL(R.string.vehicle_info_cat_general),
        TRIP(R.string.vehicle_info_cat_trip),
        FUEL(R.string.vehicle_info_cat_fuel),
        ENGINE(R.string.vehicle_info_cat_engine);

        final int titleRes;
        Category(int titleRes) { this.titleRes = titleRes; }
    }

    private final Context uiContext;
    private final VehicleMetricsRepository repository;
    private final VehicleMetricsFormatter formatter;
    private final VehicleInfoCallback callback;
    private final List<MetricBinding> bindings = new ArrayList<>();
    private ScrollView scrollView;
    private TextView statusText;
    private volatile boolean tabVisible;
    private Category selectedCategory = Category.ALL;
    private boolean activeOnlyFilter;

    private TextView identityModelYearText;

    private static final class MetricBinding {
        final View card;
        final TextView value;
        final int labelRes;
        final EnumSet<Category> categories;
        final Function<VehicleMetricsSnapshot, String> format;

        MetricBinding(View card, TextView value, int labelRes, EnumSet<Category> categories,
                Function<VehicleMetricsSnapshot, String> format) {
            this.card = card;
            this.value = value;
            this.labelRes = labelRes;
            this.categories = categories;
            this.format = format;
        }
    }

    public VehicleInfoTabBuilder(Context context, VehicleMetricsRepository repository,
            VehicleInfoCallback callback) {
        uiContext = context;
        this.repository = repository;
        this.callback = callback;
        formatter = new VehicleMetricsFormatter(context);
        build();
    }

    public ScrollView getScrollView() { return scrollView; }
    public void rebuild() { build(); }
    public void release() { stopListening(); }

    public void startListening() {
        tabVisible = true;
        if (statusText != null) statusText.setText(R.string.vehicle_info_loading);
        repository.addListener(this);
        repository.refresh();
        loadVehicleIdentity();
    }

    public void stopListening() {
        tabVisible = false;
        repository.removeListener(this);
    }

    public void refreshSnapshot() {
        if (statusText != null) statusText.setText(R.string.vehicle_info_loading);
        repository.refresh();
        loadVehicleIdentity();
    }

    @Override public void onMetricsUpdated(VehicleMetricsSnapshot snapshot) {
        if (tabVisible) bindMetrics(snapshot);
    }

    private void build() {
        bindings.clear();
        scrollView = new ScrollView(uiContext);
        scrollView.setFillViewport(true);
        LinearLayout content = new LinearLayout(uiContext);
        content.setOrientation(LinearLayout.VERTICAL);
        int pad = UiStyles.dimenPx(uiContext, R.dimen.oem_card_margin);
        content.setPadding(pad, pad, pad, pad);
        scrollView.addView(content, new ScrollView.LayoutParams(
                ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.WRAP_CONTENT));

        // Top identity row with model & refresh button

        // Identity Card (Vehicle Model, Year, VIN - software version removed)
        createIdentityCard(content, pad);

        // Category & Filter Bar
        HorizontalScrollView filterScroll = new HorizontalScrollView(uiContext);
        filterScroll.setHorizontalScrollBarEnabled(false);
        LinearLayout filterGroup = new LinearLayout(uiContext);
        filterGroup.setOrientation(LinearLayout.HORIZONTAL);
        filterGroup.setGravity(Gravity.CENTER_VERTICAL);

        int hGap = UiStyles.dimenPx(uiContext, R.dimen.spacing_tiny);
        Category[] cats = Category.values();
        Button[] catButtons = new Button[cats.length];

        for (int i = 0; i < cats.length; i++) {
            Category cat = cats[i];
            Button btn = new Button(uiContext);
            btn.setText(cat.titleRes);
            catButtons[i] = btn;
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMarginEnd(hGap * 2);
            filterGroup.addView(btn, lp);

            btn.setOnClickListener(v -> {
                selectedCategory = cat;
                for (int j = 0; j < cats.length; j++) {
                    styleFilterButton(catButtons[j], cats[j] == selectedCategory);
                }
                applyFilter();
            });
        }

        Button btnActiveOnly = new Button(uiContext);
        btnActiveOnly.setText(R.string.vehicle_info_filter_active);
        LinearLayout.LayoutParams actLp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        actLp.setMarginEnd(hGap * 2);
        filterGroup.addView(btnActiveOnly, actLp);

        Runnable refreshFilterStyles = () -> {
            for (int j = 0; j < cats.length; j++) {
                styleFilterButton(catButtons[j], cats[j] == selectedCategory);
            }
            styleFilterButton(btnActiveOnly, activeOnlyFilter);
        };

        btnActiveOnly.setOnClickListener(v -> {
            activeOnlyFilter = !activeOnlyFilter;
            refreshFilterStyles.run();
            applyFilter();
        });

        refreshFilterStyles.run();
        filterScroll.addView(filterGroup, new HorizontalScrollView.LayoutParams(
                HorizontalScrollView.LayoutParams.WRAP_CONTENT, HorizontalScrollView.LayoutParams.WRAP_CONTENT));
        LinearLayout.LayoutParams filterScrollLp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        filterScrollLp.setMargins(0, 0, 0, pad);
        content.addView(filterScroll, filterScrollLp);

        // Feature Grid with all metrics directly visible and filterable
        FeatureGridLayout grid = new FeatureGridLayout(uiContext);
        content.addView(grid);

        // General & Fuel
        addMetric(grid, R.string.vehicle_info_fuel_level, EnumSet.of(Category.GENERAL, Category.FUEL),
                s -> formatter.formatDashboardFuel(s));
        addMetric(grid, R.string.vehicle_info_range_remaining, EnumSet.of(Category.GENERAL, Category.TRIP, Category.FUEL),
                s -> read(ReadOnlyID.ID_ENDURANCE_KM, formatter::formatKm));
        addMetric(grid, R.string.vehicle_info_odo, EnumSet.of(Category.GENERAL, Category.TRIP),
                s -> read(ReadOnlyID.ID_GRAND_TOTAL_KM, formatter::formatKm));
        addMetric(grid, R.string.vehicle_info_battery, EnumSet.of(Category.GENERAL, Category.ENGINE),
                s -> read(ReadOnlyID.ID_IBS_VOLTAGE, formatter::formatVoltage));
        addMetric(grid, R.string.vehicle_info_col_tmp, EnumSet.of(Category.GENERAL, Category.ENGINE),
                s -> read(ReadOnlyID.ID_WATER_TEMPERATURE, formatter::formatTemperature));
        addMetric(grid, R.string.vehicle_info_gear, EnumSet.of(Category.GENERAL, Category.ENGINE),
                s -> read(ReadOnlyID.ID_GEARBOX_STATE, formatter::formatGear));
        addMetric(grid, R.string.vehicle_info_power_mode, EnumSet.of(Category.GENERAL),
                s -> read(ReadOnlyID.ID_SYSTEM_POWER_MODE, formatter::formatPowerMode));

        // Trip
        addMetric(grid, R.string.vehicle_info_trip, EnumSet.of(Category.TRIP),
                s -> read(ReadOnlyID.ID_TRIP, formatter::formatDeciKm));
        addMetric(grid, R.string.vehicle_info_trip_after_reset, EnumSet.of(Category.TRIP),
                s -> read(ReadOnlyID.ID_GRAND_TOTAL_KM_AFTER_CLEAR, formatter::formatKm));
        addMetric(grid, R.string.vehicle_info_trip_since_start, EnumSet.of(Category.TRIP),
                s -> read(ReadOnlyID.ID_GRAND_TOTAL_KM_AFTER_RUNNING, formatter::formatKm));
        addMetric(grid, R.string.vehicle_info_avg_speed, EnumSet.of(Category.TRIP),
                s -> read(ReadOnlyID.ID_AVG_SPEED, formatter::formatSpeed));

        // Fuel
        addMetric(grid, R.string.vehicle_info_afc, EnumSet.of(Category.FUEL),
                s -> read(ReadOnlyID.ID_AVG_FUEL_CONS, raw -> {
                    String value = formatter.formatDashboardConsumption(raw);
                    return value.equals("—") ? value : value + " km";
                }));
        addMetric(grid, R.string.vehicle_info_afc_trip, EnumSet.of(Category.FUEL, Category.TRIP),
                s -> read(ReadOnlyID.ID_AVERAGE_FUEL_CONS_AFTER_RUNNING, formatter::formatConsumption));
        addMetric(grid, R.string.vehicle_info_fuel_used, EnumSet.of(Category.FUEL),
                s -> read(ReadOnlyID.ID_SUM_FUEL, formatter::formatFuelUsedMl));
        addMetric(grid, R.string.vehicle_info_low_fuel, EnumSet.of(Category.FUEL),
                s -> read(ReadOnlyID.ID_LOW_FUEL_WARNING, formatter::formatLowFuel));
        addMetric(grid, R.string.vehicle_info_range_total, EnumSet.of(Category.FUEL),
                s -> read(ReadOnlyID.ID_TOTAL_RANGE, formatter::formatKm));

        // Engine & Speed
        addMetric(grid, R.string.vehicle_info_eng_spd, EnumSet.of(Category.ENGINE),
                s -> read(ReadOnlyID.ID_ENGINE_RPM, formatter::formatRpm));
        addMetric(grid, R.string.vehicle_info_act_speed, EnumSet.of(Category.ENGINE),
                s -> read(ReadOnlyID.ID_CAR_SPEED, formatter::formatSpeed));
        addMetric(grid, R.string.vehicle_info_dis_speed, EnumSet.of(Category.ENGINE),
                s -> read(ReadOnlyID.ID_SPEED_GAUGE_DISPLAY, formatter::formatSpeed));

        // Engineering mode button removed per request

        bindMetrics(repository.currentSnapshot());
    }

    private void createIdentityCard(LinearLayout parent, int pad) {
        LinearLayout card = new LinearLayout(uiContext);
        card.setOrientation(LinearLayout.HORIZONTAL);
        card.setGravity(Gravity.CENTER_VERTICAL);
        card.setPadding(pad, pad, pad, pad);
        UiStyles.setGlassCardBackground(card);
        LinearLayout.LayoutParams cardLp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        cardLp.setMargins(0, 0, 0, pad);
        parent.addView(card, cardLp);

        identityModelYearText = new TextView(uiContext);
        UiStyles.setTextSize(identityModelYearText, R.dimen.text_size_large);
        identityModelYearText.setTypeface(null, Typeface.BOLD);
        identityModelYearText.setTextColor(UiStyles.color(uiContext, R.color.textPrimary));
        identityModelYearText.setText("Chery Omoda 5 Pro - 2024");
        identityModelYearText.setSingleLine(true);
        card.addView(identityModelYearText, new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

        Button refresh = action(R.string.vehicle_info_refresh);
        refresh.setOnClickListener(v -> refreshSnapshot());
        card.addView(refresh, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        loadVehicleIdentity();
    }

    private void loadVehicleIdentity() {
        VehicleIdentityManager.getInstance().resolveIdentityAsync(uiContext, this::bindIdentity);
    }

    private void bindIdentity(VehicleIdentity identity) {
        if (identityModelYearText != null) {
            String model = (identity.marketName != null && !identity.marketName.isEmpty()
                    && !identity.marketName.equals("Chery Otomobil"))
                    ? identity.marketName : "Chery Omoda 5 Pro";
            String year = (identity.modelYear != null && !identity.modelYear.isEmpty()
                    && !identity.modelYear.equals("—"))
                    ? identity.modelYear : "2024";
            identityModelYearText.setText(model + " - " + year);
        }
    }

    private String read(int id, Function<Integer, String> format) {
        int raw = repository.getCombined(id);
        if (!VehicleMetricsSnapshot.isValid(raw)) return "—";
        String value = format.apply(raw);
        return uiContext.getString(R.string.vehicle_info_value_unknown).equals(value) ? "—" : value;
    }

    private void addMetric(FeatureGridLayout grid, int labelRes, EnumSet<Category> categories,
            Function<VehicleMetricsSnapshot, String> format) {
        LinearLayout card = new LinearLayout(uiContext);
        card.setOrientation(LinearLayout.VERTICAL);
        int pad = UiStyles.dimenPx(uiContext, R.dimen.oem_card_inner_padding);
        card.setPadding(pad, pad / 2, pad, pad / 2);
        UiStyles.setGlassCardBackground(card);
        TextView label = text(labelRes, R.dimen.text_size_small);
        label.setTextColor(UiStyles.color(uiContext, R.color.textHint));
        card.addView(label);
        TextView value = new TextView(uiContext);
        UiStyles.setTextSize(value, R.dimen.text_size_title);
        value.setTypeface(null, Typeface.BOLD);
        value.setTextColor(UiStyles.color(uiContext, R.color.textPrimary));
        value.setPadding(0, pad / 2, 0, 0);
        card.addView(value);
        grid.addCard(card);
        bindings.add(new MetricBinding(card, value, labelRes, categories, format));
    }

    private void styleFilterButton(Button btn, boolean selected) {
        int bg = selected ? UiStyles.color(uiContext, R.color.oemAccent)
                : UiStyles.color(uiContext, R.color.buttonSecondaryMuted);
        int textColor = selected ? UiStyles.color(uiContext, R.color.textPrimary)
                : UiStyles.color(uiContext, R.color.textSecondary);
        UiStyles.styleOemButton(btn, bg);
        btn.setTextColor(textColor);
    }

    private void applyFilter() {
        for (MetricBinding b : bindings) {
            boolean matchesCategory = (selectedCategory == Category.ALL || b.categories.contains(selectedCategory));
            boolean matchesActive = true;
            if (activeOnlyFilter) {
                CharSequence text = b.value.getText();
                matchesActive = text != null && !text.toString().trim().equals("—") && !text.toString().trim().isEmpty();
            }
            b.card.setVisibility((matchesCategory && matchesActive) ? View.VISIBLE : View.GONE);
        }
    }

    private void bindMetrics(VehicleMetricsSnapshot snapshot) {
        if (statusText != null) {
            statusText.setText(snapshot.connected ? R.string.vehicle_info_listening : R.string.vehicle_info_connection_hint);
            statusText.setTextColor(UiStyles.color(uiContext,
                    snapshot.connected ? R.color.statusSuccess : R.color.textHint));
        }
        for (MetricBinding binding : bindings) {
            String value = snapshot.connected ? binding.format.apply(snapshot) : "—";
            if (!value.contentEquals(binding.value.getText())) binding.value.setText(value);
            binding.value.setContentDescription(uiContext.getString(binding.labelRes) + ": " + value);
        }
        applyFilter();
    }

    private TextView text(int textRes, int sizeRes) {
        TextView text = new TextView(uiContext);
        text.setText(textRes);
        UiStyles.setTextSize(text, sizeRes);
        text.setTextColor(UiStyles.color(uiContext, R.color.textPrimary));
        return text;
    }

    private Button action(int labelRes) {
        Button button = new Button(uiContext);
        button.setText(labelRes);
        UiStyles.styleOemButton(button, UiStyles.color(uiContext, R.color.buttonSecondaryMuted));
        return button;
    }


    private void log(String message) {
        if (callback != null) {
            callback.log(message);
        }
    }
}
