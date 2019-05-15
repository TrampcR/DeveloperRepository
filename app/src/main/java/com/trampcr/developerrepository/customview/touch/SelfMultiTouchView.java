package com.trampcr.developerrepository.customview.touch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import com.trampcr.developerrepository.utils.DimenUtils;

public class SelfMultiTouchView extends View {
    private Paint mSelfPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path mSelfPath = new Path();
    private SparseArray<Path> mPathArray = new SparseArray<>();

    public SelfMultiTouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        mSelfPaint.setStyle(Paint.Style.STROKE);
        mSelfPaint.setStrokeWidth(DimenUtils.dp2px(5));
        mSelfPaint.setStrokeCap(Paint.Cap.ROUND);
        mSelfPaint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < mPathArray.size(); i++) {
            Path path = mPathArray.valueAt(i);
            canvas.drawPath(path, mSelfPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                int actionIndex = event.getActionIndex();
                int pointerId = event.getPointerId(actionIndex);
                Path path = new Path();
                path.moveTo(event.getX(actionIndex), event.getY(actionIndex));
                mPathArray.append(pointerId, path);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                int pointerCount = event.getPointerCount();
                for (int i = 0; i < pointerCount; i++) {
                    pointerId = event.getPointerId(i);
                    path = mPathArray.get(pointerId);
                    path.lineTo(event.getX(i), event.getY(i));
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                actionIndex = event.getActionIndex();
                pointerId = event.getPointerId(actionIndex);
                mPathArray.remove(pointerId);
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }
}
