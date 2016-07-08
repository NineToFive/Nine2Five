package com.chronos.nine2five.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chronos.nine2five.R;
import com.chronos.nine2five.datastructures.ProjectTask;

import java.util.List;

/**
 * Created by user on 01/07/2016.
 */
public class TasksAdapter extends BaseItemsAdapter<ProjectTask>{

    private int mSelectedItemPosition = -1;

    public TasksAdapter(Context context, List<ProjectTask> objects, int selectedItemPosition) {
        super(context, objects);
        setSelectedItemPosition(selectedItemPosition);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_item, parent, false);
        }
        TextView itemView = (TextView)convertView.findViewById(R.id.task_item_text);
        RadioButton radioButton = (RadioButton) convertView.findViewById(R.id.task_item_rb);

        ProjectTask item = getItem(position);
        itemView.setText(item.getDescription());

        if (mSelectedItemPosition == position) {
            radioButton.setChecked(true);
        } else {
            radioButton.setChecked(false);
        }

        convertView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setSelectedItemPosition(position);
                TasksAdapter.this.notifyDataSetChanged();
            }
        });

        return convertView;
    }

    public int getSelectedItemPosition() {
        return mSelectedItemPosition;
    }

    public void setSelectedItemPosition(int mSelectedItempPosition) {
        this.mSelectedItemPosition = mSelectedItempPosition;
    }


}
