package com.trampcr.developerrepository.architecture.mvvm.viewmodel;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.trampcr.developerrepository.architecture.mvvm.model.TextAttr;
import com.trampcr.developerrepository.architecture.mvvm.model.TextChangeListener;

public class ViewBinder {
    public static final String TAG = ViewBinder.class.getSimpleName();

    public void bind(final EditText editText, final TextAttr text) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String editText = s.toString();
                if (!text.getText().equals(editText)) {
                    text.setText(editText);
                }
                Log.d(TAG, "afterTextChanged: " + editText);
            }
        });

        text.setListener(new TextChangeListener() {
            @Override
            public void onChange(String newText) {
                if (!newText.equals(editText.getText().toString())) {
                    editText.setText(newText);
                }
                Log.d(TAG, "onChange: " + newText);
            }
        });
    }
}
