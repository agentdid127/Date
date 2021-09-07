package com.agentdid127.date.unix;

import com.agentdid127.date.Date;

/**
 * Class to handle typical Unix format.
 */
public class UnixTimestamp {

    //Instance variables
    private long data;
    private UnixFormat format;

    /**
     * Unix format
     * @param in unix timestamp
     * @param format unix timestamp format
     */
    public UnixTimestamp(long in, UnixFormat format) {
        data = in; this.format = format;
    }

    /**
     * Gets Date from Unix timestamp
     * @return
     */
    public UnixSupportedDate toDate() {
        long milli;
        if (format.equals(UnixFormat.SECONDS)) {
            milli = data * 1000;
        } else if (format.equals(UnixFormat.MILLISECONDS)) {
            milli = data;
        } else return null;
        long second = 0;
        if (milli >= 1000 || milli < 0) {
            while (milli >= 1000) {
                milli -= 1000;
                second++;
            }
            while (milli < 0) {
                milli += 1000;
                second--;
            }
        }
        long minute = 0;
        if (second >= 60 || second < 0) {
            while (second >= 60) {
                second -= 60;
                minute++;
            }
            while (second < 0) {
                second += 60;
                minute--;
            }
        }
        long hour = 0;
        if (minute >= 60 || minute < 0) {
            while (minute >= 60) {
                minute -= 60;
                hour++;
            }
            while (minute < 0) {
                minute +=60;
                hour--;
            }
        }
        long day = 0;
        if (hour >= 24 || hour < 0) {
            while (hour > 24) {
                hour -= 24;
                day++;
            }
            while (hour < 0) {
                hour += 24;
                day--;
            }
        }
        long month = 0;
        int yCycle = 1;
        int mCycle = 1;
            while (day > Date.getDaysInMonth(mCycle, yCycle)) {
                day -= Date.getDaysInMonth(mCycle, yCycle);
                mCycle++;
                month++;
                if (mCycle > 12) {
                    yCycle++;
                    mCycle = 1;
                }
                if (yCycle > 4) yCycle = 1;
            }
            while (day <= 0) {
                day += Date.getDaysInMonth(mCycle, yCycle);
                mCycle--;
                month--;
                if (mCycle < 1) {
                    yCycle--;
                    mCycle = 12;
                }
                if (yCycle < 1) yCycle = 4;
            }
            month++;
            int year = 1970;
            while (month > 12) {
                month -= 12;
                year++;
            }
            while (month < 0) {
                month += 12;
                year--;
            }

            if (year % 4 > 0 && year > 1970) day++;
            if (year % 4 != 1 && year < 1970) day++;
            //if (year < 1970) second--;
            if (year <= 0) year--;
            return new UnixSupportedDate(Date.reformatDate(new Date(year, (int)month, (int)day, (int)hour, (int)minute, (int)second, (int)milli)));

    }

    public long getData() {
        return data;
    }

    public UnixFormat getFormat() {
        return format;
    }

    public String toString() {
        return String.valueOf(data);
    }

    public java.util.Date toJavaDate() {
        long datan = data;
        java.util.Date date = new java.util.Date();
        if (format.equals(UnixFormat.SECONDS)) {
            datan *= 1000;
        }
        date.setTime(datan);
        return date;
    }

    public static UnixTimestamp current() {
        long milli = System.currentTimeMillis();
        return new UnixTimestamp(milli, UnixFormat.MILLISECONDS);
    }

    public static UnixTimestamp fromDate(Date date) {
        //Get some useful information
        int yearTemp = date.getYear();
        if (yearTemp < 0) yearTemp++;
        long leapYearTemp = (int)Math.floor(1970 / 4.0);
        long normalYearTemp = 1970 - leapYearTemp;
        long leapYears = (long)Math.floor(yearTemp / 4.0);
        long normalYears = (yearTemp - leapYears);

        //Seconds through hours
        long secondN = date.getSecond()  * 1000L;
        long minuteN = date.getMinute() * (1000 * 60);
        long hourN = date.getHour() * (1000 * 60 * 60);

        //Days
        long dayN = date.getDay();
        dayN--;
        if (Date.isLeapYear(date.getYear())) dayN--;
        dayN *= (1000 * 60 * 60 * 24);

        //Months
        long monthN = 0;
        for (int i = 1; i < date.getMonth(); i++) monthN += Date.getDaysInMonth(i, date.getYear());
        monthN *= (1000 * 60 * 60 * 24);

        //Years
        leapYears -= leapYearTemp;
        normalYears -= normalYearTemp;
        normalYears *= (1000 * 60 * 60 * 24) * 365L;
        leapYears  = leapYears *  (1000 * 60 * 60 * 24) * 366L;
        long yearN = leapYears + normalYears;

        return new UnixTimestamp( date.getMilli() + secondN + minuteN + hourN + dayN + monthN + yearN, UnixFormat.MILLISECONDS);

    }
}
