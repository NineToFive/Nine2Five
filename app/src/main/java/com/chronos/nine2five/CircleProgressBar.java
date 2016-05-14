package com.chronos.nine2five;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by user on 09/04/2016.
 */
public class CircleProgressBar extends View {

    private static final String TAG = CircleProgressBar.class.getSimpleName();
    private static final String PROGRESS_BAR = "PROGRESS_BAR";
    private static final String PROGRESS_BAR_COLOR = "PROGRESS_BAR_COLOR";
    private static final String PROGRESS_BAR_BACKGROUND_COLOR = "PROGRESS_BAR_BACKGROUND_COLOR";
    private static final String CIRCLE_STROKE_WIDTH = "CIRCLE_STROKE_WIDTH";
    private static final String SAVED_STATE = "SAVED_STATE";

    private float mTranslationOffsetX;
    private float mTranslationOffsetY;

    private final RectF mCircleBounds = new RectF();
    private int mCircleStrokeWidth = 25;

    private Paint mBackgroundColorPaint = new Paint();
    private Paint mProgressColorPaint   = new Paint();

    private int mProgressBackgroundColor = Color.parseColor("#E1BEE7");
    private int mProgressColor = Color.parseColor("#9C27B0");

    private ObjectAnimator mProgressBarAnimator;

    private float mProgress = 0.0f;
    private float mRadius = 10;

    private boolean mIsInitializing = true;

    public CircleProgressBar(Context context) {
        super(context);
        updateBackgroundColor();
        updateProgressColor();
        mIsInitializing = false;
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        updateBackgroundColor();
        updateProgressColor();
        mIsInitializing = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mTranslationOffsetX, mTranslationOffsetY);
        float progressRotation = getProgress();
        canvas.drawArc(mCircleBounds, 270, 360, false, mBackgroundColorPaint);
        canvas.drawArc(mCircleBounds, 270, progressRotation, false, mProgressColorPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int height = getDefaultSize(
                getSuggestedMinimumHeight() + getPaddingTop() + getPaddingBottom(),
                heightMeasureSpec);
        int width = getDefaultSize(
                getSuggestedMinimumWidth() + getPaddingLeft() + getPaddingRight(),
                widthMeasureSpec);

        int diameter = Math.min(width, height);

        setMeasuredDimension(diameter, diameter);

        float halfWidth = diameter * 0.5f;
        float drawedWith = mCircleStrokeWidth * 0.5f;

        mRadius = halfWidth - drawedWith - 0.5f;

        mCircleBounds.set(-mRadius, -mRadius, mRadius, mRadius);
        mTranslationOffsetX = halfWidth + (width - diameter) / 2f;
        mTranslationOffsetY = halfWidth + (height - diameter) / 2f;

    }

    private float getProgress() {
        return 360 * mProgress;
    }

    public void setProgress(float progress) {
        if (progress == mProgress) {
            return;
        }

        if (progress == 1) {
            mProgress = 1;
        } else {
            mProgress = progress % 1.0f;
        }

        if (!mIsInitializing) {
            invalidate();
        }
    }

    public void setProgressBackgroundColor(int color) {
        mProgressBackgroundColor = color;
        updateBackgroundColor();
    }

    public void setProgressColor(int color) {
        mProgressColor = color;
        updateProgressColor();
    }

    public void setCircleStrokeWidth(int strokeWidth) {
        mCircleStrokeWidth = strokeWidth;
    }

    private void updateBackgroundColor() {
        mBackgroundColorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundColorPaint.setColor(mProgressBackgroundColor);
        mBackgroundColorPaint.setStyle(Paint.Style.STROKE);
        mBackgroundColorPaint.setStrokeCap(Paint.Cap.ROUND);
        mBackgroundColorPaint.setStrokeWidth(mCircleStrokeWidth);
        invalidate();
    }

    private void updateProgressColor() {
        mProgressColorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mProgressColorPaint.setColor(mProgressColor);
        mProgressColorPaint.setStyle(Paint.Style.STROKE);
        mProgressColorPaint.setStrokeCap(Paint.Cap.ROUND);
        mProgressColorPaint.setStrokeWidth(mCircleStrokeWidth);
        invalidate();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;

            super.onRestoreInstanceState(bundle.getParcelable(SAVED_STATE));

            int progressColor = bundle.getInt(PROGRESS_BAR_COLOR);
            if (progressColor != mProgressColor) {
                setProgressColor(progressColor);
            }

            int progressBackgroundColor = bundle.getInt(PROGRESS_BAR_BACKGROUND_COLOR);
            if (progressBackgroundColor != mProgressBackgroundColor) {
                setProgressBackgroundColor(progressBackgroundColor);
            }

            int circleStrokeWidth = bundle.getInt(CIRCLE_STROKE_WIDTH);
            if (circleStrokeWidth != mCircleStrokeWidth) {
                setCircleStrokeWidth(progressBackgroundColor);
            }

            setProgress(bundle.getFloat(PROGRESS_BAR));
            return;
        }

        super.onRestoreInstanceState(state);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(SAVED_STATE, super.onSaveInstanceState());
        bundle.putFloat(PROGRESS_BAR, mProgress);
        bundle.putInt(PROGRESS_BAR_COLOR, mProgressColor);
        bundle.putInt(PROGRESS_BAR_BACKGROUND_COLOR, mProgressBackgroundColor);
        bundle.putInt(CIRCLE_STROKE_WIDTH, mCircleStrokeWidth);
        return bundle;
    }

    public void animate(final float fromProgress,final float midProgress ,final float toProgress,final int duration) {

        mProgressBarAnimator = ObjectAnimator.ofFloat(this, "progress",fromProgress,midProgress, toProgress);
        mProgressBarAnimator.setInterpolator(new DecelerateInterpolator());
        mProgressBarAnimator.setDuration(duration);

        mProgressBarAnimator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationCancel(final Animator animation) {
            }

            @Override
            public void onAnimationEnd(final Animator animation) {
                setProgress(toProgress );
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
                setProgress((Float) animation.getAnimatedValue());
            }
        });
        mProgressBarAnimator.start();
    }

    public void onDestroy() {
        if (mProgressBarAnimator != null) {
            if (mProgressBarAnimator.isRunning()) {
                mProgressBarAnimator.end();
            }
        }
    }
}
