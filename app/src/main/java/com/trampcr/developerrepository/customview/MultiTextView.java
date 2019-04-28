package com.trampcr.developerrepository.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.trampcr.developerrepository.R;
import com.trampcr.developerrepository.utils.CommonUtils;
import com.trampcr.developerrepository.utils.DimenUtils;

/**
 * Created by trampcr on 2019/4/28.
 */

public class MultiTextView extends View {
    public static final String TAG = MultiTextView.class.getSimpleName();
    public static final float BITMAP_WIDTH = DimenUtils.dp2px(100);
    public static final float BITMAP_TOP = DimenUtils.dp2px(30);
    public static final String MULTI_TEXT = "1、如果你工作上任何事情都称心如意，那说明你根本没接触过新东西和新压力。" +
            "2、任何人生的成长，都是逆着人性来的。健身如此，学习如此，工作如此，所有事物都如此。3、思考把自己的屁股放到老板的位置，" +
            "反复的把自己的想法和老板的想法、做法对比，看差了多少。同样，管理员工，把屁股放低，去想这个活儿派下去员工会怎么想，" +
            "然后反求诸己，便可以用共情心和员工共鸣，很容易带出一支好队伍。4、一个人在职场里持续上升，必须要有持续的增量成长。";

    private TextPaint mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private Paint mTextPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private StaticLayout mTextLayout;
    private float[] mTextMeasuredWidth = {0};
    private float mTextSpacing = 0;
    private int mStartIndex = 0;
    private int mLineTextLen = 0;
    private Bitmap mBitmap = CommonUtils.getAvatar(this.getContext(), (int) BITMAP_WIDTH, R.mipmap.girl);

    public MultiTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTextPaint.setTextSize(DimenUtils.dp2px(15));
        // spacingmult : 行间距乘数，默认为 1.
        // spacingadd : 行间距增加，默认为 0，经过测试，spacingmult = 1，spacingadd = 40 时，相当于 spacingmult = 2，spacingadd = 0.
        // includepad : 是否在文字上下添加额外的空间，来避免某些过高的字符的绘制出现越界。比如 Arabic and Kannada，默认为 true.
//        mTextLayout = new StaticLayout(MULTI_TEXT, mTextPaint, getWidth(), Layout.Alignment.ALIGN_NORMAL, 1, 0, true);
        mTextSpacing = mTextPaint.getFontSpacing();

        mBitmap = CommonUtils.getAvatar(this.getContext(), (int) BITMAP_WIDTH, R.mipmap.girl);
        Log.d(TAG, "onSizeChanged: mBitmap = " + mBitmap + ", mTextSpacing = " + mTextSpacing);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mTextLayout.draw(canvas);// 多行文本绘制 1

        // 多行文本绘制 2
//        while (mStartIndex < MULTI_TEXT.length()) {
//            mLineTextLen = mTextPaint.breakText(MULTI_TEXT, mStartIndex, MULTI_TEXT.length(), true, getWidth(), mTextMeasuredWidth);
//            canvas.drawText(MULTI_TEXT, mStartIndex, mStartIndex + mLineTextLen, 0, mTextSpacing, mTextPaint);
//            mStartIndex += mLineTextLen;
//            mTextSpacing += mTextPaint.getFontSpacing();
//            Log.d(TAG, "onDraw: mTextSpacing = " + mTextSpacing + ", startIndex = " + mStartIndex + ", mTextMeasuredWidth = "
//            + mTextMeasuredWidth[0] + ", multiText len = " + MULTI_TEXT.length());
//        }

        // 绘制带图片的多行文本
        canvas.drawBitmap(mBitmap, getWidth() - BITMAP_WIDTH, BITMAP_TOP, mTextPaint);

        while (mStartIndex < MULTI_TEXT.length()) {
            if (mTextSpacing >= BITMAP_TOP && mTextSpacing - mTextPaint.getFontSpacing() <= BITMAP_TOP + BITMAP_WIDTH) {
                mLineTextLen = mTextPaint.breakText(MULTI_TEXT, mStartIndex, MULTI_TEXT.length(), true,
                        getWidth() - BITMAP_WIDTH, mTextMeasuredWidth);
            } else {
                mLineTextLen = mTextPaint.breakText(MULTI_TEXT, mStartIndex, MULTI_TEXT.length(), true, getWidth(), mTextMeasuredWidth);

            }
            canvas.drawText(MULTI_TEXT, mStartIndex, mStartIndex + mLineTextLen, 0, mTextSpacing, mTextPaint);
            mStartIndex += mLineTextLen;
            mTextSpacing += mTextPaint.getFontSpacing();
            Log.d(TAG, "onDraw: mTextSpacing = " + mTextSpacing + ", startIndex = " + mStartIndex + ", mTextMeasuredWidth = "
            + mTextMeasuredWidth[0] + ", multiText len = " + MULTI_TEXT.length());
        }
    }
}
