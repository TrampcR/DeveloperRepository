package com.trampcr.developerrepository.thread.threadlocal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.trampcr.developerrepository.R;

public class ThreadLocalActivity extends AppCompatActivity {
    private static final String TAG = ThreadLocalActivity.class.getSimpleName();
    private static final String THREAD_1 = "线程1";
    private static final String THREAD_2 = "线程2";
    private static final String THREAD_INIT = "init";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_thread_local_view);

        testThreadLocal();
    }

    private void testThreadLocal() {
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable, THREAD_1).start();
        new Thread(myRunnable, THREAD_2).start();
    }

    private class MyRunnable implements Runnable {
        private ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
            @Override
            protected String initialValue() {
                return THREAD_INIT;
            }
        };

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            threadLocal.set(threadName + " 的 ThreadLocal");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.d(TAG, "run: " + threadName + ", " + threadLocal.get());
        }
    }
}
