package com.example.androidapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.androidapplication.activities.LoginActivity;
import com.example.androidapplication.activities.NewPostActivity;
import com.example.androidapplication.fragments.AccountFragment;
import com.example.androidapplication.fragments.FragmentHome;
import com.example.androidapplication.fragments.NotificationFragment;
import com.example.androidapplication.interceptors.AuthenticationInterceptor;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mainBottomNav;
    private FragmentHome homeFragment;
    private NotificationFragment notificationFragment;
    private AccountFragment accountFragment;
    private FrameLayout main_container;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBottomNav = findViewById(R.id.BottomView);
        //fragments
        homeFragment = new FragmentHome();
        notificationFragment = new NotificationFragment();
        accountFragment = new AccountFragment();
        mainBottomNav.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottomActionHome:
                        replaceFragment(homeFragment);
                    case R.id.BottomNotification:
                        replaceFragment(notificationFragment);

                    case R.id.BottonAccount:
                        replaceFragment(accountFragment);
                    default:
                }

            }

        });
        redirectToLoginActivity();
    }

    @Override
    protected void onResume() {
        super.onResume();
        redirectToLoginActivity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mainInflater = getMenuInflater();
        mainInflater.inflate(R.menu.main_menu, menu);
        MenuInflater bottomInflater = getMenuInflater();
        bottomInflater.inflate(R.menu.bottom_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutButton:
                logout();
                return true;
            default:
                return false;
        }

    }

    private void logout() {
        Intent intent = new Intent(MainActivity.this, NewPostActivity.class);
        startActivity(intent);
        finish();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Called when the user is not authenticate
     */
    public void redirectToLoginActivity() {
        if (!AuthenticationInterceptor.isUserConnected()) {
            // Do something in response to button
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}