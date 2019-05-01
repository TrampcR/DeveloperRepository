package com.trampcr.developerrepository.customview.point;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.trampcr.developerrepository.utils.DimenUtils;

public class PointView extends View {
    private Paint mPointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Point mPoint = new Point(0, 0);

    public Point getPoint() {
        return mPoint;
    }

    public void setPoint(Point point) {
        this.mPoint = point;
        invalidate();
    }

    public PointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPointPaint.setStrokeWidth(DimenUtils.dp2px(20));
        mPointPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(mPoint.x, mPoint.y, mPointPaint);
    }
}
