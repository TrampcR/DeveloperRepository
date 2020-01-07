package com.trampcr.developerrepository.proxy.dynamic;

import android.util.Log;

/**
 * @author trampcr 2019/10/15
 */

public class Buyer1 implements ISubject {
    @Override
    public void buy() {
        Log.d(DynamicProxyDemo.TAG, "buy: Buyer1");
    }
}
