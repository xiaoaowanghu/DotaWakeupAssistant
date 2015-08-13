package com.flying.personal.dotawakeupassistant;

import com.flying.personal.dotawakeupassistant.model.Hero;

import java.util.List;

/**
 * Created by wangxian on 8/10/2015.
 */
public interface IDataProvider {
    List<Hero> getAllHeroes();

    List<Hero> GetMatchedHeroes(String index);

    int getTotalHeroCount();
}
