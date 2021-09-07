package com.agentdid127.date;

public class UpdatableDate {

    private Date date, createDate;

    public UpdatableDate(Date date) {
        createDate = UnixTimestamp.current().toDate();
        this.date = date;
    }

    public Date getDate() {
        Date diff = UnixTimestamp.current().toDate().diff(createDate);
        return date.formattedSum(diff);
    }
}
