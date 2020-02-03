package com.trampcr.developerrepository.architecture.mvc.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.trampcr.developerrepository.R;

public class EditView extends LinearLayout implements IView{
    private EditText mEditText1;
    private EditText mEditText2;

    public EditView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mEditText1 = findViewById(R.id.edit_1);
        mEditText2 = findViewById(R.id.edit_2);
    }

    @Override
    public void showData(String data1, String data2) {
        mEditText1.setText(data1);
        mEditText2.setText(data2);
    }
}
