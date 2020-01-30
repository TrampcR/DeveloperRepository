package com.trampcr.developerrepository.designpatterns.productconsume;

import android.util.Log;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class PublicQueueFromSyn<T> extends PublicQueue<T> {
    public static final String TAG = PublicQueueFromSyn.class.getSimpleName();
    private int mPublicIndex = 0;
    private int mMaxCount = 50;
    private LinkedHashMap<Integer, T> mLinkedHashMap = new LinkedHashMap<>();

    @Override
    public synchronized void add(T msg) {
        Log.d(TAG, "add: mLinkedHashMap.size = " + mLinkedHashMap.size());
        if (mLinkedHashMap.size() == mMaxCount) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            notifyAll();
        }

        mLinkedHashMap.put(mPublicIndex, msg);
        Log.d(TAG, "add: mPublicIndex = " + mPublicIndex + ", msg = " + msg);
        mPublicIndex = (mPublicIndex + 1) > mMaxCount ? (mPublicIndex + 1) % mMaxCount : mPublicIndex + 1;
    }

    @Override
    public synchronized T remove() {
        if (mLinkedHashMap.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            notifyAll();
        }

        Iterator iterator = mLinkedHashMap.entrySet().iterator();
        T value = null;

        if (iterator.hasNext()) {
            Map.Entry<Integer, T> entry = (Map.Entry<Integer, T>) iterator.next();
            int index = entry.getKey();
            value = entry.getValue();
            mLinkedHashMap.remove(index);
            Log.d(TAG, "remove: key = " + index + ", value = " + value);
        }

        return value;
    }
}
