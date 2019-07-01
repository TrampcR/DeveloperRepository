package com.trampcr.developerrepository.retrofitdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.trampcr.developerrepository.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by trampcr on 2019/6/12.
 */

public class PostRequestYouDaoActivity extends AppCompatActivity {
    public static final String TAG = PostRequestYouDaoActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_post_request_youdao_view);

        request();
    }

    private void request() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IPostRequestYouDao request = retrofit.create(IPostRequestYouDao.class);
        Call<TranslationYouDao> call = request.getCall("I love you");

        call.enqueue(new Callback<TranslationYouDao>() {
            @Override
            public void onResponse(Call<TranslationYouDao> call, Response<TranslationYouDao> response) {
                Log.d(TAG, "onResponse: " + response.body().getTranslateResult().get(0).get(0).getTgt());
            }

            @Override
            public void onFailure(Call<TranslationYouDao> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
