package com.trampcr.developerrepository.retrofitdemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by trampcr on 2019/1/16.
 */

public interface GitHubService {
    @GET("user/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
