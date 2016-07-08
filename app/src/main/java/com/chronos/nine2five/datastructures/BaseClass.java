package com.chronos.nine2five.datastructures;

import java.util.Date;

/**
 * Created by user on 08/07/2016.
 */
public abstract class BaseClass {
    private String mCode;
    private String mDescription;
    private Date mExpiryDate;

    public BaseClass(String mCode, String mDescription) {
        this.mCode = mCode;
        this.mDescription = mDescription;
        this.mExpiryDate = new Date(0);
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String mCode) {
        this.mCode = mCode;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public Date getExpiryDate() {
        return mExpiryDate;
    }

    public void setExpiryDate(Date mExpiryDate) {
        this.mExpiryDate = mExpiryDate;
    }
}

