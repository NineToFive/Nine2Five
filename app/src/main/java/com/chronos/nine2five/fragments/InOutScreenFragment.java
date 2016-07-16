package com.chronos.nine2five.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chronos.nine2five.R;
import com.chronos.nine2five.datastructures.InOut;
import com.chronos.nine2five.datastructures.InOutListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 16/07/2016.
 */
public class InOutScreenFragment extends Fragment {

    private static final String TAG = InOutScreenFragment.class.getSimpleName();

    private List<InOutListItem> mInOutList;

    public InOutScreenFragment() {
        mInOutList = new ArrayList<>();

    }

    public static InOutScreenFragment newInstance() {
        InOutScreenFragment inOutScreenFragment = new InOutScreenFragment();
        return inOutScreenFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.inout_screen_layout, container, false);

        return mView;
    }
}
