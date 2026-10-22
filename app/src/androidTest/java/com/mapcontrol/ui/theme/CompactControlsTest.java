package com.mapcontrol.ui.theme;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.mapcontrol.R;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class CompactControlsTest {
    private Context context() {
        return new ContextThemeWrapper(InstrumentationRegistry.getInstrumentation().getTargetContext(),
                R.style.Theme_MapControl);
    }

    private List<CompoundButton> choices(View view) {
        List<CompoundButton> found = new ArrayList<>();
        if (view instanceof CompoundButton) found.add((CompoundButton) view);
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) found.addAll(choices(group.getChildAt(i)));
        }
        return found;
    }

    @Test public void toggleCanBeClearedAndVisualSyncDoesNotCommit() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(() -> {
            Context context = context();
            LinearLayout parent = new LinearLayout(context);
            AtomicInteger commits = new AtomicInteger();
            UiStyles.BinarySegmentHandle handle = UiStyles.addBinarySegmentedControl(context, parent,
                    "Automatic start", context.getString(R.string.common_on), context.getString(R.string.common_off),
                    "Starts automatically", "Start manually", false, selected -> commits.incrementAndGet());
            List<CompoundButton> controls = choices(parent);
            assertEquals(1, controls.size());
            CompoundButton button = controls.get(0);
            assertFalse(button.isChecked());
            button.performClick();
            assertTrue(button.isChecked());
            assertTrue(handle.isLeftSelected());
            button.performClick();
            assertFalse(button.isChecked());
            assertEquals(2, commits.get());
            handle.syncVisualWithoutCommit(true);
            handle.reapplyTheme();
            assertTrue(button.isChecked());
            assertEquals(2, commits.get());
            handle.setInteractionEnabled(false);
            assertFalse(button.isEnabled());
        });
    }

    @Test public void rejectedPermissionRestoresTheUncheckedCircle() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(() -> {
            Context context = context();
            LinearLayout parent = new LinearLayout(context);
            AtomicInteger commits = new AtomicInteger();
            UiStyles.BinarySegmentHandle[] handle = new UiStyles.BinarySegmentHandle[1];
            handle[0] = UiStyles.addBinarySegmentedControl(context, parent, "Overlay",
                    context.getString(R.string.common_on), context.getString(R.string.common_off),
                    "Show controls", "Hide controls", false, enabled -> {
                        commits.incrementAndGet();
                        if (enabled) handle[0].setLeftSelected(false);
                    });
            CompoundButton button = choices(parent).get(0);
            button.performClick();
            assertFalse(button.isChecked());
            assertFalse(handle[0].isLeftSelected());
            assertEquals(2, commits.get());
        });
    }

    @Test public void languageChoicesRemainMutuallyExclusive() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(() -> {
            Context context = context();
            LinearLayout parent = new LinearLayout(context);
            AtomicInteger commits = new AtomicInteger();
            UiStyles.BinarySegmentHandle handle = UiStyles.addBinarySegmentedControl(context, parent, "Language",
                    context.getString(R.string.settings_language_tr), context.getString(R.string.settings_language_en),
                    "Turkish", "English", true, selected -> commits.incrementAndGet());
            List<CompoundButton> buttons = choices(parent);
            assertEquals(2, buttons.size());
            buttons.get(1).performClick();
            assertFalse(buttons.get(0).isChecked());
            assertTrue(buttons.get(1).isChecked());
            assertEquals(1, commits.get());
            handle.syncVisualWithoutCommit(true);
            assertTrue(buttons.get(0).isChecked());
            assertFalse(buttons.get(1).isChecked());
            assertEquals(1, commits.get());
        });
    }

    @Test public void gridReflowsFromTwoColumnsToOneWithoutChangingOrder() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(() -> {
            Context context = context();
            FeatureGridLayout grid = new FeatureGridLayout(context);
            for (int i = 0; i < 4; i++) {
                TextView card = new TextView(context);
                card.setText("Feature " + i);
                card.setMinimumHeight(100);
                grid.addCard(card);
            }
            float density = context.getResources().getDisplayMetrics().density;
            int wide = Math.round(1200 * density);
            int narrow = Math.round(400 * density);
            grid.measure(View.MeasureSpec.makeMeasureSpec(wide, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(2400, View.MeasureSpec.AT_MOST));
            grid.layout(0, 0, wide, grid.getMeasuredHeight());
            assertEquals(grid.getChildAt(0).getTop(), grid.getChildAt(1).getTop());
            assertTrue(grid.getChildAt(1).getLeft() > grid.getChildAt(0).getLeft());
            grid.measure(View.MeasureSpec.makeMeasureSpec(narrow, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(2400, View.MeasureSpec.AT_MOST));
            grid.layout(0, 0, narrow, grid.getMeasuredHeight());
            assertTrue(grid.getChildAt(1).getTop() > grid.getChildAt(0).getTop());
            assertEquals("Feature 1", ((TextView) grid.getChildAt(1)).getText().toString());
        });
    }
}
