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

public class ListChapter extends AppCompatActivity {
    private RetrofitService retrofitService = new RetrofitService();

    private List<Chapter> chapterList;
    private RecyclerView recycler_chapter;

    private CardView cardView;

    private EditText time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chapter);
        ImageButton backBtn1 = findViewById(R.id.backBtn);
        backBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Quay lại trang trước
            }
        });
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        Product product = (Product) bundle.get("object_product");

        initUi();

        recycler_chapter.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_chapter.setLayoutManager(linearLayoutManager);
        fetchChapter(product.id);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToComment();

            }
        });

    }

    private void fetchChapter(int mangaid) {

        IComicAPI iComicAPI =  retrofitService.getRetrofit().create(IComicAPI.class);
        iComicAPI.getChapterList(mangaid).enqueue(new Callback<List<Chapter>>() {
            @Override
            public void onResponse(Call<List<Chapter>> call, Response<List<Chapter>> response) {
                chapterList = response.body();
                MyChapterAdapter chapterAdapter = new MyChapterAdapter(chapterList, new IClickItemChapterLinstener() {
                    @Override
                    public void onClickItemChapter(Chapter chapter) {
                        onClickCheckPay(chapter);
                        onClickGoToLinkList(chapter);

                    }
                });
                recycler_chapter.setAdapter(chapterAdapter);
            }

            @Override
            public void onFailure(Call<List<Chapter>> call, Throwable t) {

            }
        });
    }

    private Boolean onClickCheckPay(Chapter chapter){
        Boolean aBoolean= false;
        IComicAPI iComicAPI =  retrofitService.getRetrofit().create(IComicAPI.class);
        iComicAPI.getVerifypayment(27,chapter.id).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Log.d("12345678",String.valueOf(response.body()));
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("12345678",t.toString());

            }
        });
        return aBoolean;
    }
    private void onClickGoToLinkList(Chapter chapter){
            Intent intent = new Intent(this, ListChapterContent.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("object_chapter",chapter);
            intent.putExtras(bundle);
            startActivity(intent);

    }
    private void onClickGoToComment() {
        Intent intent = new Intent(this, Comment.class);
        startActivity(intent);
    }

    private void initUi(){
        cardView = findViewById(R.id.carview_comment);
        recycler_chapter = findViewById(R.id.recycler_chapter);
    }

}
