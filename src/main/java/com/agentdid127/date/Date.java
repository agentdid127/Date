package com.agentdid127.date;

import com.agentdid127.date.util.DateUtil;

/**
 * Custom date System, to avoid 2038 issues
 */
public class Date {

    /**
     * Date Variables
     */
    protected int year, month, day, hour, minute, second, millisecond;

    private final double offset;


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
        millisecond = 0;
        offset = 0;
    }

    /**
     * Date started as a string Date object
     * @param dateIn Date object
     */
    public Date(String dateIn, double offset) {
        java.util.Date date = DateUtil.fromString(dateIn);
        year = DateUtil.year(date);
        month = DateUtil.month(date);
        day = DateUtil.day(date);
        hour = DateUtil.hour(date);
        minute = DateUtil.minute(date);
        second = DateUtil.second(date);
        millisecond = DateUtil.millisecond(date);
        this.offset = offset;
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
     * @param offset offset
     */
    public Date(int year, int month, int day, int hour, int minute, int second, int milli, double offset) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.millisecond = milli;
        this.offset = offset;
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
        this(year, month, day, hour, minute, second, milli, 0);
    }

    /**
     * Date, but with all arguments as a short
     * @param year year
     * @param month month
     * @param day day
     * @param hour hour
     * @param minute minute
     * @param second second
     * @param milli millisecond
     * @deprecated Use the int constructor instead.
     */
    @Deprecated
    public Date(short year, short month, short day, short hour, short minute, short second, short milli) {
        this(year,month,day,hour,minute,second,(int)milli, 0);
    }

    public Date(String date) {
        this(date, 0);;
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


    /**
     * Adds the two dates, then makes it look nice
     * @param date Date to add
     * @return Date object of added date
     */
    public Date formattedSum(Date date) {
        return new Date(DateUtil.getString(new java.util.Date(DateUtil.fromString(this.toString(), this.offset).getTime() + DateUtil.fromString(date.toString()).getTime())));
    }

    /**
     * Adds two dates
     * @param date Date to add
     * @return added date
     */
    public Date sum(Date date) {
        return new Date(year + date.year, month + date.month, day + date.day, hour + date.hour, minute + date.minute, second + date.second, millisecond + date.millisecond);
    }

    /**
     * Subtracts the dates then formats them
     * @param date Date to subtract
     * @return Date object of subtracted date
     */
    public Date formattedDiff(Date date) {
        return new Date(DateUtil.getString(new java.util.Date(DateUtil.fromString(this.toString(), this.offset).getTime() - DateUtil.fromString(date.toString()).getTime())));
    }

    /**
     * Subtracts Dates
     * @param date Date to subtract
     * @return Date object of subtracted Date
     */
    public Date diff(Date date) {
        return new Date(year - date.year, month - date.month, day - date.day, hour - date.hour, minute - date.minute, second - date.second, millisecond - date.millisecond);
    }

    /**
     * Gets date object with a Timezone offset
     * @param offset Timezone offset Ex: if it's UTC-4, write -4
     * @return Date object in local form.
     */
    public Date localDate(double offset) {
        return new Date(DateUtil.getLocalString(DateUtil.fromString(this.toString(), this.offset), offset));
    }


    /**
     * Converts Date object back to string form.
     * @return String form of Date
     */
    public String toString() {
        return (year + ":" + getValue(month , 2) + ":" + getValue(day, 2) + ":" + getValue(hour, 2) + ":" + getValue(minute, 2) + ":" + getValue(second, 2)+ ":"+ getValue(millisecond, 3));
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
     * Gets the current Year
     * @return Year int
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the current month
     * @return Month int
     */
    public int getMonth() {
        return month;
    }

    /**
     * Gets the current day
     * @return Day int
     */
    public int getDay() {
        return day;
    }

    /**
     * Gets the current hour
     * @return Hour int
     */
    public int getHour() {
        return hour;
    }

    /**
     * Gets the current minute
     * @return Minute int
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Gets the current Second
     * @return Second int
     */
    public int getSecond() {
        return second;
    }

    /**
     * Gets the current millisecond
     * @return Millisecond int
     */
    public int getMilli() {
        return millisecond;
    }


    /**
     * Checks if a year is a leap year
     * @param year year to check
     * @return boolean variable of if it's a leap year
     */
    public static boolean isLeapYear(int year) {
        if (year % 4 != 0) return false;
        else if (year % 100 != 0) return true;
        else return year % 400 == 0;
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

    /**
     * Updates the date variable to not have invalid numbers
     * @param date Date to reformat
     * @return Formatted date
     */
    public static Date reformatDate(Date date) {

        if (date.year < 1970) {
            throw new IllegalArgumentException("Date must be newer than the UNIX epoch.");
        }
        long milli = date.millisecond;
        milli += date.getSecond() * DateUtil.milliToSecond();
        milli += date.getMinute() * DateUtil.milliToMinute();
        milli += date.getHour() * DateUtil.milliToHour();

        while (date.month <= 0) {
            date.month += 12;
            date.year--;
        }

        while (date.month > 12) {
            date.year--;
            date.month -= 12;
        }

        if (date.day > getDaysInMonth(date.month, date.year)) {
            while (date.day > getDaysInMonth(date.month, date.year)) {
                date.day -= getDaysInMonth(date.month, date.year);
                date.month++;
            }
        }

        long days = date.day;

        for (int i = 0; i < date.month; i++) {
            days += getDaysInMonth(i+1, date.year);
        }


        for (int i = 0; i < date.year - 1970; i++) {
            days += 365;
            if (isLeapYear(i + 1970)) days += 1;
        }
        days--;
        milli += days * DateUtil.milliToDay();

        return new Date(DateUtil.getString(new java.util.Date(milli)));
    }
}