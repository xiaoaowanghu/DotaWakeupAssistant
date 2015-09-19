package com.flying.personal.dotawakeupassistant.model;

import com.flying.personal.dotawakeupassistant.R;
import com.flying.personal.dotawakeupassistant.util.CommonUtility;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxian on 8/10/2015.
 */
public class Hero implements IResource {
    protected String name;
    protected List<String> alias;
    protected WakeUpTask[] tasks;
    protected PositionType positionType;
    protected AbilityType abilityType;
    protected String picPath;
    protected String portraitPath;
    protected List<HeroTag> tags;
    protected WakeupSkill wakeupSkill;
    protected boolean isBuiltInData = true;

    public WakeupSkill getWakeupSkill() {
        return wakeupSkill;
    }

    public void setWakeupSkill(WakeupSkill wakeupSkill) {
        this.wakeupSkill = wakeupSkill;
        this.wakeupSkill.hero = this;
    }

    public Hero() {
        tags = new ArrayList<HeroTag>(5);
    }

    public List<HeroTag> getTags() {
        return tags;
    }

    @Override
    public boolean isBuiltInData() {
        return isBuiltInData;
    }

    public void setIsBuiltInData(boolean isBuiltInData) {
        this.isBuiltInData = isBuiltInData;
    }

    public void setTags(List<HeroTag> tags) {
        this.tags = tags;
    }

    public enum PositionType {
        Front, Middle, Back
    }

    public String getPortraitPath() {
        return portraitPath;
    }

    public void setPortraitPath(String portraitPath) {
        this.portraitPath = portraitPath;
    }

    public enum AbilityType {
        Strength, Agility, Intelligence
    }

    public String getName() {
        return name;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public WakeUpTask[] getTasks() {
        return tasks;
    }

    public void setTasks(WakeUpTask[] tasks) {
        this.tasks = tasks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAlias() {
        return alias;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    public AbilityType getAbilityType() {
        return abilityType;
    }

    public void setAbilityType(AbilityType abilityType) {
        this.abilityType = abilityType;
    }

    public PositionType getPositionType() {
        return positionType;
    }

    public int getPositionDisplayNameIndex() {
        switch (positionType) {
            case Front:
                return R.string.position_front;
            case Back:
                return R.string.position_back;
            case Middle:
                return R.string.position_middle;
            default:
                return -1;
        }
    }

    public int getAbilityDisplayNameIndex() {
        switch (abilityType) {
            case Strength:
                return R.string.ability_type_strength;
            case Agility:
                return R.string.ablility_type_Agility;
            case Intelligence:
                return R.string.ablility_type_Intelligence;
            default:
                return -1;
        }
    }

    public void setPositionType(PositionType positionType) {
        this.positionType = positionType;
    }

    public static class HeroDeserializeAdapter implements JsonDeserializer<Hero> {
        @Override
        public Hero deserialize(JsonElement jsonElement, Type type,
                                JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject object = jsonElement.getAsJsonObject();
            Hero result = new Hero();
            CommonUtility.deserializeNormalField(result, Hero.class, object, jsonDeserializationContext, true);
            return result;
        }
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.Hero;
    }
}
