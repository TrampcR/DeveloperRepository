package com.trampcr.developerrepository.reflect;

import android.util.Log;

public class ReflectBean {
    public static final String NAME = "name";
    private String name;

    public ReflectBean() {
        Log.d(ReflectActivity.TAG, "无参构造方法 创建了一个 ReflectBean 实例");
    }

    public ReflectBean(String name) {
        Log.d(ReflectActivity.TAG, "有参构造方法 创建了一个 ReflectBean 实例 name = " + name);
    }

    public void setName1() {
        Log.d(ReflectActivity.TAG, "无参方法 setName1: ");
    }

    public void setName2(String name) {
        Log.d(ReflectActivity.TAG, "有参方法 setName2: name = " + name);
    }
}
