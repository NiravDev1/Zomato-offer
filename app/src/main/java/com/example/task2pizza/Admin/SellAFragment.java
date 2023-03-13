package com.example.task2pizza.Admin;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.task2pizza.Customer.OrderModel;
import com.example.task2pizza.databinding.FragmentSellABinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SellAFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SellAFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SellAFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SellAFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SellAFragment newInstance(String param1, String param2) {
        SellAFragment fragment = new SellAFragment();
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

    SellAdapterA sellAdapterA;
    FragmentSellABinding binding;
    String Data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSellABinding.inflate(inflater, container, false);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d-M-yyyy");
        Data = simpleDateFormat.format(calendar.getTime()).toString();
        System.out.println(Data);
        binding.DataTextId.setText(Data);

        binding.datePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        binding.DataTextId.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                        Data = binding.DataTextId.getText().toString();
                    }
                }, year, month, day);

                datePickerDialog.show();

            }
        });

        binding.searchBtnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recyclerview(Data);
            }
        });

        return binding.getRoot();
    }

    private void recyclerview(String data) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("AddminOrder");

        binding.sellPizzaAdminRecyclerViewId.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<OrderModel> options = new FirebaseRecyclerOptions.Builder<OrderModel>()
                .setQuery(reference.child(Data), OrderModel.class)
                .build();

        sellAdapterA = new SellAdapterA(options);
        binding.sellPizzaAdminRecyclerViewId.setAdapter(sellAdapterA);
        sellAdapterA.startListening();
    }


}