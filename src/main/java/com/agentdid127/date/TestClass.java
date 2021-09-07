package com.agentdid127.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * A test class to test the Date system, will be removed at a later date
 */
public class TestClass {


    public static void main(String[] args) throws InterruptedException {
        new TestClass().run(args);
    }
    /**
     * Tests some info for the Date class
     * @param args arguments, one Date string for each.
     */

    public void run(String[] args) throws InterruptedException {
            Date date = new Date(args[0]);



            date = Date.reformatDate(date);


            Date finalDate = date;

            UpdatableDate uDate = new UpdatableDate(date);
            System.out.println("Is Leap Year: " + finalDate.isLeapYear());
            System.out.println("Days in Month: " + finalDate.getDaysInMonth());
            System.out.println("UNIX Timestamp: " + finalDate.getUnixSeconds());
            System.out.println("UNIX Timestamp (Milli): " + finalDate.getUnixMilli());
            System.out.println("UTC-4 time: " + finalDate.localDate(-4));
            System.out.println("Time in India: " + finalDate.localDate(5.5));
            System.out.println("Date object: " + finalDate);

            System.out.println("Sum: " + finalDate.formattedSum(new Date("1970:0:0:0:0:0:0")));
            System.out.println("Diff: " + finalDate.formattedDiff(new Date("1970:0:0:0:0:0:0")));

            System.out.println("Testing Milliseconds: " + new UnixTimestamp(finalDate.getUnixMilli().getData(), UnixFormat.MILLISECONDS).toDate());
            System.out.println("Testing Seconds :" + new UnixTimestamp(finalDate.getUnixSeconds().getData(), UnixFormat.SECONDS).toDate());

            System.out.println("Testing Java Date:");
            java.util.Date jDate = finalDate.getUnixMilli().toJavaDate();
            DateFormat formatter = new SimpleDateFormat("EEEEEE yy/MM/dd hh:mm");

            String formattedDate = formatter.format(jDate);

            System.out.println("Java Formatted Date: " + formattedDate);

            System.out.println("Current Time: " + UnixTimestamp.current().toDate());

            System.out.println(uDate.getDate());
        }
    }

