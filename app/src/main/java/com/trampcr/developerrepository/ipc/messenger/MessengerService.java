package com.trampcr.developerrepository.ipc.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by trampcr on 2019/7/17.
 */

public class MessengerService extends Service {
    public static final String TAG = MessengerService.class.getSimpleName();
    public static final int MSG_FROM_CLIENT = 1;

    private Messenger mMessenger = new Messenger(new MessengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case MSG_FROM_CLIENT:
                    Log.d(TAG, "handleMessage: receive msg from client : " + msg.getData().getString("msg"));

                    Messenger client = msg.replyTo;
                    Message message = Message.obtain(null, MessengerActivity.MSG_FROM_SERVICE);
                    Bundle replyData = new Bundle();
                    replyData.putString("reply", "service is receive client data");
                    message.setData(replyData);
                    try {
                        client.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
