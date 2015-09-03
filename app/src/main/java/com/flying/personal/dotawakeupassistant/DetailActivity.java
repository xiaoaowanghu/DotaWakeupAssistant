package com.flying.personal.dotawakeupassistant;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.flying.personal.dotawakeupassistant.model.Hero;
import com.flying.personal.dotawakeupassistant.model.HeroTag;
import com.flying.personal.dotawakeupassistant.model.WakeupSkill;
import com.flying.personal.dotawakeupassistant.util.Utility;
import com.flying.personal.dotawakeupassistant.view.RoundImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxian on 8/13/2015.
 */
public class DetailActivity extends ActionBarActivity {

    private GestureDetector gesture;
    private Hero currentHero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        LinearLayout parentView = (LinearLayout) findViewById(R.id.llparent_in_detail);
        BitmapDrawable bd = new BitmapDrawable(null, Utility.getInstance().createImageFromAsset(this, "bg9.jpg", 1080, 1920 * 1080));
        parentView.setBackgroundDrawable(bd);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        currentHero = ProviderFactory.getInstance().getDataProvider().getHeroByName(name);
        showDetail();

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

    private void showDetail() {
        RoundImageView ivPortrait = (RoundImageView) findViewById(R.id.ivHeroPortrait);
        ivPortrait.setFilePath(getResources().getString(R.string.dir_hero_path) + "/" + currentHero.getPicPath());
        ivPortrait.invalidate();
        TextView tvName = (TextView) findViewById(R.id.tvHeroName);
        tvName.setText(currentHero.getName());

        TextView tvSkillDesc = (TextView) findViewById(R.id.tvSkillDesc);

        if (currentHero.getWakeupSkill() != null)
            tvSkillDesc.setText("觉醒技能: " + currentHero.getWakeupSkill().description);
        else
            tvSkillDesc.setText("觉醒技能: " + currentHero.getWakeupSkillString());
        ((TextView) findViewById(R.id.tvTask1)).setText(currentHero.getTasks()[0].getDisplayInfo());
        ((TextView) findViewById(R.id.tvTask2)).setText(currentHero.getTasks()[1].getDisplayInfo());
        ((TextView) findViewById(R.id.tvTask3)).setText(currentHero.getTasks()[2].getDisplayInfo());

        LinearLayout ll = (LinearLayout) findViewById(R.id.llTopName);
        LinearLayout.LayoutParams layoutParamForTV = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamForTV.gravity = Gravity.CENTER_VERTICAL | Gravity.LEFT;
        layoutParamForTV.weight = 4;
        layoutParamForTV.setMargins(Utility.getInstance().dip2px(this, 10), Utility.getInstance().dip2px(this, 5),
                0, Utility.getInstance().dip2px(this, 5));

        if (currentHero.getTags() == null || currentHero.getTags().size() == 0) {
            TextView tv = new TextView(this);
            tv.setTextColor(getResources().getColor(R.color.white));
            tv.setText(R.string.no_affected_skill);
            tv.setTextAppearance(this, R.style.normal_margin);
            tv.setTextSize(14); //sp
            ll.addView(tv, layoutParamForTV);
        } else {
            final List<Map<String, Object>> affectedData = getAffectedSkillData();
            if (affectedData == null || affectedData.size() == 0)
                return;

            if (affectedData.size() > 3) {
                LinearLayout headLayout = (LinearLayout) findViewById(R.id.llHead);
                LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) headLayout.getLayoutParams();
                param.weight = 500;
            }

            SimpleAdapter sa = new SimpleAdapter(this, affectedData, R.layout.affected_skill,
                    new String[]{"tag", "skill"},
                    new int[]{R.id.ivAffectedSkillPic, R.id.tvAffectedSkillDesc});
            sa.setViewBinder(new SimpleAdapter.ViewBinder() {
                @Override
                public boolean setViewValue(View view, Object data, String textRepresentation) {
                    if (view instanceof ImageView) {
                        HeroTag tag = (HeroTag) data;
                        ImageView iv = ((ImageView) view);
                        ViewGroup.LayoutParams lp = iv.getLayoutParams();
                        if (affectedData.size() > 2) {
                            lp.width = lp.height = Utility.getInstance().dip2px(DetailActivity.this, 20);
                        } else {
                            lp.width = lp.height = Utility.getInstance().dip2px(DetailActivity.this, 30);
                        }
                        ((ImageView) view).setImageBitmap(Utility.getInstance().createImageFromAsset(DetailActivity.this, "hero/xiaohei.jpg"));
//                        ((ImageView) view).setImageBitmap(Utility.getInstance().createImageFromAsset(DetailActivity.this, tag.picPath));
                        return true;
                    } else if (view instanceof TextView) {
                        WakeupSkill ws = (WakeupSkill) data;
                        String desc = "";
                        for (int i = 0; i < ws.abilitiesAffected.length; i++) {
                            WakeupSkill.AbilityAffected a = ws.abilitiesAffected[i];
                            desc += a.abilityType.toString() + " " + a.value + ",";
                        }

                        if (desc.length() > 0) {
                            desc = desc.substring(0, desc.length() - 1);
                        }
                        ((TextView) view).setText(desc);
                        return true;
                    }

                    return false;
                }
            });
            ListView lv = new ListView(this);
            lv.setBackgroundColor(Color.TRANSPARENT);
            lv.setAdapter(sa);
            ll.addView(lv, layoutParamForTV);
        }
    }

    @Nullable
    private List<Map<String, Object>> getAffectedSkillData() {
        IDataProvider dataProvider = ProviderFactory.getInstance().getDataProvider();
        List<WakeupSkill> affectedSkills = dataProvider.getHeroAffectedSkill(currentHero.getName());
        if (affectedSkills == null || affectedSkills.size() == 0) {
            return null;
        }

        List<Map<String, Object>> data = new ArrayList<>(affectedSkills.size());
        for (int i = 0; i < affectedSkills.size(); i++) {
            WakeupSkill ws = affectedSkills.get(i);

            if (ws.abilitiesAffected == null)
                continue;

            Map<String, Object> itemData = new HashMap<>(3);
            itemData.put("skill", ws);
            HeroTag tag = ws.affectTag;
            itemData.put("tag", tag);
            data.add(itemData);
        }
        return data;
    }


}