package com.trampcr.developerrepository.customview.touch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.trampcr.developerrepository.R;
import com.trampcr.developerrepository.utils.CommonUtils;
import com.trampcr.developerrepository.utils.DimenUtils;

/**
 * Created by trampcr on 2019/5/14.
 */

public class CooperateMultiTouchView extends View {
    public static final String TAG = CooperateMultiTouchView.class.getSimpleName();
    private static final int IMAGE_WIDTH = (int) DimenUtils.dp2px(200);

    private Paint mImagePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap mImageBitmap;

    private float mDownX;
    private float mDownY;
    private float mOffsetX;
    private float mOffsetY;
    private float mOriginalOffsetX;
    private float mOriginalOffsetY;

    public CooperateMultiTouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        mImageBitmap = CommonUtils.getAvatar(context, IMAGE_WIDTH, R.mipmap.girl);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mImageBitmap, mOffsetX, mOffsetY, mImagePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float sumX = 0;
        float sumY = 0;
        boolean isPointerUp = event.getActionMasked() == MotionEvent.ACTION_POINTER_UP;
        int upPointerIndex = isPointerUp ? event.getActionIndex() : -1;
        int pointerCount = event.getPointerCount();

        for (int i = 0; i < pointerCount; i++) {
            if (upPointerIndex != i) {
                sumX += event.getX(i);
                sumY += event.getY(i);
            }
        }

        int divisor = isPointerUp ? pointerCount - 1 : pointerCount;
        float focusX = sumX / divisor;
        float focusY = sumY / divisor;
        Log.d(TAG, "onTouchEvent: sumX = " + sumX + ", sumY = " + sumY + ", focusX = " + focusX + ", focusY = " + focusY
        + ", divisor = " + divisor);

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_POINTER_UP:
                mDownX = focusX;
                mDownY = focusY;
                mOriginalOffsetX = mOffsetX;
                mOriginalOffsetY = mOffsetY;
                break;
            case MotionEvent.ACTION_MOVE:
                mOffsetX = mOriginalOffsetX + focusX - mDownX;
                mOffsetY = mOriginalOffsetY + focusY - mDownY;
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }
}
