package com.example.task2pizza.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.task2pizza.R;
import com.example.task2pizza.databinding.ActivityAdminDashbordBinding;
import com.google.android.material.navigation.NavigationView;

public class AdminDashbordActivity extends AppCompatActivity {
    ActivityAdminDashbordBinding binding;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminDashbordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toolbar=binding.toolbarId;
        setSupportActionBar(toolbar);
        drawerLayout = binding.adminDrawerlayoutId;


        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open, R.string.Close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction().replace(R.id.Admin_dashbord_framelayout_id,new HomeAFragment()).commit();
        binding.adminNavigationViewId.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.admin_home_m_id:
                        fragment = new HomeAFragment();
                        break;
                    case R.id.admin_add_m_id:
                        fragment = new AddAFragment();
                        break;
                    case R.id.admin_sell_m_id:
                        fragment = new SellAFragment();
                        break;


                }
                getSupportFragmentManager().beginTransaction().replace(R.id.Admin_dashbord_framelayout_id, fragment).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }

        });


    }
}