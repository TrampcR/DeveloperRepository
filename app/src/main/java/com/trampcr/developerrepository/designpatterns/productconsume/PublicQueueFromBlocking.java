package com.trampcr.developerrepository.designpatterns.productconsume;

import android.util.Log;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class PublicQueueFromBlocking<T> extends PublicQueue<T> {
    public static final String TAG = PublicQueueFromBlocking.class.getSimpleName();
    private BlockingDeque mBlockingDeque = new LinkedBlockingDeque(50);

    @Override
    public synchronized void add(T msg) {
        try {
            mBlockingDeque.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "add: msg = " + msg);
    }

    @Override
    public synchronized T remove() {
        T t = null;

        try {
            t = (T) mBlockingDeque.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "remove: t = " + t);
        return t;
    }
}
