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

/**
 * Created by trampcr on 2019/4/29.
 */

public class CameraAnimationView extends View {
    public static final float BITMAP_WIDTH = DimenUtils.dp2px(200);
    public static final float BITMAP_PADDING = DimenUtils.dp2px(100);

    private Paint mAnimationPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Camera mAnimationCamera = new Camera();
    private Bitmap mAnimationBitmap = CommonUtils.getAvatar(this.getContext(), (int) BITMAP_WIDTH, R.mipmap.girl);

    private int mRotate = 0;
    private int mTopFlip = 0;
    private int mBottomFlip = 0;

    public CameraAnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mAnimationCamera.setLocation(0, 0, DimenUtils.getCameraLocation());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制上半部分
        canvas.save();
        canvas.translate(BITMAP_PADDING + BITMAP_WIDTH / 2, BITMAP_PADDING + BITMAP_WIDTH / 2);
        canvas.rotate(mRotate);
        mAnimationCamera.save();
        mAnimationCamera.rotateX(mTopFlip);
        mAnimationCamera.applyToCanvas(canvas);
        mAnimationCamera.restore();
        canvas.clipRect(-BITMAP_WIDTH, -BITMAP_WIDTH, BITMAP_WIDTH, 0);
        canvas.rotate(-mRotate);
        canvas.translate(-(BITMAP_PADDING + BITMAP_WIDTH / 2), -(BITMAP_PADDING + BITMAP_WIDTH / 2));
        canvas.drawBitmap(mAnimationBitmap, BITMAP_PADDING, BITMAP_PADDING, mAnimationPaint);
        canvas.restore();

        // 绘制下半部分
        canvas.save();
        canvas.translate(BITMAP_PADDING + BITMAP_WIDTH / 2, BITMAP_PADDING + BITMAP_WIDTH / 2);
        canvas.rotate(mRotate);
        mAnimationCamera.save();
        mAnimationCamera.rotateX(mBottomFlip);
        mAnimationCamera.applyToCanvas(canvas);
        mAnimationCamera.restore();
        canvas.clipRect(-BITMAP_WIDTH, 0, BITMAP_WIDTH, BITMAP_WIDTH);
        canvas.rotate(-mRotate);
        canvas.translate(-(BITMAP_PADDING + BITMAP_WIDTH / 2), -(BITMAP_PADDING + BITMAP_WIDTH / 2));
        canvas.drawBitmap(mAnimationBitmap, BITMAP_PADDING, BITMAP_PADDING, mAnimationPaint);
        canvas.restore();
    }

    public int getRotate() {
        return mRotate;
    }

    public void setRotate(int rotate) {
        this.mRotate = rotate;
        invalidate();

    }

    public int getTopFlip() {
        return mTopFlip;
    }

    public void setTopFlip(int topFlip) {
        this.mTopFlip = topFlip;
        invalidate();
    }

    public int getBottomFlip() {
        return mBottomFlip;
    }

    public void setBottomFlip(int bottomFlip) {
        this.mBottomFlip = bottomFlip;
        invalidate();
    }
}
