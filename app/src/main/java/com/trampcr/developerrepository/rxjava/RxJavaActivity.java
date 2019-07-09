package com.trampcr.developerrepository.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.trampcr.developerrepository.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by trampcr on 2019/7/3.
 */

public class RxJavaActivity extends AppCompatActivity {
    public static final String TAG = RxJavaActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rx_java_view);

        rxJavaDemo1();
        rxJavaDemo2();
    }

    private void rxJavaDemo1() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: 开始采用 subscribe 连接");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "onNext: 对 Next 事件：" + value + " 作出响应");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: 对 Error 事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: 对 Complete 事件作出响应");
            }
        };

        observable.subscribe(observer);
    }

    private void rxJavaDemo2() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: +++ 开始采用 subscribe 连接");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "onNext: +++ 对 Next 事件：" + value + " 作出响应");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: +++ 对 Error 事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: +++ 对 Complete 事件作出响应");
            }
        });
    }
}
