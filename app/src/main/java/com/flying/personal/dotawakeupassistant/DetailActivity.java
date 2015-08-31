package com.flying.personal.dotawakeupassistant;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flying.personal.dotawakeupassistant.model.Hero;
import com.flying.personal.dotawakeupassistant.util.Utility;
import com.flying.personal.dotawakeupassistant.view.RoundImageView;

/**
 * Created by wangxian on 8/13/2015.
 */
public class DetailActivity extends ActionBarActivity {

    private GestureDetector gesture;

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

        gesture = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (Math.abs(velocityX) >= 100 && e2.getX() - e1.getX() > 200) {
                    close();
                    return true;
                } else {
                    return super.onFling(e1, e2, velocityX, velocityY);
                }
            }
        });
    }

    public void close() {
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!this.gesture.onTouchEvent(event))
            return super.onTouchEvent(event);
        else
            return true;
    }

    private void showDetail(Hero hero) {
        RoundImageView ivPortrait = (RoundImageView) findViewById(R.id.ivHeroPortrait);
        ivPortrait.setFilePath(hero.getPicPath());
        ivPortrait.invalidate();
        TextView tvName = (TextView) findViewById(R.id.tvHeroName);
        tvName.setText(hero.getName());

        TextView tvSkillDesc = (TextView) findViewById(R.id.tvSkillDesc);
        tvSkillDesc.setText("觉醒技能: " + hero.getWakeupSkillString());

        ((TextView) findViewById(R.id.tvTask1)).setText(hero.getTasks()[0].getDisplayInfo());
        ((TextView) findViewById(R.id.tvTask2)).setText(hero.getTasks()[1].getDisplayInfo());
        ((TextView) findViewById(R.id.tvTask3)).setText(hero.getTasks()[2].getDisplayInfo());

        if (hero.getTasks()[0].getStage() == null)
            return;



//        List<EquipmentItem> equipItems = hero.getTasks()[0].getStage().getOutputItems();

//        if (equipItems != null && equipItems.size() > 0) {
//            LinearLayout ll = (LinearLayout) findViewById(R.id.llTask1);
//
//            LinearLayout subLayoutForEquip = new LinearLayout(this);
//            subLayoutForEquip.setOrientation(LinearLayout.HORIZONTAL);
//            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.MATCH_PARENT);
//            subLayoutForEquip.setGravity(Gravity.CENTER_VERTICAL);
//            subLayoutForEquip.setLayoutParams(param);
//            ll.addView(subLayoutForEquip);
//
//            TextView tv = new TextView(this);
//            tv.setTextColor(getResources().getColor(R.color.white));
//            tv.setText(R.string.extra_equipitem);
//            tv.setTextAppearance(this, R.style.normal_margin);
//            tv.setTextSize(12); //12sp
//
//            LinearLayout.LayoutParams layoutParamForTV = new LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            layoutParamForTV.gravity = Gravity.CENTER_VERTICAL | Gravity.LEFT;
//            layoutParamForTV.setMargins(Utility.getInstance().dip2px(this, 10), Utility.getInstance().dip2px(this, 5), 0, 0);
//            subLayoutForEquip.addView(tv, layoutParamForTV);
//
//            for (EquipmentItem item : equipItems) {
//                RoundImageView riv = new RoundImageView(this);
//                riv.setBorderColor(item.getBorderColor());
//                riv.setBorderWidthPX(Utility.getInstance().dip2px(this, 2));
//                LinearLayout.LayoutParams imgLayout = new LinearLayout.LayoutParams(
//                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                imgLayout.setMargins(10, 10, 0, 10);
//                riv.setLayoutParams(imgLayout);
//                riv.setmBorderRadiusPX(Utility.getInstance().dip2px(this, 8));
//                riv.setFilePath(item.getPicPath());
//                riv.invalidate();
//                subLayoutForEquip.addView(riv);
//            }
//        }
    }
}