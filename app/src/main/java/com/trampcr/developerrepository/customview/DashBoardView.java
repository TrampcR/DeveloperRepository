package com.trampcr.developerrepository.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by trampcr on 2019/1/9.
 */

public class DashBoardView extends View{
    public static final String TAG = DashBoardView.class.getSimpleName();
    public static final float ARC_ANGLE = 120;
    public static final float RIGHT_ANGLE = 90;
    public static final float CIRCLE_ANGLE = 360;
    public static final float RADIUS = ViewUtils.dp2px(100);
    public static final float POINTER_LEN = ViewUtils.dp2px(80);

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF mArcRectF = new RectF();
    private PathEffect mDashPathEffect;
    private Path mDashPath = new Path();
    private Path mArcPath = new Path();

    public DashBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(ViewUtils.dp2px(2));
        mArcRectF.set(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);
        mDashPath.addRect(0, 0, ViewUtils.dp2px(2), ViewUtils.dp2px(10), Path.Direction.CW);
        mArcPath.addArc(mArcRectF, ARC_ANGLE + (RIGHT_ANGLE - ARC_ANGLE / 2), CIRCLE_ANGLE - ARC_ANGLE);
        PathMeasure arcPathMeasure = new PathMeasure(mArcPath, false);
        mDashPathEffect = new PathDashPathEffect(mDashPath, (arcPathMeasure.getLength() - ViewUtils.dp2px(2)) / 20, 0,
                PathDashPathEffect.Style.ROTATE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画弧
        canvas.drawArc(mArcRectF, ARC_ANGLE + (RIGHT_ANGLE - ARC_ANGLE / 2), CIRCLE_ANGLE - ARC_ANGLE, false, mPaint);

        // 画刻度
        mPaint.setPathEffect(mDashPathEffect);
        canvas.drawArc(mArcRectF, ARC_ANGLE + (RIGHT_ANGLE - ARC_ANGLE / 2), CIRCLE_ANGLE - ARC_ANGLE, false, mPaint);
        mPaint.setPathEffect(null);

        // 画指针
        canvas.drawLine(getWidth() / 2, getHeight() / 2,
                (float) (getWidth() / 2 + POINTER_LEN * Math.cos(Math.toRadians(ARC_ANGLE + (RIGHT_ANGLE - ARC_ANGLE / 2) + 3 * (CIRCLE_ANGLE - ARC_ANGLE) / 20))),
                (float) (getHeight() / 2 + POINTER_LEN * Math.sin(Math.toRadians(ARC_ANGLE + (RIGHT_ANGLE - ARC_ANGLE / 2) + 3 * (CIRCLE_ANGLE - ARC_ANGLE) / 20))),
                mPaint);
    }
}
