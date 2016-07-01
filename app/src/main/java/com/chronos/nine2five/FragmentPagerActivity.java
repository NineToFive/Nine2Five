package com.chronos.nine2five;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.chronos.nine2five.adapters.PagerAdapter;
import com.chronos.nine2five.fragments.PunchButtonFragment;
import com.chronos.nine2five.fragments.ProjectsScreenFragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentPagerActivity extends AppCompatActivity implements ProjectsScreenFragment.OnTaskSelectedListener{

    private static final String TAG = FragmentPagerActivity.class.getSimpleName();

    private ViewPager mFragmentPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_pager);

        mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), getList());
        mFragmentPager = (ViewPager)findViewById(R.id.fragment_pager);
        mFragmentPager.setAdapter(mPagerAdapter);
    }

    public List<Fragment> getList(){
        List<Fragment> mListOfFragments = new ArrayList<Fragment>();
        PunchButtonFragment mFragment0 = new PunchButtonFragment();
        mListOfFragments.add(mFragment0);
        ProjectsScreenFragment mFragment1 = new ProjectsScreenFragment();
        mListOfFragments.add(mFragment1);
        return mListOfFragments;
    }

    @Override
    public void setCurrentTask(String currentTask) {
        ((PunchButtonFragment)mPagerAdapter.getItem(0)).setCurrentTask(currentTask);
    }

}
