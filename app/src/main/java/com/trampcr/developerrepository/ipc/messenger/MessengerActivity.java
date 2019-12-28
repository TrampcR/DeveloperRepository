package com.trampcr.developerrepository.ipc.messenger;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.trampcr.developerrepository.R;

/**
 * Created by trampcr on 2019/7/17.
 */

public class MessengerActivity extends AppCompatActivity {
    public static final String TAG = MessengerActivity.class.getSimpleName();
    public static final int MSG_FROM_SERVICE = 2;

    private Messenger mService;
    private Messenger mClient = new Messenger(new ClientHandler());

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);
            Message message = Message.obtain(null, MessengerService.MSG_FROM_CLIENT);
            Bundle data = new Bundle();
            data.putString("msg", "hello, this is client");
            message.setData(data);
            message.replyTo = mClient;
            try {
                mService.send(message);
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

        setContentView(R.layout.activity_messenger_view);

        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(mServiceConnection);
        super.onDestroy();
    }

    private static class ClientHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case MSG_FROM_SERVICE:
                    Log.d(TAG, "handleMessage: receive msg from service : " + msg.getData().getString("reply"));
                    break;
                default:
                    break;
            }
        }
    }
}
