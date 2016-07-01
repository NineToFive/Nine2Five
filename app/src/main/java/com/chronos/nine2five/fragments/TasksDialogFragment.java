package com.chronos.nine2five.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
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

    private static final String PROJECT_NAME = "projectName";
    private String mProjectName;
    private List<String> mTasksArray;

    public static TasksDialogFragment newInstance(String selectedProject, List<String> tasksList) {
        TasksDialogFragment tasksDialogFragment = new TasksDialogFragment();
        tasksDialogFragment.setProjectName(selectedProject);
        tasksDialogFragment.setTasksArray(tasksList);
        return tasksDialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

        final TasksAdapter adapter = new TasksAdapter(getActivity(), mTasksArray);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.tasks_dialog_layout,null);
        ListView tasksListView = (ListView) dialogView.findViewById(R.id.taskslistView);
        EditText taskToSearch = (EditText) dialogView.findViewById(R.id.editTextTaskSearch);
        tasksListView.setAdapter(adapter);

        //tasksListView.setOnItemClickListener();
        taskToSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });


        dialogBuilder.setTitle(getProjectName() + " - Please select a task");
        dialogBuilder.setView(dialogView);

//        dialogBuilder.setAdapter(adapter, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getActivity(), mTasksArray.get(which) + " was selected", Toast.LENGTH_SHORT).show();
//            }
//        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Cancel was pressed", Toast.LENGTH_SHORT).show();
            }
        });

        return dialogBuilder.create();

//        return new AlertDialog.Builder(getActivity())
//                .setTitle(getProjectName() + " - Please select a task")
//                .setView(inflater.inflate(R.layout.tasks_dialog_layout, null))
////                .setItems(Constants.TASKS_LIST, new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int which) {
////                        Toast.makeText(getActivity(), Constants.TASKS_LIST[which] + " was selected", Toast.LENGTH_SHORT).show();
////                    }
////
////                })
//                .setAdapter(adapter, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(getActivity(), mTasksArray.get(which) + " was selected", Toast.LENGTH_SHORT).show();
//                    }
//                })
////                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int which) {
////                        Toast.makeText(getActivity(), "OK was pressed", Toast.LENGTH_SHORT).show();
////                    }
////                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(getActivity(), "Cancel was pressed", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .create();

    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putString(PROJECT_NAME, getProjectName());
//    }



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

    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }
}
