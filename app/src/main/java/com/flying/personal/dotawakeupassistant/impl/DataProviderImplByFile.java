package com.flying.personal.dotawakeupassistant.impl;

import android.util.Log;

import com.flying.personal.dotawakeupassistant.BuiltInData;
import com.flying.personal.dotawakeupassistant.IDataProvider;
import com.flying.personal.dotawakeupassistant.model.GameStage;
import com.flying.personal.dotawakeupassistant.model.Hero;
import com.flying.personal.dotawakeupassistant.model.HeroTag;
import com.flying.personal.dotawakeupassistant.model.WakeUpTask;
import com.flying.personal.dotawakeupassistant.model.WakeupSkill;
import com.flying.personal.dotawakeupassistant.util.FileUtility;
import com.flying.personal.dotawakeupassistant.util.HanyuPinyinHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxian on 8/23/2015.
 */
public class DataProviderImplByFile implements IDataProvider {
    //adb command
    //cd C:/Users/wangxian/AppData/Local/Android/sdk/platform-tools/
    //adb pull /data/data/com.flying.personal.dotawakeupassistant/files/data.json z:/
    //adb push z:/data.json /data/data/com.flying.personal.dotawakeupassistant/files/
    private List<Hero> heroes;
    private Map<Hero, List<String>> searchIndexs;
    private List<WakeupSkill> wakupAffectSkills;
    private Map<String, List<String>> tagHeroes;
    private String appDir;
    private double dataFileVersion = 0.5;
    private String updateURL;

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

        if (tagsOfHero == null || tagsOfHero.size() == 0)
            return null;

        List<WakeupSkill> result = new ArrayList<WakeupSkill>(5);

        for (int i = 0; i < tagsOfHero.size(); i++) {
            for (int j = 0; j < wakupAffectSkills.size(); j++) {
                WakeupSkill skill = wakupAffectSkills.get(j);
                if (tagsOfHero.get(i).tagName.equalsIgnoreCase(skill.affectTag.tagName)) {
                    result.add(skill);
                }
            }
        }

