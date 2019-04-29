package com.trampcr.developerrepository.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.trampcr.developerrepository.R;
import com.trampcr.developerrepository.utils.CommonUtils;
import com.trampcr.developerrepository.utils.DimenUtils;

public class CameraView extends View {
    public static final float BITMAP_WIDTH = DimenUtils.dp2px(300);
    public static final float BITMAP_PADDING = DimenUtils.dp2px(100);
    private Paint mCameraPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Camera mCamera = new Camera();
    private Bitmap mCameraBitmap = CommonUtils.getAvatar(this.getContext(), (int) BITMAP_WIDTH, R.mipmap.girl);

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mCamera.rotateX(45);
        mCamera.setLocation(0, 0, DimenUtils.getCameraLocation());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制上半部分
        canvas.save();
        canvas.translate(BITMAP_PADDING + BITMAP_WIDTH / 2, BITMAP_PADDING + BITMAP_WIDTH / 2);
        canvas.rotate(-30);
        canvas.clipRect(-BITMAP_WIDTH, -BITMAP_WIDTH, BITMAP_WIDTH, 0);
        canvas.rotate(30);
        canvas.translate(-(BITMAP_PADDING + BITMAP_WIDTH / 2), -(BITMAP_PADDING + BITMAP_WIDTH / 2));
        canvas.drawBitmap(mCameraBitmap, BITMAP_PADDING, BITMAP_PADDING, mCameraPaint);
        canvas.restore();

        // 绘制下半部分
        canvas.save();
        canvas.translate(BITMAP_PADDING + BITMAP_WIDTH / 2, BITMAP_PADDING + BITMAP_WIDTH / 2);
        canvas.rotate(-30);
        mCamera.applyToCanvas(canvas);
        canvas.clipRect(-BITMAP_WIDTH, 0, BITMAP_WIDTH, BITMAP_WIDTH);
        canvas.rotate(30);
        canvas.translate(-(BITMAP_PADDING + BITMAP_WIDTH / 2), -(BITMAP_PADDING + BITMAP_WIDTH / 2));
        canvas.drawBitmap(mCameraBitmap, BITMAP_PADDING, BITMAP_PADDING, mCameraPaint);
        canvas.restore();
    }
}
