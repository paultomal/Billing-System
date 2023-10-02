package com.paul.billing_system.enums;

import java.util.ArrayList;
import java.util.List;

public enum DaysOfWeek {
    SUNDAY("Sunday"),
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday");

    private final String label;

    DaysOfWeek(String label) {
        this.label = label;
    }

    public static  DaysOfWeek getDaysByLabel(String label){
        for (DaysOfWeek days : DaysOfWeek.values()){
            if(days.label.equals(label))
                return days;
        }
        return null;
    }

    public static String getLabelByDays(DaysOfWeek days){
        return days.label;
    }

    public static List<String> getAllDaysOfWeek() {
        List<String> daysList = new ArrayList<>();
        for (DaysOfWeek day : DaysOfWeek.values()) {
            daysList.add(DaysOfWeek.getLabelByDays(day));
        }
        return daysList;
    }
}
