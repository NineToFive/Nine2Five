package com.chronos.nine2five.datastructures;

import java.util.Date;

/**
 * Created by user on 09/07/2016.
 */
public class InOutDetails extends InOut {

    private String mProjectCode;
    private String mTaskCode;

    public InOutDetails(User user) {
        super(user);
    }

    public InOutDetails(User user, Date inDate, Date outDate) {
        super(user, inDate, outDate);
    }

    public String getProjectCode() {
        return mProjectCode;
    }

    public void setProjectCode(String projectCode) {
        this.mProjectCode = projectCode;
    }

    public String getTaskCode() {
        return mTaskCode;
    }

    public void setTaskCode(String taskCode) {
        this.mTaskCode = taskCode;
    }
}
