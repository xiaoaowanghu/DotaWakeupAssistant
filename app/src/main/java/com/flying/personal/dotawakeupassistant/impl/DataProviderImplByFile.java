package com.flying.personal.dotawakeupassistant.impl;

import android.util.Log;

import com.flying.personal.dotawakeupassistant.IDataProvider;
import com.flying.personal.dotawakeupassistant.model.EquipmentItem;
import com.flying.personal.dotawakeupassistant.model.GameStage;
import com.flying.personal.dotawakeupassistant.model.Hero;
import com.flying.personal.dotawakeupassistant.model.SpecialStage;
import com.flying.personal.dotawakeupassistant.model.WakeUpRepeatableTask;
import com.flying.personal.dotawakeupassistant.model.WakeUpTask;
import com.flying.personal.dotawakeupassistant.util.HanyuPinyinHelper;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxian on 8/23/2015.
 */
public class DataProviderImplByFile implements IDataProvider {

    private List<Hero> heroes;
    private Map<Hero, List<String>> searchIndexs;
    private double version = 1.0;
    private String dataFilePath;

    @Override
    public List<Hero> getAllHeroes() {
        return heroes;
    }

    @Override
    public List<Hero> getMatchedHeroes(String index, Map<Hero, String> matchedIndex) {
        if (matchedIndex == null)
            throw new IllegalArgumentException("The matchedIndex shouldn't be null.");

        if (index == null || index.length() == 0)
            return getAllHeroes();

        List<Hero> result = new ArrayList<>(heroes.size());

        for (Hero h : heroes) {
            List<String> tmpList = searchIndexs.get(h);
            for (String s : tmpList) {
                if (s.contains(index)) {
                    matchedIndex.put(h, s);
                    result.add(h);
                    break;
                }
            }
        }

        return result;
    }

    @Override
    public List<Hero> getMatchedHeroes(String index, Hero.PositionType position, Map<Hero, String> matchedIndex) {
        if (matchedIndex == null)
            throw new IllegalArgumentException("The matchedIndex shouldn't be null.");

        if (position == null)
            return getMatchedHeroes(index, matchedIndex);
        else {
            List<Hero> result = new ArrayList<>(heroes.size());
            for (Hero h : heroes) {
                if (h.getPositionType() != position)
                    continue;

                if (index != null && index.length() > 0) {
                    List<String> tmpList = searchIndexs.get(h);
                    for (String s : tmpList) {
                        if (s.contains(index)) {
                            matchedIndex.put(h, s);
                            result.add(h);
                            break;
                        }
                    }
                } else {
                    result.add(h);
                }
            }

            return result;
        }
    }

    @Override
    public int getTotalHeroCount() {
        return heroes.size();
    }

    @Override
    public Hero getHeroByName(String heroName) {
        if (heroName == null || heroName.length() == 0)
            return null;

        for (Hero h : heroes) {
            if (heroName.equalsIgnoreCase(h.getName()))
                return h;
        }

        return null;
    }

    @Override
    public List<Hero> getHeroesByPosition(Hero.PositionType position) {
        if (position == null)
            return getAllHeroes();

        List<Hero> result = new ArrayList<>(heroes.size());

        for (Hero h : heroes) {
            if (h.getPositionType() == position)
                result.add(h);
        }

        return result;
    }

    @Override
    public List<Hero> getHeroesByAbilityType(Hero.AbilityType abilityType) {
        if (abilityType == null)
            return getAllHeroes();

        List<Hero> result = new ArrayList<>(heroes.size());

        for (Hero h : heroes) {
            if (h.getAbilityType() == abilityType)
                result.add(h);
        }

        return result;
    }

    @Override
    public void init(String[] args) {
        try {
            generateData();
            dataFilePath = args[0];

            if (isDataFileExit()) {
                SerializedData extraData = getExtraDataFromJsonFile();
                if (extraData != null) {
                    version = extraData.version;
                    if (extraData.extraDatas != null) {
                        for (int i = 0; i < extraData.extraDatas.size(); i++) {
                            Hero h = extraData.extraDatas.get(i);
                            int j = 0;
                            for (; j < heroes.size(); j++) {
                                if (h.getName().equalsIgnoreCase(heroes.get(j).getName()))
                                    break;
                            }
                            //Replace with new one
                            if (j < heroes.size()) {
                                heroes.set(j, h);
                            } else {
                                heroes.add(h);
                            }
                        }
                    }
                }
            }

            searchIndexs = new HashMap<Hero, List<String>>(heroes.size());

            for (Hero h : heroes) {
                List<String> indexes = new ArrayList<String>(20);
                indexes.addAll(HanyuPinyinHelper.getInstance().getHanziT9Index(h.getName()));

                if (h.getAlias() == null)
                    continue;

                for (String a : h.getAlias()) {
                    indexes.addAll(HanyuPinyinHelper.getInstance().getHanziT9Index(a));
                }

                searchIndexs.put(h, indexes);
            }

        } catch (Exception e) {
            Log.e(this.getClass().getName(), Log.getStackTraceString(e));
        }
    }

