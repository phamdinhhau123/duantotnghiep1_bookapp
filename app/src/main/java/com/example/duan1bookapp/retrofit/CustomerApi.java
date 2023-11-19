package com.example.duan1bookapp.retrofit;

import com.example.duan1bookapp.models.Customer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CustomerApi {
    @Headers("Content-Type: application/json")
    @POST("/api/v1/customer/dang-ky")
    Call<Customer> save(@Body Customer customer);

    @Headers("Content-Type: application/json")
    @POST("/api/v1/customer/cap-nhap")
    Call<Customer> update(@Body Customer customer);


    @Headers("Content-Type: application/json")
    @POST("/api/v1/customer/dang-nhap")
    Call<Customer> login(@Body Customer customer);

}

