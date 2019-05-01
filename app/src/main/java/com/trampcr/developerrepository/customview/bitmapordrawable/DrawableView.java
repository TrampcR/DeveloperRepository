package com.trampcr.developerrepository.customview.bitmapordrawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.trampcr.developerrepository.utils.DimenUtils;

public class DrawableView extends View {
    private Paint mDrawablePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Drawable mDrawable;
    public DrawableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mDrawable = new ColorDrawable(Color.BLUE);
        mDrawable = new MeshDrawable();
        mDrawable.setBounds((int) DimenUtils.dp2px(50), (int) DimenUtils.dp2px(50), getWidth(), getHeight());
        mDrawable.draw(canvas);
    }
}
