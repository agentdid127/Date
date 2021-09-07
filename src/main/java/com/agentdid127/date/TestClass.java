package com.agentdid127.date;

import com.agentdid127.date.unix.UnixFormat;
import com.agentdid127.date.unix.UnixSupportedDate;
import com.agentdid127.date.unix.UnixTimestamp;
import com.agentdid127.date.unix.UpdatableDate;

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

            UnixSupportedDate date2 = new UnixSupportedDate(date);
            Date finalDate = date;

            UpdatableDate uDate = new UpdatableDate(date);
            System.out.println("Is Leap Year: " + finalDate.isLeapYear());
            System.out.println("Days in Month: " + finalDate.getDaysInMonth());
            System.out.println("UNIX Timestamp: " + date2.getUnixSeconds());
            System.out.println("UNIX Timestamp (Milli): " + date2.getUnixMilli());
            System.out.println("UTC-4 time: " + finalDate.localDate(-4));
            System.out.println("Time in India: " + finalDate.localDate(5.5));
            System.out.println("Date object: " + finalDate);

            System.out.println("Sum: " + finalDate.formattedSum(new Date("1970:0:0:0:0:0:0")));
            System.out.println("Diff: " + finalDate.formattedDiff(new Date("1970:0:0:0:0:0:0")));

            System.out.println("Testing Milliseconds: " + new UnixTimestamp(date2.getUnixMilli().getData(), UnixFormat.MILLISECONDS).toDate());
            System.out.println("Testing Seconds :" + new UnixTimestamp(date2.getUnixSeconds().getData(), UnixFormat.SECONDS).toDate());

            System.out.println("Testing Java Date:");
            java.util.Date jDate = date2.getUnixMilli().toJavaDate();
            DateFormat formatter = new SimpleDateFormat("EEEEEE yy/MM/dd hh:mm");

            String formattedDate = formatter.format(jDate);

            System.out.println("Java Formatted Date: " + formattedDate);

            System.out.println("Current Time: " + UnixTimestamp.current().toDate());

            System.out.println("Unix Date: " + uDate.getDate());


        }
    }

