package com.trampcr.plugin_demo;

import android.app.Application;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.initLogan(this.getApplicationContext());
    }
}
