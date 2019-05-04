package com.trampcr.developerrepository.customview.measureorlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.trampcr.developerrepository.utils.DimenUtils;

public class CircleView extends View {
    private static final float PADDING = DimenUtils.dp2px(20);
    private static final float RADIUS = DimenUtils.dp2px(80);
    private Paint mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = (int) ((PADDING + RADIUS) * 2);
        int height = (int) ((PADDING + RADIUS) * 2);

        setMeasuredDimension(resolveSizeAndState(width, widthMeasureSpec, 0),
                resolveSizeAndState(height, heightMeasureSpec, 0));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLUE);
        canvas.drawCircle(PADDING + RADIUS, PADDING + RADIUS, RADIUS, mCirclePaint);
    }
}
