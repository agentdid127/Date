package com.agentdid127.date.util;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    /**
     * Date Testing Program
     * @param args String to check.
     */
    public static void main(String[] args) {
        System.out.println("now: " + getString(now()));
        String val = "2024:10:29:10:43:52:0";

        if (args.length > 0) {
            val = args[0];
        }

        Date d = fromString(val);
        System.out.println("String: \"" + val + "\" Date:" + d + " back to string: " + getString(d));

        System.out.println("In 2 hours: " + getString(addTime(now(), milliToHour() * 2)));
    }

    public static Date now() {
        return new Date();
    }

    public static Date addTime(Date in, long amount) {
        return new Date(in.getTime() + amount);
    }

    private static TimeZone zoneFromOffset(double offset) {
        int hours = (int)(Math.floor(offset));
        int minutes = (int)(Math.floor((offset - hours) / 60.0));
        return TimeZone.getTimeZone(ZoneId.ofOffset("UTC", ZoneOffset.ofHoursMinutes(-hours, minutes)));
    }

    public static int getValue(Date in, int val, double timezone) {
        Calendar cal = Calendar.getInstance();

        cal.setTimeZone(zoneFromOffset(timezone));
        cal.setTime(in);
        return cal.get(val);
    }

    public static int getValue(Date in, int val) {
        return getValue(in, val, 0);
    }

    public static int year(Date in) {
        return getValue(in, Calendar.YEAR);
    }

    public static int year(Date in, double offset) {
        return getValue(in, Calendar.YEAR, offset);
    }


    public static int month(Date in) {
        return getValue(in, Calendar.MONTH);
    }

    public static int month(Date in, double offset) {
        return getValue(in, Calendar.MONTH, offset);
    }
    public static int day(Date in) {
        return getValue(in, Calendar.DAY_OF_MONTH);
    }

    public static int day(Date in, double offset) {
        return getValue(in, Calendar.DAY_OF_MONTH, offset);
    }

    public static int hour(Date in) {
        return getValue(in, Calendar.HOUR_OF_DAY);
    }

    public static int hour(Date in, double offset) {
        return getValue(in, Calendar.HOUR_OF_DAY, offset);
    }

    public static int minute(Date in) {
        return getValue(in, Calendar.MINUTE);
    }

    public static int minute(Date in, double offset) {
        return getValue(in, Calendar.MINUTE, offset);
    }

    public static int second(Date in) {
        return getValue(in, Calendar.SECOND);
    }

    public static int second(Date in, double offset) {
        return getValue(in, Calendar.SECOND, offset);
    }

    public static int millisecond(Date in) {
        return getValue(in, Calendar.MILLISECOND);
    }

    public static int millisecond(Date in, double offset) {
        return getValue(in, Calendar.MILLISECOND, offset);
    }

    public static String getString(Date in) {
        return getLocalString(in, 0);
    }

    public static String getLocalString(Date in, double offset) {
        return year(in, offset) + ":" +(month(in, offset) + 1) + ":" + day(in, offset) + ":" + hour(in, offset) + ":" + minute(in, offset) + ":" + second(in, offset) + ":" + millisecond(in, offset);
    }

    public static String tomorrow() {
        return getString(DateUtil.addTime(now(), milliToDay()));
    }

    public static long milliToSecond() {
        return 1000;
    }

    public static long milliToMinute() {
        return milliToSecond() * 60;
    }

    public static long milliToHour() {
        return milliToMinute() * 60;
    }

    public static long milliToDay() {
        return milliToHour() * 24;
    }

    public static Date fromString(String in) {
        return fromString(in, 0);
    }

    public static Date fromString(String in, double offset) {
        String[] vals = in.split(":");
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(zoneFromOffset(offset));
        cal.set(Calendar.YEAR, Integer.parseInt(vals[0]));
        cal.set(Calendar.MONTH, Integer.parseInt(vals[1]) - 1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(vals[2]));
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(vals[3]));
        cal.set(Calendar.MINUTE, Integer.parseInt(vals[4]));
        cal.set(Calendar.SECOND, Integer.parseInt(vals[5]));
        cal.set(Calendar.MILLISECOND, Integer.parseInt(vals[6]));

        return cal.getTime();
    }
}

