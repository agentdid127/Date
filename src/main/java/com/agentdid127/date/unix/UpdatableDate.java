package com.agentdid127.date.unix;

import com.agentdid127.date.Date;

/**
 * Updateable Date Subclass
 */
public class UpdatableDate {
    private Date date, createDate;

    /**
     * UpdatableDate
     * @param date Date to start updating
     */
    public UpdatableDate(Date date) {
        createDate = UnixTimestamp.current().toDate();
        this.date = date;
    }

    /**
     * Gets the updated date
     * @return
     */
    public Date getDate() {
        Date diff = UnixTimestamp.current().toDate().diff(createDate);
        return date.formattedSum(diff);
    }
}
