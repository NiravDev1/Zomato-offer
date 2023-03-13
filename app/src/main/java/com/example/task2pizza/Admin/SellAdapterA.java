package com.example.task2pizza.Admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task2pizza.Customer.OrderModel;
import com.example.task2pizza.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class SellAdapterA  extends FirebaseRecyclerAdapter<OrderModel,SellAdapterA.sellerviewholder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public SellAdapterA(@NonNull FirebaseRecyclerOptions<OrderModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull sellerviewholder holder, int position, @NonNull OrderModel model) {
        holder.cemail.setText(model.getCustomerEmail());
        holder.pname.setText(model.getPizzaName());
        holder.pqunty.setText(model.getPizzaQunty());
        holder.subtotal.setText(model.getGrandTotal());
        holder.discoutprice.setText(model.getDiscountPrice());
        holder.date.setText(model.getDateofBuy());
        holder.oprice.setText(model.getPizzaOrigin());
        holder.discoutinword.setText(model.getPizzaDiscount());

        try {
            int dp= Integer.parseInt(model.getDiscountPrice());
            int sp= Integer.parseInt(model.getGrandTotal());

            holder.grandtotal.setText("\u20B9"+String.valueOf(dp+sp));

        }catch (Exception e)
        {
            System.out.println(e);
        }


    }

    @NonNull
    @Override
    public sellerviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_admin_order_card,parent,false);

        return new sellerviewholder(view);
    }

    public class  sellerviewholder extends RecyclerView.ViewHolder{


        TextView  cemail,pname,pqunty,subtotal,discoutinword,grandtotal,date,oprice,discoutprice;

        public sellerviewholder(@NonNull View itemView) {
            super(itemView);
            cemail=itemView.findViewById(R.id.scard_customerEmail_id);
            pname=itemView.findViewById(R.id.scard_pizza_name_id);
            pqunty=itemView.findViewById(R.id.scard_pizza_qunty_id);
            subtotal=itemView.findViewById(R.id.scard_sub_total_id);
            discoutinword=itemView.findViewById(R.id.scard_pizza_discoutin_word_id);
            grandtotal=itemView.findViewById(R.id.scard_Grand_total_id);
            date=itemView.findViewById(R.id.scard_data_of_buy_id);
            oprice=itemView.findViewById(R.id.pizza_original_price);
            discoutprice=itemView.findViewById(R.id.scard_pizza_discountPrice_id);


        }
    }
}
