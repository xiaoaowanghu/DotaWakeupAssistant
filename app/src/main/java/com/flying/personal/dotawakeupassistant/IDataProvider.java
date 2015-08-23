package com.flying.personal.dotawakeupassistant;

import com.flying.personal.dotawakeupassistant.model.Hero;

import java.util.List;

/**
 * Created by wangxian on 8/10/2015.
 */
public interface IDataProvider {
    List<Hero> getAllHeroes();

    List<Hero> getMatchedHeroes(String index);

    int getTotalHeroCount();

    Hero getHeroByName(String heroName);

    List<Hero> getHeroesByPosition(Hero.PositionType position);

    List<Hero> getHeroesByAbilityType(Hero.AbilityType abilityType);

    void init(String[] args);

    void save(String[] args);

    void visitHero(Hero hero);
}
