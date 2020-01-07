package com.trampcr.developerrepository.proxy.dynamic;

import android.util.Log;

/**
 * @author trampcr 2019/10/15
 */

public class Buyer2 implements ISubject {
    @Override
    public void buy() {
        Log.d(DynamicProxyDemo.TAG, "buy: Buyer2");
    }
}
