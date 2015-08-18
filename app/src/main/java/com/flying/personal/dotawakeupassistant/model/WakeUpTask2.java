package com.flying.personal.dotawakeupassistant.model;

/**
 * Created by wangxian on 8/10/2015.
 */
public class WakeUpTask2 {
    private int executeTimes = 2;
    private SpecialStage stage;
    private String description;

    public int GetStaminaSpend() {
        return stage.getStamina() * executeTimes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExecuteTimes() {
        return executeTimes;
    }

    public void setExecuteTimes(int executeTimes) {
        this.executeTimes = executeTimes;
    }

    public SpecialStage getStage() {
        return stage;
    }

    public void setStage(SpecialStage stage) {
        this.stage = stage;
    }
}
