package com.flying.personal.dotawakeupassistant;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.flying.personal.dotawakeupassistant.model.EquipmentItem;
import com.flying.personal.dotawakeupassistant.model.Hero;
import com.flying.personal.dotawakeupassistant.model.HeroTag;
import com.flying.personal.dotawakeupassistant.model.WakeupSkill;
import com.flying.personal.dotawakeupassistant.util.Utility;
import com.flying.personal.dotawakeupassistant.view.RoundImageView;

import java.util.ArrayList;
import java.util.Calendar;
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
    public void finish() {
        super.finish();
        this.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!this.gesture.onTouchEvent(event))
            return super.onTouchEvent(event);
        else
            return true;
    }

    private void showAffectedTag() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.llTopName);
        LinearLayout.LayoutParams lpForAffectedItem = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0);
        lpForAffectedItem.gravity = Gravity.CENTER_VERTICAL | Gravity.LEFT;
        lpForAffectedItem.weight = 5;
        int marginRightPX = Utility.getInstance().dip2px(this, 10);
        lpForAffectedItem.setMargins(marginRightPX, Utility.getInstance().dip2px(this, 5),
                0, 0);

        final List<Map<String, Object>> affectedData = getAffectedSkillData();

        if (affectedData == null || affectedData.size() == 0) {
            TextView tv = new TextView(this);
            tv.setTextColor(getResources().getColor(R.color.font_task_lable));
            tv.setText(R.string.no_affected_skill);
            tv.setTextAppearance(this, R.style.normal_margin);
            tv.setTextSize(14); //sp
            ll.addView(tv, lpForAffectedItem);
        } else {
            SimpleAdapter sa = new SimpleAdapter(this, affectedData, R.layout.affected_skill,
                    new String[]{"tag", "skill"},
                    new int[]{R.id.tvAffectedTag, R.id.tvAffectedSkillDesc});
            sa.setViewBinder(
                    new SimpleAdapter.ViewBinder() {
                        @Override
                        public boolean setViewValue(View view, Object data, String textRepresentation) {
                            if (data instanceof HeroTag) {
                                TextView tv = (TextView) view;
                                HeroTag tag = (HeroTag) data;
                                ViewGroup.LayoutParams lp = tv.getLayoutParams();
                                int widthDP = 30;

                                if (affectedData.size() > 3) {
                                    widthDP = 25;
                                }

                                int widthPX = Utility.getInstance().dip2px(DetailActivity.this, widthDP) - 2;
                                String text = tag.getKeyName();

                                if (text == null)
                                    text = "N";
                                else {
                                    if (text.length() > 2)
                                        text = text.substring(0, 2);
                                }

                                String tmpText = text;
                                if (tmpText.length() < 2)
                                    tmpText = tmpText + "占";// 占位符

                                Paint paint = tv.getPaint();
                                Rect textBound = new Rect();
                                int fontSize = Utility.getInstance().getSuitableTextSizePX(paint, (int) tv.getTextSize(),
                                        widthPX - tv.getPaddingLeft() - tv.getPaddingRight(), tmpText, textBound);
                                lp.width = lp.height = widthPX;
                                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize);
                                tv.setText(text);
                                return true;
                            } else if (data instanceof WakeupSkill) {
                                TextView tv = (TextView) view;
                                WakeupSkill ws = (WakeupSkill) data;
                                String desc = "";

                                if (!ws.hero.getName().equalsIgnoreCase(currentHero.getName())) {
                                    desc = "搭配" + ws.hero.getName() + " ";
                                }

                                for (int i = 0; i < ws.abilitiesAffected.length; i++) {
                                    WakeupSkill.AbilityAffected a = ws.abilitiesAffected[i];
                                    desc += a.abilityType.toString() + a.value + ",";
                                }

                                if (desc.length() > 0) {
                                    desc = desc.substring(0, desc.length() - 1);
                                }

                                tv.setText(desc);
                                return true;
                            }
                            return false;
                        }
                    }

            );
            ListView lv = new ListView(this);
            ColorDrawable cd = new ColorDrawable(Color.TRANSPARENT);
            lv.setDivider(cd);
            lv.setDividerHeight(5);
            lv.setBackgroundColor(Color.TRANSPARENT);
            lv.setAdapter(sa);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Map<String, Object> data = (Map<String, Object>) parent.getItemAtPosition(position);
                    HeroTag heroTag = (HeroTag) data.get("tag");
                    List<String> heroes = ProviderFactory.getInstance().getDataProvider().getHeroesByTag(heroTag.tagName);

                    StringBuilder sb = new StringBuilder(50);
                    for (String s : heroes) {
                        sb.append(s);
                        sb.append(", ");
                    }

                    Utility.getInstance().showNormalDialog(DetailActivity.this, heroTag.tagName, sb.substring(0, sb.length() - 2));
                }
            });
            ll.addView(lv, lpForAffectedItem);
        }
    }

    private void showDetail() {
        RoundImageView ivPortrait = (RoundImageView) findViewById(R.id.ivHeroPortrait);
        ivPortrait.setFilePath(getResources().getString(R.string.dir_hero_path) + "/" + currentHero.getPicPath());

        if (currentHero.isBuiltData())
            ivPortrait.setLoadSource(RoundImageView.LoadSource.Asset);
        else
            ivPortrait.setLoadSource(RoundImageView.LoadSource.AppDataDir);

        ivPortrait.invalidate();
        TextView tvName = (TextView) findViewById(R.id.tvHeroName);
        tvName.setText(currentHero.getName());
        TextView tvSkillDesc = (TextView) findViewById(R.id.tvSkillDesc);

        if (currentHero.getWakeupSkill() != null)
            tvSkillDesc.setText("觉醒技能: " + currentHero.getWakeupSkill().description);
        else
            Log.d(this.getClass().getName(), currentHero.getName() + "skill is null");

        ((TextView) findViewById(R.id.tvTask1)).setText(currentHero.getTasks()[0].getDisplayInfo());

        final Calendar c = Calendar.getInstance();
        int mWay = c.get(Calendar.DAY_OF_WEEK);
        String day = null;
        switch (mWay) {
            case 1:
                day = "日";
                break;
            case 2:
                day = "一";
                break;
            case 3:
                day = "二";
                break;
            case 4:
                day = "三";
                break;
            case 5:
                day = "四";
                break;
            case 6:
                day = "五";
                break;
            case 7:
                day = "六";
                break;
        }

        ((TextView) findViewById(R.id.tvTask2)).setText(currentHero.getTasks()[1].getDisplayInfo() + "\n(今天周" + day + ")");
        ((TextView) findViewById(R.id.tvTask3)).setText(currentHero.getTasks()[2].getDisplayInfo());

        showAffectedTag();

        EquipmentItem[] neededEquipments = currentHero.getTasks()[0].getNeededEquip();

        if (neededEquipments == null || neededEquipments.length == 0) {
            Log.d(this.getClass().getName(), currentHero.getName() + " needed equipment is null");
        } else {

            LinearLayout llForNeededEquip = new LinearLayout(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            int lineMarginLeft = Utility.getInstance().dip2px(this, 10);
            int columnMargin = Utility.getInstance().dip2px(this, 1);
            lp.setMargins(lineMarginLeft, columnMargin, columnMargin, columnMargin);
            llForNeededEquip.setGravity(Gravity.LEFT | Gravity.CENTER);
            llForNeededEquip.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout task1LL = (LinearLayout) findViewById(R.id.llTask1);
            task1LL.addView(llForNeededEquip, lp);

            TextView tv = new TextView(this);
            tv.setTextSize(12);
            tv.setText(R.string.wakeup_equipment_matires_remind);
            tv.setTextColor(getResources().getColor(R.color.font_detail_main));
            LinearLayout.LayoutParams lpForTv = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            llForNeededEquip.addView(tv, lpForTv);

            int radius = Utility.getInstance().dip2px(this, 1);
            int borderWidth = Utility.getInstance().dip2px(this, 1);
            int imgHeight = Utility.getInstance().dip2px(this, 36);

            for (int i = 0; i < neededEquipments.length; i++) {
                EquipmentItem ei = neededEquipments[i];
                RoundImageView riv = new RoundImageView(this);
                riv.setBorderColor(ei.getBorderColor());
                riv.setMaxHeightPX(imgHeight);
                riv.setFilePath("equipment/" + ei.getPicPath());
                riv.setBorderWidthPX(radius);
                riv.setBorderWidthPX(borderWidth);
                riv.invalidate();

                LinearLayout.LayoutParams lpForIM = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                lpForIM.setMargins(columnMargin, 0, columnMargin, 0);
                llForNeededEquip.addView(riv, lpForIM);
            }
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

            if (ws.hero.getName().equalsIgnoreCase(currentHero.getName())) {
                data.add(0, itemData);
            } else {
                data.add(itemData);
            }
        }
        return data;
    }
}