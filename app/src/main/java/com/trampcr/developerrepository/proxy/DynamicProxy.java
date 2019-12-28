package com.trampcr.developerrepository.proxy;

import android.util.Log;

import com.trampcr.developerrepository.MainActivity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by trampcr on 2019/7/1.
 */

public class DynamicProxy implements InvocationHandler {
    private Object mProxyObject;

    public Object newProxyInstance(Object proxyObject) {
        mProxyObject = proxyObject;
        return Proxy.newProxyInstance(proxyObject.getClass().getClassLoader(), proxyObject.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.d(MainActivity.TAG, "invoke: proxy go to buy something");
        return method.invoke(mProxyObject, args);
    }
}
