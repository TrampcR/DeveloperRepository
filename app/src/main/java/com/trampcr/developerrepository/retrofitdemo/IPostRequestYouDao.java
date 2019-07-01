package com.trampcr.developerrepository.retrofitdemo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by trampcr on 2019/6/12.
 */

public interface IPostRequestYouDao {
    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    Call<TranslationYouDao> getCall(@Field("i") String targetSentence);
}
