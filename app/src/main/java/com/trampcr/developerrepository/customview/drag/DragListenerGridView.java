package com.trampcr.developerrepository.customview.drag;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class DragListenerGridView extends ViewGroup {
    public static final String TAG = DragListenerGridView.class.getSimpleName();
    private static final int COLS = 2;
    private static final int ROWS = 3;

    private View mDraggedView;
    private List<View> mOrderedChildren = new ArrayList<>();
    private MyDragListener mMyDragListener = new MyDragListener();

    public DragListenerGridView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        setChildrenDrawingOrderEnabled(true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            mOrderedChildren.add(child);
            child.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mDraggedView = v;
                    v.startDrag(null, new DragShadowBuilder(v), v, 0);
                    return false;
                }
            });
            child.setOnDragListener(mMyDragListener);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int specHeight = MeasureSpec.getSize(heightMeasureSpec);
        int childWidth = specWidth / COLS;
        int childHeight = specHeight / ROWS;

        measureChildren(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY));
        setMeasuredDimension(specWidth, specHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int childLeft;
        int childTop;
        int childWidth = getWidth() / COLS;
        int childHeight = getHeight() / ROWS;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            childLeft = i % 2 * childWidth;
            childTop = i / 2 * childHeight;
            child.layout(0, 0, childWidth, childHeight);
            child.setTranslationX(childLeft);
            child.setTranslationY(childTop);
        }
    }

    private class MyDragListener implements OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    if (event.getLocalState() == v) {
                        v.setVisibility(INVISIBLE);
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    if (event.getLocalState() != v) {
                        sort(v);
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setVisibility(VISIBLE);
                    break;
                default:
                    break;
            }
            return true;
        }
    }

    private void sort(View targetView) {
        int targetIndex = -1;
        int draggedIndex = -1;
        for (int i = 0; i < getChildCount(); i++) {
            View child = mOrderedChildren.get(i);
            if (child == targetView) {
                targetIndex = i;
            } else if (child == mDraggedView) {
                draggedIndex = i;
            }
        }

        if (targetIndex > draggedIndex) {
            mOrderedChildren.remove(draggedIndex);
            mOrderedChildren.add(targetIndex, mDraggedView);
        } else if (targetIndex < draggedIndex) {
            mOrderedChildren.remove(draggedIndex);
            mOrderedChildren.add(targetIndex, mDraggedView);
        }

        int childLeft;
        int childTop;
        int childWidth = getWidth() / COLS;
        int childHeight = getHeight() / ROWS;

        for (int i = 0; i < getChildCount(); i++) {
            View child = mOrderedChildren.get(i);
            childLeft = i % 2 * childWidth;
            childTop = i / 2 * childHeight;
            Log.d(TAG, "sort: childLeft = " + childLeft + ", childTop = " + childTop);
            child.animate()
                    .translationX(childLeft)
                    .translationY(childTop)
                    .setDuration(150);
        }
    }
}
