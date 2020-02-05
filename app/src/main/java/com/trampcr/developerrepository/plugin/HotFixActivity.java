package com.trampcr.developerrepository.plugin;

import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.trampcr.developerrepository.R;
import com.trampcr.developerrepository.utils.FileUtils;

public class HotFixActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = HotFixActivity.class.getSimpleName();
    private TextView mTvTitle;
    private Button mBtnShowTitle;
    private Button mBtnHotfix;
    private Button mBtnRemoveHotfix;
    private Button mBtnKillSelf;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hotfix);
        initView();
        initListener();
    }

    private void initListener() {
        mBtnShowTitle.setOnClickListener(this);
        mBtnHotfix.setOnClickListener(this);
        mBtnRemoveHotfix.setOnClickListener(this);
        mBtnKillSelf.setOnClickListener(this);
    }

    private void initView() {
        mTvTitle = findViewById(R.id.tv_title);
        mBtnShowTitle = findViewById(R.id.btn_show_title);
        mBtnHotfix = findViewById(R.id.btn_hotfix);
        mBtnRemoveHotfix = findViewById(R.id.btn_remove_hotfix);
        mBtnKillSelf = findViewById(R.id.btn_kill_self);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_title:
                Title title = new Title();
                mTvTitle.setText(title.getTitle());
                break;
            case R.id.btn_hotfix:
                // 1、复制 assets 下 apk 文件到缓存目录
                FileUtils.copyAssetsToCache(this, FileUtils.HOT_FIX_FILE_NAME);

                Title title1 = new Title();
                mTvTitle.setText(title1.getTitle());
                break;
            case R.id.btn_remove_hotfix:
                removeHotfix();
                break;
            case R.id.btn_kill_self:
                killSelf();
                break;
        }
    }

    private void killSelf() {
        Log.d(TAG, "killSelf: Process.myPid() = " + Process.myPid());
        Process.killProcess(Process.myPid());
    }

    private void removeHotfix() {
        FileUtils.deleteCache(this, FileUtils.HOT_FIX_FILE_NAME);
    }
}
