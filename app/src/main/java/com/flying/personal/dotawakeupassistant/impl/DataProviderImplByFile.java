package com.flying.personal.dotawakeupassistant.impl;

import android.util.Log;

import com.flying.personal.dotawakeupassistant.BuiltInData;
import com.flying.personal.dotawakeupassistant.IDataProvider;
import com.flying.personal.dotawakeupassistant.model.Hero;
import com.flying.personal.dotawakeupassistant.model.HeroTag;
import com.flying.personal.dotawakeupassistant.model.WakeupSkill;
import com.flying.personal.dotawakeupassistant.util.HanyuPinyinHelper;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    private List<WakeupSkill> wakupSkills;

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

        index = index.toLowerCase();
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
            index = index.toLowerCase();
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
    public List<WakeupSkill> getHeroAffectedSkill(String heroName) {
        Hero h = getHeroByName(heroName);
        List<HeroTag> tagsOfHero = h.getTags();
        List<WakeupSkill> result = new ArrayList<WakeupSkill>(5);

        for (int i = 0; i < tagsOfHero.size(); i++) {
            for (int j = 0; j < wakupSkills.size(); j++) {
                WakeupSkill skill = wakupSkills.get(j);
                if (tagsOfHero.get(i) == skill.affectTag) {
                    result.add(skill);
                }
            }
        }

        return result;
    }

    @Override
    public void init(String[] args) {
        BuiltInData data = new BuiltInData();
        try {
            this.heroes = data.getHeroes();
            dataFilePath = args[0];

            if (isDataFileExit()) {
                Log.d(this.getClass().getName(), "Load data from file");

                SerializedData extraData = getExtraDataFromJsonFile();
                if (extraData.tags != null) {
                    Map<String, HeroTag> tagCache = data.getTagCache();
                    for (int i = 0; i < extraData.tagHeros.size(); i++) {
                        HeroTag t = extraData.tags.get(i);
                        tagCache.put(t.tagName, t);
                    }
                }

                if (extraData.tagHeros != null) {
                    Map<String, List<String>> tagHeroCache = data.getTagHeroCache();
                    Iterator<Map.Entry<String, List<String>>> entries = extraData.tagHeros.entrySet().iterator();
                    while (entries.hasNext()) {
                        Map.Entry<String, List<String>> entry = entries.next();
                        tagHeroCache.put(entry.getKey(), entry.getValue());
                    }
                }

                if (extraData != null) {
                    version = extraData.version;
                    if (extraData.heroDatas != null) {
                        for (int i = 0; i < extraData.heroDatas.size(); i++) {
                            Hero h = extraData.heroDatas.get(i);
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
            //Save tags
            Map<String, HeroTag> tagCache = data.getTagCache();
            Map<String, List<String>> tagHeroCache = data.getTagHeroCache();

            Iterator<Map.Entry<String, List<String>>> entries = tagHeroCache.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, List<String>> entry = entries.next();
                String tagName = entry.getKey();
                List<String> heroNames = entry.getValue();

                for (int i = 0; i < heroNames.size(); i++) {
                    Hero h = getHeroByName(heroNames.get(i));
                    if (h == null) {
                        Log.e(this.getClass().getName(), "Can't find this Hero:" + heroNames.get(i));
                    } else {
                        h.getTags().add(tagCache.get(tagName));
                    }
                }
            }
            //Create wakeup skill relation
            wakupSkills = new ArrayList<WakeupSkill>(heroes.size() * 2 / 3);
            for (Hero h : heroes) {
                String skillDisplayString = h.getWakeupSkillString();
                Iterator<Map.Entry<String, HeroTag>> tagEntries = tagCache.entrySet().iterator();

                while (tagEntries.hasNext()) {
                    Map.Entry<String, HeroTag> entry = tagEntries.next();
                    String tagName = entry.getKey();
                    if (skillDisplayString.contains(tagName)) {
                        WakeupSkill ws = new WakeupSkill();
                        ws.description = skillDisplayString;
                        ws.hero = h;
                        ws.affectTag = entry.getValue();
                        break;
                    }
                }
            }
            //create index for quick search
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

        data.clear();
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
        public List<Hero> heroDatas;
        public List<HeroTag> tags;
        public Map<String, List<String>> tagHeros;
    }
}
