package com.chronos.nine2five;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ObjectAnimator mProgressBarAnimator;

    private Handler mShiftHandler = new Handler();
    private long mStartShiftInMillis = 0L;
    private long mTotalShiftInMillis = 0L;
    private long mActiveShiftInMillis = 0L;

    private float mFullCircleInMins = 10 * 1000;

    private boolean mIsPunchedIn = false;

    private CircleProgressBar mCircleProgressBar;
    private Button mPunchButton;
//    private TextView mActiveShiftDuration;
    private TextView mActiveTaskName;
    private TextView mActiveTaskDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPunchButton = (Button)findViewById(R.id.punch_button);
        //mActiveShiftDuration = (TextView)findViewById(R.id.active_shift_duration_txt);
        mActiveTaskName = (TextView)findViewById(R.id.active_task_name_txt);
        mActiveTaskDuration = (TextView)findViewById(R.id.active_task_duration_txt);
        mCircleProgressBar = (CircleProgressBar) findViewById(R.id.CircularProgressBar);

        mPunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPunchedIn()) {
                    mIsPunchedIn = false;
                    mPunchButton.setText(R.string.punch_in_btn_lbl);
                    mTotalShiftInMillis += mActiveShiftInMillis;
                    mShiftHandler.removeCallbacks(updateTimerThread);
                } else {
                    mIsPunchedIn = true;
                    mPunchButton.setText(R.string.punch_out_btn_lbl);
                    mStartShiftInMillis = SystemClock.uptimeMillis();
                    mShiftHandler.postDelayed(updateTimerThread, 0);
                }
            }
        });
    }

    public boolean isPunchedIn() {
        return mIsPunchedIn;
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            mActiveShiftInMillis = SystemClock.uptimeMillis() - mStartShiftInMillis;

            long mShiftInMillis = mTotalShiftInMillis + mActiveShiftInMillis;

            float mProgress = (float)mShiftInMillis / mFullCircleInMins;
            if ((mShiftInMillis + 2000)  > mFullCircleInMins) {
                mFullCircleInMins = mFullCircleInMins * 5;
                mProgress = (float)mShiftInMillis / mFullCircleInMins;
                animate(mCircleProgressBar, 1f , 0f , mProgress, 2000);
            } else {
                mCircleProgressBar.setProgress(mProgress);
            }

            // Log.d(TAG," " + mActiveShiftInMillis);
            int secs = (int) (mShiftInMillis / 1000 % 60);
            int mins = (int) (mShiftInMillis / (60 * 1000) % 60);
            int hours = (int) (mShiftInMillis / (60 * 60 * 1000) % 24);
            mPunchButton.setText(getString(R.string.punch_out_btn_lbl) + '\n'
                            + String.format("%02d", hours) + ":"
                            + String.format("%02d", mins) + ":"
                            + String.format("%02d", secs));
            mShiftHandler.postDelayed(this, 0);
        }

    };

    private void animate(final CircleProgressBar progressBar, final float fromProgress,final float midProgress ,final float toProgress,final int duration) {

        mProgressBarAnimator = ObjectAnimator.ofFloat(progressBar, "progress",fromProgress,midProgress, toProgress);
        mProgressBarAnimator.setInterpolator(new DecelerateInterpolator());
        mProgressBarAnimator.setDuration(duration);

        mProgressBarAnimator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationCancel(final Animator animation) {
            }

            @Override
            public void onAnimationEnd(final Animator animation) {
                float tempProgress = (float)duration / mFullCircleInMins;
                progressBar.setProgress(toProgress + tempProgress);
            }

            @Override
            public void onAnimationRepeat(final Animator animation) {
            }

            @Override
            public void onAnimationStart(final Animator animation) {
            }
        });

        mProgressBarAnimator.reverse();
        mProgressBarAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(final ValueAnimator animation) {
                progressBar.setProgress((Float) animation.getAnimatedValue());
            }
        });
        mProgressBarAnimator.start();
    }

    @Override
    protected void onDestroy() {
        if (mProgressBarAnimator.isRunning()){
            mProgressBarAnimator.end();
        }
        super.onDestroy();
    }
}

