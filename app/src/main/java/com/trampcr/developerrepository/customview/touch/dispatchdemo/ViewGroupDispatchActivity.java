package com.trampcr.developerrepository.customview.touch.dispatchdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.trampcr.developerrepository.R;

public class ViewGroupDispatchActivity extends AppCompatActivity implements View.OnClickListener,
        View.OnTouchListener {
    private static final String TAG = ViewGroupDispatchActivity.class.getSimpleName();

    private ViewGroup mViewGroupLayout;
    private Button mBtnView1;
    private Button mBtnView2;

    private boolean mResult = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_group_dispatch);

        initView();
        initListener();
    }

    private void initView() {
        mViewGroupLayout = (ViewGroup) findViewById(R.id.view_group_layout);
        mBtnView1 = (Button) findViewById(R.id.btn_view_1);
        mBtnView2 = (Button) findViewById(R.id.btn_view_2);
    }

    private void initListener() {
        mViewGroupLayout.setOnClickListener(this);
        mBtnView1.setOnClickListener(this);
        mBtnView1.setOnTouchListener(this);
        mBtnView2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_group_layout:
                Log.d(TAG, "onClick: click the view group");
                break;
            case R.id.btn_view_1:
                Log.d(TAG, "onClick: click the button 1");
                break;
            case R.id.btn_view_2:
                Log.d(TAG, "onClick: click the button 2");
                break;
            default:
                break;
        }
    }

    /**
     * onTouch() 返回 false
     * 通过 OnClickListener() 为控件设置点击事件，为 mOnClickListener 变量赋值，从而往下回调onClick()
     * onTouch() 返回 true
     * 由于 dispatchTouchEvent() 返回 true，即事件不再向下传递，故不调用 onClick()
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d(TAG, "onTouch: event action = " + event.getActionMasked());
        return true;
    }
}
