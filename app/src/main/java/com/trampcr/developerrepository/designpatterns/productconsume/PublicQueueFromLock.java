package com.trampcr.developerrepository.designpatterns.productconsume;

import android.util.Log;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PublicQueueFromLock<T> extends PublicQueue<T> {
    public static final String TAG = PublicQueueFromLock.class.getSimpleName();
    private int mIndex = 0;
    private int mMaxCount = 50;
    private ReentrantLock mLock;
    private Condition mAddCondition;
    private Condition mRemoveCondition;
    private LinkedHashMap<Integer, T> mLinkedHashMap = new LinkedHashMap<>();

    public PublicQueueFromLock() {
        mLock = new ReentrantLock();
        mAddCondition = mLock.newCondition();
        mRemoveCondition = mLock.newCondition();
    }

    @Override
    public void add(T msg) {
        try {
            mLock.lock();

            if (mLinkedHashMap.size() == mMaxCount) {
                mAddCondition.await();
            }

            mLinkedHashMap.put(mIndex, msg);
            Log.d(TAG, "add: mIndex = " + mIndex + ", size = " + mLinkedHashMap.size() + ", msg = " + msg);
            mIndex = (mIndex + 1) > mMaxCount ? (mIndex + 1) % mMaxCount : mIndex + 1;

            mRemoveCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mLock.unlock();
        }
    }

    @Override
    public T remove() {
        T t = null;
        try {
            mLock.lock();

            if (mLinkedHashMap.size() == 0) {
                mRemoveCondition.await();
            }

            Iterator iterator = mLinkedHashMap.entrySet().iterator();
            if (iterator.hasNext()) {
                Map.Entry<Integer, T> entry = (Map.Entry<Integer, T>) iterator.next();
                int key = entry.getKey();
                t = entry.getValue();
                mLinkedHashMap.remove(key);
                Log.d(TAG, "remove: key = " + key + ", t = " + t);
            }

            mAddCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mLock.unlock();
        }

        return t;
    }
}
