package com.example.androidapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
public class LoginActivity extends AppCompatActivity {

    private EditText loginEmailText;
    private EditText PasswordLoginText;
    private Button LoginButton;
    private Button SignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginEmailText = (EditText) findViewById(R.id.emailReg);
        PasswordLoginText =(EditText)findViewById(R.id.passconfirmReg);
        LoginButton=(Button)findViewById(R.id.LoginButton);
        SignUp=(Button)findViewById(R.id.SignUpButtonLogin);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}