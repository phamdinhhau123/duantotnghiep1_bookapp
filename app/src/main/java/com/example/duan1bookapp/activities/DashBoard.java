package com.example.duan1bookapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.duan1bookapp.R;
import com.example.duan1bookapp.databinding.ActivityDashBoardBinding;
import com.example.duan1bookapp.fragment.CoinFragment;
import com.example.duan1bookapp.fragment.FavoriteFragment;
import com.example.duan1bookapp.fragment.HomeFragment;
import com.example.duan1bookapp.fragment.MangaFrament;
import com.example.duan1bookapp.fragment.MyPageFragment;
import com.example.duan1bookapp.models.Customer;

public class DashBoard extends AppCompatActivity {
    public Customer customer;
    ActivityDashBoardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        customer = (Customer) bundle.get("object_customer");
        int customerid= customer.getId();
        replaceFragment(new HomeFragment());
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.ic_home) {
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.ic_manga) {
                replaceFragment(new MangaFrament());
            } else if (item.getItemId() == R.id.ic_buy_coin) {
                CoinFragment coinFragment = new CoinFragment();
                Bundle args = new Bundle();
                args.putSerializable("customerid",String.valueOf(customerid));
                coinFragment.setArguments(args);
                replaceFragment(coinFragment);
            } else if (item.getItemId() == R.id.ic_user) {
                MyPageFragment myPageFragment = new MyPageFragment();
                Bundle args = new Bundle();
                args.putSerializable("object_customer1",customer);
                myPageFragment.setArguments(args);
                replaceFragment(myPageFragment);
            }
            return true;
        });

    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}



