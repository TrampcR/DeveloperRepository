package com.trampcr.developerrepository.thread.handlerthread;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.trampcr.developerrepository.R;

/**
 * Created by trampcr on 2019/5/27.
 */

public class HandlerThreadActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int WHAT_LOVE_STUDY = 1;
    private static final int WHAT_NOT_LOVE_STUDY = 2;
    private static final String TEXT_LOVE_STUDY = "我爱学习";
    private static final String TEXT_NOT_LOVE_STUDY = "我不爱学习";

    private TextView mTvShowResult;
    private Button mBtnLoveStudy;
    private Button mBtnNotLoveStudy;
    private Button mBtnStopHandlerThread;

    private Handler mWorkHandler;
    private HandlerThread mHandlerThread;
    private Handler mMainHandler = new Handler();

    private String mTextResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_handler_thread_view);

        initView();
        initListener();

        mHandlerThread = new HandlerThread("handlerThread");
        mHandlerThread.start();

        mWorkHandler = new Handler(mHandlerThread.getLooper()) {
            @Override
            public void handleMessage(final Message msg) {
                switch (msg.what) {
                    case WHAT_LOVE_STUDY:
                        mTextResult = msg.obj.toString();
                        mMainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mTvShowResult.setText(mTextResult);
                            }
                        });
                        break;
                    case WHAT_NOT_LOVE_STUDY:
                        mTextResult = msg.obj.toString();
                        mMainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mTvShowResult.setText(mTextResult);
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void initView() {
        mTvShowResult = (TextView) findViewById(R.id.tv_show_result);
        mBtnLoveStudy = (Button) findViewById(R.id.btn_love_study);
        mBtnNotLoveStudy = (Button) findViewById(R.id.btn_not_love_study);
        mBtnStopHandlerThread = (Button) findViewById(R.id.btn_cancel_handler_thread);
    }

    private void initListener() {
        mBtnLoveStudy.setOnClickListener(this);
        mBtnNotLoveStudy.setOnClickListener(this);
        mBtnStopHandlerThread.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_love_study:
                Message messageLove = Message.obtain();
                messageLove.what = WHAT_LOVE_STUDY;
                messageLove.obj = TEXT_LOVE_STUDY;
                mWorkHandler.sendMessageDelayed(messageLove, 1000);
                break;
            case R.id.btn_not_love_study:
                Message messageNotLove = Message.obtain();
                messageNotLove.what = WHAT_NOT_LOVE_STUDY;
                messageNotLove.obj = TEXT_NOT_LOVE_STUDY;
                mWorkHandler.sendMessageDelayed(messageNotLove, 3000);
                break;
            case R.id.btn_cancel_handler_thread:
                mHandlerThread.quit();
                break;
            default:
                break;
        }
    }
}
