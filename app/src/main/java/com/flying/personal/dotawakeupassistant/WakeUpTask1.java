package com.flying.personal.dotawakeupassistant;

import com.flying.personal.dotawakeupassistant.GameStage;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by wangxian on 8/10/2015.
 */
public class WakeUpTask1 {
    private GameStage stage;

    public Date whenCanFinish(Date startDate, int refreshTimes) {
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        double days = 60 / (6 / 4.0 * (1 + refreshTimes));
        c.add(Calendar.DATE, (int) Math.ceil(days));
        return c.getTime();
    }

    public int GetStaminaSpend() {
        return stage.getStamina();
    }
}
