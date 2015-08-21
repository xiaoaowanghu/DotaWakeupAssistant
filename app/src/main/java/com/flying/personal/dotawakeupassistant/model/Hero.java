package com.flying.personal.dotawakeupassistant.model;

import com.flying.personal.dotawakeupassistant.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxian on 8/10/2015.
 */
public class Hero {
    protected String name;
    protected List<String> alias;
    protected WakeUpTask1 task1;
    protected WakeUpTask2 task2;
    protected WakeUpTask1 task3;
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

    public List<String> GenerateIndex() {
        List<String> result = new ArrayList<>(8);
        //TODO Index create. Including full/first characters for Chinese name, short name, English name, alias.

        return result;
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

    public WakeUpTask1 getTask1() {
        return task1;
    }

    public void setTask1(WakeUpTask1 task1) {
        this.task1 = task1;
    }

    public WakeUpTask2 getTask2() {
        return task2;
    }

    public void setTask2(WakeUpTask2 task2) {
        this.task2 = task2;
    }

    public WakeUpTask1 getTask3() {
        return task3;
    }

    public void setTask3(WakeUpTask1 task3) {
        this.task3 = task3;
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

    public int getPostionDisplayNameIndex() {
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
