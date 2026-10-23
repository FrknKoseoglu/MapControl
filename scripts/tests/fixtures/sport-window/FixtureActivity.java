package com.desaysv.vehiclesetting;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.*;
/** Emulator-only lookalike. No vehicle APIs; mode only changes through explicit launch extras. */
public final class FixtureActivity extends Activity {
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        getSharedPreferences("fixture",0).edit().putInt("mode",getIntent().getIntExtra("mode",2))
                .putInt("closes",0).putInt("token",getIntent().getIntExtra("token",0)).commit();
        setContentView(new FrameLayout(this));
        Dialog dialog=new Dialog(this);
        LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);
        FrameLayout video=new FrameLayout(this);video.setId(R.id.view_video);
        root.addView(video,new LinearLayout.LayoutParams(700,180));
        LinearLayout items=new LinearLayout(this);items.setId(R.id.layout_items);
        TextView mode=new TextView(this);mode.setText("Fixture mode (not a control)");items.addView(mode);
        root.addView(items);
        Button back=new Button(this);back.setId(R.id.btn_back);back.setText("Back");
        back.setOnClickListener(v -> {
            int count=getSharedPreferences("fixture",0).getInt("closes",0);
            getSharedPreferences("fixture",0).edit().putInt("closes",count+1).commit();dialog.dismiss();
        });root.addView(back);
        dialog.setContentView(root);dialog.show();
    }
}
