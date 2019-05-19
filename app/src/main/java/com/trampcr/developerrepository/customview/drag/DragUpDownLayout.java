package com.trampcr.developerrepository.customview.drag;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import com.trampcr.developerrepository.R;

public class DragUpDownLayout extends FrameLayout {
    public static final String TAG = DragUpDownLayout.class.getSimpleName();
    
    private View mDragView;
    private ViewDragHelper mViewDragHelper;
    private ViewConfiguration mViewConfiguration;
    private DragCallback mDragCallback = new DragCallback();

    public DragUpDownLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mViewDragHelper = ViewDragHelper.create(this, mDragCallback);
        mViewConfiguration = ViewConfiguration.get(getContext());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mDragView = findViewById(R.id.btn_drag_up_down_view);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent: ");
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: ");
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    private class DragCallback extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            Log.d(TAG, "tryCaptureView: ");
            return true;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            Log.d(TAG, "clampViewPositionVertical: ");
            return top;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            Log.d(TAG, "onViewReleased: ");
            if (Math.abs(yvel) > mViewConfiguration.getScaledMinimumFlingVelocity()) {
                if (yvel > 0) {
                    mViewDragHelper.settleCapturedViewAt(0,
                            getHeight() - releasedChild.getHeight());
                } else {
                    mViewDragHelper.settleCapturedViewAt(0, 0);
                }
            } else {
                if (releasedChild.getTop() > getHeight() - releasedChild.getBottom()) {
                    mViewDragHelper.settleCapturedViewAt(0,
                            getHeight() - releasedChild.getHeight());
                } else {
                    mViewDragHelper.settleCapturedViewAt(0, 0);
                }
            }
            postInvalidateOnAnimation();
        }
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
