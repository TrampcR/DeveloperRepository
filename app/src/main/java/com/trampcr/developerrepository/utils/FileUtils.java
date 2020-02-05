package com.trampcr.developerrepository.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.PathClassLoader;

public class FileUtils {
    public static final String TAG = FileUtils.class.getSimpleName();
    public static final String HOT_FIX_FILE_NAME = "hotfix-app-debug.apk";

    public static void copyAssetsToCache(Context context, String fileName) {
        try {
            File file = new File(context.getCacheDir().getPath() + "/" + fileName);
//            File file = new File(context.getExternalFilesDir("").getPath() + "/" + fileName);
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

    public static void deleteCache(Context context, String fileName) {
        File file = new File(context.getCacheDir().getPath() + "/" + fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    public static void hotfix(Context context, String fileName) {
        String dexPath = context.getCacheDir().getPath() + "/" + fileName;
        File dexFile = new File(dexPath);
        if (!dexFile.exists()) {
            return;
        }

        // 1、获取当前 apk 中的 dexElements
        try {
            ClassLoader classLoader = context.getClassLoader();
            Log.d(TAG, "hotfix: classLoader = " + classLoader);
            Class loaderClass = BaseDexClassLoader.class;
            Field pathListField = loaderClass.getDeclaredField("pathList");
            pathListField.setAccessible(true);
            Object pathListObject = pathListField.get(classLoader);
            Class pathListClass = pathListObject.getClass();
            Field dexElementsField = pathListClass.getDeclaredField("dexElements");
            Object dexElementsObject = dexElementsField.get(pathListObject);
            dexElementsField.setAccessible(true);

            // 2、获取补丁 dexElements
            PathClassLoader pathClassLoader = new PathClassLoader(dexPath, null);
            Log.d(TAG, "hotfix: pathClassLoader = " + pathClassLoader);
            Object newPathListObject = pathListField.get(pathClassLoader);
            Object newDexElementsObject = dexElementsField.get(newPathListObject);

            // 3、合并 dexElementsObject 和 newDexElementsObject
            int oldLength = Array.getLength(dexElementsObject);
            int newLength = Array.getLength(newDexElementsObject);
            Object concatDexElementsObject = Array.newInstance(dexElementsObject.getClass().getComponentType(),
                    oldLength + newLength);
            for (int i = 0; i < newLength; i++) {
                Array.set(concatDexElementsObject, i, Array.get(newDexElementsObject, i));
            }

            for (int i = 0; i < oldLength; i++) {
                Array.set(concatDexElementsObject, newLength + i, Array.get(dexElementsObject, i));
            }

            // 4、用补丁 dexElements 或合并后的 dexElements替换当前 apk 的 dexElements
//            dexElementsField.set(pathListObject, newDexElementsObject);
            dexElementsField.set(pathListObject, concatDexElementsObject);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
