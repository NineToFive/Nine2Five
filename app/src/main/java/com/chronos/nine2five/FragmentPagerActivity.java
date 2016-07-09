package com.chronos.nine2five;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.chronos.nine2five.adapters.PagerAdapter;
import com.chronos.nine2five.adapters.TasksAdapter;
import com.chronos.nine2five.datastructures.Project;
import com.chronos.nine2five.fragments.PunchButtonFragment;
import com.chronos.nine2five.fragments.ProjectsScreenFragment;
import com.chronos.nine2five.fragments.TasksDialogFragment;
import com.chronos.nine2five.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentPagerActivity extends AppCompatActivity implements ProjectsScreenFragment.OnTaskSelectedListener,
        TasksDialogFragment.TasksDialogListener {

    private static final String TAG = FragmentPagerActivity.class.getSimpleName();
    private static final int PUNCH_BUTTON_FRAG_POSITION = 0;
    private static final int PROJECTS_SCREEN_FRAG_POSITION = 1;

    private ViewPager mFragmentPager;
    private PagerAdapter mPagerAdapter;

    //// TODO: 09/07/2016 need to enhance the project list to a better data structure
    private List<Project> mProjectsArray = new ArrayList<>();
    private int mCurrentProjectPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_pager);
        mCurrentProjectPosition = 0;
        setProjectsList();

        mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), getList());
        mFragmentPager = (ViewPager) findViewById(R.id.fragment_pager);
        if (mPagerAdapter != null) {
            mFragmentPager.setAdapter(mPagerAdapter);
        }
    }

    public List<Fragment> getList() {
        List<Fragment> mListOfFragments = new ArrayList<>();
        PunchButtonFragment mFragment0 = new PunchButtonFragment();
        mListOfFragments.add(mFragment0);
        ProjectsScreenFragment mFragment1 = ProjectsScreenFragment.newInstance(mProjectsArray, mCurrentProjectPosition);
        mListOfFragments.add(mFragment1);
        return mListOfFragments;
    }

    @Override
    public void setCurrentTask(String currentTask) {
        ((PunchButtonFragment) mPagerAdapter.getItem(PUNCH_BUTTON_FRAG_POSITION)).setCurrentTask(currentTask);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        mCurrentProjectPosition = ((TasksDialogFragment) dialog).getSelectedProjectPosittion();
        int currentTaskPosition = ((TasksDialogFragment) dialog).getSelectedTaskPosition();
        Project project = mProjectsArray.get(mCurrentProjectPosition);
        project.setCurrentTaskPosition(currentTaskPosition);
        ((ProjectsScreenFragment) mPagerAdapter.getItem(PROJECTS_SCREEN_FRAG_POSITION))
                .setCurrentProjectPosition(mCurrentProjectPosition, true);

        String projectName = project.getDescription();
        String taskName = project.getTask(currentTaskPosition).getDescription();
        setCurrentTask(projectName + "\n" + taskName);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(this, "Cancel was selected", Toast.LENGTH_SHORT).show();
    }

    private void setProjectsList() {
        mProjectsArray.addAll(Arrays.asList(Constants.PROJECTS_LIST));
    }
}
