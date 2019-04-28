package com.trampcr.developerrepository.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.Settings;

/**
 * Created by trampcr on 2018/10/8.
 */

public class CommonUtils {

    /**
     * 获取Android ID
     *
     * @param context 上下文
     * @return String类型的Android ID
     */
    public static String getAndroidId(Context context) {
        if (context == null) {
            return "";
        }

        try {
            ContentResolver contentResolver = context.getContentResolver();
            return Settings.System.getString(contentResolver, Settings.System.ANDROID_ID);
        } catch (Exception e) {
            return "";
        }


        //创建实例 ID
//        String iid = InstanceID.getInstance(context).getId();

        //自定义全局唯一 ID (GUID)
//        String uniqueID = UUID.randomUUID().toString();
    }

    public static Bitmap getAvatar(Context context, int width, int bitmapId) {
        if (context == null) {
            return null;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;// 设置为 true 后，只会获取图片的宽高
        BitmapFactory.decodeResource(context.getResources(), bitmapId, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;

        return BitmapFactory.decodeResource(context.getResources(), bitmapId, options);
    }
}
