package com.trampcr.plugin_demo;

import android.content.Context;
import android.util.Log;

import com.dianping.logan.Logan;
import com.dianping.logan.LoganConfig;

import java.io.File;

public class Utils {
    public static final String TAG = Utils.class.getSimpleName();
    public void shout() {
//        Logan.w("shout: this is plugin", 1);
//        Logan.f();
        Log.d(TAG, "shout: this is plugin from log");
    }

    public static void initLogan(Context context) {
        if (context == null) {
            return;
        }

        LoganConfig config = new LoganConfig.Builder()
                .setCachePath(context.getFilesDir().getAbsolutePath())
                .setPath(context.getExternalFilesDir(null).getAbsolutePath()
                        + File.separator + "logan_v1")
                .setEncryptKey16("0123456789012345".getBytes())
                .setEncryptIV16("0123456789012345".getBytes())
                .build();
        Logan.init(config);
    }
}
