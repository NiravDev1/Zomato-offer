package com.example.task2pizza.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.task2pizza.Auth.AuthActivity;
import com.example.task2pizza.R;
import com.example.task2pizza.databinding.ActivityCustomerHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class CustomerHomeActivity extends AppCompatActivity {
    ActivityCustomerHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        binding.logOutId.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences sharedPreferences = getSharedPreferences("auth", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putBoolean("flag", false);
//                editor.apply();
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(CustomerHomeActivity.this, AuthActivity.class));
//                finish();
//            }
//        });
        getSupportFragmentManager().beginTransaction().replace(R.id.customer_dashbord_fremelayout_id, new HomeCFragment()).commit();
        binding.customerDashbordBottomNavId.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.home_menu_id:
                        fragment = new HomeCFragment();
                        break;
                    case R.id.profile_menu_id:
                        fragment = new ProfileCFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.customer_dashbord_fremelayout_id, fragment).commit();


                return true;
            }
        });
    }
}