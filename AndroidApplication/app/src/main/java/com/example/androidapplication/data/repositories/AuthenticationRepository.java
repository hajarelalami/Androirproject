package com.example.androidapplication.data.repositories;

import com.example.androidapplication.models.User;
import com.example.androidapplication.data.entities.TokenEntity;
import com.example.androidapplication.data.entities.UserEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AuthenticationRepository {
    @POST("/authenticate")
    Call<TokenEntity> authentication(@Body UserEntity user);

    @GET("/info")
    Call<User> getUserInfo();

}
