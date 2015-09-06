package com.flying.personal.dotawakeupassistant;

import com.flying.personal.dotawakeupassistant.model.Hero;
import com.flying.personal.dotawakeupassistant.model.WakeupSkill;

import java.util.List;
import java.util.Map;

/**
 * Created by wangxian on 8/10/2015.
 */
public interface IDataProvider {
    List<Hero> getAllHeroes();

    List<Hero> getMatchedHeroes(String index, Map<Hero, String> matchedIndex);

    List<Hero> getMatchedHeroes(String index, Hero.PositionType position, Map<Hero, String> matchedIndex);

    int getTotalHeroCount();

    Hero getHeroByName(String heroName);

    List<Hero> getHeroesByPosition(Hero.PositionType position);

    List<Hero> getHeroesByAbilityType(Hero.AbilityType abilityType);

    List<WakeupSkill> getHeroAffectedSkill(String heroName);

    void init(String[] args);

    void save(String[] args);

    void visitHero(Hero hero);

    double getVersion();
}
