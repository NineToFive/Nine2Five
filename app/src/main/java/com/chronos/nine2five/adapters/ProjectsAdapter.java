package com.chronos.nine2five.adapters;

import android.content.Context;

import com.chronos.nine2five.datastructures.Project;

import java.util.List;

/**
 * Created by user on 01/07/2016.
 */
public class ProjectsAdapter extends BaseItemsAdapter<Project>{
    public ProjectsAdapter(Context context, List<Project> objects) {
        super(context, objects);
    }
}
