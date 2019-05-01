package com.trampcr.developerrepository.customview.province;

import android.animation.TypeEvaluator;

public class ProvinceTypeEvaluator implements TypeEvaluator<String> {
    @Override
    public String evaluate(float fraction, String startValue, String endValue) {
        int startIndex = ProvinceView.mProvinces.indexOf(startValue);
        int endIndex = ProvinceView.mProvinces.indexOf(endValue);
        int index = (int) (startIndex + (endIndex - startIndex) * fraction);
        String province = ProvinceView.mProvinces.get(index);
        return province;
    }
}
