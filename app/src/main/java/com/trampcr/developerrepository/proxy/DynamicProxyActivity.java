package com.trampcr.developerrepository.proxy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.trampcr.developerrepository.R;

/**
 * Created by trampcr on 2019/7/1.
 */

public class DynamicProxyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dynamic_proxy_view);

        DynamicProxy dynamicProxy = new DynamicProxy();
        Buyer1 buyer1 = new Buyer1();
        ISubject buyer1DynamicProxy = (ISubject) dynamicProxy.newProxyInstance(buyer1);
        buyer1DynamicProxy.buy();

        Buyer2 buyer2 = new Buyer2();
        ISubject buyer2DynamicProxy = (ISubject) dynamicProxy.newProxyInstance(buyer2);
        buyer2DynamicProxy.buy();
    }
}
