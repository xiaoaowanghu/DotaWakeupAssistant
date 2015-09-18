package com.flying.personal.dotawakeupassistant.model;

import com.flying.personal.dotawakeupassistant.util.Utility;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wangxian on 8/10/2015.
 */
public class WakeUpTask {
    protected GameStage stage;
    protected double dropRate = 6 / 4.0 * 1.2;
    protected String description;
    protected EquipmentItem[] neededEquipment;

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
        String result = "";

        if (stage != null)
            result = "第" + stage.getChapter() + "章 " + stage.name;

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

    public EquipmentItem[] getNeededEquip() {
        return neededEquipment;
    }

    public void setNeededEquipment(EquipmentItem[] neededEquipment) {
        this.neededEquipment = neededEquipment;
    }

    public static class TaskDeserializeAdapter implements JsonDeserializer<WakeUpTask> {
        @Override
        public WakeUpTask deserialize(JsonElement jsonElement, Type type,
                                      JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject object = jsonElement.getAsJsonObject();
            WakeUpTask result = null;

            //occurDays
            JsonElement occurDays = object.get("executeTimes");
            if (occurDays != null) {
                result = new WakeUpRepeatableTask();
                ((WakeUpRepeatableTask) result).setExecuteTimes(occurDays.getAsInt());
            }

            if (result == null) {
                result = new WakeUpTask();
            }

            Utility.getInstance().deserializeNormalField(result, WakeUpTask.class, object, jsonDeserializationContext, true);
            return result;
        }
    }
}
