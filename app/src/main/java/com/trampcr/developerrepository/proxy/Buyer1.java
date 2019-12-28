package com.trampcr.developerrepository.proxy;

import android.util.Log;

import com.trampcr.developerrepository.MainActivity;

/**
 * Created by trampcr on 2019/7/1.
 */

public class Buyer1 implements ISubject {

    @Override
    public void buy() {
        Log.d(MainActivity.TAG, "buy: mac");
    }
}
