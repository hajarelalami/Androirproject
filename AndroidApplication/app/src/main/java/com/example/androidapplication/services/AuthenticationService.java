package com.example.androidapplication.services;

import android.util.Log;

import com.example.androidapplication.models.User;
import com.example.androidapplication.activities.callbacks.AuthenticationCallBack;
import com.example.androidapplication.data.entities.TokenEntity;
import com.example.androidapplication.data.entities.UserEntity;
import com.example.androidapplication.data.repositories.AuthenticationRepository;
import com.example.androidapplication.interceptors.AuthenticationInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthenticationService {
    public static final String API_BASE_URL = "http://192.168.1.103:8081";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = builder.build();
    private final AuthenticationCallBack authenticationCallBack;
    private AuthenticationRepository authenticationRepository;

    public AuthenticationService(AuthenticationCallBack authenticationCallBack) {
        this.authenticationCallBack = authenticationCallBack;
    }

    public void login(String username, String password) {
        Log.d("Login", username + " : " + password);

        authenticationRepository = createService();

        Call<TokenEntity> call = authenticationRepository.authentication(new UserEntity(username, password));
        call.enqueue(new Callback<TokenEntity>() {
            @Override
            public void onResponse(Call<TokenEntity> call, Response<TokenEntity> response) {
                if (response.isSuccessful()) {
                    Log.d("Successful", "Login Ok");
                    AuthenticationInterceptor.AUTH_TOKEN = response.body().getToken();
                    getUserInfo();
                } else {
                    Log.d("Error", "Login KO");
                    authenticationCallBack.onAuthenticationFailed();
                }
            }

            @Override
            public void onFailure(Call<TokenEntity> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    private void getUserInfo() {
        Call<User> call = createService().getUserInfo();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Log.d("Successful", "Login Ok");
                    authenticationCallBack.onAuthenticationSuccessful();
                } else {
                    Log.d("Error", "Login KO");
                    authenticationCallBack.onAuthenticationFailed();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }


    private AuthenticationRepository createService() {
        if (AuthenticationInterceptor.isUserConnected()) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor();
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);

                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }
        return retrofit.create(AuthenticationRepository.class);
    }
}
