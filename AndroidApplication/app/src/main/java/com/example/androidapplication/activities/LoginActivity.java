package com.example.androidapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidapplication.MainActivity;
import com.example.androidapplication.R;
import com.example.androidapplication.activities.callbacks.AuthenticationCallBack;
import com.example.androidapplication.presenters.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements AuthenticationCallBack {

    private EditText emailText;
    private EditText passwordText;
    private Button loginButton;
    private Button signUp;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPresenter = new LoginPresenter(this);
        emailText = (EditText) findViewById(R.id.emailText_login);
        passwordText = (EditText) findViewById(R.id.passwordText_login);
        loginButton = (Button) findViewById(R.id.loginButton_login);
        signUp = (Button) findViewById(R.id.signUpButton_login);

        loginButton.setOnClickListener(new OnLoginButtonClicked());
        signUp.setOnClickListener(new OnSignUpButtonClicked());
    }

    @Override
    public void onAuthenticationSuccessful() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onAuthenticationFailed() {
        //Show error message
    }

    private class OnLoginButtonClicked implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Log.d("Login", "Login");
            loginPresenter.login(
                    emailText.getText().toString(),
                    passwordText.getText().toString()
            );
        }
    }

    private class OnSignUpButtonClicked implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        }
    }
}