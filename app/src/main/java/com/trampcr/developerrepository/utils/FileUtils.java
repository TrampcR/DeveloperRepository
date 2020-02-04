package com.trampcr.developerrepository.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {
    public static void copyAssetsToCache(Context context, String fileName) {
        try {
//            File file = new File(context.getCacheDir().getPath() + "/" + fileName);
            File file = new File(context.getExternalFilesDir("").getPath() + "/" + fileName);
            Log.d("zxm", "copyAssetsToCache: " + file.getPath());
            if (!file.exists()) {
                AssetManager assetManager = context.getAssets();
                InputStream inputStream = assetManager.open("apk/" + fileName);
                int len = inputStream.available();
                Log.d("zxm", "copyAssetsToCache: len = " + len);
                byte[] buffer = new byte[len];
                inputStream.read(buffer);
                inputStream.close();

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(buffer);
                fileOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
