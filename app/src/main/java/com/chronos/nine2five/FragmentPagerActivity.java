package com.chronos.nine2five;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chronos.nine2five.adapters.PagerAdapter;
import com.chronos.nine2five.fragments.PunchButtonFragment;
import com.chronos.nine2five.fragments.TaskScreenFragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentPagerActivity extends AppCompatActivity {

    private static final String TAG = FragmentPagerActivity.class.getSimpleName();

    private ViewPager mFragmentPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_pager);

        mFragmentPager = (ViewPager)findViewById(R.id.fragment_pager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), getList());
        mFragmentPager.setAdapter(pagerAdapter);
    }

    public List<Fragment> getList(){
        List<Fragment> mListOfFragments = new ArrayList<Fragment>();
        PunchButtonFragment mFragment0 = new PunchButtonFragment();
        mListOfFragments.add(mFragment0);
        TaskScreenFragment mFragment1 = new TaskScreenFragment();
        mListOfFragments.add(mFragment1);
        return mListOfFragments;
    }
}
