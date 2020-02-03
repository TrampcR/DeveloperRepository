package com.trampcr.developerrepository.architecture.mvvm.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.trampcr.developerrepository.R;
import com.trampcr.developerrepository.architecture.mvvm.viewmodel.ViewBinder;
import com.trampcr.developerrepository.architecture.mvvm.viewmodel.ViewModel;

public class MvvmActivity extends AppCompatActivity {
    private EditText editText1;
    private EditText editText2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_architecture);
        editText1 = findViewById(R.id.edit_1);
        editText2 = findViewById(R.id.edit_2);

        ViewBinder binder = new ViewBinder();
        ViewModel viewModel = new ViewModel(binder, editText1, editText2);
        viewModel.load();
    }
}
