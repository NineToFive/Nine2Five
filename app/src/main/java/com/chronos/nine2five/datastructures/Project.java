package com.chronos.nine2five.datastructures;

import java.util.List;

/**
 * Created by user on 08/07/2016.
 */
public class Project extends BaseClass {

    private int mCurrentTaskPosition;
    private List<ProjectTask> mTasksList;

    public Project(String mCode, String mDescription, int mCurrentTaskPosition, List<ProjectTask> mTasksList) {
        super(mCode, mDescription);
        this.mCurrentTaskPosition = mCurrentTaskPosition;
        this.mTasksList = mTasksList;
    }

    public int getCurrentTaskPosition() {
        return mCurrentTaskPosition;
    }

    public void setCurrentTaskPosition(int mCurrentTaskPosition) {
        this.mCurrentTaskPosition = mCurrentTaskPosition;
    }

    public List<ProjectTask> getTasksList() {
        return mTasksList;
    }

    public ProjectTask getTask(int position) {
        return mTasksList.get(position);
    }
}
