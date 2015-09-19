package com.flying.personal.dotawakeupassistant.model;

import com.flying.personal.dotawakeupassistant.util.CommonUtility;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by wangxian on 8/10/2015.
 */
public class GameStage {
    protected int chapter;
    protected String name;
    protected int sequenceNo;
    protected int stamina;
    protected List<EquipmentItem> outputItems;
    protected int difficultyLevel = 3;

    public int getChapter() {
        return chapter;
    }

    public int getSequenceNo() {
        return sequenceNo;
    }

    public int getStamina() {
        return stamina;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EquipmentItem> getOutputItems() {
        return outputItems;
    }

    public void setOutputItems(List<EquipmentItem> outputItems) {
        this.outputItems = outputItems;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public static class StageDeserializeAdapter implements JsonDeserializer<GameStage> {
        @Override
        public GameStage deserialize(JsonElement jsonElement, Type type,
                                     JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject object = jsonElement.getAsJsonObject();
            GameStage result = null;

            //occurDays
            JsonElement occurDays = object.get("occurDays");
            if (occurDays != null) {
                result = new SpecialStage();
                ((SpecialStage) result).setOccurDays(occurDays.getAsInt());

                JsonElement canBeExecutedTimes = object.get("canBeExecutedTimes");
                ((SpecialStage) result).setCanBeExecutedTimes(canBeExecutedTimes.getAsInt());
            }

            if (result == null) {
                result = new GameStage();
            }

            CommonUtility.deserializeNormalField(result, GameStage.class, object, jsonDeserializationContext, true);
            return result;
        }
    }
}
