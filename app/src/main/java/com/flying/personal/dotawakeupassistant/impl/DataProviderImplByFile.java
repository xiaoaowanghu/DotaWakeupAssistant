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

    private Map<String, EquipmentItem> getEquipCache() {
        Map<String, EquipmentItem> equipCache = new HashMap<>(30);
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("冰甲卷轴");
            item.setPicPath("bjjz.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("辉耀卷轴");
            item.setPicPath("hyjz.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("大炮卷轴");
            item.setPicPath("dpjz.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("龙心卷轴");
            item.setPicPath("lxjz.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("撒旦卷轴");
            item.setPicPath("sdjz.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("恶魔刀锋");
            item.setPicPath("emdf.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("远古遗物");
            item.setPicPath("ygyw.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("永恒冰柱");
            item.setPicPath("yhbz.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("银月长矛");
            item.setPicPath("yycm.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem(true);
            item.setDisplayName("鹰语指环");
            item.setPicPath("yyzh.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("巫师之冠");
            item.setPicPath("wszg.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem(true);
            item.setDisplayName("泰坦战斧");
            item.setPicPath("ttzf.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem(true);
            item.setDisplayName("水晶球");
            item.setPicPath("sjq.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("极限球");
            item.setPicPath("jxq.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("红宝石吊坠");
            item.setPicPath("hbsdz.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("禁卫军胸甲");
            item.setPicPath("jwjxj.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("恶魔之失");
            item.setPicPath("emzs.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem(true);
            item.setDisplayName("蓝宝石法杖");
            item.setPicPath("lbsfz.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem(true);
            item.setDisplayName("麒麟弯刀");
            item.setPicPath("qlwd.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("静息项链");
            item.setPicPath("jxxl.jpg");
            equipCache.put(item.getDisplayName(), item);
        }

        return equipCache;
    }

    private Map<String, GameStage> getStageCache() {
        Map<String, EquipmentItem> equipCache = getEquipCache();
        Map<String, GameStage> stageCache = new HashMap<String, GameStage>(56);
        //=============================11===================================
        {
            GameStage gs = new GameStage();
            gs.setName("双头(精英)");
            gs.setChapter(11);
            gs.setSequenceNo(1);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("冰甲卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("以一敌百(精英)");
            gs.setChapter(11);
            gs.setSequenceNo(2);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("辉耀卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("黑暗者(精英)");
            gs.setChapter(11);
            gs.setSequenceNo(3);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("大炮卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("决死(精英)");
            gs.setChapter(11);
            gs.setSequenceNo(4);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("震荡(精英)");
            gs.setChapter(11);
            gs.setSequenceNo(5);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("龙心"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("斩杀(精英)");
            gs.setChapter(11);
            gs.setSequenceNo(6);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("撒旦卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("出口(精英)");
            gs.setChapter(11);
            gs.setSequenceNo(7);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("恶魔刀锋"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        //==============================12====================================
        {
            GameStage gs = new GameStage();
            gs.setName("火药(精英)");
            gs.setChapter(12);
            gs.setSequenceNo(1);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("大炮卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("火药");
            gs.setChapter(12);
            gs.setSequenceNo(1);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("大炮卷轴"));
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
        {
            GameStage gs = new GameStage();
            gs.setName("冰火两重天");
            gs.setChapter(12);
            gs.setSequenceNo(2);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("沉默");
            gs.setChapter(12);
            gs.setSequenceNo(3);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("沉默(精英)");
            gs.setChapter(12);
            gs.setSequenceNo(3);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("神殿守卫(精英)");
            gs.setChapter(12);
            gs.setSequenceNo(4);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("神殿守卫");
            gs.setChapter(12);
            gs.setSequenceNo(4);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("一姐");
            gs.setChapter(12);
            gs.setSequenceNo(5);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("撒旦卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("一姐(精英)");
            gs.setChapter(12);
            gs.setSequenceNo(5);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("撒旦卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("食人(精英)");
            gs.setChapter(12);
            gs.setSequenceNo(6);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("龙心卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("食人");
            gs.setChapter(12);
            gs.setSequenceNo(6);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("龙心卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("游走");
            gs.setChapter(12);
            gs.setSequenceNo(7);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("远古卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("游走(精英)");
            gs.setChapter(12);
            gs.setSequenceNo(7);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("远古遗物"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("近卫(精英)");
            gs.setChapter(12);
            gs.setSequenceNo(8);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("辉耀卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("近卫");
            gs.setChapter(12);
            gs.setSequenceNo(8);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("辉耀卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        //==============================13=====================================
        {
            GameStage gs = new GameStage();
            gs.setName("沼泽边缘(精英)");
            gs.setChapter(13);
            gs.setSequenceNo(1);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("永恒冰柱"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("沼泽边缘");
            gs.setChapter(13);
            gs.setSequenceNo(1);
            gs.setStamina(12);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("永恒冰柱"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("河道相逢");
            gs.setChapter(13);
            gs.setSequenceNo(2);
            gs.setStamina(12);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("银月长矛"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("河道相逢(精英)");
            gs.setChapter(13);
            gs.setSequenceNo(2);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("银月长矛"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("团战中心(精英)");
            gs.setChapter(13);
            gs.setSequenceNo(3);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("鹰语指环"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("团战中心");
            gs.setChapter(13);
            gs.setSequenceNo(3);
            gs.setStamina(12);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("鹰语指环"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("遗骨小岛");
            gs.setChapter(13);
            gs.setSequenceNo(4);
            gs.setStamina(12);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("巫师之冠"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("遗骨小岛(精英)");
            gs.setChapter(13);
            gs.setSequenceNo(4);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("巫师之冠"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("亡语森林(精英)");
            gs.setChapter(13);
            gs.setSequenceNo(5);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("龙心卷轴"));
            }
            {
                items.add(equipCache.get("泰坦战斧"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("亡语森林");
            gs.setChapter(13);
            gs.setSequenceNo(5);
            gs.setStamina(12);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("龙心卷轴"));
            }
            {
                items.add(equipCache.get("泰坦战斧"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("泰坦遗迹");
            gs.setChapter(13);
            gs.setSequenceNo(6);
            gs.setStamina(12);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("水晶球"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("泰坦遗迹(精英)");
            gs.setChapter(13);
            gs.setSequenceNo(6);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("水晶球"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        //==============================14======================================
        {
            GameStage gs = new GameStage();
            gs.setName("柳暗花明(精英)");
            gs.setChapter(14);
            gs.setSequenceNo(1);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("巫师之冠"));
            }
            {
                items.add(equipCache.get("极限球"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("柳暗花明");
            gs.setChapter(14);
            gs.setSequenceNo(1);
            gs.setStamina(12);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("巫师之冠"));
            }
            {
                items.add(equipCache.get("极限球"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("瀑布崖");
            gs.setChapter(14);
            gs.setSequenceNo(2);
            gs.setStamina(12);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("红宝石吊坠"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("瀑布崖(精英)");
            gs.setChapter(14);
            gs.setSequenceNo(2);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("红宝石吊坠"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("山腰相逢(精英)");
            gs.setChapter(14);
            gs.setSequenceNo(3);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("禁卫军胸甲"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("山腰相逢");
            gs.setChapter(14);
            gs.setSequenceNo(3);
            gs.setStamina(12);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("禁卫军胸甲"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("乱石山坡");
            gs.setChapter(14);
            gs.setSequenceNo(4);
            gs.setStamina(12);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("远古遗物"));
            }
            {
                items.add(equipCache.get("恶魔之失"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("乱石山坡(精英)");
            gs.setChapter(14);
            gs.setSequenceNo(4);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("远古遗物"));
            }
            {
                items.add(equipCache.get("恶魔之失"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("龙语(精英)");
            gs.setChapter(14);
            gs.setSequenceNo(5);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("蓝宝石法杖"));
            }
            {
                items.add(equipCache.get("撒旦卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("龙语");
            gs.setChapter(14);
            gs.setSequenceNo(5);
            gs.setStamina(12);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("蓝宝石法杖"));
            }
            {
                items.add(equipCache.get("撒旦卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("巨龙巢穴(精英)");
            gs.setChapter(14);
            gs.setSequenceNo(6);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("麒麟弯刀"));
            }
            {
                items.add(equipCache.get("辉耀卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("巨龙巢穴");
            gs.setChapter(14);
            gs.setSequenceNo(6);
            gs.setStamina(12);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("麒麟弯刀"));
            }
            {
                items.add(equipCache.get("辉耀卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }


        //=============================================Special Stage======================================
        {
            SpecialStage gs = new SpecialStage();
            gs.setName("诅咒之城(魔免)");
            gs.setStamina(6);
            gs.setOccurDays(SpecialStage.Weekday.Monday | SpecialStage.Weekday.Thursday | SpecialStage.Weekday.Sunday);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("水晶球"));
            }
            {
                items.add(equipCache.get("巫师之冠"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            SpecialStage gs = new SpecialStage();
            gs.setName("折戟山谷(物免)");
            gs.setStamina(6);
            gs.setOccurDays(SpecialStage.Weekday.Tuesday | SpecialStage.Weekday.Friday | SpecialStage.Weekday.Sunday);
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
                items.add(equipCache.get("静息项链"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
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
            gs.setName("矮人军工厂");
            gs.setStamina(6);
            gs.setOccurDays(SpecialStage.Weekday.Tuesday | SpecialStage.Weekday.Thursday | SpecialStage.Weekday.Saturday | SpecialStage.Weekday.Sunday);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }

        return stageCache;
    }

    private void generateData() {
        Map<String, GameStage> stageCache = getStageCache();

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
            w3.setDescription("独自完成关卡");
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
            w1.setStage(stageCache.get("泰坦遗迹(精英)"));

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
        {
            Hero h1 = new Hero();
            h1.setName("凤凰");
            h1.setPositionType(Hero.PositionType.Middle);
            h1.setAbilityType(Hero.AbilityType.Strength);
            h1.setPortraitPath("fenghuang.jpg");
            h1.setPicPath("fenghuang_big.jpg");
            h1.setWakeupSkill("敢死队队友增加270点智力");
            List<String> alias = new ArrayList<String>(5);
            alias.add("烧烤");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("收集60个凤羽冠碎片，合成装备");
            w1.setStage(stageCache.get("山腰相逢(精英)"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("折戟山谷(物免)"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，造成35w伤害");
            w3.setStage(stageCache.get("乱石山坡(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("刚背猪");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Strength);
            h1.setPortraitPath("gangbei.jpg");
            h1.setPicPath("gangbei_big.jpg");
            h1.setWakeupSkill("针刺扫射可造成额外伤害，并且每次叠加造成的额外伤害提升540点");
            List<String> alias = new ArrayList<String>(5);
            alias.add("刚猪");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("收集60个防暴腰铠碎片，合成装备");
            w1.setStage(stageCache.get("山腰相逢(精英)"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("诅咒之城(魔免)"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("独自完成关卡");
            w3.setStage(stageCache.get("神殿守卫"));
            tasks[2] = w3;

            heroes.add(h1);
        }

        {
            Hero h1 = new Hero();
            h1.setName("巫医");
            h1.setPositionType(Hero.PositionType.Back);
            h1.setAbilityType(Hero.AbilityType.Intelligence);
            h1.setPortraitPath("wuyi.jpg");
            h1.setPicPath("wuyi_big.jpg");
            h1.setWakeupSkill("所有药剂师队友增加1674点魔法");
            List<String> alias = new ArrayList<String>(2);
            alias.add("51");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("收集60个巫毒面具碎片，合成装备");
            w1.setStage(stageCache.get("沼泽边缘(精英)"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("诅咒之城(魔免)"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡且无人阵亡");
            w3.setStage(stageCache.get("乱石山坡(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("火女");
            h1.setPositionType(Hero.PositionType.Middle);
            h1.setAbilityType(Hero.AbilityType.Intelligence);
            h1.setPortraitPath("lina.jpg");
            h1.setPicPath("lina_big.jpg");
            h1.setWakeupSkill("普通攻击增加45点能量");
            List<String> alias = new ArrayList<String>(2);
            alias.add("lina");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("收集60个和式战服碎片，合成装备");
            w1.setStage(stageCache.get("沼泽边缘(精英)"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("女武神的对决"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("亲自消灭小黑");
            w3.setStage(stageCache.get("山腰相逢(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("恶魔巫师");
            h1.setPositionType(Hero.PositionType.Middle);
            h1.setAbilityType(Hero.AbilityType.Intelligence);
            h1.setPortraitPath("lion.jpg");
            h1.setPicPath("lion_big.jpg");
            h1.setWakeupSkill("增加变小黑鸭技能，可吸取对方能量并降低魔抗");
            List<String> alias = new ArrayList<String>(2);
            alias.add("lion");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("收集60个小黑鸭权杖碎片，合成装备");
            w1.setStage(stageCache.get("团战中心(精英)"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("诅咒之城(魔免)"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，并造成35w伤害");
            w3.setStage(stageCache.get("乱石山坡(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("痛苦女王");
            h1.setPositionType(Hero.PositionType.Middle);
            h1.setAbilityType(Hero.AbilityType.Intelligence);
            h1.setPortraitPath("qdp.jpg");
            h1.setPicPath("qdp_big.jpg");
            h1.setWakeupSkill("受到男性英雄伤害降低15.64%");
            List<String> alias = new ArrayList<String>(2);
            alias.add("qdp");
            alias.add("sm");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个{0}碎片，合成装备", "吸血鬼之触"));
            w1.setStage(stageCache.get("山腰相逢(精英)"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("女武神的对决"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，并亲自消灭冰女");
            w3.setStage(stageCache.get("遗骨小岛(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
    }

}
