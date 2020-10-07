package com.example.androidapplication.interceptors;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {

    public static String AUTH_TOKEN;

    public AuthenticationInterceptor() {
    }

    public static boolean isUserConnected() {
        return !TextUtils.isEmpty(AUTH_TOKEN);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header("Authorization", AUTH_TOKEN);

        Request request = builder.build();
        return chain.proceed(request);
    }
}
