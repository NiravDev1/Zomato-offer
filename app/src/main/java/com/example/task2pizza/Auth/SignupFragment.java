package com.example.task2pizza.Auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.task2pizza.R;
import com.example.task2pizza.databinding.FragmentSignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
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
    FragmentSignupBinding binding;
    FirebaseAuth auth;
    DatabaseReference reference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentSignupBinding.inflate(inflater, container, false);
        auth=FirebaseAuth.getInstance();
        reference= FirebaseDatabase.getInstance().getReference("Customer");
        binding.ToLoginF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.auth_framelayout_id, new LoginFragment());
                fragmentTransaction.addToBackStack(String.valueOf(new LoginFragment()));
                fragmentTransaction.commit();
            }
        });

        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "add button", Toast.LENGTH_SHORT).show();

                String Name=binding.nameSign.getEditText().getText().toString().trim();
                String Email=binding.emailSign.getEditText().getText().toString().trim();
                String Password=binding.passwordSign.getEditText().getText().toString().trim();

                if (Name.isEmpty()||Email.isEmpty()||Password.isEmpty())
                {
                    Toast.makeText(getContext(), "fill the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "fill", Toast.LENGTH_SHORT).show();
                     auth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                String uid=task.getResult().getUser().getUid().toString();
                                CustomerModel customerModel=new CustomerModel(Name,Email,Password,uid);
                                reference.child(uid).setValue(customerModel);
                                Toast.makeText(getContext(), "Successful Sign up", Toast.LENGTH_SHORT).show();
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.auth_framelayout_id, new LoginFragment());
                                fragmentTransaction.addToBackStack(String.valueOf(new LoginFragment()));
                                fragmentTransaction.commit();
                            }
                            else
                            {

                            }

                         }
                     }).addOnFailureListener(new OnFailureListener() {
                         @Override
                         public void onFailure(@NonNull Exception e) {
                             Toast.makeText(getContext(), "Error::"+e.getMessage(), Toast.LENGTH_SHORT).show();
                         }
                     });

                }

            }
        });

        return binding.getRoot();
    }
}