package com.trampcr.developerrepository.customview.measureorlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

import com.trampcr.developerrepository.utils.DimenUtils;

import java.util.Random;

public class ColorTextView extends android.support.v7.widget.AppCompatTextView {
    private static final int X_PADDING = (int) DimenUtils.dp2px(16);
    private static final int Y_PADDING = (int) DimenUtils.dp2px(8);
    private static final int CONNER_RADIUS = (int) DimenUtils.dp2px(5);
    private static final int[] TEXT_SIZE = {16, 20, 24};
    private static final int[] COLORS = {
            Color.parseColor("#c6ff00"),
            Color.parseColor("#f57f17"),
            Color.parseColor("#ff6f00"),
            Color.parseColor("#18ffff"),
            Color.parseColor("#8c9eff"),
            Color.parseColor("#ff4081")
    };

    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Random mRandom = new Random();

    public ColorTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
        setTextColor(Color.WHITE);
        setPadding(X_PADDING, Y_PADDING, X_PADDING, Y_PADDING);
        setTextSize(TEXT_SIZE[mRandom.nextInt(TEXT_SIZE.length)]);
        mTextPaint.setColor(COLORS[mRandom.nextInt(COLORS.length)]);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(0, 0, getWidth(), getHeight(), CONNER_RADIUS, CONNER_RADIUS, mTextPaint);
        super.onDraw(canvas);
    }
}
