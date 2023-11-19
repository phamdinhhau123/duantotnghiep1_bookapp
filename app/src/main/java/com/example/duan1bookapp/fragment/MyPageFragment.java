package com.example.duan1bookapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.duan1bookapp.R;
import com.example.duan1bookapp.a_interface.IClickItemProductListener;
import com.example.duan1bookapp.adapters.MyComicAdapter;
import com.example.duan1bookapp.models.Coin;
import com.example.duan1bookapp.models.Customer;
import com.example.duan1bookapp.models.Product;
import com.example.duan1bookapp.retrofit.CustomerApi;
import com.example.duan1bookapp.retrofit.IComicAPI;
import com.example.duan1bookapp.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyPageFragment extends Fragment {
    RetrofitService retrofitService = new RetrofitService();
    private View view;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Customer customer;
    public MyPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyPageFragment newInstance(String param1, String param2) {
        MyPageFragment fragment = new MyPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customer = (Customer) getArguments().get("object_customer1");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_page, container, false);
        TextView textView =  (TextView) view.findViewById(R.id.cs_name_tv);
        TextView textView2 = (TextView) view.findViewById(R.id.customer_value_coin);
        ImageButton imageButtonSetting = (ImageButton) view.findViewById(R.id.customer_information_update_btn);
        ImageButton imageButtonLogout = (ImageButton) view.findViewById(R.id.customer_information_logout_btn);

        imageButtonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });


        textView.setText(customer.getCustomerName().toString());
        textView2.setText(String.valueOf(customer.getCoin().getValue()));
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}