package com.example.githubuserapp.Config;

import com.example.githubuserapp.Models.ResponseDetail;
import com.example.githubuserapp.Models.ResponseGithub;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search/users")
    @Headers("Authorization: token " + TOKEN)
    Call<ResponseGithub> getUsers(@Query("q") String username);

    @GET("users/{username}")
    Call<ResponseDetail> getDetailUser(@Path("username") String username);

    String TOKEN = "";
}
