package com.agentdid127.date.unix;

import com.agentdid127.date.Date;

public class UnixSupportedDate extends Date {

    public UnixSupportedDate(Date date) {
        super(date.toString());
    }

    public UnixSupportedDate(int year, int month, int day, int hour, int minute, int second, int milli) {
        super(year, month, day, hour, minute, second, milli);
    }

    public UnixSupportedDate(String dateIn) {
        super(dateIn);
    }

    public UnixTimestamp getUnixSeconds() {
        return new UnixTimestamp(getUnixMilli().getData()/1000, UnixFormat.SECONDS);
    }

    public UnixTimestamp getUnixMilli() {
        return UnixTimestamp.fromDate(this);
    }
}
