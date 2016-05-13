package com.chronos.nine2five.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.chronos.nine2five.R;
import com.chronos.nine2five.adapters.TasksAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 16/04/2016.
 */
public class TaskScreenFragment extends Fragment{
    private static final String TAG = TaskScreenFragment.class.getSimpleName();
    private List<String> tasksArray = new ArrayList<>();
    private ListView mTasksListView;
    private OnTaskSelectedListener mCallBack;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallBack = (OnTaskSelectedListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnTaskSelectedListener");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.tasks_screen_layout, container, false);

        TasksAdapter arrayAdapter = new TasksAdapter(getContext(), getTasksList());
        mTasksListView = (ListView)mView.findViewById(R.id.taskslistView);
        mTasksListView.setAdapter(arrayAdapter);

        mTasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String taskString = parent.getItemAtPosition(position).toString();
                mCallBack.setCurrentTask(taskString);
                Toast.makeText(getContext(), "The task " + taskString + " has been selected", Toast.LENGTH_SHORT).show();
            }
        });

        return mView;
    }

    @Override
    public void onDestroyView() {
        mCallBack = null;
        super.onDestroyView();
    }

    public interface OnTaskSelectedListener {
        public void setCurrentTask(String currentTask);
    }

    private List<String> getTasksList() {
        tasksArray.add("Programming");
        tasksArray.add("Documentation");
        tasksArray.add("Meeting");
        tasksArray.add("Professional Support");
        return  tasksArray;
    }
}
