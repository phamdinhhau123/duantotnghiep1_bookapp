package com.example.duan1bookapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1bookapp.R;
import com.example.duan1bookapp.models.Address;
import com.example.duan1bookapp.models.Customer;
import com.example.duan1bookapp.retrofit.CustomerApi;
import com.example.duan1bookapp.retrofit.RetrofitService;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPageDetail extends AppCompatActivity {
    public EditText editText2,editText3,editText4,editText5,editText6,editText7;
    public TextView textView1;

    private ImageButton imageButton;
    public Button btnleft,btnright;
    RetrofitService retrofitService = new RetrofitService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page_detail);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        Customer customer = (Customer) bundle.get("customerdetail");

        initUI(customer);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearUi();
            }
        });


        btnright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(customer);
            }
        });

    }

    private void initUI(Customer customer){
        imageButton = (ImageButton) findViewById(R.id.my_page_detail_backBtn) ;
        btnleft = (Button) findViewById(R.id.my_page_detail_fg_btn_1) ;
        btnright = (Button) findViewById(R.id.my_page_detail_fg_btn_2) ;
        textView1 =  (TextView) findViewById(R.id.editext_1);
        editText2 = (EditText) findViewById(R.id.editext_2);
        editText3 = (EditText) findViewById(R.id.editext_3);
        editText4 = (EditText) findViewById(R.id.editext_4);
        editText5 = (EditText) findViewById(R.id.editext_5);
        editText6 = (EditText) findViewById(R.id.editext_6);
        editText7 = (EditText) findViewById(R.id.editext_7);
        textView1.setText(customer.getCustomereMail());
        editText2.setText(customer.getCustomerPassword());
        editText3.setText(customer.getCustomerPassword());
        editText4.setText(customer.getCustomerbirthDate());
        editText5.setText(customer.getAddress().getCountry());
        editText6.setText(customer.getAddress().getCity());
        editText7.setText(customer.getAddress().getStreet());
    }


    public void clearUi(){
        editText2.setText("");
        editText3.setText("");
        editText4.setText("");
        editText5.setText("");
        editText6.setText("");
        editText7.setText("");
    }

    public void update(Customer customer){
        String text2,text3,text4,text5,text6,text7;
        text2 = editText2.getText().toString();
        text3 = editText3.getText().toString();
        text4 = editText4.getText().toString();
        text5 = editText5.getText().toString();
        text6 = editText6.getText().toString();
        text7 = editText7.getText().toString();
        if(text2.equals(text3))
        {
            Address address= new Address(customer.getAddress().getId(),
                    text5,text6,text7);
            Customer newcustomer = new Customer(address,customer.getId(),customer.getCustomerName(),text3,customer.getCustomereMail(), text4);
            CustomerApi customerApi = retrofitService.getRetrofit().create(CustomerApi.class);

            customerApi.update(newcustomer).enqueue(new Callback<Customer>() {
                @Override
                public void onResponse(Call<Customer> call, Response<Customer> response) {
                    Toast.makeText(getApplicationContext(), "Cap Nhap Thanh Cong!", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFailure(Call<Customer> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Cap Nhap That Bai ", Toast.LENGTH_SHORT).show();
                }
            });

        }else {
            Toast.makeText(getApplicationContext(), "Mat khau Khong Giong Nhau ", Toast.LENGTH_SHORT).show();

        }

    }
}