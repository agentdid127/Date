package com.agentdid127.date.unix;

import com.agentdid127.date.Date;
import com.agentdid127.date.util.DateUtil;

/**
 * Updateable Date Subclass
 */
public class UpdatableDate {
    private java.util.Date date, createDate;

    /**
     * UpdatableDate
     * @param date Date to start updating
     */
    public UpdatableDate(Date date) {
        createDate = DateUtil.now();
        this.date = DateUtil.fromString(date.toString());
    }

    /**
     * Gets the updated date
     * @return Updated date
     */
    public Date getDate() {
        long diff = DateUtil.now().getTime() - createDate.getTime();
        return new Date(DateUtil.getString(new java.util.Date(date.getTime() + diff)));
    }
}