    private SerializedData getExtraDataFromJsonFile() {
        SerializedData result = null;
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(this.dataFilePath), "UTF-8");
            Gson gson = new Gson();
            result = gson.fromJson(isr, SerializedData.class);
        } catch (Exception e) {
            Log.e(this.getClass().getName(), Log.getStackTraceString(e));
        }
        return result;
    }

    @Override
    public void save(String[] args) {
        Log.e(this.getClass().getName(), "save not implemented");
    }

    @Override
    public void visitHero(Hero hero) {
        int i = 0;

        for (; i < heroes.size(); i++) {
            if (heroes.get(i) == hero)
                break;
        }

        if (i >= heroes.size())
            return;

        heroes.remove(i);
        heroes.add(0, hero);
    }

    private boolean isDataFileExit() {
        File f = new File(dataFilePath);
        return f.exists();
    }

    @Override
    public double getVersion() {
        return version;
    }

    public class SerializedData {
        public double version = 1;
        public List<Hero> extraDatas;

    }

    private void generateData() {
        Map<String, GameStage> stageCache = new HashMap<String, GameStage>(30);
        {
            GameStage gs = new GameStage();
            gs.setName("乱石山坡(精英)");
            gs.setChapter(14);
            gs.setSequenceNo(4);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                EquipmentItem item = new EquipmentItem();
                item.setDisplayName("远古遗物");
                item.setPicPath("ygyw.jpg");
                items.add(item);
            }
            {
                EquipmentItem item = new EquipmentItem();
                item.setDisplayName("恶魔之失");
                item.setPicPath("emzs.jpg");
                items.add(item);
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }

        {
            GameStage gs = new GameStage();
            gs.setName("团战中心(精英)");
            gs.setChapter(13);
            gs.setSequenceNo(4);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                EquipmentItem item = new EquipmentItem();
                item.setDisplayName("鹰语指环");
                item.setPicPath("yuzh.jpg");
                items.add(item);
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }

        {
            GameStage gs = new GameStage();
            gs.setName("黑暗者");
            gs.setChapter(11);
            gs.setSequenceNo(3);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                EquipmentItem item = new EquipmentItem();
                item.setDisplayName("大炮卷轴");
                item.setPicPath("dpjz.jpg");
                items.add(item);
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("冰火两重天(精英)");
            gs.setChapter(12);
            gs.setSequenceNo(2);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }

        //=============================================Special Stage======================================
        {
            SpecialStage gs = new SpecialStage();
            gs.setName("潮汐神庙");
            gs.setStamina(6);
            gs.setOccurDays(SpecialStage.Weekday.Monday | SpecialStage.Weekday.Wednesday | SpecialStage.Weekday.Friday | SpecialStage.Weekday.Sunday);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }

        {
            SpecialStage gs = new SpecialStage();
            gs.setName("女武神的对决");
            gs.setStamina(6);
            gs.setOccurDays(SpecialStage.Weekday.Wednesday | SpecialStage.Weekday.Saturday | SpecialStage.Weekday.Sunday);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                EquipmentItem item = new EquipmentItem();
                item.setDisplayName("静息项链");
                item.setPicPath("jxxl.jpg");
                items.add(item);
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        //============================================Hero====================================================

        heroes = new ArrayList<Hero>(100);
        {
            Hero h1 = new Hero();
            h1.setName("小黑");
            h1.setPositionType(Hero.PositionType.Back);
            h1.setAbilityType(Hero.AbilityType.Agility);
            h1.setPortraitPath("xiaohei.jpg");
            h1.setPicPath("xiaohei_big.jpg");
            h1.setWakeupSkill("增加女武神队友90点护甲穿透");
            List<String> alias = new ArrayList<String>(5);
            alias.add("冰箭");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("收集60个倚天剑碎片，合成装备");
            w1.setStage(stageCache.get("乱石山坡(精英)"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("女武神的对决"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("小黑独自完成");
            w3.setStage(stageCache.get("黑暗者"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("双头龙");
            h1.setPositionType(Hero.PositionType.Middle);
            h1.setAbilityType(Hero.AbilityType.Intelligence);
            h1.setPortraitPath("shuangtoulong.jpg");
            h1.setPicPath("shuangtoulong_big.jpg");
            h1.setWakeupSkill("吐息系队友增加819点魔法");
            List<String> alias = new ArrayList<String>(5);
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("收集60个烈焰之角碎片，合成装备");
            w1.setStage(stageCache.get("团战中心(精英)"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(4);
            w2.setStage(stageCache.get("潮汐神庙"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("参与完成关卡并且无人阵亡");
            w3.setStage(stageCache.get("冰火两重天(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
    }
}
