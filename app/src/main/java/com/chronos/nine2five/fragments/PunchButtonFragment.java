package com.chronos.nine2five.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.chronos.nine2five.CircleProgressBar;
import com.chronos.nine2five.utils.Helpers;
import com.chronos.nine2five.R;

/**
 * Created by user on 16/04/2016.
 */
public class PunchButtonFragment extends Fragment {

    public interface PunchInOutHandler {
        void punchIn();
        void punchOut();
    }

    private static final String TAG = PunchButtonFragment.class.getSimpleName();

    private Handler mShiftHandler = new Handler();
    private long mStartShiftInMillis = 0L;
    private long mTotalShiftInMillis = 0L;
    private long mActiveShiftInMillis = 0L;

    private float mFullCircleInMins = 10 * 1000;

    private boolean mIsPunchedIn = false;

    private PunchInOutHandler mPunchInOutHandler;

    private CircleProgressBar mCircleProgressBar;
    private Button mPunchButton;
    private TextView mActiveTaskName;
    private TextView mActiveTaskDuration;

    public PunchButtonFragment() {    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.punch_button_layout, container, false);

        mPunchButton = (Button) mView.findViewById(R.id.punch_button);
        mActiveTaskName = (TextView) mView.findViewById(R.id.active_task_name_txt);
        mActiveTaskDuration = (TextView) mView.findViewById(R.id.active_task_duration_txt);
        mCircleProgressBar = (CircleProgressBar) mView.findViewById(R.id.CircularProgressBar);

        mPunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPunchedIn()) {
                    mIsPunchedIn = false;
                    mPunchButton.setText(R.string.punch_in_btn_lbl);
                    mTotalShiftInMillis += mActiveShiftInMillis;
                    mShiftHandler.removeCallbacks(updateTimerThread);
                    mPunchInOutHandler.punchOut();
                } else {
                    mIsPunchedIn = true;
                    mPunchButton.setText(R.string.punch_out_btn_lbl);
                    mStartShiftInMillis = SystemClock.uptimeMillis();

                    mShiftHandler.postDelayed(updateTimerThread, 0);
                    mPunchInOutHandler.punchIn();
                }
            }
        });
        return mView;
    }

    public boolean isPunchedIn() {
        return mIsPunchedIn;
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            mActiveShiftInMillis = SystemClock.uptimeMillis() - mStartShiftInMillis;

            long mShiftInMillis = mTotalShiftInMillis + mActiveShiftInMillis;

            float mProgress = (float)mShiftInMillis / mFullCircleInMins;
            if ((mShiftInMillis + 1500)  > mFullCircleInMins) {
                mFullCircleInMins = mFullCircleInMins * 5;
                mProgress = (float)(mShiftInMillis + 2000) / mFullCircleInMins;
                mCircleProgressBar.animate(1f , 0f , mProgress, 2000);
            } else {
                mCircleProgressBar.setProgress(mProgress);
            }

            mPunchButton.setText(getString(R.string.punch_out_btn_lbl) + '\n' + Helpers.FormatTimeString(mShiftInMillis));
            mShiftHandler.postDelayed(this, 0);
        }

    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mPunchInOutHandler = (PunchInOutHandler) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement PunchInOutHandler");
        }
    }

    @Override
    public void onDestroy() {
        mShiftHandler.removeCallbacks(updateTimerThread);
        mCircleProgressBar.onDestroy();
        super.onDestroy();
    }

    public void setCurrentTask(String currentTask) {
        mActiveTaskName.setText(currentTask);
    }
}
