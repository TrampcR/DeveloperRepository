package com.trampcr.developerrepository.customview;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by trampcr on 2019/4/24.
 */

public class ViewUtils {
    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }
}
