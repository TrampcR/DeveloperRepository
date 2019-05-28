package com.trampcr.developerrepository.thread.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

public class MyIntentService extends IntentService {
    public static final String TAG = MyIntentService.class.getSimpleName();

    public MyIntentService() {
        super("myIntentService");
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) {
            return;
        }

        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return;
        }

        String taskName = bundle.getString(IntentServiceActivity.TASK_NAME);
        if (TextUtils.isEmpty(taskName)) {
            return;
        }

        switch (taskName) {
            case IntentServiceActivity.TASK_1:
                Log.d(TAG, "onHandleIntent: do task 1");
                break;
            case IntentServiceActivity.TASK_2:
                Log.d(TAG, "onHandleIntent: do task 2");
                break;
            default:
                break;
        }
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
