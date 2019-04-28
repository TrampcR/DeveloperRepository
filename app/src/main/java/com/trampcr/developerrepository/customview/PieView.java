package com.trampcr.developerrepository.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.trampcr.developerrepository.utils.DimenUtils;

public class PieView extends View {
    public static final String TAG = PieView.class.getSimpleName();
    private static final float PIE_RADIUS = DimenUtils.dp2Px(100);
    private static final float MOVE_DISTANCE = DimenUtils.dp2Px(10);
    private static int mStandOutNum = 1;
    private Paint mPiePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF mPieRectF = new RectF();
    private int[] mAngleArr = {30, 60, 120, 150};
    private int[] mColorArr = {
            Color.parseColor("#ff8a80"),
            Color.parseColor("#ea80fc"),
            Color.parseColor("#82b1ff"),
            Color.parseColor("#69f0ae")};

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPieRectF.set(getWidth() / 2 - PIE_RADIUS, getHeight() / 2 - PIE_RADIUS,
                getWidth() / 2 + PIE_RADIUS, getHeight() / 2 + PIE_RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int startAngle = 0;
        for (int i = 0; i < mAngleArr.length; i++) {
            mPiePaint.setColor(mColorArr[i]);
            canvas.save();
            if (mStandOutNum == i) {
                canvas.translate((float) (MOVE_DISTANCE * Math.cos(Math.toRadians(startAngle + mAngleArr[i] / 2))),
                        (float) (MOVE_DISTANCE * Math.sin(Math.toRadians(startAngle + mAngleArr[i] / 2))));
            }

            canvas.drawArc(mPieRectF, startAngle, mAngleArr[i], true, mPiePaint);
            canvas.restore();
            startAngle += mAngleArr[i];
        }
    }
}
