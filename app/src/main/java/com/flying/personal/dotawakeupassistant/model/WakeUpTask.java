package com.flying.personal.dotawakeupassistant.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by wangxian on 8/10/2015.
 */
public class WakeUpTask {
    protected GameStage stage;
    protected double dropRate = 6 / 4.0;
    protected String description;

    public Date whenCanFinish(Date startDate, int refreshTimes) {
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        double days = 60 / (dropRate * (1 + refreshTimes));
        c.add(Calendar.DATE, (int) Math.ceil(days));
        return c.getTime();
    }

    public int GetStaminaSpend() {
        return stage.getStamina();
    }

    public GameStage getStage() {
        return stage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayInfo() {
        String result = "第" + stage.getChapter() + "章 " + stage.name;
        return result + " " + description;
    }

    public void setStage(GameStage stage) {
        this.stage = stage;
    }

    public double getDropRate() {
        return dropRate;
    }

    public void setDropRate(double dropRate) {
        this.dropRate = dropRate;
    }
}
