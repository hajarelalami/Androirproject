package com.example.androidapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.androidapplication.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText emailSignUp;
    private EditText passSignUp;
    private EditText confiPasSignUp;
    private Button login;
    private ImageButton back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        emailSignUp =(EditText) findViewById(R.id.emailReg);
        passSignUp=(EditText) findViewById(R.id.passwordReg);
        confiPasSignUp =(EditText) findViewById(R.id.passconfirmReg);
        login=(Button)findViewById(R.id.LoginButton);
        back=(ImageButton) findViewById(R.id.Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String email=emailSignUp.getText().toString();
                String password=passSignUp.getText().toString();
                String confirm_Password=confiPasSignUp.getText().toString();
                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) &&!TextUtils.isEmpty(confirm_Password)){
                        if(password.equals(confirm_Password)){
                            Intent setUpIntent =new Intent(RegisterActivity.this, SetupActivity.class);
                            startActivity(setUpIntent);
                            finish();
                        }else{
                            Toast.makeText(RegisterActivity.this,"confirm password and password field doesn't match",Toast.LENGTH_LONG).show();
                        }
                }
            }
        });
    }

}
