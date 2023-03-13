package com.example.task2pizza.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.task2pizza.R;
import com.example.task2pizza.databinding.ActivityCustomerHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

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

        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(task -> {
                    String messag = "Successfully";

                    if (!task.isSuccessful()) {
                        messag = "Failed";
                    }
                    Toast.makeText(CustomerHomeActivity.this, messag, Toast.LENGTH_SHORT).show();


                });

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (!task.isSuccessful())
            {
                System.out.println("not failed");
            }
            String  token =task.getResult();
            System.out.println(token);

                       });
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel("TestNotification", "TestNotification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);

        }


        getSupportFragmentManager().beginTransaction().replace(R.id.customer_dashbord_fremelayout_id, new HomeCFragment()).commit();
        binding.customerDashbordBottomNavId.setOnNavigationItemSelectedListener(item -> {
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
        });
    }
}