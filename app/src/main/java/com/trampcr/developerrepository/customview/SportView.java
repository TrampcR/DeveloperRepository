package com.trampcr.developerrepository.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.trampcr.developerrepository.utils.DimenUtils;

public class SportView extends View {
    public static final String TAG = SportView.class.getSimpleName();
    private static final float SPORT_VIEW_RADIUS = DimenUtils.dp2px(150);
    private static final float SPORT_VIEW_WIDTH = DimenUtils.dp2px(10);
    private static final float SPORT_START_ANGLE = 60;
    private static final float SPORT_SWEEP_ANGLE = 220;
    private static final String SPORT_TEXT = "AbaB";

    private Paint mSportViewPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Rect mSportTextRounds = new Rect();

    public SportView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mSportViewPaint.setTextSize(DimenUtils.dp2px(80));
        mSportViewPaint.setTextAlign(Paint.Align.CENTER);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制环
        mSportViewPaint.setColor(Color.parseColor("#eeeeee"));
        mSportViewPaint.setStyle(Paint.Style.STROKE);
        mSportViewPaint.setStrokeWidth(SPORT_VIEW_WIDTH);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, SPORT_VIEW_RADIUS, mSportViewPaint);

        // 绘制进度条
        mSportViewPaint.setColor(Color.parseColor("#80d8ff"));
        mSportViewPaint.setStrokeCap(Paint.Cap.ROUND);// 设置圆头
        canvas.drawArc(getWidth() / 2 - SPORT_VIEW_RADIUS, getHeight() / 2 - SPORT_VIEW_RADIUS,
                getWidth() / 2 + SPORT_VIEW_RADIUS, getHeight() / 2 + SPORT_VIEW_RADIUS,
                SPORT_START_ANGLE, SPORT_SWEEP_ANGLE, false, mSportViewPaint);

        // 绘制文字
        mSportViewPaint.setStyle(Paint.Style.FILL);// 绘制文字需要用 Fill
//        mSportViewPaint.getTextBounds(SPORT_TEXT, 0, SPORT_TEXT.length(), mSportTextRounds);
//        canvas.drawText(SPORT_TEXT, getWidth() / 2, getHeight() / 2 + mSportTextRounds.height() / 2,
//                mSportViewPaint);
        Paint.FontMetrics fontMetrics = mSportViewPaint.getFontMetrics();
        Log.d(TAG, "onDraw: ascent = " + fontMetrics.ascent + ", descent = " + fontMetrics.descent);
        canvas.drawText(SPORT_TEXT, getWidth() / 2, getHeight() / 2 - (fontMetrics.ascent + fontMetrics.descent) / 2,
                mSportViewPaint);
    }
}
