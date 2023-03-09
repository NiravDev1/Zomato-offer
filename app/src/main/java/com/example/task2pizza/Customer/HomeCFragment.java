package com.example.task2pizza.Customer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.task2pizza.Admin.PizzaModel;
import com.example.task2pizza.R;
import com.example.task2pizza.databinding.FragmentHomecBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeCFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeCFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeCFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeCFragment newInstance(String param1, String param2) {
        HomeCFragment fragment = new HomeCFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    FragmentHomecBinding binding;
    PizzaAdapter pizzaAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomecBinding.inflate(inflater, container, false);
        binding.pizzaCustomerRecyclerViewId.setLayoutManager(new LinearLayoutManager(getContext()));
        pizzaAdapter = new PizzaAdapter(getContext());
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Pizza");
        reference.keepSynced(true);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pizzaAdapter.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    PizzaModel pizzaModel = dataSnapshot.getValue(PizzaModel.class);
                    pizzaAdapter.add(pizzaModel);
                    System.out.println(pizzaModel);
                    pizzaAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "error::"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        binding.pizzaCustomerRecyclerViewId.setAdapter(pizzaAdapter);
        pizzaAdapter.notifyDataSetChanged();


        return binding.getRoot();
    }


}