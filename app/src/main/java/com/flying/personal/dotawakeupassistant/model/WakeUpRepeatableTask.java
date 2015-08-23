package com.flying.personal.dotawakeupassistant.model;

/**
 * Created by wangxian on 8/10/2015.
 */
public class WakeUpRepeatableTask extends WakeUpTask {
    private int executeTimes = 2;

    public int GetStaminaSpend() {
        return stage.getStamina() * executeTimes;
    }

    public int getExecuteTimes() {
        return executeTimes;
    }

    public void setExecuteTimes(int executeTimes) {
        this.executeTimes = executeTimes;
    }

    @Override
    public SpecialStage getStage() {
        return (SpecialStage) stage;
    }

    public void setStage(SpecialStage stage) {
        this.stage = stage;
    }
}
