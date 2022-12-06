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
        double milli_correct = milli / 1000.0;
        milli = milli % 1000;
        second += Math.floor(milli_correct);

        long minute = 0;
        double second_correct = second / 60.0;
        second = second % 60;
        minute += Math.floor(second_correct);

        long hour = 0;

        double minute_correct = minute / 60.0;
        minute = minute % 60;
        hour += Math.floor(minute_correct);

        long day = 0;

        double hour_correct = hour / 24.0;
        hour = hour % 24;
        day += Math.floor(hour_correct);

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
            double month_correct = (month - 1) / 12.0;
            month = (month - 1) % 12 + 1;
            year += Math.floor(month_correct);


            if (year % 4 > 0 && year > 1970) day++;
            if (year % 4 != 1 && year < 1970) day++;
            //if (year < 1970) second--;
            if (year <= 0) year--;
            return new UnixSupportedDate(Date.reformatDate(new Date(year, (int)month, (int)day, (int)hour, (int)minute, (int)second, (int)milli)));

    }

    /**
     * Gets the Unix Timestamp
     * @return Unix Timestamp
     */
    public long getData() {
        return data;
    }

    /**
     * Gets the Unix Format
     * @return Either Milliseconds or Seconds
     */
    public UnixFormat getFormat() {
        return format;
    }

    /**
     * Gets the data value
     * @return Data value
     */
    public String toString() {
        return String.valueOf(data);
    }

    /**
     * Converts Unix Timestamp into Java Date
     * @return
     */
    public java.util.Date toJavaDate() {
        long datan = data;
        java.util.Date date = new java.util.Date();
        if (format.equals(UnixFormat.SECONDS)) {
            datan *= 1000;
        }
        date.setTime(datan);
        return date;
    }

    /**
     * Gets the timestamp at this current date
     * @return UnixTimestamp of the current date and time
     */
    public static UnixTimestamp current() {
        long milli = System.currentTimeMillis();
        return new UnixTimestamp(milli, UnixFormat.MILLISECONDS);
    }

    /**
     * Gets a UnixTimestamp from the date
     * @param date Date to get timestamp from
     * @return Timestamp
     */
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
