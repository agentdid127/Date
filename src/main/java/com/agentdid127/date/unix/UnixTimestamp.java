package com.agentdid127.date.unix;

import com.agentdid127.date.Date;
import com.agentdid127.date.util.DateUtil;

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
     * @return UnixSupported Date
     */
    public UnixSupportedDate toDate() {
            return new UnixSupportedDate(DateUtil.getString(toJavaDate()));
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
     * @return Java date
     */
    public java.util.Date toJavaDate() {
        return new java.util.Date(format == UnixFormat.MILLISECONDS ? data : data * 1000L);
    }

    /**
     * Gets the timestamp at this current date
     * @return UnixTimestamp of the current date and time
     */
    public static UnixTimestamp current() {
        return new UnixTimestamp(DateUtil.now().getTime(), UnixFormat.MILLISECONDS);
    }

    /**
     * Gets a UnixTimestamp from the date
     * @param date Date to get timestamp from
     * @return Timestamp
     */
    public static UnixTimestamp fromDate(Date date) {
        return new UnixTimestamp(DateUtil.fromString(date.toString()).getTime(), UnixFormat.MILLISECONDS);
    }
}
