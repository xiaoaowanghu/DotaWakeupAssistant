package com.flying.personal.dotawakeupassistant.model;

import com.flying.personal.dotawakeupassistant.R;

import java.util.List;

/**
 * Created by wangxian on 8/10/2015.
 */
public class Hero {
    protected String name;
    protected List<String> alias;
    protected WakeUpTask[] tasks;
    protected PositionType positionType;
    protected AbilityType abilityType;
    protected String picPath;

    public enum PositionType {
        Front, Middle, Back
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
}
