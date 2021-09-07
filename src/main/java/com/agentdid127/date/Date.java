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
        return Util.isLeapYear(year);
    }


    /**
     * Gets the days in the Date object's month.
     * @return number of days in current month
     */
    public int getDaysInMonth() {
        return Util.getDaysInMonth(month, year);
    }



    public Unix getUnixSeconds() {
        return new Unix(getMilliTimestamp()/1000, UnixFormat.SECONDS);
    }

    public Unix getUnixMilli() {
        return new Unix(getMilliTimestamp(), UnixFormat.MILLISECONDS);
    }
    /**
     * Gets the current timestamp using the UNIX date format.
     * @return timestamp in UNIX format.
     */
    private long getMilliTimestamp() {
        //Get some useful information
        int yearTemp = year;
        long leapYearTemp = (int)Math.floor(1970 / 4.0);
        long normalYearTemp = 1970 - leapYearTemp;
        long leapYears = (long)Math.floor(yearTemp / 4.0);
        long normalYears = (yearTemp - leapYears);

        //Seconds through hours
        long secondN = this.second  * 1000L;
        long minuteN = this.minute * (1000 * 60);
        long hourN = this.hour * (1000 * 60 * 60);

        //Days
        long dayN = this.day;
        dayN--;
        if (isLeapYear()) dayN--;
        dayN *= (1000 * 60 * 60 * 24);

        //Months
        long monthN = 0;
        for (int i = 1; i < month; i++) monthN += Util.getDaysInMonth(i, year);
        monthN *= (1000 * 60 * 60 * 24);

        //Years
        leapYears -= leapYearTemp;
        normalYears -= normalYearTemp;
        normalYears *= (1000 * 60 * 60 * 24) * 365L;
        leapYears  = leapYears *  (1000 * 60 * 60 * 24) * 366L;
        long yearN = leapYears + normalYears;

        return (long) this.milli + secondN + minuteN + hourN + dayN + monthN + yearN;

    }



    /**
     * Adds the two dates
     * @param date Date to add
     * @return Date object of added date
     */
    public Date sum(Date date) {
        return reformatDate(new Date(year + date.year, month + date.month, day + date.day,hour + date.hour, minute + date.minute, second + date.second, milli + date.milli));
    }

    /**
     * Subtracts the dates
     * @param date Date to subtract
     * @return Date object of subtracted date
     */
    public Date diff(Date date) {
        return reformatDate(new Date(year - date.year, month - date.month, day - date.day,hour - date.hour, minute - date.minute, second - date.second, milli - date.milli));
    }

    public static Date reformatDate(Date date) {
        int year = date.year, month = date.month, day = date.day, hour = date.hour, minute = date.minute, second = date.second, milli = date.milli;

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

        while (day > Util.getDaysInMonth(tempM, tempY)) {
            day -= Util.getDaysInMonth(tempM, tempY);
            tempM++;
            month++;
            if (tempM > 12) {
                tempY++;
                tempM = 1;
            }
            if (tempY > 4) tempY = 1;
        }
        while (day < 0) {
            day += Util.getDaysInMonth(tempM, tempY);
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
        while (month < 0) {
            month += 12;
            year--;
        }

        return new Date(year, month, day, hour, minute, second, milli);

    }

    /**
     * Gets date object with a Timezone offset
     * @param offset Timezone offset Ex: if it's UTC-4, write -4
     * @return Date object in local form.
     */
    public Date localDate(int offset) {
        int day = this.day, month = this.month, year = this.year;
        int hour = this.hour +  offset;
        if (hour < 0 || hour > 23) {
            while (hour < 0) {
                day--;
                hour += 24;
            }
            while (hour > 23) {
                hour -= 24;
                day++;
            }
        }
        if (day > Util.getDaysInMonth(month, year) || day < 1) {
            while (day > Util.getDaysInMonth(month, year)) {
                day -= Util.getDaysInMonth(month, year);
                month += 1;

            }
            while (day < 1) {
                day += Util.getDaysInMonth(month, year);
                month -= 1;
            }
        }
        if (month > 12 || month < 0) {
            while (month > 12) {
                month -= 12;
                year++;
            }
            while (month < 1) {
                month += 12;
                year--;
            }
        }
        if (year < 0) year--;


        return new Date(year, month, day, hour, minute, second, milli);
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
}