package com.example.androidapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.androidapplication.R;

public class SetupActivity extends AppCompatActivity {
    private ImageButton avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        avatar=(ImageButton) findViewById(R.id.imageButton);
        avatar.setOnClickListener(new View.OnClickListener()
        {   @Override
            public void onClick(View v)
            {


            }
        });
    }
}