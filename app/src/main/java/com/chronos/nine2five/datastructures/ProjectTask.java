package com.chronos.nine2five.datastructures;

/**
 * Created by user on 08/07/2016.
 */
public class ProjectTask extends BaseClass {

    public ProjectTask(String mCode, String mDescription) {
        super(mCode, mDescription);
    }

    public ProjectTask(ProjectTask task) {
        super(task.getCode(), task.getDescription());
    }
}
