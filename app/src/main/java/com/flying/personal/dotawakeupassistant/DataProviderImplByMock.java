package com.flying.personal.dotawakeupassistant;

import com.flying.personal.dotawakeupassistant.model.GameStage;
import com.flying.personal.dotawakeupassistant.model.Hero;
import com.flying.personal.dotawakeupassistant.model.WakeUpTask1;

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
            h1.setName("鲜还阿");
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
    public List<Hero> getAllHeroes() {
        return heroes;
    }

    @Override
    public List<Hero> GetMatchedHeroes(String index) {
        return heroes;
    }

    @Override
    public int getTotalHeroCount() {
        return heroes.size();
    }
}