        return result;
    }

    @Override
    public List<String> getHeroesByTag(String tagName) {
        return tagHeroes.get(tagName);
    }

    @Override
    public void init(String[] args) {
        SerializedData extraData = null;
        appDir = args[0];
        try {
            BuiltInData builtInData = new BuiltInData();
            this.updateURL = "http://www.baidu.com";

            if (isDataFileExit()) {
                Log.d(this.getClass().getName(), "Load data from file");
                extraData = getExtraDataFromJsonFile();
            }

            if (extraData != null) {
                //必须按顺序来load: version, url, tag, tagHero, hero
                this.dataFileVersion = extraData.version;

                if (extraData.updateURL != null) {
                    this.updateURL = extraData.updateURL;
                }

                if (extraData.tags != null) {
                    Map<String, HeroTag> tagCache = builtInData.getTagCache();
                    for (int i = 0; i < extraData.tagHeroes.size(); i++) {
                        HeroTag t = extraData.tags.get(i);
                        tagCache.put(t.tagName, t);
                    }
                }

                if (extraData.tagHeroes != null) {
                    Map<String, List<String>> tagHeroCache = builtInData.getTagHeroCache();
                    Iterator<Map.Entry<String, List<String>>> entries = extraData.tagHeroes.entrySet().iterator();
                    while (entries.hasNext()) {
                        Map.Entry<String, List<String>> entry = entries.next();
                        tagHeroCache.put(entry.getKey(), entry.getValue());
                    }
                }

                //hero must be loaded after updating tag from external files
                this.heroes = builtInData.getHeroes();

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
                            heroes.add(0, h);
                        }
                    }
                }
            } else {
                this.heroes = builtInData.getHeroes();
            }

            //Save tags to hero
            Map<String, HeroTag> tagCache = builtInData.getTagCache();
            tagHeroes = builtInData.getTagHeroCache();

            Iterator<Map.Entry<String, List<String>>> entries = tagHeroes.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, List<String>> entry = entries.next();
                String tagName = entry.getKey();
                List<String> heroNames = entry.getValue();

                for (int i = 0; i < heroNames.size(); i++) {
                    Hero h = getHeroByName(heroNames.get(i));
                    if (h == null) {
                        Log.d(this.getClass().getName(), "Can't find this Hero:" + heroNames.get(i));
                    } else {
                        h.getTags().add(tagCache.get(tagName));
                    }
                }
            }
            //Create wakeup skill relation
            wakupAffectSkills = new ArrayList<WakeupSkill>(heroes.size() * 2 / 3);
            for (Hero h : heroes) {
                WakeupSkill ws = h.getWakeupSkill();
                if (h.getWakeupSkill() != null && ws.abilitiesAffected != null) {
                    wakupAffectSkills.add(ws);
                }
            }
            //create index for quick search
            searchIndexs = new HashMap<Hero, List<String>>(heroes.size());

            for (Hero h : heroes) {
                List<String> indexes = new ArrayList<String>(20);
                indexes.addAll(HanyuPinyinHelper.getInstance().getHanziT9Index(h.getName()));

                List<String> alias = h.getAlias();
                if (alias != null && alias.size() > 0) {
                    for (String a : alias) {
                        indexes.addAll(HanyuPinyinHelper.getInstance().getHanziT9Index(a));
                    }
                }

                if (alias == null)
                    Log.d(this.getClass().getName(), h.getName() + " alias is null");

                searchIndexs.put(h, indexes);
            }

            if (searchIndexs.size() != heroes.size())
                Log.d(this.getClass().getName(), "searchIndexs.size() != heroes.size()");

        } catch (Exception e) {
            Log.e(this.getClass().getName(), Log.getStackTraceString(e));
        }
    }

    private SerializedData getExtraDataFromJsonFile() {
        SerializedData result = null;
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(getDataFilePath()), "UTF-8");
            Gson gson = new GsonBuilder().registerTypeAdapter(GameStage.class, new GameStage.StageDeserializeAdapter())
                    .registerTypeAdapter(WakeUpTask.class, new WakeUpTask.TaskDeserializeAdapter())
                    .registerTypeAdapter(Hero.class, new Hero.HeroDeserializeAdapter()).create();
            result = gson.fromJson(isr, SerializedData.class);
        } catch (Exception e) {
            Log.e(this.getClass().getName(), Log.getStackTraceString(e));
            //DeleteAllFiles
            File dir = new File(appDir);
            File[] files = dir.listFiles();
            for (File f : files) {
                FileUtility.deleteAllFiles(f);
            }
        }
        return result;
    }

    @Override
    public void save(String[] args) {
        SerializedData s = new SerializedData();
        s.version = 1.1;
        s.heroDatas = heroes;
        s.tagHeroes = this.tagHeroes;
        s.tags = new ArrayList<HeroTag>(40);

        for (int i = 0; i < heroes.size(); i++) {
            Hero h = heroes.get(i);
            for (int j = 0; j < h.getTags().size(); j++) {
                boolean isFind = false;

                for (HeroTag t : s.tags) {
                    if (t.tagName.equalsIgnoreCase(h.getTags().get(j).tagName)) {
                        isFind = true;
                        break;
                    }
                }
                if (!isFind) {
                    s.tags.add(h.getTags().get(j));
                }
            }
        }

        s.updateURL = "http://www.baidu.com";

        try {
            Log.e(this.getClass().getName(), getDataFilePath());
            String path = args != null && args.length > 0 ? getDataFilePath() : "/sdcard/data.json";
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(path), "UTF-8");
            Gson gson = new Gson();
            gson.toJson(s, outputStreamWriter);
            outputStreamWriter.flush();
            outputStreamWriter.close();
        } catch (Exception e) {
            Log.e(this.getClass().getName(), Log.getStackTraceString(e));
        }
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

    private String getDataFilePath() {
        return appDir + File.separator + "data.json";
    }

    private boolean isDataFileExit() {
        File f = new File(getDataFilePath());
        return f.exists();
    }

    @Override
    public String getDataVersion() {
        return String.valueOf(dataFileVersion);
    }

    public String getUpdateURL() {
        return updateURL;
    }

    public class SerializedData {
        public double version = 1;
        public List<HeroTag> tags;
        public Map<String, List<String>> tagHeroes;
        public List<Hero> heroDatas;
        public String updateURL;

    }
}
