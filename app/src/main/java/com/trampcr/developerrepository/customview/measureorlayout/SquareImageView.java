package com.trampcr.developerrepository.customview.measureorlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SquareImageView extends android.support.v7.widget.AppCompatImageView {
    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measureWidth = getMeasuredWidth();
        int measureHeight = getMeasuredHeight();
        int size = Math.max(measureWidth, measureHeight);
        setMeasuredDimension(size, size);
    }
}
