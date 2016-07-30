package com.chronos.nine2five.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.chronos.nine2five.R;
import com.chronos.nine2five.adapters.InOutListAdapter;
import com.chronos.nine2five.datastructures.inout.InOutListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 16/07/2016.
 */
public class InOutScreenFragment extends Fragment {

    private static final String TAG = InOutScreenFragment.class.getSimpleName();

    private InOutListAdapter mInOutListAdapter;

    private List<InOutListItem> mInOutList;

    public static InOutScreenFragment newInstance(List<InOutListItem> inOutListItems) {
        InOutScreenFragment inOutScreenFragment = new InOutScreenFragment();
        inOutScreenFragment.setInOutList(inOutListItems);
        return inOutScreenFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.inout_screen_layout, container, false);
        mInOutListAdapter = new InOutListAdapter(getContext(), mInOutList);
        ListView mTasksListView = (ListView) mView.findViewById(R.id.inOutListView);
        mTasksListView.setAdapter(mInOutListAdapter);
        return mView;
    }

    public void setInOutList(List<InOutListItem> inOutList) {
        this.mInOutList = inOutList;
    }

    public InOutListAdapter getInOutListAdapter() {
        return mInOutListAdapter;
    }


}
