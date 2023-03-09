package com.example.task2pizza.Customer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.task2pizza.Admin.PizzaModel;
import com.example.task2pizza.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.PhoneAuthOptions;

import java.util.ArrayList;
import java.util.List;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.pizzaviewholder> {
    int count = 1;
    List<PizzaModel> pizzaModelArrayList;
    Context context;


    public PizzaAdapter(Context context) {
        pizzaModelArrayList = new ArrayList<>();
        this.context = context;
    }

    public void add(PizzaModel pizzaModel) {
        pizzaModelArrayList.add(pizzaModel);
        notifyDataSetChanged();

    }

    void clear() {
        pizzaModelArrayList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public pizzaviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pizza_single_card, parent, false);

        return new pizzaviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull pizzaviewholder holder, int position) {
        PizzaModel model = pizzaModelArrayList.get(position);
        holder.pname.setText(model.getPizzaname());
        holder.pprice.setText(model.getPizzaprice());
        Glide.with(holder.pizzaimg).load(model.getPizzaImg()).into(holder.pizzaimg);
        holder.increseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;
                holder.qunty.setText(String.valueOf(count));
            }
        });
        holder.decreseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count != 1) {
                    count--;
                    holder.qunty.setText(String.valueOf(count));
                }

            }
        });

        holder.additemBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, OrderActivity.class);
                i.putExtra("pizzaname", model.getPizzaname().toString().trim());
                i.putExtra("pizzaqunty", holder.qunty.getText().toString());
                i.putExtra("pizzaimg", model.getPizzaImg());
                i.putExtra("pizzaprice", model.getPizzaprice());
                context.startActivity(i);
//                Bundle bundle = new Bundle();
//                bundle.putString("n", "nirav");
//                OrderconfirmFragment fragment = new OrderconfirmFragment();
//                fragment.setArguments(bundle);
//                FragmentManager fragmentManager=((AppCompatActivity)context).getSupportFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.customer_dashbord_fremelayout_id,fragment).commit();


            }
        });

    }

    @Override
    public int getItemCount() {
        return pizzaModelArrayList.size();
    }

    public class pizzaviewholder extends RecyclerView.ViewHolder {
        ImageView pizzaimg, increseBtn, decreseBtn;
        TextView pname, pprice, qunty;
        Button additemBTN;

        public pizzaviewholder(@NonNull View itemView) {
            super(itemView);
            pizzaimg = itemView.findViewById(R.id.card_cpizza_img_id);
            pname = itemView.findViewById(R.id.card_cpizza_name_id);
            pprice = itemView.findViewById(R.id.card_cpizza_price_id);

            increseBtn = itemView.findViewById(R.id.increse_Btn_card_id);
            decreseBtn = itemView.findViewById(R.id.decrese_btn_Card);
            qunty = itemView.findViewById(R.id.qunty_count_Card_id);

            additemBTN = itemView.findViewById(R.id.add_pizza_iteme_Card_BTn_id);
        }
    }
}
