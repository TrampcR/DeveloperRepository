package com.trampcr.developerrepository.architecture.mvp.presenter;

import com.trampcr.developerrepository.architecture.mvc.model.DataCenter;
import com.trampcr.developerrepository.architecture.mvp.view.IView;

public class Presenter {
    private IView mMvpActivity;

    public Presenter(IView mvpActivity) {
        mMvpActivity = mvpActivity;
    }

    public void load() {
        String[] datas = DataCenter.getData();
        mMvpActivity.showData(datas[0], datas[1]);
    }
}
