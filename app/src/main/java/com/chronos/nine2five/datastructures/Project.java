package com.chronos.nine2five.datastructures;

import android.nfc.Tag;
import android.util.Log;

import java.util.List;

/**
 * Created by user on 08/07/2016.
 */
public class Project extends BaseClass {

    private static final String TAG = Project.class.getSimpleName();

    private List<ProjectTask> mTasksList;
    private ProjectTask mActiveTask;

    public Project(String mCode, String mDescription, List<ProjectTask> tasksList) {
        super(mCode, mDescription);
        this.mTasksList = tasksList;
    }

    public List<ProjectTask> getTasksList() {
        return mTasksList;
    }

    public ProjectTask getActiveTask() {
        return mActiveTask;
    }

    public void setActiveTask(ProjectTask activeTask) {
        if (activeTask != null) {
            this.mActiveTask = activeTask;
            this.mActiveTask.setActive(true);
            Log.d(TAG, "new current active task = " + mActiveTask.getDescription());
        }

    }
}
