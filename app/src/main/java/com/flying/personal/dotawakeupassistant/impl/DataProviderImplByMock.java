package com.flying.personal.dotawakeupassistant.impl;

import com.flying.personal.dotawakeupassistant.IDataProvider;
import com.flying.personal.dotawakeupassistant.model.EquipmentItem;
import com.flying.personal.dotawakeupassistant.model.GameStage;
import com.flying.personal.dotawakeupassistant.model.Hero;
import com.flying.personal.dotawakeupassistant.model.WakeUpTask1;
import com.flying.personal.dotawakeupassistant.model.WakeUpTask2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxian on 8/11/2015.
 */
public class DataProviderImplByMock implements IDataProvider {

    private List<Hero> heroes;
    private List<GameStage> stages;

    public DataProviderImplByMock() {
        heroes = new ArrayList<Hero>(10);

        {
            Hero h1 = new Hero();
            h1.setName("飞机");
            h1.setPositionType(Hero.PositionType.Back);
            h1.setAbilityType(Hero.AbilityType.Agility);
            WakeUpTask1 w1 = new WakeUpTask1();
            GameStage g1 = new GameStage();
            g1.setChapter(1);
            g1.setSequenceNo(1);
            g1.setStamina(16);
            List<String> outPut1 = new ArrayList<String>(5);
            outPut1.add("遗物");
            g1.setOutput(outPut1);
            w1.setStage(g1);
            w1.setDescription("集满60个碎片,集满60个碎片,集满60个碎片");
            h1.setTask1(w1);
            h1.setPicPath("feiji.jpg");
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            WakeUpTask2 w2 = new WakeUpTask2();
            w2.setDescription("冰龙");
            h1.setTask2(w2);
            WakeUpTask1 w3 = new WakeUpTask1();
            w3.setDescription("单挑敌法");
            h1.setTask3(w3);

            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("敏捷+10");
            item.setPath("jxfq.jpg");
            items.add(item);
            g1.setItems(items);
            heroes.add(h1);
        }

        {
            Hero h1 = new Hero();
            h1.setName("骨弓");
            h1.setPositionType(Hero.PositionType.Back);
            h1.setAbilityType(Hero.AbilityType.Agility);
            WakeUpTask1 w1 = new WakeUpTask1();
            GameStage g1 = new GameStage();
            g1.setChapter(1);
            g1.setSequenceNo(1);
            g1.setStamina(16);
            List<String> outPut1 = new ArrayList<String>(5);
            outPut1.add("遗物");
            g1.setOutput(outPut1);
            w1.setStage(g1);
            h1.setTask1(w1);
            h1.setPicPath("ic_launcher.png");
            heroes.add(h1);
        }

        {
            Hero h1 = new Hero();
            h1.setName("船长");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Strength);
            WakeUpTask1 w1 = new WakeUpTask1();
            GameStage g1 = new GameStage();
            g1.setChapter(1);
            g1.setSequenceNo(1);
            g1.setStamina(16);
            List<String> outPut1 = new ArrayList<String>(5);
            outPut1.add("遗物");
            g1.setOutput(outPut1);
            w1.setStage(g1);
            h1.setTask1(w1);
            h1.setPicPath("ic_launcher.png");
            heroes.add(h1);

            for (int i = 0; i < 30; i++)
                heroes.add(h1);
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
}
