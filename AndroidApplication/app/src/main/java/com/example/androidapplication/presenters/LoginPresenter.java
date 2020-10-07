package com.example.androidapplication.presenters;


import com.example.androidapplication.activities.callbacks.AuthenticationCallBack;
import com.example.androidapplication.services.AuthenticationService;

public class LoginPresenter implements AuthenticationCallBack {
    private final AuthenticationService authenticationService;
    private final AuthenticationCallBack authenticationCallBack;

    public LoginPresenter(AuthenticationCallBack authenticationCallBack) {
        this.authenticationCallBack = authenticationCallBack;
        authenticationService = new AuthenticationService(this);
    }

    public void login(String username, String password) {
        authenticationService.login(username, password);
    }

    @Override
    public void onAuthenticationSuccessful() {
        authenticationCallBack.onAuthenticationSuccessful();

    }

    @Override
    public void onAuthenticationFailed() {
        authenticationCallBack.onAuthenticationFailed();
    }
}
