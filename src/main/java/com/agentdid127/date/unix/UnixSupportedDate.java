package com.agentdid127.date.unix;

import com.agentdid127.date.Date;

/**
 * Date class that supports the Unix format
 */
public class UnixSupportedDate extends Date {

    /**
     * Unix Supported Date
     * @param date Date to initialize
     */
    public UnixSupportedDate(Date date) {
        super(date.toString());
    }

    /**
     * Create new UnixSupported Date
     * @param year Year
     * @param month Month
     * @param day Day
     * @param hour Hour
     * @param minute Minute
     * @param second Second
     * @param milli Millisecond
     */
    public UnixSupportedDate(int year, int month, int day, int hour, int minute, int second, int milli) {
        super(year, month, day, hour, minute, second, milli);
    }

    /**
     * Create new Unix Supported Date
     * @param dateIn Date String to create from
     */
    public UnixSupportedDate(String dateIn) {
        super(dateIn);
    }

    /**
     * Returns new UnixTimestamp in seconds since 1970
     * @return UnixTimestamp in seconds
     */
    public UnixTimestamp getUnixSeconds() {
        return new UnixTimestamp(getUnixMilli().getData()/1000, UnixFormat.SECONDS);
    }

    /**
     * Gets UnixTimestamp in milliseconds since 1/1/1970
     * @return UnixTimestamp in Milliseconds
     */
    public UnixTimestamp getUnixMilli() {
        return UnixTimestamp.fromDate(this);
    }
}
