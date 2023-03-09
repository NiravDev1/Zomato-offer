package com.example.task2pizza.Auth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.task2pizza.Admin.AdminDashbordActivity;
import com.example.task2pizza.Customer.CustomerHomeActivity;
import com.example.task2pizza.R;
import com.example.task2pizza.databinding.FragmentAdminLoginBinding;
import com.example.task2pizza.databinding.FragmentLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminLoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminLoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminLoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminLoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminLoginFragment newInstance(String param1, String param2) {
        AdminLoginFragment fragment = new AdminLoginFragment();
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

    FragmentAdminLoginBinding binding;
    FirebaseAuth auth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAdminLoginBinding.inflate(inflater, container, false);
        auth=FirebaseAuth.getInstance();
        binding.adminLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=binding.emailAdmin.getEditText().getText().toString().trim();
                String Password=binding.passwordAdmim.getEditText().getText().toString().trim();
                if (Email.isEmpty()||Password.isEmpty())
                {
                    Toast.makeText(getContext(), "fill the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "fill", Toast.LENGTH_SHORT).show();
                    auth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getContext(), AdminDashbordActivity.class));
                                getActivity().finish();
                            }
                            else {
                                Toast.makeText(getContext(), "fail to login", Toast.LENGTH_SHORT).show();
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