package com.agentdid127.date;

public class TestClass {

    public static void main(String[] args) {
        for (String arg : args) {
            Date date = new Date(arg);

            System.out.println("Is Leap Year: " + date.isLeapYear());
            System.out.println("Days in Month: " + date.getDaysInMonth());
            System.out.println("UNIX Timestamp: " + date.getMilliTimestamp());
            System.out.println("UTC-4 time: " + date.localDate(-4));
            System.out.println("Date object: " + date);
        }
    }
}
