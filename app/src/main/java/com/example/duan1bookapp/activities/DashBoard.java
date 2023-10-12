package com.example.duan1bookapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.duan1bookapp.MangaFragment;
import com.example.duan1bookapp.MenuFragment;
import com.example.duan1bookapp.PersonFragment;
import com.example.duan1bookapp.R;
import com.example.duan1bookapp.databinding.ActivityDashBoardBinding;

public class DashBoard extends AppCompatActivity {

    ActivityDashBoardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.ic_home) {
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.ic_manga) {
                replaceFragment(new MangaFragment());
            } else if (item.getItemId() == R.id.ic_menubook) {
                replaceFragment(new MenuFragment());
            } else if (item.getItemId() == R.id.ic_user) {
                replaceFragment(new PersonFragment());
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
}



