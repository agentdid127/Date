package com.agentdid127.date;

public class DateFormat {

    public static String getDayOfWeek(Date date) {
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        int[] t = {0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};
        int tempYear = year;
        tempYear -= (month < 3 ? 1 : 0);
        int dow =  (tempYear + tempYear / 4 - tempYear/100 + tempYear/400 + t[month-1] + day) % 7;
        return DayOfWeek.values()[dow].name();
    }

    public static String getMonth(Date date) {
        int month = date.getMonth()-1;
        return Month.values()[month].name();
    }

    public static String globalPrintingDate(Date date) {
        return formalName(getDayOfWeek(date)) + " " + date.getDay() + " " + formalName(getMonth(date)) + " " + date.getYear();
    }

    public static String globalPrintingTime(Date date) {
        String minute = date.getMinute() + "";
        if (minute.length() == 1) minute = "0" + minute;

        String second = date.getSecond() + "";
        if (second.length() == 1) second = "0" + second;
        return date.getHour() + ":" + minute + ":" + second;
    }

    public static String americanPrintingDate(Date date) {
        return formalName(getDayOfWeek(date)) + " " + formalName(getMonth(date)) + " " + date.getDay() + ", " + date.getYear();
    }

    public static String americanPrintingTime(Date date) {
        int hourIn = date.getHour();
        boolean pm = false;
        int hour = hourIn % 12;
        if (hour == 0) hour = 12;
        if (hourIn >= 12) pm = true;

        String timeType = pm ? "PM" : "AM";

        String minute = date.getMinute() + "";
        if (minute.length() == 1) minute = "0" + minute;

        String second = date.getSecond() + "";
        if (second.length() == 1) second = "0" + second;
        return hour + ":" + minute + ":" + second + " " + timeType;
    }

    private static String formalName(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }




}

enum DayOfWeek {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY
}

enum Month {
    JANUARY,
    FEBRUARY,
    MARCH,
    APRIL,
    MAY,
    JUNE,
    JULY,
    AUGUST,
    SEPTEMBER,
    OCTOBER,
    NOVEMBER,
    DECEMBER
}