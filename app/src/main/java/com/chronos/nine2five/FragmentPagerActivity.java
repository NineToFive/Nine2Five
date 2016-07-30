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
import com.chronos.nine2five.datastructures.User;
import com.chronos.nine2five.datastructures.inout.InOut;
import com.chronos.nine2five.datastructures.inout.InOutHandler;
import com.chronos.nine2five.fragments.InOutScreenFragment;
import com.chronos.nine2five.fragments.PunchButtonFragment;
import com.chronos.nine2five.fragments.ProjectsScreenFragment;
import com.chronos.nine2five.fragments.TasksDialogFragment;
import com.chronos.nine2five.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentPagerActivity extends AppCompatActivity
        implements ProjectsScreenFragment.OnTaskSelectedListener,
        PunchButtonFragment.PunchInOutHandler,
        TasksDialogFragment.TasksDialogListener {

    private static final String TAG = FragmentPagerActivity.class.getSimpleName();
    private static final int PUNCH_BUTTON_FRAG_POSITION = 0;
    private static final int PROJECTS_SCREEN_FRAG_POSITION = 1;
    private static final int INOUT_SCREEN_FRAG_POSITION = 2;

    private User mUser;
    private List<InOut> mInOutList;
    private ViewPager mFragmentPager;
    private PagerAdapter mPagerAdapter;
    private InOutHandler mInOutHandler;

    //// TODO: 09/07/2016 need to enhance the project list to a better data structure
    private List<Project> mProjectsArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_pager);
        setProjectsList();

        setCurrentUser();
        setInOutList();
        mInOutHandler = new InOutHandler(mUser, mInOutList);

        mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), getList());
        mFragmentPager = (ViewPager) findViewById(R.id.fragment_pager);
        if (mFragmentPager != null) {
            mFragmentPager.setAdapter(mPagerAdapter);
        }

    }

    private void setCurrentUser() {
        mUser = new User("IXB2", "Green Lemur");
    }

    private void setInOutList() {
        mInOutList = new ArrayList<>();
    }

    public List<Fragment> getList() {
        List<Fragment> mListOfFragments = new ArrayList<>();
        PunchButtonFragment mFragment0 = new PunchButtonFragment();
        mListOfFragments.add(mFragment0);
        ProjectsScreenFragment mFragment1 = ProjectsScreenFragment.newInstance(mProjectsArray);
        mListOfFragments.add(mFragment1);
        InOutScreenFragment mFragment2 = InOutScreenFragment.newInstance(mInOutHandler.getInOutListItems());
        mListOfFragments.add(mFragment2);
        return mListOfFragments;
    }

    @Override
    public void setCurrentTask(String currentTask) {
        ((PunchButtonFragment) mPagerAdapter
                .getItem(PUNCH_BUTTON_FRAG_POSITION))
                .setCurrentTask(currentTask);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Project project = ((TasksDialogFragment) dialog).getProject();
        String projectName = project.getDescription();
        String taskName = project.getActiveTask().getDescription();
        setCurrentTask(projectName + "\n" + taskName);

        ((ProjectsScreenFragment) mPagerAdapter.getItem(PROJECTS_SCREEN_FRAG_POSITION))
                .setActiveProject(project);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(this, "Cancel was selected", Toast.LENGTH_SHORT).show();
    }

    private void setProjectsList() {
        mProjectsArray.addAll(Arrays.asList(Constants.PROJECTS_LIST));
    }


    @Override
    public void punchIn() {
        mInOutHandler.punchIn();
        ((InOutScreenFragment) mPagerAdapter.getItem(INOUT_SCREEN_FRAG_POSITION))
                .setInOutList(mInOutHandler.getInOutListItems());
    }

    @Override
    public void punchOut() {
        mInOutHandler.punchOut();
        ((InOutScreenFragment) mPagerAdapter.getItem(INOUT_SCREEN_FRAG_POSITION))
                .setInOutList(mInOutHandler.getInOutListItems());
        ((InOutScreenFragment) mPagerAdapter.getItem(INOUT_SCREEN_FRAG_POSITION))
                .getInOutListAdapter().notifyDataSetChanged();
    }
}
