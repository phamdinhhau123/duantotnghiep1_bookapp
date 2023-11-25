package com.example.duan1bookapp.models;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.duan1bookapp.R;
import com.example.duan1bookapp.retrofit.IComicAPI;
import com.example.duan1bookapp.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelViewFavorite extends ViewModel {
    private RetrofitService retrofitService = new RetrofitService();
    private IComicAPI iComicAPI ;
    private MutableLiveData<List<Product>> listMutableLiveData;
    private List<Product> products;
    private int cusomterId;
    public ModelViewFavorite() {

        listMutableLiveData = new MutableLiveData<>();
        initAnonService();
        loadProduct();
    }
    public void setCusomterId(int cusomterId) {
        this.cusomterId = cusomterId;
        loadProduct();
    }

    private void initAnonService() {
        iComicAPI = retrofitService.getRetrofit().create(IComicAPI.class);
    }

    public MutableLiveData<List<Product>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    private void loadProduct() {

        iComicAPI.getFlavorFromBag(cusomterId).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> products = response.body();
                    if (products != null && !products.isEmpty()) {
                        listMutableLiveData.setValue(products);
                    } else {
                        // Handle empty response
                    }
                } else {
                    // Handle unsuccessful response
                    Log.e("ModelViewFavorite", "Failed to fetch data: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                // Handle API call failure
                Log.e("ModelViewFavorite", "API call failed: " + t.getMessage());
            }
        });
    }
}
