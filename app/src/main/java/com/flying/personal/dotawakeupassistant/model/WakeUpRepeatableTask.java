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

    @Override
    public String getDisplayInfo() {
        String result = "完成" + stage.getName() + executeTimes + "次";
        List<Integer> weekDayIndex = getStage().taskWeekDayIndex();

        if (weekDayIndex.size() > 0) {
            result += " 星期";

            for (int i = 0; i < weekDayIndex.size(); i++) {
                int dayIndex = weekDayIndex.get(i);
                dayIndex++;
                result += String.valueOf(dayIndex) + ",";
            }
            result = result.substring(0, result.length() - 1).replace("7", "日") + " 开放";
        }

        return result;
    }
}
