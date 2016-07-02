package com.chronos.nine2five.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.chronos.nine2five.R;
import com.chronos.nine2five.adapters.ProjectsAdapter;
import com.chronos.nine2five.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 16/04/2016.
 */
public class ProjectsScreenFragment extends Fragment  {
    private static final String TAG = ProjectsScreenFragment.class.getSimpleName();
    private List<String> mTasksArray = new ArrayList<>();
    private List<String> mProjectsArray = new ArrayList<>();
    private ListView mTasksListView;
    private EditText mEditTextProjectSearch;
    private OnTaskSelectedListener mCallBack;
    private ProjectsAdapter mArrayAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallBack = (OnTaskSelectedListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnTaskSelectedListener");
        }
        setProjectsList();
        setTasksList();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.projects_screen_layout, container, false);

        mArrayAdapter = new ProjectsAdapter(getContext(), mProjectsArray);
        mTasksListView = (ListView)mView.findViewById(R.id.projectslistView);
        mTasksListView.setAdapter(mArrayAdapter);

        mTasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedProject = parent.getItemAtPosition(position).toString();
                TasksDialogFragment tasksDialogFragment = TasksDialogFragment.newInstance(selectedProject, mTasksArray,1);
                tasksDialogFragment.show(getFragmentManager(), Constants.TASKS_DIALOG);
            }
        });

        mEditTextProjectSearch = (EditText)mView.findViewById(R.id.editTextProjectSearch);
        mEditTextProjectSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mArrayAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) { }
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

    private void setTasksList() {
        mTasksArray.addAll(Arrays.asList(Constants.TASKS_LIST));
    }

    private void setProjectsList() {
        mProjectsArray.addAll(Arrays.asList(Constants.PROJECTS_LIST));
    }

    private void ShowTaksDialog() {

    }
}
