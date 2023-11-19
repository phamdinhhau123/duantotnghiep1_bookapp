package com.example.duan1bookapp.retrofit;

import com.example.duan1bookapp.models.Coin;
import com.example.duan1bookapp.models.Customer;
import com.example.duan1bookapp.models.Order;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface OrderApi {
    @Headers("Content-Type: application/json")
    @POST("api/v1/customer/order/create")
    Call<Order> CreateOrder(@Body Order order);



}

