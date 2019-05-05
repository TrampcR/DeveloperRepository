package com.trampcr.developerrepository.customview.measureorlayout;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TagLayout extends ViewGroup {

    private List<Rect> mChildrenBounds = new ArrayList<>();

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthUsed = 0;
        int heightUsed = 0;
        int lineMaxHeight = 0;
        int childMeasuredWidth;
        int childMeasuredHeight;
        int measuredWidth;
        int measuredHeight;
        int specWidth;
        int specMode;
        int lineWidthUsed = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            childMeasuredWidth = child.getMeasuredWidth();
            childMeasuredHeight = child.getMeasuredHeight();
            specMode = MeasureSpec.getMode(widthMeasureSpec);
            specWidth = MeasureSpec.getSize(widthMeasureSpec);

            if (specMode != MeasureSpec.UNSPECIFIED &&
                    lineWidthUsed + childMeasuredWidth > specWidth) {
                lineWidthUsed = 0;
                heightUsed += lineMaxHeight;
                lineMaxHeight = 0;
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            } else {
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            }

            Rect childBound;
            if (mChildrenBounds.size() <= i) {
                childBound = new Rect();
                mChildrenBounds.add(childBound);
            } else {
                childBound = mChildrenBounds.get(i);
            }

            childBound.set(lineWidthUsed, heightUsed, lineWidthUsed + childMeasuredWidth,
                    heightUsed + childMeasuredHeight);
            lineWidthUsed += childMeasuredWidth;
            widthUsed = Math.max(widthUsed, lineWidthUsed);
            lineMaxHeight = Math.max(lineMaxHeight, childMeasuredHeight);
        }

        measuredWidth = widthUsed;
        measuredHeight = heightUsed + lineMaxHeight;
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (mChildrenBounds == null || mChildrenBounds.size() <= 0) {
                continue;
            }
            Rect childBound = mChildrenBounds.get(i);
            child.layout(childBound.left, childBound.top, childBound.right, childBound.bottom);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
