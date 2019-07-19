package com.trampcr.developerrepository.ipc.aidl;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.trampcr.developerrepository.R;

import java.util.List;

/**
 * Created by trampcr on 2019/7/18.
 */

public class BookManagerActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = BookManagerActivity.class.getSimpleName();
    public static final int MESSAGE_NEW_BOOK_ARRIVED = 1;

    private IBookManager mRemoteBookManager;
    private Button mBtnGetBookList;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_NEW_BOOK_ARRIVED:
                    Log.d(TAG, "handleMessage: receive new book = " + msg.obj);
                    break;
                default:
                    break;
            }
        }
    };

    private IOnNewBookArrivedListener mOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {

        @Override
        public void onNewBookArrived(Book book) throws RemoteException {
            mHandler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED, book).sendToTarget();
        }
    };

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mRemoteBookManager = IBookManager.Stub.asInterface(service);
            try {
                List<Book> bookList = mRemoteBookManager.getBookList();
                Log.d(TAG, "onServiceConnected: bookList type = " + bookList.getClass().getCanonicalName());
                Log.d(TAG, "onServiceConnected: bookList = " + bookList.toString());

                Book newBook = new Book(3, "Android Art");
                mRemoteBookManager.addBook(newBook);
                List<Book> newBookList = mRemoteBookManager.getBookList();
                Log.d(TAG, "onServiceConnected: newBookList = " + newBookList.toString());

                mRemoteBookManager.registerListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_aidl_view);
        mBtnGetBookList = (Button) findViewById(R.id.btn_get_book_list);
        mBtnGetBookList.setOnClickListener(this);

        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        if (mRemoteBookManager != null && mRemoteBookManager.asBinder().isBinderAlive()) {
            try {
                Log.d(TAG, "onDestroy: unregister listener = " + mOnNewBookArrivedListener);
                mRemoteBookManager.unregisterListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(mServiceConnection);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_book_list:
                Log.d(TAG, "onClick: click the button");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (mRemoteBookManager != null) {
                                List<Book> bookList = mRemoteBookManager.getBookList();
                                Log.d(TAG, "onClick: book list = " + bookList.toString());
                            }
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            default:
                break;
        }
    }
}
