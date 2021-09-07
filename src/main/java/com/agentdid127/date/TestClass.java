package com.agentdid127.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A test class to test the Date system, will be removed at a later date
 */
public class TestClass {

    /**
     * Tests some info for the Date class
     * @param args arguments, one Date string for each.
     */
    public static void main(String[] args) {
        for (String arg : args) {
            Date date = new Date(arg);

            date = Date.reformatDate(date);
            System.out.println("Is Leap Year: " + date.isLeapYear());
            System.out.println("Days in Month: " + date.getDaysInMonth());
            System.out.println("UNIX Timestamp: " + date.getUnixSeconds());
            System.out.println("UNIX Timestamp (Milli): " + date.getUnixMilli());
            System.out.println("UTC-4 time: " + date.localDate(-4));
            System.out.println("Date object: " + date);

            System.out.println("Sum: " + date.sum(new Date()));
            System.out.println("Diff: " + date.diff(new Date()));

            System.out.println("Testing Milliseconds: " + new Unix(date.getUnixMilli().getData(), UnixFormat.MILLISECONDS).toDate());
            System.out.println("Testing Seconds :" + new Unix(date.getUnixSeconds().getData(), UnixFormat.SECONDS).toDate());

            System.out.println("Testing Java Date:");
            java.util.Date jDate = date.getUnixMilli().toJavaDate();
            DateFormat formatter = new SimpleDateFormat("EEEEEE yy/MM/dd hh:mm");

            String formattedDate = formatter.format(jDate);

            System.out.println("Java Formatted Date: " + formattedDate);
        }
    }
}
