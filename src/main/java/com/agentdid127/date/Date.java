package com.agentdid127.date;

/**
 * Custom date System, to avoid 2038 issues
 */
public class Date {

    //Instance Variables
    protected int year, month, day, hour, minute, second, milli;


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


    /**
     * Adds the two dates, then makes it look nice
     * @param date Date to add
     * @return Date object of added date
     */
    public Date formattedSum(Date date) {
        int yearOut = year + date.year;
        if (year > 0 && year + date.year <= 0) yearOut -= 1;
        else if (year < 0 && year + date.year >= 0) yearOut += 1;
        return reformatDate(new Date(yearOut, month + date.month, day + date.day,hour + date.hour, minute + date.minute, second + date.second, milli + date.milli));
    }

    /**
     * Adds two dates
     * @param date Date to add
     * @return added date
     */
    public Date sum(Date date) {
        return new Date(year + date.year, month + date.month, day + date.day, hour + date.hour, minute + date.minute, second + date.second, milli + date.milli);
    }

    /**
     * Subtracts the dates then formats them
     * @param date Date to subtract
     * @return Date object of subtracted date
     */
    public Date formattedDiff(Date date) {
        int yearOut = year - date.year;
        if (year > 0 && year - date.year <= 0) yearOut -= 1;
        else if (year < 0 && year - date.year >= 0) yearOut += 1;
        return reformatDate(new Date(yearOut, month - date.month, day - date.day,hour - date.hour, minute - date.minute, second - date.second, milli - date.milli));
    }

    /**
     * Subtracts Dates
     * @param date Date to subtract
     * @return Date object of subtracted Date
     */
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

        return formattedSum(new Date(0, 0, 0, hour, minute, 0, 0));
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
        return milli;
    }


    /**
     * Checks if a year is a leap year
     * @param year year to check
     * @return boolean variable of if it's a leap year
     */
    public static boolean isLeapYear(int year) {
        if (year % 4 != 0) return false;
        else if (year % 100 != 0) return true;
        else if (year % 400 != 0) return false;
        else return true;
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
        int year = date.getYear(), month = date.getMonth(), day = date.getDay(), hour = date.getHour(), minute = date.getMinute(), second = date.getSecond(), milli = date.getMilli();

        double milli_correct = milli / 1000.0;
        milli = milli % 1000;
        second += Math.floor(milli_correct);

        double second_correct = second / 60.0;
        second = second % 60;
        minute += Math.floor(second_correct);

        double minute_correct = minute / 60.0;
        minute = minute % 60;
        hour += Math.floor(minute_correct);

        double hour_correct = hour / 24.0;
        hour = hour % 24;
        day += Math.floor(hour_correct);

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

        double month_correct = (month - 1) / 12.0;
        month = (month - 1) % 12 + 1;
        year += Math.floor(month_correct);

         if (date.year > 0 && year <= 0) year--;
         else if (date.year < 0 && year >= 0) year++;

        return new Date(year, month, day, hour, minute, second, milli);

    }
}