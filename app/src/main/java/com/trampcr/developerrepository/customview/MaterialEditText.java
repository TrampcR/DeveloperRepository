package com.trampcr.developerrepository.customview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;

import com.trampcr.developerrepository.R;
import com.trampcr.developerrepository.utils.DimenUtils;

public class MaterialEditText extends AppCompatEditText {
    public static final String TAG = MaterialEditText.class.getSimpleName();
    private static final int TEXT_SIZE = (int) DimenUtils.dp2px(12);
    private static final int TOP_PADDING = (int) DimenUtils.dp2px(8);
    private static final int ANIMATOR_MOVE = (int) DimenUtils.dp2px(16);

    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float mFloatingLabelFraction;
    private boolean mShowedAnimator;
    private boolean mUseFloatingLabel;
    private ObjectAnimator mObjectAnimator;
    private Rect mMaterialTextPadding = new Rect();

    public float getFloatingLabelFraction() {
        return mFloatingLabelFraction;
    }

    public void setFloatingLabelFraction(float mFloatingLabelFraction) {
        this.mFloatingLabelFraction = mFloatingLabelFraction;
        invalidate();
    }

    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText);
        mUseFloatingLabel = typedArray.getBoolean(R.styleable.MaterialEditText_useFloatingLabel,
                false);
        typedArray.recycle();
        Log.d(TAG, "MaterialEditText: mUseFloatingLabel = " + mUseFloatingLabel);
        init();
    }

    private void init() {
        if (mUseFloatingLabel) {
            getBackground().getPadding(mMaterialTextPadding);
            setPadding(getPaddingLeft(), mMaterialTextPadding.top + TEXT_SIZE + TOP_PADDING,
                    getPaddingRight(), getPaddingBottom());
            mTextPaint.setTextSize(TEXT_SIZE);
        }

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mUseFloatingLabel) {
                    if (TextUtils.isEmpty(s)) {
                        mShowedAnimator = false;
                        Log.d(TAG, "onTextChanged: s = " + s);
                        getAnimator().reverse();
                    } else if (!mShowedAnimator && !TextUtils.isEmpty(s)) {
                        mShowedAnimator = true;
                        Log.d(TAG, "onTextChanged: s = " + s);
                        getAnimator().start();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private ObjectAnimator getAnimator() {
        if (mObjectAnimator == null) {
            mObjectAnimator = ObjectAnimator.ofFloat(MaterialEditText.this,
                    "floatingLabelFraction", 1);
        }

        return mObjectAnimator;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mUseFloatingLabel) {
            Log.d(TAG, "onDraw: ");
            mTextPaint.setAlpha((int) (0xff * mFloatingLabelFraction));
            float animatorMove = ANIMATOR_MOVE * (1 - mFloatingLabelFraction);
            canvas.drawText(getHint(), 0, getHint().length(), getPaddingLeft(),
                    getPaddingTop() - TOP_PADDING + animatorMove, mTextPaint);
        }
    }
}
