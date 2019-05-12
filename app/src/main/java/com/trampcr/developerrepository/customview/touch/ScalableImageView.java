package com.trampcr.developerrepository.customview.touch;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.OverScroller;

import com.trampcr.developerrepository.R;
import com.trampcr.developerrepository.utils.CommonUtils;
import com.trampcr.developerrepository.utils.DimenUtils;

public class ScalableImageView extends View {
    private static final String TAG = ScalableImageView.class.getSimpleName();
    private static final float SCALE_FACTOR = 1.5f;
    private static final int IMAGE_WIDTH = (int) DimenUtils.dp2px(200);

    private ScaleRunnable mScaleRunnable = new ScaleRunnable();
    private Paint mImagePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private SimpleGestureListener mSimpleGestureListener = new SimpleGestureListener();
    private ScaleGestureListener mScaleGestureListener = new ScaleGestureListener();
    private Bitmap mImageBitmap;
    private OverScroller mScaleScroller;
    private ObjectAnimator mScaleAnimator;
    private GestureDetectorCompat mGestureDetector;
    private ScaleGestureDetector mScaleGestureDetector;

    private boolean mIsBig;
    private float mBigScale;
    private float mSmallScale;
    private float mOffsetX;
    private float mOffsetY;
    private float mOriginalOffsetX;
    private float mOriginalOffsetY;
    private float mCurrentScale;

    public float getCurrentScale() {
        return mCurrentScale;
    }

    public void setCurrentScale(float currentScale) {
        this.mCurrentScale = currentScale;
        invalidate();
    }

    public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        if (context == null) {
            return;
        }

        mScaleScroller = new OverScroller(context);
        mImageBitmap = CommonUtils.getAvatar(context, IMAGE_WIDTH, R.mipmap.girl);
        mGestureDetector = new GestureDetectorCompat(context, mSimpleGestureListener);
        mScaleGestureDetector = new ScaleGestureDetector(context, mScaleGestureListener);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mOriginalOffsetX = (getWidth() - mImageBitmap.getWidth()) / 2f;
        mOriginalOffsetY = (getHeight() - mImageBitmap.getHeight()) / 2f;

        if ((float) mImageBitmap.getWidth() / getWidth() > (float) mImageBitmap.getHeight() / getHeight()) {
            mSmallScale = (float) getWidth() / mImageBitmap.getWidth();
            mBigScale = (float) getHeight() / mImageBitmap.getHeight() * SCALE_FACTOR;
        } else {
            mSmallScale = (float) getHeight() / mImageBitmap.getHeight();
            mBigScale = (float) getWidth() / mImageBitmap.getWidth() * SCALE_FACTOR;
        }

        mCurrentScale = mSmallScale;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float scaleFraction = (mCurrentScale - mSmallScale) / (mBigScale - mSmallScale);
        canvas.translate(mOffsetX * scaleFraction, mOffsetY * scaleFraction);
        canvas.scale(mCurrentScale, mCurrentScale, getWidth() / 2f, getHeight() / 2f);
        canvas.drawBitmap(mImageBitmap, mOriginalOffsetX, mOriginalOffsetY, mImagePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = mScaleGestureDetector.onTouchEvent(event);
        Log.d(TAG, "onTouchEvent: " + mScaleGestureDetector.isInProgress());
        if (!mScaleGestureDetector.isInProgress()) {
            result = mGestureDetector.onTouchEvent(event);
        }

        return result;
    }

    private ObjectAnimator getScaleAnimator() {
        if (mScaleAnimator == null) {
            mScaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", 0);
        }
        mScaleAnimator.setFloatValues(mSmallScale, mBigScale);

        return mScaleAnimator;
    }

    private class SimpleGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            mIsBig = !mIsBig;
            if (mIsBig) {
                mOffsetX = e.getX() - getWidth() / 2f - (e.getX() - getWidth() / 2f) * mBigScale / mSmallScale;
                mOffsetY = e.getY() - getHeight() / 2f - (e.getY() - getHeight() / 2f) * mBigScale / mSmallScale;
                fixOffsets();
                getScaleAnimator().start();
            } else {
                getScaleAnimator().reverse();
            }
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent down, MotionEvent event, float distanceX, float distanceY) {
            if (mIsBig) {
                mOffsetX -= distanceX;
                mOffsetY -= distanceY;
                fixOffsets();
                invalidate();
            }
            return false;
        }

        @Override
        public boolean onFling(MotionEvent down, MotionEvent event, float velocityX, float velocityY) {
            mScaleScroller.fling((int) mOffsetX, (int) mOffsetY, (int) velocityX, (int) velocityY,
                    -(int)((mImageBitmap.getWidth() * mBigScale - getWidth()) / 2f),
                    (int)((mImageBitmap.getWidth() * mBigScale - getWidth()) / 2f),
                    -(int)((mImageBitmap.getHeight() * mBigScale - getHeight()) / 2f),
                    (int)((mImageBitmap.getHeight() * mBigScale - getHeight()) / 2f));
            postOnAnimation(mScaleRunnable);
            return false;
        }
    }

    private class ScaleRunnable implements Runnable {
        @Override
        public void run() {
            if (mScaleScroller.computeScrollOffset()) {
                mOffsetX = mScaleScroller.getCurrX();
                mOffsetY = mScaleScroller.getCurrY();
                invalidate();
                postOnAnimation(mScaleRunnable);
            }
        }
    }

    private class ScaleGestureListener implements ScaleGestureDetector.OnScaleGestureListener {
        private float initScale;
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mCurrentScale = initScale * detector.getScaleFactor();
            invalidate();
            return false;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            initScale = mCurrentScale;
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
    }

    private void fixOffsets() {
        mOffsetX = Math.min(mOffsetX, (mImageBitmap.getWidth() * mBigScale - getWidth()) / 2f);
        mOffsetX = Math.max(mOffsetX, -(mImageBitmap.getWidth() * mBigScale - getWidth()) / 2f);
        mOffsetY = Math.min(mOffsetY, (mImageBitmap.getHeight() * mBigScale - getHeight()) / 2f);
        mOffsetY = Math.max(mOffsetY, -(mImageBitmap.getHeight() * mBigScale - getHeight()) / 2f);
    }
}
