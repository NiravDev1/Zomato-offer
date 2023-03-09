package com.example.task2pizza.Admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.task2pizza.R;
import com.example.task2pizza.databinding.FragmentAddABinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddAFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddAFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddAFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddAFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddAFragment newInstance(String param1, String param2) {
        AddAFragment fragment = new AddAFragment();
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
        FragmentAddABinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentAddABinding.inflate(inflater, container, false);

        binding.addPizzaBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PName=binding.addPizzaNameId.getEditText().getText().toString().trim();
                String Pprice=binding.addPizzaPrice.getEditText().getText().toString().trim();
                if (PName.isEmpty()||Pprice.isEmpty())
                {
                    Toast.makeText(getContext(), "fill the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Pizza");
                    String Pizzaid=reference.push().getKey();
                    String Pizzaimg="https://firebasestorage.googleapis.com/v0/b/task-demo-37431.appspot.com/o/small.png?alt=media&token=21fcea72-805e-4001-9ff7-dc16c656ad96";
                    PizzaModel pizzaModel=new PizzaModel(PName,Pprice,Pizzaid,Pizzaimg);

                    reference.child(Pizzaid).setValue(pizzaModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(getContext(), "add success", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(getContext(), "adding fail", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "error::"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        return binding.getRoot();
    }
}