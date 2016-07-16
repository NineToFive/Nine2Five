package com.chronos.nine2five.fragments;

import android.content.Context;
import android.os.Bundle;
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
import com.chronos.nine2five.datastructures.Project;
import com.chronos.nine2five.utils.Constants;

import java.util.List;

/**
 * Created by user on 16/04/2016.
 */
public class ProjectsScreenFragment extends Fragment {
    private static final String TAG = ProjectsScreenFragment.class.getSimpleName();
    private List<Project> mProjectsArray;
    private OnTaskSelectedListener mCallBack;
    private ProjectsAdapter mProjectsAdapter;
    private Project mActiveProject;

    public static ProjectsScreenFragment newInstance(List<Project> theListOfProjects) {
        ProjectsScreenFragment projectsScreenFragment = new ProjectsScreenFragment();
        projectsScreenFragment.setProjectsArray(theListOfProjects);
        return projectsScreenFragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallBack = (OnTaskSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnTaskSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.projects_screen_layout, container, false);

        mProjectsAdapter = new ProjectsAdapter(getContext(), mProjectsArray);
        ListView mTasksListView = (ListView) mView.findViewById(R.id.projectslistView);
        mTasksListView.setAdapter(mProjectsAdapter);

        mTasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TasksDialogFragment tasksDialogFragment =
                        TasksDialogFragment.newInstance(mProjectsAdapter.getItem(position));
                tasksDialogFragment.show(getFragmentManager(), Constants.TASKS_DIALOG);
            }
        });

        EditText mEditTextProjectSearch = (EditText) mView.findViewById(R.id.editTextProjectSearch);
        mEditTextProjectSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mProjectsAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return mView;
    }

    public void setProjectsArray(List<Project> projectsArray) {
        this.mProjectsArray = projectsArray;
    }

    public void clearAllProjectsCheckBoxes() {
        for (Project project : mProjectsArray) {
            project.setActive(false);
        }
    }

    @Override
    public void onDestroyView() {
        mCallBack = null;
        super.onDestroyView();
    }


    public interface OnTaskSelectedListener {
        public void setCurrentTask(String currentTask);
    }

    public Project getActiveProject() {
        return mActiveProject;
    }

    public void setActiveProject(Project activeProject) {
        if (this.mActiveProject != activeProject) {
            this.mActiveProject = activeProject;
            clearAllProjectsCheckBoxes();
            this.mActiveProject.setActive(true);
            mProjectsAdapter.notifyDataSetChanged();
        }
    }
}
