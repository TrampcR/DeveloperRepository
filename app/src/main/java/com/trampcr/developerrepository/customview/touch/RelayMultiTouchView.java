package com.trampcr.developerrepository.customview.touch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.trampcr.developerrepository.R;
import com.trampcr.developerrepository.utils.CommonUtils;
import com.trampcr.developerrepository.utils.DimenUtils;

public class RelayMultiTouchView extends View {
    private static final int IMAGE_WIDTH = (int) DimenUtils.dp2px(200);

    private Paint mImagePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap mImageBitmap;

    private float mDownX;
    private float mDownY;
    private float mOffsetX;
    private float mOffsetY;
    private float mOriginalOffsetX;
    private float mOriginalOffsetY;
    private int mTrackingPointerId;

    public RelayMultiTouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        if (context == null) {
            return;
        }

        mImageBitmap = CommonUtils.getAvatar(context, IMAGE_WIDTH, R.mipmap.girl);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mImageBitmap, mOffsetX, mOffsetY, mImagePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int actionIndex;
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mTrackingPointerId = event.getPointerId(0);
                mDownX = event.getX(0);
                mDownY = event.getY(0);
                mOriginalOffsetX = mOffsetX;
                mOriginalOffsetY = mOffsetY;
                break;
            case MotionEvent.ACTION_MOVE:
                actionIndex = event.findPointerIndex(mTrackingPointerId);
                mOffsetX = mOriginalOffsetX + event.getX(actionIndex) - mDownX;
                mOffsetY = mOriginalOffsetY + event.getY(actionIndex) - mDownY;
                invalidate();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                actionIndex = event.getActionIndex();
                mTrackingPointerId = event.getPointerId(actionIndex);
                mDownX = event.getX(actionIndex);
                mDownY = event.getY(actionIndex);
                mOriginalOffsetX = mOffsetX;
                mOriginalOffsetY = mOffsetY;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                actionIndex = event.getActionIndex();
                int pointerId = event.getPointerId(actionIndex);
                if (mTrackingPointerId == pointerId) {
                    if (actionIndex == event.getPointerCount() - 1) {
                        actionIndex = event.getPointerCount() - 2;
                    } else {
                        actionIndex = event.getPointerCount() - 1;
                    }

                    mTrackingPointerId = event.getPointerId(actionIndex);
                    mDownX = event.getX(actionIndex);
                    mDownY = event.getY(actionIndex);
                    mOriginalOffsetX = mOffsetX;
                    mOriginalOffsetY = mOffsetY;
                }
                break;
        }
        return true;
    }
}
