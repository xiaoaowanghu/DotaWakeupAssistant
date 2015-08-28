package com.flying.personal.dotawakeupassistant.model;

import java.util.List;

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

    @Override
    public String getDisplayInfo() {
        String result = "完成" + stage.getName() + executeTimes + "次";
        List<Integer> weekDayIndex = getStage().taskWeekDayIndex();
        for (int i = 0; i < weekDayIndex.size(); i++) {
            String dayIndex = null;
            if (i == 0) {
                result += " 星期";
                dayIndex = "日";
            } else {
                dayIndex = String.valueOf(i);
            }
            if (i == weekDayIndex.size() - 1) {
                result += dayIndex + " 可执行";
            } else {
                result += dayIndex + ",";
            }
        }
        return result;
    }
}
