package com.chronos.nine2five.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.chronos.nine2five.R;
import com.chronos.nine2five.adapters.TasksAdapter;
import com.chronos.nine2five.datastructures.Project;
import com.chronos.nine2five.datastructures.ProjectTask;

import java.util.List;

/**
 * Created by user on 14/05/2016.
 */
public class TasksDialogFragment extends DialogFragment {

    private static final String TAG = TasksDialogFragment.class.getSimpleName();

    private static final String PROJECT_NAME = "projectName";
    private Project mProject;
    private ProjectTask mOriginalActiveTask;
    private List<ProjectTask> mTasksArray;
    private TasksDialogListener mTasksDialogListener;

    private TasksAdapter mTasksAdapter;

    public interface TasksDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    public static TasksDialogFragment newInstance(Project selectedProject) {
        TasksDialogFragment tasksDialogFragment = new TasksDialogFragment();
        tasksDialogFragment.setProject(selectedProject);
        tasksDialogFragment.setTasksArray(selectedProject.getTasksList());
        tasksDialogFragment.setOriginalActiveTask(selectedProject.getActiveTask());
        return tasksDialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mTasksDialogListener = (TasksDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement TasksDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

        mTasksAdapter = new TasksAdapter(getActivity(), mTasksArray,mProject);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.tasks_dialog_layout, null);
        ListView tasksListView = (ListView) dialogView.findViewById(R.id.taskslistView);
        EditText taskToSearch = (EditText) dialogView.findViewById(R.id.editTextTaskSearch);
        tasksListView.setAdapter(mTasksAdapter);

        taskToSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTasksAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        dialogBuilder.setTitle(mProject.getDescription() + " - Please select a task");
        dialogBuilder.setView(dialogView);

        // Buttons
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mTasksDialogListener.onDialogPositiveClick(TasksDialogFragment.this);
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mTasksAdapter.clearAll();
                mProject.setActiveTask(mOriginalActiveTask);
                mTasksDialogListener.onDialogNegativeClick(TasksDialogFragment.this);
            }
        });

        return dialogBuilder.create();

    }

    public Project getProject() {
        return mProject;
    }

    public void setProject(Project project) {
        this.mProject = project;
    }

    public List<ProjectTask> getTasksArray() {
        return mTasksArray;
    }

    public void setTasksArray(List<ProjectTask> tasksArray) {
        this.mTasksArray = tasksArray;
    }

    public void setOriginalActiveTask(ProjectTask originalActiveTask) {
        this.mOriginalActiveTask = originalActiveTask;
    }

    @Override
    public void onDestroyView() {
        mTasksDialogListener = null;
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }
}
