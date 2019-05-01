package com.trampcr.developerrepository.customview.bitmapordrawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.trampcr.developerrepository.utils.DimenUtils;

public class MeshDrawable extends Drawable {
    private static final int INTERVAL = (int) DimenUtils.dp2px(50);
    private Paint mDrawablePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    @Override
    public void draw(@NonNull Canvas canvas) {
        for (int i = 0; i < getBounds().right; i += INTERVAL) {
            for (int j = 0; j < getBounds().bottom; j += INTERVAL) {
                canvas.drawLine(getBounds().left, j, getBounds().right, j, mDrawablePaint);
                canvas.drawLine(i, getBounds().top, i, getBounds().bottom, mDrawablePaint);
            }
        }
    }

    @Override
    public void setAlpha(int alpha) {
        mDrawablePaint.setAlpha(alpha);
    }

    @Override
    public int getAlpha() {
        return mDrawablePaint.getAlpha();
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mDrawablePaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return mDrawablePaint.getAlpha() == 0 ? PixelFormat.TRANSPARENT :
                mDrawablePaint.getAlpha() == 0xff ? PixelFormat.OPAQUE : PixelFormat.TRANSLUCENT;
    }
}
