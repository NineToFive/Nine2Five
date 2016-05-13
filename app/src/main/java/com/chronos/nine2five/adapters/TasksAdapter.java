package com.chronos.nine2five.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chronos.nine2five.R;

import java.util.List;

/**
 * Created by user on 13/05/2016.
 */
public class TasksAdapter extends ArrayAdapter<String> {

    public TasksAdapter(Context context, List<String> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String task = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_list_item, parent, false);
        }

        TextView taskView = (TextView)convertView.findViewById(R.id.task_item);
        taskView.setText(task);

        return convertView;
    }
}
