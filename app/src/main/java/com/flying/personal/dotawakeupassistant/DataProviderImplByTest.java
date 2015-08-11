package com.flying.personal.dotawakeupassistant;

import com.flying.personal.dotawakeupassistant.model.GameStage;
import com.flying.personal.dotawakeupassistant.model.Hero;
import com.flying.personal.dotawakeupassistant.model.WakeUpTask1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxian on 8/11/2015.
 */
public class DataProviderImplByTest implements IDataProvider {

    private List<Hero> heros;
    private List<GameStage> stages;

    public DataProviderImplByTest()
    {
        heros = new ArrayList<Hero>(10);
        Hero h1 = new Hero();
        h1.setName("小黑");
        WakeUpTask1 w1 = new WakeUpTask1();
        
    }

    @Override
    public List<Hero> getAllHeros() {
        return null;
    }

    @Override
    public List<Hero> GetMatchedHeros(String index) {
        return null;
    }
}
