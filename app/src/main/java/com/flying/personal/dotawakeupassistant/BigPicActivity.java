package com.flying.personal.dotawakeupassistant;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.flying.personal.dotawakeupassistant.util.CommonUtility;
import com.flying.personal.dotawakeupassistant.view.RoundImageView;

/**
 * Created by wangxian on 9/18/2015.
 */
public class BigPicActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String picPath = this.getIntent().getStringExtra("path");
        boolean isBuiltIn = this.getIntent().getBooleanExtra("isBuiltIn", true);
        LinearLayout ll = new LinearLayout(this);
        ll.setBackgroundColor(getResources().getColor(R.color.light_blue));
        RoundImageView riv = new RoundImageView(this);
        riv.setBorderWidthPX(CommonUtility.dip2px(this, 10));
        riv.setBorderColor(getResources().getColor(R.color.light_blue));
        riv.setmBorderRadiusPX(CommonUtility.dip2px(this, 20));

        if (isBuiltIn)
            riv.setLoadSource(RoundImageView.LoadSource.Asset);
        else
            riv.setLoadSource(RoundImageView.LoadSource.AppDataDir);

        riv.setFilePath(picPath);
        riv.setScaleMode(RoundImageView.SimpleScale.Fill);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ll.addView(riv, p);
        riv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BigPicActivity.this.finish();
            }
        });
        setContentView(ll);
    }

}
