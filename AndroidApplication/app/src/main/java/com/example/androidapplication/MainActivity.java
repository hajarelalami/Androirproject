package com.example.androidapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mainBottomNav;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        mainBottomNav=(BottomNavigationView)findViewById(R.id.BottomView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mainInflater = getMenuInflater();
        mainInflater.inflate(R.menu.main_menu,menu);
        MenuInflater bottomInflater = getMenuInflater();
        bottomInflater.inflate(R.menu.bottom_menu,menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.logoutButton:
                logout();
                return true;
            default:
                return false;
        }
     
    }

    private void logout() {
        Intent intent =new Intent(MainActivity.this,NewPostActivity.class);
        startActivity(intent);
        finish();
    }
}