package com.trampcr.developerrepository.customview.province;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.trampcr.developerrepository.utils.DimenUtils;

import java.util.Arrays;
import java.util.List;

public class ProvinceView extends View {
    public static List<String> mProvinces = Arrays.asList(
            "山东：济南",
            "河北：石家庄",
            "吉林：长春",
            "黑龙江：哈尔滨",
            "辽宁：沈阳",
            "内蒙古：呼和浩特",
            "新疆：乌鲁木齐",
            "甘肃：兰州",
            "宁夏：银川",
            "山西：太原",
            "陕西：西安",
            "河南：郑州",
            "安徽：合肥",
            "江苏：南京",
            "浙江：杭州",
            "福建：福州",
            "广东：广州",
            "江西：南昌",
            "海南：海口",
            "广西：南宁",
            "贵州：贵阳",
            "湖南：长沙",
            "湖北：武汉",
            "四川：成都",
            "云南：昆明",
            "西藏：拉萨",
            "青海：西宁",
            "天津：天津",
            "上海：上海",
            "重庆：重庆",
            "北京：北京",
            "台湾：台北",
            "香港",
            "澳门");

    private Paint mProvincePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private String mProvince = "山东：济南";

    public ProvinceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mProvincePaint.setTextAlign(Paint.Align.CENTER);
        mProvincePaint.setTextSize(DimenUtils.dp2px(20));
        canvas.drawText(mProvince, getWidth() / 2, getHeight() / 2, mProvincePaint);
    }

    public String getProvince() {
        return mProvince;
    }

    public void setProvince(String province) {
        this.mProvince = province;
        invalidate();
    }
}
