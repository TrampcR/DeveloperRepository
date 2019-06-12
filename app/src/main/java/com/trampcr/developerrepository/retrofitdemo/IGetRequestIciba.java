package com.trampcr.developerrepository.retrofitdemo;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by trampcr on 2019/6/11.
 */

public interface IGetRequestIciba {
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<Translation> getCall();
}
