package com.chronos.nine2five.fragments;

import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.chronos.nine2five.R;
import com.chronos.nine2five.adapters.TasksAdapter;
import com.chronos.nine2five.utils.Constants;

import java.util.List;

/**
 * Created by user on 14/05/2016.
 */
public class TasksDialogFragment extends DialogFragment {

    private static final String TAG = TasksDialogFragment.class.getSimpleName();

    private static final String PROJECT_NAME = "projectName";
    private String mProjectName;
    private List<String> mTasksArray;
    private int mSelectedTaskPosition = -1;
    private TasksDialogListener mTasksDialogListener;

    public interface TasksDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    public static TasksDialogFragment newInstance(String selectedProject, List<String> tasksList,
                                                  int selectedTaskPosition) {
        TasksDialogFragment tasksDialogFragment = new TasksDialogFragment();
        tasksDialogFragment.setProjectName(selectedProject);
        tasksDialogFragment.setTasksArray(tasksList);
        tasksDialogFragment.setSelectedTaskPosition(selectedTaskPosition);
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

        final TasksAdapter adapter = new TasksAdapter(getActivity(), mTasksArray, getSelectedTaskPosition());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.tasks_dialog_layout, null);
        ListView tasksListView = (ListView) dialogView.findViewById(R.id.taskslistView);
        EditText taskToSearch = (EditText) dialogView.findViewById(R.id.editTextTaskSearch);
        tasksListView.setAdapter(adapter);

        taskToSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        dialogBuilder.setTitle(getProjectName() + " - Please select a task");
        dialogBuilder.setView(dialogView);

        // Buttons
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setSelectedTaskPosition(adapter.getSelectedItemPosition());
                mTasksDialogListener.onDialogPositiveClick(TasksDialogFragment.this);
                //Toast.makeText(getActivity(), "OK was pressed", Toast.LENGTH_SHORT).show();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mTasksDialogListener.onDialogNegativeClick(TasksDialogFragment.this);
                //Toast.makeText(getActivity(), "Cancel was pressed", Toast.LENGTH_SHORT).show();
            }
        });

        return dialogBuilder.create();

    }

    public String getProjectName() {
        return mProjectName;
    }

    public void setProjectName(String mProjectName) {
        this.mProjectName = mProjectName;
    }

    public List<String> getTasksArray() {
        return mTasksArray;
    }

    public void setTasksArray(List<String> mTasksArray) {
        this.mTasksArray = mTasksArray;
    }

    public int getSelectedTaskPosition() {
        return mSelectedTaskPosition;
    }

    public void setSelectedTaskPosition(int mSelectedTaskPosition) {
        this.mSelectedTaskPosition = mSelectedTaskPosition;
    }

    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }
}
