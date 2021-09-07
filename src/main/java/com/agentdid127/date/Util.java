package com.agentdid127.date;

public class Util {

    /**
     * Calculates the number of days in the current month
     * @param month month to check
     * @param year year of month to check (for the case of Feb.)
     * @return integer, number of days in the month
     */
    public static int getDaysInMonth(int month, int year) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
            return 31;
        else if (month == 2) {
            if (isLeapYear(year)) return 29;
            else return 28;
        }
        else if (month == 4 || month == 6 || month == 9 || month == 11)
            return 30;
        else if (month < 1) return getDaysInMonth(month + 12, year);
        else return getDaysInMonth(month - 12, year);
    }

    /**
     * Checks if a year is a leap year
     * @param year year to check
     * @return boolean variable of if it's a leap year
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0);
    }

}
