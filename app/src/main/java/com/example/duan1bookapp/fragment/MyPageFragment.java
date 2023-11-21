package com.example.duan1bookapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.duan1bookapp.R;
import com.example.duan1bookapp.activities.LoginActivity;
import com.example.duan1bookapp.models.Customer;
import com.example.duan1bookapp.retrofit.RetrofitService;

public class MyPageFragment extends Fragment {
    private RetrofitService retrofitService = new RetrofitService();
    private View view;
    private Customer customer;

    public MyPageFragment() {
        // Required empty public constructor
    }

    public static MyPageFragment newInstance() {
        return new MyPageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            customer = getArguments().getParcelable("object_customer1");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_page, container, false);
        TextView textView = view.findViewById(R.id.cs_name_tv);
        TextView textView2 = view.findViewById(R.id.customer_value_coin);
        ImageButton imageButtonSetting = view.findViewById(R.id.customer_information_update_btn);
        ImageButton imageButtonLogout = view.findViewById(R.id.customer_information_logout_btn);

        imageButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the logout method
                logout();
            }
        });

        imageButtonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle setting button click
            }
        });

        if (customer != null) {
            textView.setText(customer.getCustomerName());
            textView2.setText(String.valueOf(customer.getCoin().getValue()));
        }

        return view;
    }

    private void logout() {
        // Add code here to perform logout actions, such as clearing session, navigating to login screen, etc.

        // For example, you might want to navigate to a login screen after logout:
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        // Clear the back stack, so the user cannot navigate back to the MyPageFragment
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        // Note: The actual implementation might depend on your authentication mechanism.
    }
}
