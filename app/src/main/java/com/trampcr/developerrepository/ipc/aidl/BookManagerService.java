package com.trampcr.developerrepository.ipc.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by trampcr on 2019/7/17.
 */

public class BookManagerService extends Service {
    public static final String TAG = BookManagerService.class.getSimpleName();

    private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);

    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();
    private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList = new RemoteCallbackList<>();

    private Binder mBinder = new IBookManager.Stub() {

        @Override
        public List<Book> getBookList() throws RemoteException {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            mListenerList.register(listener);
            Log.d(TAG, "registerListener: listener add succeed, listener size = " + mListenerList.beginBroadcast());
            mListenerList.finishBroadcast();
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            mListenerList.unregister(listener);
            Log.d(TAG, "unregisterListener: listener remove succeed, listener size = " + mListenerList.beginBroadcast());
            mListenerList.finishBroadcast();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        mBookList.add(new Book(1, "Android"));
        mBookList.add(new Book(2, "iOS"));

//        new Thread(new ServiceWorker()).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private void onNewBookArrived(Book book) {
        mBookList.add(book);
        int listenerNum = mListenerList.beginBroadcast();
        Log.d(TAG, "onNewBookArrived: notify listeners = " + listenerNum);
        for (int i = 0; i < listenerNum; i++) {
            IOnNewBookArrivedListener listener = mListenerList.getBroadcastItem(i);
            Log.d(TAG, "onNewBookArrived: notify listener = " + listener);
            if (listener == null) {
                return;
            }

            try {
                listener.onNewBookArrived(book);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        mListenerList.finishBroadcast();
    }

    private class ServiceWorker implements Runnable {

        @Override
        public void run() {
            while (!mIsServiceDestoryed.get()) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int bookId = mBookList.size() + 1;
                Book newBook = new Book(bookId, "new book #" + bookId);
                onNewBookArrived(newBook);
            }
        }
    }
}
