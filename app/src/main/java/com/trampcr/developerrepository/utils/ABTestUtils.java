package com.trampcr.developerrepository.utils;

import android.content.Context;
import android.text.TextUtils;

/**
 * Created by trampcr on 2018/10/8.
 */

public class ABTestUtils {

    /**
     * 是否单号，用于AB测试
     *
     * @param context 上下文
     * @return true/false
     */
    public static boolean isATest(Context context) {
        int lastNum;

        try {
            String androidId = CommonUtils.getAndroidId(context);
            if (TextUtils.isEmpty(androidId)) {
                return false;
            }
            String subAndroidId = androidId.substring(androidId.length() - 1, androidId.length());
            lastNum = Integer.parseInt(subAndroidId, 16);
        } catch (Exception e) {
            return false;
        }

        return (lastNum & 0x1) == 1;
    }
}
