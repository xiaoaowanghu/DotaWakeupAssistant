package com.flying.personal.dotawakeupassistant;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flying.personal.dotawakeupassistant.model.EquipmentItem;
import com.flying.personal.dotawakeupassistant.model.Hero;
import com.flying.personal.dotawakeupassistant.util.Utility;
import com.flying.personal.dotawakeupassistant.view.RoundImageView;

/**
 * Created by wangxian on 8/13/2015.
 */
public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        LinearLayout parentView = (LinearLayout) findViewById(R.id.llparent_in_detail);
        BitmapDrawable bd = new BitmapDrawable(null, Utility.getInstance().createImageFromAsset(this, "bg9.jpg", 1080, 1920 * 1080));
        parentView.setBackgroundDrawable(bd);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        showDetail(ProviderFactory.getInstance().getDataProvider().getHeroByName(name));
    }

    private void showDetail(Hero hero) {
        RoundImageView ivPortrait = (RoundImageView) findViewById(R.id.ivHeroPortrait);
        ivPortrait.setFilePath(hero.getPicPath());
        ivPortrait.invalidate();
        TextView tvName = (TextView) findViewById(R.id.tvHeroName);
        tvName.setText(hero.getName());

        ((TextView) findViewById(R.id.tvTask1)).setText(hero.getTask1().getDescription());
        ((TextView) findViewById(R.id.tvTask2)).setText(hero.getTask2().getDescription());
        ((TextView) findViewById(R.id.tvTask3)).setText(hero.getTask3().getDescription());

        if (hero.getTask1().getStage().getItems().size() > 0) {
            LinearLayout ll = (LinearLayout) findViewById(R.id.llTask1);

            LinearLayout subLayoutForEquip = new LinearLayout(this);
            subLayoutForEquip.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            subLayoutForEquip.setLayoutParams(param);
            ll.addView(subLayoutForEquip);

            DisplayMetrics dm = new DisplayMetrics();
            this.getWindowManager().getDefaultDisplay().getMetrics(dm);
            int screenWidthPX = dm.widthPixels;
            int colCount = 5;
            int marginDP = 2;
            int marginPX = Utility.getInstance().dip2px(this, marginDP);
            int picWidthPX = (int) ((screenWidthPX - marginPX * (colCount + 1)) / colCount * 1.0 - 0.5f);

            TextView tv = new TextView(this);
//            tv.setTextColor(getResources().getColor(R.color.white));
            tv.setText(R.string.equipRemind);
            tv.setTextAppearance(this, R.style.normal_margin);
            tv.setTextSize(14); //14sp
            LinearLayout.LayoutParams layoutParamForTV = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParamForTV.gravity = Gravity.CENTER;
            layoutParamForTV.setMargins(Utility.getInstance().dip2px(this, 10), 0, 0, 0);
            subLayoutForEquip.addView(tv, layoutParamForTV);

            for (EquipmentItem item : hero.getTask1().getStage().getItems()) {
                ImageView ivEquip = new ImageView(this);
                LinearLayout.LayoutParams imgLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                imgLayout.setMargins(2, 2, 0, 0);

                ivEquip.setLayoutParams(imgLayout);
                //ivEquip.setMaxWidth(picWidthPX);
                //ivEquip.setScaleType(ImageView.ScaleType.FIT_XY);
                ivEquip.setAdjustViewBounds(true);
//                ivEquip.setImageResource(res.getIdentifier(item.getPath(), "drawable", getPackageName()));
                Bitmap equipBM = Utility.getInstance().createImageFromAsset(this, item.getPath());
                ivEquip.setImageBitmap(equipBM);
                ivEquip.setBackgroundResource(R.drawable.equip_image_border);
                subLayoutForEquip.addView(ivEquip);
            }
        }
    }
}