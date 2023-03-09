package com.example.task2pizza.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.task2pizza.Auth.AuthActivity;
import com.example.task2pizza.R;
import com.example.task2pizza.databinding.ActivityOrderBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;

public class OrderActivity extends AppCompatActivity {
    ActivityOrderBinding binding;
    String pname;
    String pqunty;
    String pimg;
    String pprice, Discoutw;
    int price, qunty, total;
    int shop;
    int a;
    FirebaseUser user;
    String data;
    String OrderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        user = FirebaseAuth.getInstance().getCurrentUser();


        binding.placeorderBTNId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                data = simpleDateFormat.format(calendar.getTime()).toString();

                OrderId = reference.push().getKey().toString();

                OrderModel orderModel = new OrderModel(pname, String.valueOf(qunty), Discoutw, String.valueOf(shop), data, String.valueOf(total), user.getEmail(), OrderId, pimg);


                reference.child("Order").child(user.getUid().toString()).child(OrderId).setValue(orderModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(OrderActivity.this, "place order successful", Toast.LENGTH_SHORT).show();
                            reference.child("AddminOrder").child(data).child(OrderId).setValue(orderModel);
                            startActivity(new Intent(OrderActivity.this, AuthActivity.class));
                            finish();
                        } else {
                            Toast.makeText(OrderActivity.this, "fail to place order ", Toast.LENGTH_SHORT).show();
                        }


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(OrderActivity.this, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        pname = getIntent().getStringExtra("pizzaname");
        pqunty = getIntent().getStringExtra("pizzaqunty");
        pimg = getIntent().getStringExtra("pizzaimg");

        Glide.with(binding.orderPizzaImageId).load(pimg).into(binding.orderPizzaImageId);

        pprice = getIntent().getStringExtra("pizzaprice");
        binding.orderPizzaPriceId.setText(pprice);
        binding.orderPizzaNameId.setText(pname);
        binding.orderQuntyId.setText(pqunty);
        binding.orderPriceId.setText(pprice);
        price = Integer.parseInt(pprice);
        qunty = Integer.parseInt(pqunty);

        String type = pname;
        switch (type) {
            case "Small":
                small();
                break;
            case "Medium":
                medium();
                break;
            case "Large":
                large();
                break;
            case "Monster":
                monster();
                break;

        }


    }

    private void monster() {
        binding.orderDiscountCardId.setVisibility(View.VISIBLE);
        a = qunty / 1;
        System.out.println(a);
        shop = a * 350;
        binding.orderDicountId.setText("Discount::" + a + "Medium pizza 350" + "\u20B9");
        Discoutw = "Medium pizza";
        gtotal();


    }

    private void large() {
        if (qunty >= 2) {
            binding.orderDiscountCardId.setVisibility(View.VISIBLE);
            a = qunty / 2;
            shop = a * 250;
            binding.orderDicountId.setText("Discount::" + a + "Small pizza 250" + "\u20B9");
            Discoutw = "small pizza";
            gtotal();
        } else {
            gtotal();
        }

    }

    private void medium() {
        if (qunty >= 3) {
            binding.orderDiscountCardId.setVisibility(View.VISIBLE);
            a = qunty / 3;
            shop = a * 80;
            binding.orderDicountId.setText("Discount::" + a + "coke 2.5ltr 80" + "\u20B9");
            Discoutw = "Cock 2.5ltr";
            gtotal();
        } else {
            gtotal();
        }


    }

    private void small() {
        if (qunty >= 4) {
            binding.orderDiscountCardId.setVisibility(View.VISIBLE);
            a = qunty / 4;
            shop = a * 40;
            binding.orderDicountId.setText("Discount::" + a + "coke 750ml 40" + "\u20B9");
            Discoutw = "Cock 750ml";
            gtotal();
        } else {
            gtotal();
        }


    }

    private void gtotal() {

        total = price * qunty;
        binding.orderGrandTotalId.setText(String.valueOf(total));

    }
}