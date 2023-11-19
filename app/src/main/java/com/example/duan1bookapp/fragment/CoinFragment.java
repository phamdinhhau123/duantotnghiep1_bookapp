package com.example.duan1bookapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1bookapp.R;
import com.example.duan1bookapp.models.Order;
import com.example.duan1bookapp.retrofit.OrderApi;
import com.example.duan1bookapp.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.momo.momo_partner.AppMoMoLib;

import org.json.JSONException;
import org.json.JSONObject;

public class CoinFragment extends Fragment {

    private static final String ARG_PARAM1 = "customerid";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    RetrofitService retrofitService = new RetrofitService();

    private EditText edtCoin;
    private RadioGroup radioGroup;
    private Button napButton;

    public CoinFragment() {
    }

    public static CoinFragment newInstance(String param1, String param2) {
        CoinFragment fragment = new CoinFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.DEVELOPMENT);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coin, container, false);

        edtCoin = view.findViewById(R.id.edtcoin);
        radioGroup = view.findViewById(R.id.Gradiocoin);
        napButton = view.findViewById(R.id.coin_btn1);

        edtCoin.setVisibility(View.INVISIBLE);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = view.findViewById(checkedId);
                String selectedOption = selectedRadioButton.getText().toString();
                showEditText(selectedOption);
            }
        });

        napButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Order order = new Order(false, "nap10k", Integer.valueOf(mParam1));
                CreateOrder(order);
            }
        });

        return view;
    }

    private void showEditText(String selectedOption) {
        edtCoin.setVisibility(View.VISIBLE);
        edtCoin.setHint("Số tiền muốn nạp (" + selectedOption + ")");
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    private void CreateOrder(Order order) {
        OrderApi orderApi = retrofitService.getRetrofit().create(OrderApi.class);
        orderApi.CreateOrder(order).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                Log.v("TAG", "11111111= Success" + response.body());
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.v("TAG", "11111111= failed" + t);
            }
        });
    }
}
