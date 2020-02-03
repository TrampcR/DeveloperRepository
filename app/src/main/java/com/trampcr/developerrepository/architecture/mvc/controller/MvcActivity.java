package com.trampcr.developerrepository.architecture.mvc.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.trampcr.developerrepository.R;
import com.trampcr.developerrepository.architecture.mvc.model.DataCenter;
import com.trampcr.developerrepository.architecture.mvc.view.IView;

public class MvcActivity extends AppCompatActivity {
//    private EditText mEditText1;
//    private EditText mEditText2;
    private IView mEditView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_architecture_mvc);

//        mEditText1 = findViewById(R.id.edit_1);
//        mEditText2 = findViewById(R.id.edit_2);
        mEditView = findViewById(R.id.edit_view);

        String[] datas = DataCenter.getData();

//        mEditText1.setText(datas[0]);
//        mEditText2.setText(datas[1]);
        mEditView.showData(datas[0], datas[1]);
    }
}
