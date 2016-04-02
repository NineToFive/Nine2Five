package com.chronos.nine2five;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    private Handler mShiftHandler = new Handler();
    private long mStartShiftInMillis = 0L;
    private long mTotalShiftInMillis = 0L;
    private long mActiveShiftInMillis = 0L;

    private boolean mIsPunchedIn = false;

    private Button mPunchButton;
    private TextView mActiveShiftDuration;
    private TextView mActiveTaskName;
    private TextView mActiveTaskDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPunchButton = (Button)findViewById(R.id.punch_button);
        mActiveShiftDuration = (TextView)findViewById(R.id.active_shift_duration_txt);
        mActiveTaskName = (TextView)findViewById(R.id.active_task_name_txt);
        mActiveTaskDuration = (TextView)findViewById(R.id.active_task_duration_txt);

        mPunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPunchedIn()) {
                    mIsPunchedIn = false;
                    mPunchButton.setText(R.string.punch_in_btn_lbl);
//                    timeSwapBuff += timeInMilliseconds;
//                    customHandler.removeCallbacks(updateTimerThread);
                    mActiveShiftDuration.setText("You are punched out");
                } else {
                    mIsPunchedIn = true;
                    mPunchButton.setText(R.string.punch_out_btn_lbl);
//                    startTime = SystemClock.uptimeMillis();
//                    customHandler.postDelayed(updateTimerThread, 0);
                    mActiveShiftDuration.setText("You are punched in");
                }
            }
        });
    }

    public boolean isPunchedIn() {
        return mIsPunchedIn;
    }

//    private Runnable updateTimerThread = new Runnable() {
//
//        public void run() {
//
//            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
//
//            updatedTime = timeSwapBuff + timeInMilliseconds;
//
//            int secs = (int) (updatedTime / 1000);
//            int mins = secs / 60;
//            secs = secs % 60;
//            int milliseconds = (int) (updatedTime % 1000);
//            timerValue.setText("" + mins + ":"
//                    + String.format("%02d", secs) + ":"
//                    + String.format("%03d", milliseconds));
//            customHandler.postDelayed(this, 0);
//        }
//
//    };
}

