package com.trampcr.developerrepository.architecture.mvvm.viewmodel;

import android.widget.EditText;

import com.trampcr.developerrepository.architecture.mvvm.model.DataCenter;
import com.trampcr.developerrepository.architecture.mvvm.model.TextAttr;

public class ViewModel {
    private TextAttr data1 = new TextAttr();
    private TextAttr data2 = new TextAttr();

    public ViewModel(ViewBinder binder, EditText editText1, EditText editText2) {
        binder.bind(editText1, data1);
        binder.bind(editText2, data2);
    }

    public void load() {
        String[] datas = DataCenter.getData();
        data1.setText(datas[0]);
        data2.setText(datas[1]);
    }
}
