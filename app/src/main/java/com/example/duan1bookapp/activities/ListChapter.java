package com.example.duan1bookapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

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
    RetrofitService retrofitService = new RetrofitService();

    private List<Chapter> chapterList;
    private RecyclerView recycler_chapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chapter);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        Product product = (Product) bundle.get("object_product");

        recycler_chapter = findViewById(R.id.recycler_chapter);
        recycler_chapter.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_chapter.setLayoutManager(linearLayoutManager);
        fetchChapter(product.id);

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

    private void onClickGoToLinkList(Chapter chapter){
        Intent intent = new Intent(this, ListPage.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_chapter",chapter);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
//Log.v("TAG", "11111111=" + chapterList.get(0).name);