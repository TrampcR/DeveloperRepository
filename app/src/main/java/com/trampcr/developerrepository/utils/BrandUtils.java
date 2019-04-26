package com.trampcr.developerrepository.utils;

import android.os.Build;
import android.text.TextUtils;

/**
 * Created by trampcr on 2018/10/9.
 */

public class BrandUtils {

    /** xiaomi */
    public static final String MIUI = "MIUI";
    public static final String MI_ONE = "MI-ONE";
    public static final String MIONE = "mione";
    public static final String XIAOMI = "Xiaomi";

    /**
     * 是否小米手机
     *
     * @return true/false
     */
    public static boolean isMiui() {
        String brand;
        try {
            brand = Build.DISPLAY;
        } catch (NoSuchFieldError error) {
            error.printStackTrace();
            brand = null;
        }

        if (!TextUtils.isEmpty(brand)) {
            if (brand.toUpperCase().contains(MIUI)) {
                return true;
            }
        }

        brand = Build.MODEL;
        if (!TextUtils.isEmpty(brand)) {
            if (brand.contains(MI_ONE)) {
                return true;
            }
        }

        brand = Build.DEVICE;
        if (!TextUtils.isEmpty(brand)) {
            if (brand.contains(MIONE)) {
                return true;
            }
        }

        brand = Build.MANUFACTURER;
        if (!TextUtils.isEmpty(brand)) {
            if (brand.equalsIgnoreCase(XIAOMI)) {
                return true;
            }
        }

        brand = Build.PRODUCT;
        if (!TextUtils.isEmpty(brand)) {
            if (brand.contains(MIONE)) {
                return true;
            }
        }

        return false;
    }
}
