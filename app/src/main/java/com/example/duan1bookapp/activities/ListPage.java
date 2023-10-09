package com.example.duan1bookapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.duan1bookapp.R;
import com.example.duan1bookapp.adapters.MyComicPageAdapter;
import com.example.duan1bookapp.models.Chapter;
import com.example.duan1bookapp.models.Link;
import com.example.duan1bookapp.retrofit.IComicAPI;
import com.example.duan1bookapp.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPage extends AppCompatActivity {
    RetrofitService retrofitService = new RetrofitService();

    private List<Link> linkList;
    private RecyclerView recycler_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        Chapter chapter = (Chapter) bundle.get("object_chapter");

        recycler_link = findViewById(R.id.recycler_link);
        recycler_link.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_link.setLayoutManager(linearLayoutManager);
        fetchLink(chapter.id);
    }

    private void fetchLink(int id) {
        IComicAPI iComicAPI =  retrofitService.getRetrofit().create(IComicAPI.class);
        iComicAPI.getPageList(id).enqueue(new Callback<List<Link>>() {
            @Override
            public void onResponse(Call<List<Link>> call, Response<List<Link>> response) {
                linkList = response.body();
                recycler_link.setAdapter(new MyComicPageAdapter(linkList));
            }

            @Override
            public void onFailure(Call<List<Link>> call, Throwable t) {

            }
        });
    }
}