package com.trampcr.developerrepository.startactivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trampcr.developerrepository.R;

/**
 * Created by trampcr on 2019/1/30.
 */

public class StartActivityDemo extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }
}
