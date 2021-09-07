package com.agentdid127.date;

/**
 * Custom date System, to avoid 2038 issues
 */
public class Date {

    //Instance Variables
    private int year, month, day, hour, minute, second, milli;


    /**
     * Basic object stated at the UNIX start time
     */
    public Date() {
        year = 1970;
        month = 1;
        day = 1;
        hour = 0;
        minute = 0;
        second = 0;
        milli = 0;
    }

    /**
     * Date started as a string Date object
     * @param dateIn Date object
     */
    public Date(String dateIn) {
        String[] date = dateIn.split(":");
        year = Integer.parseInt(date[0]);
        month = Integer.parseInt(date[1]);
        day = Integer.parseInt(date[2]);
        hour = Integer.parseInt(date[3]);
        minute = Integer.parseInt(date[4]);
        second = Integer.parseInt(date[5]);
        milli = Integer.parseInt(date[6]);
    }

    /**
     * Date, but with all arguments as an integer
     * @param year year
     * @param month month
     * @param day day
     * @param hour hour
     * @param minute minute
     * @param second second
     * @param milli millisecond
     */
    public Date(int year, int month, int day, int hour, int minute, int second, int milli) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.milli = milli;
    }

    /**
     * Checks if the year in the Date object is a leap year
     * @return boolean variable of a leap year
     */
    public boolean isLeapYear() {
        return isLeapYear(year);
    }


    /**
     * Gets the days in the Date object's month.
     * @return number of days in current month
     */
    public int getDaysInMonth() {
        return getDaysInMonth(month, year);
    }

    public UnixTimestamp getUnixSeconds() {
        return new UnixTimestamp(getUnixMilli().getData()/1000, UnixFormat.SECONDS);
    }

    public UnixTimestamp getUnixMilli() {
        return UnixTimestamp.fromDate(this);
    }


    /**
     * Adds the two dates
     * @param date Date to add
     * @return Date object of added date
     */
    public Date formattedSum(Date date) {
        int yearOut = year + date.year;
        if (year > 0 && year + date.year <= 0) yearOut -= 1;
        else if (year < 0 && year + date.year >= 0) yearOut += 1;
        return reformatDate(new Date(yearOut, month + date.month, day + date.day,hour + date.hour, minute + date.minute, second + date.second, milli + date.milli));
    }

    public Date sum(Date date) {
        return new Date(year + date.year, month + date.month, day + date.day, hour + date.hour, minute + date.minute, second + date.second, milli + date.milli);
    }

    /**
     * Subtracts the dates
     * @param date Date to subtract
     * @return Date object of subtracted date
     */
    public Date formattedDiff(Date date) {
        int yearOut = year - date.year;
        if (year > 0 && year - date.year <= 0) yearOut -= 1;
        else if (year < 0 && year - date.year >= 0) yearOut += 1;
        return reformatDate(new Date(yearOut, month - date.month, day - date.day,hour - date.hour, minute - date.minute, second - date.second, milli - date.milli));
    }
    
    public Date diff(Date date) {
        return new Date(year - date.year, month - date.month, day - date.day, hour - date.hour, minute - date.minute, second - date.second, milli - date.milli);
    }

    /**
     * Gets date object with a Timezone offset
     * @param offset Timezone offset Ex: if it's UTC-4, write -4
     * @return Date object in local form.
     */
    public Date localDate(double offset) {
        int hour = (int)(offset);
        int minute = (int)((offset-hour) * (60));

        return reformatDate(sum(new Date(0, 0, 0, hour, minute, 0, 0)));
        }


    /**
     * Converts Date object back to string form.
     * @return String form of Date
     */
    public String toString() {
        return (year + ":" + getValue(month , 2) + ":" + getValue(day, 2) + ":" + getValue(hour, 2) + ":" + getValue(minute, 2) + ":" + getValue(second, 2)+ ":"+ getValue(milli, 3));
    }

    /**
     * Makes sure that the String format uses zeros where needed.
     * @param in Article to update
     * @param length how many zeroes to add if needed.
     * @return String of int, with zeroes added if needed
     */
    private String getValue(int in, int length) {
        StringBuilder inS = new StringBuilder(String.valueOf(in));
        if (inS.length() < length) {
            int l = length - inS.length();
            for (int i = 0; i < l; i++) {
                inS.insert(0, "0");
            }
        }
        return inS.toString();
    }

    /**
     * Returns an example String date, to show off how it could work.
     * @return String example date.
     */
    public static String getExample() {
        return "2021:09:06:12:23:12:236";
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public int getMilli() {
        return milli;
    }


    /**
     * Checks if a year is a leap year
     * @param year year to check
     * @return boolean variable of if it's a leap year
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0);
    }

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

    public static Date reformatDate(Date date) {
        int year = date.getYear(), month = date.getMonth(), day = date.getDay(), hour = date.getHour(), minute = date.getMinute(), second = date.getSecond(), milli = date.getMilli();

        while (milli >= 1000) {
            milli -= 1000;
            second++;
        }
        while (milli < 0) {
            milli += 1000;
            second--;
        }
        while (second >= 60) {
            second -= 60;
            minute++;
        }
        while (second < 0) {
            second += 60;
            minute--;
        }

        while (minute >= 60) {
            minute -= 60;
            hour++;
        }
        while (minute < 0) {
            minute += 60;
            hour--;
        }

        while (hour >= 24) {
            hour -= 24;
            day++;
        }
        while (hour < 0) {
            hour += 24;
            day--;
        }

        int tempM = month;
        int tempY = year % 4 + 1;

        while (day > getDaysInMonth(tempM, tempY)) {
            day -= getDaysInMonth(tempM, tempY);
            tempM++;
            month++;
            if (tempM > 12) {
                tempY++;
                tempM = 1;
            }
            if (tempY > 4) tempY = 1;
        }
        while (day <= 0) {
            day += getDaysInMonth(tempM, tempY);
            tempM--;
            month--;
            if (tempM < 1) {
                tempM = 12;
                tempY--;
            }
            if (tempY < 1) tempY = 4;
        }

        while (month > 12) {
            month -= 12;
            year++;
        }
        while (month <= 0) {
            month += 12;
            year--;
        }

         if (date.year > 0 && year <= 0) year--;
         else if (date.year < 0 && year >= 0) year++;

        return new Date(year, month, day, hour, minute, second, milli);

    }
}