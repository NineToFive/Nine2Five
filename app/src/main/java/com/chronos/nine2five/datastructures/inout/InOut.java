package com.chronos.nine2five.datastructures.inout;

import com.chronos.nine2five.datastructures.User;

import java.util.Date;

/**
 * Created by user on 09/07/2016.
 */
public class InOut {

    protected User mUser;
    protected Date mInDate;
    protected Date mOutDate;


    public InOut(User mUser) {
        this(mUser, null, null);
    }

    public InOut(User mUser, Date inDate, Date outDate) {
        this.mUser = mUser;
        this.mInDate = inDate;
        this.mOutDate = outDate;
    }

    public Date getInDate() {
        return mInDate;
    }

    public void setInDate(Date inDate) {
        this.mInDate = inDate;
    }

    public Date getOutDate() {
        return mOutDate;
    }

    public void setOutDate(Date outDate) {
        this.mOutDate = outDate;
    }

    public User getUserCode() {
        return mUser;
    }

    public void setUserCode(User userCode) {
        this.mUser = userCode;
    }

}
