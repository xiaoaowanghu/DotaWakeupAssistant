package com.flying.personal.dotawakeupassistant.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxian on 8/10/2015.
 */
public class SpecialStage extends GameStage {
    private static final List<Integer> weekdayCollection;

    static {
        weekdayCollection = new ArrayList<Integer>();
        weekdayCollection.add(Weekday.Monday);
        weekdayCollection.add(Weekday.Tuesday);
        weekdayCollection.add(Weekday.Wednesday);
        weekdayCollection.add(Weekday.Thursday);
        weekdayCollection.add(Weekday.Friday);
        weekdayCollection.add(Weekday.Saturday);
        weekdayCollection.add(Weekday.Sunday);
    }

    public interface Weekday {
        int Sunday = 1;
        int Monday = 2;
        int Tuesday = 4;
        int Wednesday = 8;
        int Thursday = 16;
        int Friday = 32;
        int Saturday = 64;
        int AllDays = 127;
    }

    private int occurDays;
    private int canBeExecutedTimes = 1;

    public List<Integer> taskWeekDayIndex() {
        List<Integer> result = new ArrayList<Integer>(7);

        for (int i = 0; i < 7; i++) {
            if ((weekdayCollection.get(i) & occurDays) != 0)
                result.add(i);
        }

        return result;
    }

    public int getOccurDays() {
        return occurDays;
    }

    public void setOccurDays(int occurDays) {
        this.occurDays = occurDays;
    }

    public int getCanBeExecutedTimes() {
        return canBeExecutedTimes;
    }

    public void setCanBeExecutedTimes(int canBeExecutedTimes) {
        this.canBeExecutedTimes = canBeExecutedTimes;
    }
}
