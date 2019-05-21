package com.trampcr.developerrepository.thread.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.trampcr.developerrepository.R;

public class AsyncTaskActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnStartLoad;
    private Button mBtnCancelLoad;
    private static ProgressBar mPbLoad;
    private static TextView mTvProgress;
    private MyAsyncTask mMyAsyncTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_async_task_view);

        initView();
        initListener();

        mMyAsyncTask = new MyAsyncTask();
    }

    private void initView() {
        mBtnStartLoad = (Button) findViewById(R.id.btn_start_load);
        mBtnCancelLoad = (Button) findViewById(R.id.btn_cancel_load);
        mPbLoad = (ProgressBar) findViewById(R.id.pb_load);
        mTvProgress = (TextView) findViewById(R.id.tv_progress);
    }

    private void initListener() {
        mBtnStartLoad.setOnClickListener(this);
        mBtnCancelLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_load:
                mMyAsyncTask.execute();
                mBtnStartLoad.setEnabled(false);
                break;
            case R.id.btn_cancel_load:
                mMyAsyncTask.cancel(true);
                break;
            default:
                break;
        }
    }

    private static class MyAsyncTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            mTvProgress.setText(R.string.text_loading);
        }

        @Override
        protected String doInBackground(String... strings) {
            int count = 0;
            while (count < 99) {
                count++;
                publishProgress(count);

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mPbLoad.setProgress(values[0]);
            mTvProgress.setText("loading..." + values[0] + "%");
        }

        @Override
        protected void onPostExecute(String s) {
            mTvProgress.setText(R.string.text_load_success);
        }

        @Override
        protected void onCancelled() {
            mTvProgress.setText(R.string.text_has_cancelled);
            mPbLoad.setProgress(0);
        }
    }
}
