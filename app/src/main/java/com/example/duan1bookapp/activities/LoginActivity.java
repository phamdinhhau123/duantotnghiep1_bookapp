package com.example.duan1bookapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.duan1bookapp.databinding.ActivityLoginBinding;
import com.example.duan1bookapp.models.Customer;
import com.example.duan1bookapp.retrofit.CustomerApi;
import com.example.duan1bookapp.retrofit.RetrofitService;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    //test

    private ActivityLoginBinding binding;

    private RetrofitService retrofitService = new RetrofitService();

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //init firebase auth

        // setup progressDialog

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);

        // handle click, go to register screen
        binding.noAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        // handle click, begin login
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                validateData();
            }
        });
    }

    private String email = "", password = "";

    private void validateData() {
        //get data
        email = binding.emailEt.getText().toString();
        password = binding.passwordEt.getText().toString();

        //validate data
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Invalid email pattern...!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter password...", Toast.LENGTH_SHORT).show();
        } else {
            // data is validate, begin login

            loginUser(email,password);

        }
    }

    private void loginUser(String email,String pwd) {
        Customer customer = new Customer();
        customer.setCustomereMail(email);
        customer.setCustomerPassword(pwd);
        CustomerApi customerApi =  retrofitService.getRetrofit().create(CustomerApi.class);
        customerApi.login(customer)
                .enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(Call<Customer> call, Response<Customer> response) {
                        Toast.makeText(getApplicationContext(),"Login Thanh Cong", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, DashBoard.class));
                    }
                    @Override
                    public void onFailure(Call<Customer> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Login failded!", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(RegisterActivity.class.getName()).log(Level.SEVERE, "Error occurred",t);
                    }
                });

    }

}