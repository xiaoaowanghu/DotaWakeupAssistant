package com.flying.personal.dotawakeupassistant.model;

/**
 * Created by wangxian on 8/10/2015.
 */
public class WakeUpTask2 {
    private int executeTimes = 2;
    private SpecialStage stage;

    public int GetStaminaSpend() {
        return stage.getStamina() * executeTimes;
    }
}
