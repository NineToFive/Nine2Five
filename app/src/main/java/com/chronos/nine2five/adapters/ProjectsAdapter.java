package com.chronos.nine2five.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chronos.nine2five.R;
import com.chronos.nine2five.datastructures.BaseClass;
import com.chronos.nine2five.datastructures.Project;

import java.util.List;

/**
 * Created by user on 01/07/2016.
 */
public class ProjectsAdapter extends BaseItemsAdapter<Project>{

    private static final String TAG = ProjectsAdapter.class.getSimpleName();
    private ViewHold holder;

    public ProjectsAdapter(Context context, List<Project> objects) {
        super(context, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.project_item, parent, false);
            holder = new ViewHold();
            holder.projectView = (TextView)convertView.findViewById(R.id.project_item);
            holder.projectCheckBox = (CheckBox)convertView.findViewById(R.id.projectCheckBox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHold) convertView.getTag();
        }

        Project project = getItem(position);
        holder.projectView.setText(project.getDescription());

        //if (mSelectedItemPosition == position) {
        if (project.isActive()) {
            holder.projectCheckBox.setChecked(true);
        } else {
            holder.projectCheckBox.setChecked(false);
        }

        return convertView;
    }
    private static class ViewHold {
        TextView projectView;
        CheckBox projectCheckBox;
    }
}
