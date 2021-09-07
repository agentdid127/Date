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
        }
        else if (format.equals(UnixFormat.MILLISECONDS)) {
            milli = data;
        }
        else return null;
        long day = (milli / (86400 * 1000));
        double month = (day / 30.44) + 1;

        int year = 0;
        while (month > 12) {
            month-= 12;
            year += 1;
        }
        int yearTemp = year;
        double dayTemp = day;
        while (dayTemp > 365.24 && yearTemp > 0) {
            dayTemp -= 365.24;
            yearTemp--;
        }
        day = (int)dayTemp;
        int monthTemp = (int) month;
        while (day > Util.getDaysInMonth(monthTemp, (int) year) && monthTemp > 0) {
            day -= Util.getDaysInMonth(monthTemp, (int) year);
            monthTemp--;
        }
        while (day > Util.getDaysInMonth((int)month, year)) {
            day -= Util.getDaysInMonth((int)month, year);
            month +=1;
        }
        while (month > 12) {
            month-= 12;
            year += 1;
        }

        long hour = milli / (1000 * 60 * 60);

        while (hour > 24) {
            hour -= 24;
        }
        long minute = milli / (1000 * 60) ;

        while (minute > 60) {
            minute -= 60;
        }
        long second = milli / (1000);
        while (second > 60) {
            second -= 60;
        }
        while (milli >= 1000) {
            milli -= 1000;
        }
        while (milli < 0) {
            milli += 1000;
        }

        return new Date(year + 1970, (int)month, (int)day, (int)hour, (int)minute, (int)second, (int)milli);

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
}
