package com.flying.personal.dotawakeupassistant.model;

import java.util.List;

/**
 * Created by wangxian on 8/10/2015.
 */
public class GameStage {
    protected int chapter;
    protected String name;
    protected int sequenceNo;
    protected int stamina;
    protected List<String> output;
    protected List<EquipmentItem> items;

    public int getChapter() {
        return chapter;
    }

    public int getSequenceNo() {
        return sequenceNo;
    }

    public int getStamina() {
        return stamina;
    }

    public List<String> getOutput() {
        return output;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public void setSequenceNo(int sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public void setOutput(List<String> output) {
        this.output = output;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EquipmentItem> getItems() {
        return items;
    }

    public void setItems(List<EquipmentItem> items) {
        this.items = items;
    }
}
