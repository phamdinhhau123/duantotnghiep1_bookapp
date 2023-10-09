package com.example.duan1bookapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.duan1bookapp.databinding.ActivityRegisterBinding;
import com.example.duan1bookapp.models.Customer;
import com.example.duan1bookapp.retrofit.CustomerApi;
import com.example.duan1bookapp.retrofit.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    private ProgressDialog progressDialog;
    RetrofitService retrofitService = new RetrofitService();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // init firebase auth

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // handle click, begin register
        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }

    private String name = "", email = "", password = "" ;
    private String birthday = "1999-01-01";

    private void validateData() {
        // Before creating account, lets do some data validation

        // get data
        name = binding.nameEt.getText().toString().trim();
        email = binding.emailEt.getText().toString().trim();
        password = binding.passwordEt.getText().toString().trim();
        String cPassword = binding.cPasswordEt.getText().toString().trim();


//        // validate data
//        if (TextUtils.isEmpty(name)) {
//            Toast.makeText(this, "Enter your name...", Toast.LENGTH_SHORT).show();
//        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            Toast.makeText(this, "Invalid email pattern...!", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(password)) {
//            Toast.makeText(this, "Enter password...", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(cPassword)) {
//            Toast.makeText(this, "Confirm password...", Toast.LENGTH_SHORT).show();
//        } else if (!password.equals(cPassword)) {
//            Toast.makeText(this, "Password doesn't match...", Toast.LENGTH_SHORT).show();
//        } else {
            Customer customer = new Customer();
            customer.setCustomerName(name);
            customer.setCustomereMail(email);
            customer.setCustomerPassword(password);
            customer.setCustomerbirthDate(birthday);
            customer.setEnabled_CS(true);
            CustomerApi customerApi =  retrofitService.getRetrofit().create(CustomerApi.class);
            customerApi.save(customer)
                    .enqueue(new Callback<Customer>() {
                        @Override
                        public void onResponse(Call<Customer> call, Response<Customer> response) {

                            Toast.makeText(getApplicationContext(),"Save successful!", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onFailure(Call<Customer> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"Save failded!", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(RegisterActivity.class.getName()).log(Level.SEVERE, "Error occurred",t);
                        }
                    });

    }



}
