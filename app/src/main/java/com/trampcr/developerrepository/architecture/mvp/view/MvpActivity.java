package com.trampcr.developerrepository.architecture.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.trampcr.developerrepository.R;
import com.trampcr.developerrepository.architecture.mvp.presenter.Presenter;

public class MvpActivity extends AppCompatActivity implements IView {
    private EditText mEditText1;
    private EditText mEditText2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_architecture);

        mEditText1 = findViewById(R.id.edit_1);
        mEditText2 = findViewById(R.id.edit_2);

        Presenter presenter = new Presenter(this);
        presenter.load();
    }

    @Override
    public void showData(String data1, String data2) {
        mEditText1.setText(data1);
        mEditText2.setText(data2);
    }
}
