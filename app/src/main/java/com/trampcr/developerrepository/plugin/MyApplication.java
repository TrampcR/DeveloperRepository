package com.trampcr.developerrepository.plugin;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.trampcr.developerrepository.utils.FileUtils;

public class MyApplication extends Application {
    public static final String TAG = MyApplication.class.getSimpleName();
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        Log.d(TAG, "attachBaseContext: base = " + base);
        FileUtils.hotfix(base, FileUtils.HOT_FIX_FILE_NAME);
    }
}
