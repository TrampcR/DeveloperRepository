package com.trampcr.developerrepository.customview.touch.dispatchdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 若 ViewGroup 拦截了一个半路的事件（如 MOVE），该事件将会被系统变成一个 CANCEL 事件
 * 传递给之前处理该事件的子 View，该事件不会再传递给 ViewGroup 的 onTouchEvent()
 * 只有再到来的事件才会传递到 ViewGroup 的 onTouchEvent()
 *
 * SubViewGroup: onInterceptTouchEvent: MotionEvent.ACTION_DOWN
 * SubView: onTouchEvent: MotionEvent.ACTION_DOWN
 * SubViewGroup: onInterceptTouchEvent: MotionEvent.ACTION_MOVE
 * SubView: onTouchEvent: MotionEvent.ACTION_CANCEL
 * SubViewGroup: SubViewGroup onTouchEvent: MotionEvent.ACTION_MOVE
 * SubViewGroup: SubViewGroup onTouchEvent: MotionEvent.ACTION_MOVE
 * ...
 * SubViewGroup: SubViewGroup onTouchEvent: MotionEvent.ACTION_UP
 */

public class SubViewGroup extends ViewGroup {
    public static final String TAG = SubViewGroup.class.getSimpleName();

    private boolean mResult = false;
    public SubViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.layout(l, t, r, b);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onInterceptTouchEvent: MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onInterceptTouchEvent: MotionEvent.ACTION_MOVE");
                mResult = true;
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onInterceptTouchEvent: MotionEvent.ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "onInterceptTouchEvent: MotionEvent.ACTION_CANCEL");
                break;
            default:
                break;
        }
        return mResult;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "SubViewGroup onTouchEvent: MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "SubViewGroup onTouchEvent: MotionEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "SubViewGroup onTouchEvent: MotionEvent.ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "SubViewGroup onTouchEvent: MotionEvent.ACTION_CANCEL");
                break;
            default:
                break;
        }
        return false;
    }
}
