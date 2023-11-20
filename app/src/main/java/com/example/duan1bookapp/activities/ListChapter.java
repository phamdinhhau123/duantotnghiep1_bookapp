package com.example.duan1bookapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.duan1bookapp.R;
import com.example.duan1bookapp.a_interface.IClickItemChapterLinstener;
import com.example.duan1bookapp.adapters.MyChapterAdapter;
import com.example.duan1bookapp.models.Chapter;
import com.example.duan1bookapp.models.Product;
import com.example.duan1bookapp.retrofit.IComicAPI;
import com.example.duan1bookapp.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import androidx.appcompat.app.AppCompatActivity;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.duan1bookapp.R;
import com.example.duan1bookapp.a_interface.IClickItemChapterLinstener;
import com.example.duan1bookapp.adapters.MyChapterAdapter;
import com.example.duan1bookapp.models.Chapter;
import com.example.duan1bookapp.models.Product;
import com.example.duan1bookapp.retrofit.IComicAPI;
import com.example.duan1bookapp.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListChapter extends AppCompatActivity {
    private RetrofitService retrofitService = new RetrofitService();
    private List<Chapter> chapterList;
    private RecyclerView recycler_chapter;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chapter);

        // Back button
        findViewById(R.id.backBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Go back to the previous screen
            }
        });

        // Get product from the intent
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        Product product = (Product) bundle.get("object_product");

        // Initialize UI components
        initUi();

        // Set up RecyclerView
        recycler_chapter.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_chapter.setLayoutManager(linearLayoutManager);

        // Fetch chapters for the given product
        fetchChapter(product.id);

        // Handle click on the comment CardView
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToComment();
            }
        });
    }

    private void fetchChapter(int mangaid) {
        IComicAPI iComicAPI = retrofitService.getRetrofit().create(IComicAPI.class);
        iComicAPI.getChapterList(mangaid).enqueue(new Callback<List<Chapter>>() {
            @Override
            public void onResponse(Call<List<Chapter>> call, Response<List<Chapter>> response) {
                chapterList = response.body();
                MyChapterAdapter chapterAdapter = new MyChapterAdapter(chapterList, new IClickItemChapterLinstener() {
                    @Override
                    public void onClickItemChapter(Chapter chapter) {
                        handleChapterClick(chapter);
                    }
                });
                recycler_chapter.setAdapter(chapterAdapter);
            }

            @Override
            public void onFailure(Call<List<Chapter>> call, Throwable t) {
                // Handle failure
                Log.e("ListChapter", "Failed to fetch chapters: " + t.getMessage());
            }
        });
    }

    private void handleChapterClick(Chapter chapter) {
        // Check payment and proceed accordingly
        onClickCheckPay(chapter);
    }

    private void onClickCheckPay(Chapter chapter) {
        IComicAPI iComicAPI = retrofitService.getRetrofit().create(IComicAPI.class);
        iComicAPI.getVerifypayment(27, chapter.id).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Boolean paymentResult = response.body();
                    if (paymentResult) {
                        onClickGoToLinkList(chapter);
                    } else {
                        showPaymentFailedDialog();
                    }
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("ListChapter", "Payment verification failed: " + t.getMessage());
                
            }
        });
    }

    private void showPaymentFailedDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ListChapter.this);
        builder.setTitle("Payment Failed")
                .setMessage("Payment verification failed. Please try again.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle the OK button click if needed
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void onClickGoToLinkList(Chapter chapter) {
        Intent intent = new Intent(this, ListChapterContent.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_chapter", chapter);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void onClickGoToComment() {
        Intent intent = new Intent(this, Comment.class);
        startActivity(intent);
    }

    private void initUi() {
        cardView = findViewById(R.id.carview_comment);
        recycler_chapter = findViewById(R.id.recycler_chapter);
    }
}

