package com.trampcr.developerrepository.proxy.dynamic;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author trampcr 2019/10/15
 */

public class DynamicProxy implements InvocationHandler {
    private Object mRealObject;
    public Object newProxyInstance(Object realObject) {
        mRealObject = realObject;
        return Proxy.newProxyInstance(realObject.getClass().getClassLoader(), realObject.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.d(DynamicProxyDemo.TAG, "invoke: proxy work");
        Object result = method.invoke(mRealObject, args);
        return result;
    }
}
