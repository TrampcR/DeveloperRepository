package com.trampcr.developerrepository.proxy;

import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trampcr.developerrepository.R;

/**
 * Created by trampcr on 2019/7/1.
 */

public class DynamicProxyActivity extends AppCompatActivity {

    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dynamic_proxy_view);
        mLinearLayout = (LinearLayout) findViewById(R.id.ll_text);

        DynamicProxy dynamicProxy = new DynamicProxy();
        Buyer1 buyer1 = new Buyer1();
        ISubject buyer1DynamicProxy = (ISubject) dynamicProxy.newProxyInstance(buyer1);
        buyer1DynamicProxy.buy();

        Buyer2 buyer2 = new Buyer2();
        ISubject buyer2DynamicProxy = (ISubject) dynamicProxy.newProxyInstance(buyer2);
        buyer2DynamicProxy.buy();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                TextView textView = new TextView(DynamicProxyActivity.this);
                textView.setText("hahaha");
                textView.setTextColor(getResources().getColor(R.color.colorPrimary));
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_APPLICATION, 0, 0);
                getWindowManager().addView(textView, layoutParams);

                Looper.loop();
            }
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Looper.prepare();
//
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                TextView textView = new TextView(DynamicProxyActivity.this);
//                textView.setText("hahaha");
//                textView.setTextColor(getResources().getColor(R.color.colorPrimary));
//                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
//                        WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_APPLICATION, 0, 0);
//                getWindowManager().addView(textView, layoutParams);
//
//                Looper.loop();
//            }
//        }).start();
    }
}
