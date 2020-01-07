package com.trampcr.developerrepository.proxy.dynamic;

import android.util.Log;

/**
 * @author trampcr 2019/10/15
 */

public class DynamicProxyDemo {
    public static final String TAG = DynamicProxyDemo.class.getSimpleName();
    public void testDynamicProxy() {
        //生成$Proxy0的class文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        DynamicProxy dynamicProxy = new DynamicProxy();
        ISubject buyer1 = new Buyer1();
        ISubject buyer1Proxy = (ISubject) dynamicProxy.newProxyInstance(buyer1);
        buyer1Proxy.buy();
        Log.d(TAG, "testDynamicProxy: 目标对象1 = " + buyer1.getClass());
        Log.d(TAG, "testDynamicProxy: 代理对象1 = " + buyer1Proxy.getClass());

        ISubject buyer2 = new Buyer2();
        ISubject buyer2Proxy = (ISubject) dynamicProxy.newProxyInstance(buyer2);
        buyer2Proxy.buy();
        Log.d(TAG, "testDynamicProxy: 目标对象2 = " + buyer2.getClass());
        Log.d(TAG, "testDynamicProxy: 代理对象2 = " + buyer2Proxy.getClass());
    }
}
