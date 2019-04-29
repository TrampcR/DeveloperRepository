package com.trampcr.developerrepository.utils;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by trampcr on 2018/10/8.
 */

public class DimenUtils {

    /**
     * dp转px
     *
     * @param dp 尺寸大小
     * @return px 像素大小
     */
    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem()
                .getDisplayMetrics());
    }

    /**
     * 获取 Camera 的 Location
     *
     * @return Camera Z 轴坐标，适配所有手机的 Camera Z 轴坐标
     */
    public static float getCameraLocation() {
        return -8 * Resources.getSystem().getDisplayMetrics().density;
    }
}
