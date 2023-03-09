package com.example.task2pizza.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.task2pizza.Customer.CustomerHomeActivity;
import com.example.task2pizza.R;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        SharedPreferences sharedPreferences=getSharedPreferences("auth",MODE_PRIVATE);

        boolean check=sharedPreferences.getBoolean("flag",false);

        if (check)
        {
            startActivity(new Intent(AuthActivity.this, CustomerHomeActivity.class));
            finish();
        }
        else
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.auth_framelayout_id ,new LoginFragment()).commit();
        }

    }
}