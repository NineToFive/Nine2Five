package com.chronos.nine2five.datastructures;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 09/07/2016.
 */
public class InOut {

    protected String mUserCode;
    protected Date mDate;
    protected boolean isIn;

    public InOut(String mUserCode, boolean isIn) {
        this(mUserCode, isIn, null);
    }

    public InOut(String mUserCode, boolean isIn, Date mDate) {
        this.mUserCode = mUserCode;
        this.isIn = isIn;
        if (mDate != null) {
            this.mDate = mDate;
        } else {
            this.mDate = new Date();
        }
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }

    public String getUserCode() {
        return mUserCode;
    }

    public void setUserCode(String userCode) {
        this.mUserCode = userCode;
    }

    public boolean isIn() {
        return isIn;
    }

    public void setIn(boolean in) {
        isIn = in;
    }
}
