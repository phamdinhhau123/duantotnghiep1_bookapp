package com.example.duan1bookapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.duan1bookapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashBoard extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Fragment fragment1, fragment2, fragment3, fragment4;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        bottomNavigationView = findViewById(R.id.fragment_container);
        fragmentManager = getSupportFragmentManager();

        // Khởi tạo các fragment ứng với mỗi tab
        fragment1 = new Tab1Fragment();
        fragment2 = new Tab2Fragment();
        fragment3 = new Tab3Fragment();
        fragment4 = new Tab4Fragment();

        // Mặc định hiển thị fragment1 khi ứng dụng được khởi chạy
        setFragment(fragment1);

        // Thiết lập sự kiện lắng nghe khi người dùng chọn tab
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_tab1) {
                    setFragment(fragment1);
                } else if (itemId == R.id.navigation_tab2) {
                    setFragment(fragment2);
                } else if (itemId == R.id.navigation_tab3) {
                    setFragment(fragment3);
                } else if (itemId == R.id.navigation_tab4) {
                    setFragment(fragment4);
                }
                return true;
            }
        });
    }

    // Phương thức để thay đổi fragment hiện tại
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
