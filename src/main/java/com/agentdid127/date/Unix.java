package com.agentdid127.date;

/**
 * Class to handle typical Unix format.
 */
public class Unix {

    //Instance variables
    private long data;
    private UnixFormat format;

    /**
     * Unix format
     * @param in unix timestamp
     * @param format unix timestamp format
     */
    public Unix (long in, UnixFormat format) {
        data = in; this.format = format;
    }

    /**
     * Gets Date from Unix timestamp
     * @return
     */
    public Date toDate() {
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
            while (day > Util.getDaysInMonth(mCycle, yCycle)) {
                day -= Util.getDaysInMonth(mCycle, yCycle);
                mCycle++;
                month++;
                if (mCycle > 12) {
                    yCycle++;
                    mCycle = 1;
                }
                if (yCycle > 4) yCycle = 1;
            }
            while (day < 0) {
                day += Util.getDaysInMonth(mCycle, yCycle);
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

            if (year % 4 != 0) day++;
            if (year < 1970) second--;
            return new Date(year, (int)month, (int)day, (int)hour, (int)minute, (int)second, (int)milli);

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
            data *= 1000;
        }
        date.setTime(data);
        return date;
    }
}
