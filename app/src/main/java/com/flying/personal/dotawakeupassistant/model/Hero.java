package com.flying.personal.dotawakeupassistant.model;

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

    public List<String> GenerateIndex()
    {
        List<String> result = new ArrayList<>(8);
        //TODO Index create. Including full/first characters for Chinese name, short name, English name, alias.

        return result;
    }

    public String getName() {
        return name;
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
}
