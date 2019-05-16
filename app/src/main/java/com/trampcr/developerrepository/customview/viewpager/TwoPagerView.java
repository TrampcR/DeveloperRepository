package com.trampcr.developerrepository.customview.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.OverScroller;

/**
 * Created by trampcr on 2019/5/16.
 */

public class TwoPagerView extends ViewGroup {
    public static final String TAG = TwoPagerView.class.getSimpleName();

    private ViewConfiguration mViewConfiguration;
    private VelocityTracker mVelocityTracker;
    private OverScroller mOverScroller;

    private float mDownX;
    private float mDownY;
    private float mDownScrollX;
    private float mMinVelocity;
    private float mMaxVelocity;
    private boolean mScrolling;

    public TwoPagerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        if (context == null) {
            return;
        }

        mViewConfiguration = ViewConfiguration.get(context);
        mVelocityTracker = VelocityTracker.obtain();
        mMinVelocity = mViewConfiguration.getScaledMinimumFlingVelocity();
        mMaxVelocity = mViewConfiguration.getScaledMaximumFlingVelocity();
        mOverScroller = new OverScroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft = 0;
        int childTop = 0;
        int childRight = getWidth();
        int childBottom = getHeight();

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.layout(childLeft, childTop, childRight, childBottom);

            childLeft += getWidth();
            childRight += getWidth();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = false;
        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
            mVelocityTracker.clear();
        }
        Log.d(TAG, "onInterceptTouchEvent: ");

        mVelocityTracker.addMovement(ev);

        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onInterceptTouchEvent: down");
                mScrolling = false;
                mDownX = ev.getX();
                mDownY = ev.getY();
                mDownScrollX = getScrollX();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onInterceptTouchEvent: move");
                float dx = mDownX - ev.getX();
                if (!mScrolling) {
                    if (Math.abs(dx) >= mViewConfiguration.getScaledPagingTouchSlop()) {
                        Log.d(TAG, "onInterceptTouchEvent: move > slop");
                        getParent().requestDisallowInterceptTouchEvent(true);
                        mScrolling = true;
                        result = true;
                    }
                }
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            mVelocityTracker.clear();
        }

        Log.d(TAG, "onTouchEvent: ");

        mVelocityTracker.addMovement(event);

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent: down");
                mDownX = event.getX();
                mDownY = event.getY();
                mDownScrollX = getScrollX();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouchEvent: move");
                float dx = mDownX + mDownScrollX - event.getX();
                if (dx >= getWidth() * 2) {
                    dx = getWidth() * 2;
                } else if (dx < 0){
                    dx = 0;
                }

                scrollTo((int) dx, 0);
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: up");
                mVelocityTracker.computeCurrentVelocity(1000, mMaxVelocity);
                float velocityX = mVelocityTracker.getXVelocity();
                int scrollX = getScrollX();
                int targetPage;
                if (Math.abs(velocityX) < mMinVelocity) {
                    targetPage = scrollX > getWidth() / 2 ? 1 : 0;
                } else {
                    targetPage = velocityX < 0 ? 1 : 0;
                }

                int scrollDistance = targetPage == 1 ? (getWidth() - scrollX) : -scrollX;
                mOverScroller.startScroll(getScrollX(), 0, scrollDistance, 0);
                postInvalidateOnAnimation();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        if (mOverScroller.computeScrollOffset()) {
            scrollTo(mOverScroller.getCurrX(), mOverScroller.getCurrY());
            postInvalidateOnAnimation();
        }
    }
}
