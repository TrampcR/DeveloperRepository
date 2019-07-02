package com.trampcr.developerrepository.proxy;

import android.util.Log;

import com.trampcr.developerrepository.MainActivity;

/**
 * Created by Administrator on 2019/7/1.
 */

public class Buyer2 implements ISubject {
    @Override
    public void buy() {
        Log.d(MainActivity.TAG, "buy: iphone");
    }
}
