package com.trampcr.developerrepository.customview.drag;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class DragHelperGridView extends ViewGroup {
    public static final String TAG = DragHelperGridView.class.getSimpleName();
    private static final int COLS = 2;
    private static final int ROWS = 3;

    private ViewDragHelper mViewDragHelper;
    public DragHelperGridView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mViewDragHelper = ViewDragHelper.create(this, new DragCallback());
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        Log.d(TAG, "onInterceptHoverEvent: ");
        return mViewDragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        Log.d(TAG, "onTouchEvent: ");
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int specHeight = MeasureSpec.getSize(heightMeasureSpec);
        int childWidth = getWidth() / COLS;
        int childHeight = getHeight() / ROWS;
        measureChildren(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY));

        setMeasuredDimension(specWidth, specHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft;
        int childTop;
        int childWidth = getWidth() / COLS;
        int childHeight = getHeight() / ROWS;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            childLeft = i % 2 * childWidth;
            childTop = i / 2 * childHeight;
            child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
        }
    }

    private class DragCallback extends ViewDragHelper.Callback {
        float captureLeft;
        float captureTop;
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            Log.d(TAG, "tryCaptureView: ");
            return true;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            Log.d(TAG, "onViewCaptured: ");
            capturedChild.setElevation(getElevation() + 1);
            captureLeft = capturedChild.getLeft();
            captureTop = capturedChild.getTop();
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onViewDragStateChanged(int state) {
            Log.d(TAG, "onViewDragStateChanged: ");
            if (state == ViewDragHelper.STATE_IDLE) {
                View captureView = mViewDragHelper.getCapturedView();
                if (captureView != null) {
                    captureView.setElevation(captureView.getElevation() - 1);
                }
            }
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Log.d(TAG, "clampViewPositionHorizontal: ");
            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            Log.d(TAG, "clampViewPositionVertical: ");
            return top;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            Log.d(TAG, "onViewReleased: ");
            mViewDragHelper.settleCapturedViewAt((int) captureLeft, (int) captureTop);
            postInvalidateOnAnimation();
        }
    }

    @Override
    public void computeScroll() {
        Log.d(TAG, "computeScroll: ");
        if (mViewDragHelper.continueSettling(true)) {
            Log.d(TAG, "computeScroll: +++");
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
