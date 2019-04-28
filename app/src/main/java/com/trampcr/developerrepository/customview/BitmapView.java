package com.trampcr.developerrepository.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.trampcr.developerrepository.R;
import com.trampcr.developerrepository.utils.DimenUtils;

public class BitmapView extends View {
    public static final float AVATAR_WIDTH = DimenUtils.dp2Px(300);
    public static final float AVATAR_PADDING = DimenUtils.dp2Px(50);
    public static final float AVATAR_MARGIN = DimenUtils.dp2Px(5);

    private Paint mAvatarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap mAvatarBitmap;
    private Xfermode mAvatarMode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    private RectF mSavedArea = new RectF();
    private RectF mAvatarArea = new RectF();

    public BitmapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mAvatarBitmap = getAvatar((int) AVATAR_WIDTH);
        mSavedArea.set(AVATAR_PADDING, AVATAR_PADDING, AVATAR_PADDING + AVATAR_WIDTH,
                AVATAR_PADDING + AVATAR_WIDTH);
        mAvatarArea.set(AVATAR_PADDING + AVATAR_MARGIN, AVATAR_PADDING + AVATAR_MARGIN,
                AVATAR_PADDING + AVATAR_WIDTH - AVATAR_MARGIN, AVATAR_PADDING + AVATAR_WIDTH - AVATAR_MARGIN);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mAvatarPaint.setColor(Color.parseColor("#ff80ab"));
        canvas.drawOval(mSavedArea, mAvatarPaint);
        int saved = canvas.saveLayer(mSavedArea, mAvatarPaint);
        canvas.drawOval(mAvatarArea, mAvatarPaint);
        mAvatarPaint.setXfermode(mAvatarMode);
        canvas.drawBitmap(mAvatarBitmap, AVATAR_PADDING, AVATAR_PADDING, mAvatarPaint);
        mAvatarPaint.setXfermode(null);
        canvas.restoreToCount(saved);
    }

    private Bitmap getAvatar(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//设置为 true 后，只会获取图片的宽高
        BitmapFactory.decodeResource(getResources(), R.mipmap.girl, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(), R.mipmap.girl, options);
    }
}
