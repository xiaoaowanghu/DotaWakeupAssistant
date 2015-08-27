package com.flying.personal.dotawakeupassistant.impl;

import android.util.Log;

import com.flying.personal.dotawakeupassistant.IDataProvider;
import com.flying.personal.dotawakeupassistant.model.EquipmentItem;
import com.flying.personal.dotawakeupassistant.model.GameStage;
import com.flying.personal.dotawakeupassistant.model.Hero;
import com.flying.personal.dotawakeupassistant.model.WakeUpRepeatableTask;
import com.flying.personal.dotawakeupassistant.model.WakeUpTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxian on 8/11/2015.
 */
public class DataProviderImplByMock implements IDataProvider {

    private List<Hero> heroes;
    private List<GameStage> stages;

    @Override
    public void save(String[] args) {
        try {
            com.google.gson.stream.JsonWriter jw = new com.google.gson.stream.JsonWriter(new FileWriter(args[0]));
            Gson gson = new Gson();
            Type heroListType = new TypeToken<List<Hero>>() {
            }.getType();
            gson.toJson(heroes, heroes.getClass(), jw);
            jw.close();
        } catch (Exception e) {
            Log.e(this.getClass().getName(), Log.getStackTraceString(e));
        }
    }

    @Override
    public void visitHero(Hero hero) {

    }

    public DataProviderImplByMock() {
        heroes = new ArrayList<Hero>(10);
        Hero hero1;
        Hero hero2;
        {
            Hero h1 = new Hero();
            h1.setName("幻影刺客");
            h1.setPositionType(Hero.PositionType.Back);
            h1.setAbilityType(Hero.AbilityType.Agility);
            h1.setPortraitPath("pa.jpg");
            h1.setPicPath("pa_big.jpg");

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);

            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("集满60个碎片,集满60个碎片,集满60个碎片");
            GameStage g1 = new GameStage();
            g1.setChapter(1);
            g1.setSequenceNo(1);
            g1.setStamina(16);
            w1.setStage(g1);

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            w2.setDescription("冰龙");
            tasks[1] = w2;

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("单挑敌法");
            tasks[2] = w3;

            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("敏捷+10");
            item.setPicPath("jxfq.jpg");
            items.add(item);
            g1.setOutputItems(items);
            heroes.add(h1);
            hero1 = h1;
        }
        {
            Hero h1 = new Hero();
            h1.setName("圣堂刺客");
            h1.setPositionType(Hero.PositionType.Back);
            h1.setAbilityType(Hero.AbilityType.Agility);
            h1.setPortraitPath("ta.jpg");
            h1.setPicPath("ta_big.jpg");

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);

            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("集满60个碎片,集满60个碎片,集满60个碎片");
            GameStage g1 = new GameStage();
            g1.setChapter(1);
            g1.setSequenceNo(1);
            g1.setStamina(16);
            w1.setStage(g1);

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            w2.setDescription("时间之穴");
            tasks[1] = w2;

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("单挑敌法");
            tasks[2] = w3;

            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("敏捷+10");
            item.setPicPath("jxfq.jpg");
            items.add(item);
            g1.setOutputItems(items);
            heroes.add(h1);
            hero2 = h1;
        }

        for (int i = 0; i < 20; i++) {
            heroes.add(hero1);
            heroes.add(hero2);
        }

    }

    @Override
    public Hero getHeroByName(String heroName) {
        for (Hero h : heroes) {
            if (h.getName().equalsIgnoreCase(heroName))
                return h;
        }

        return null;
    }

    @Override
    public List<Hero> getHeroesByPosition(Hero.PositionType position) {
        return null;
    }

    @Override
    public List<Hero> getHeroesByAbilityType(Hero.AbilityType abilityType) {
        return null;
    }

    @Override
    public List<Hero> getAllHeroes() {
        return heroes;
    }

    @Override
    public List<Hero> getMatchedHeroes(String index) {
        return heroes;
    }

    @Override
    public int getTotalHeroCount() {
        return heroes.size();
    }

    @Override
    public void init(String[] args) {
    }


    @Override
    public List<Hero> getMatchedHeroes(String index, Hero.PositionType position) {
        return heroes;
    }
}
