package com.example.duan1bookapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.duan1bookapp.R;
import com.example.duan1bookapp.a_interface.IClickItemChapterLinstener;
import com.example.duan1bookapp.adapters.MyChapterAdapter;
import com.example.duan1bookapp.models.Chapter;
import com.example.duan1bookapp.models.Product;
import com.example.duan1bookapp.retrofit.IComicAPI;
import com.example.duan1bookapp.retrofit.RetrofitService;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListChapter extends AppCompatActivity {
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;

    private Menu mMenu;
    private Boolean isExpanded = true;

    private RetrofitService retrofitService = new RetrofitService();

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

        initUi();
        initToolbar();
//        initToolbarAnimation();
        recycler_chapter.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_chapter.setLayoutManager(linearLayoutManager);
        fetchChapter(product.id);
//        onClickBtnAdd();

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
        Intent intent = new Intent(this, ListChapterContent.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_chapter",chapter);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void initUi(){
//        appBarLayout= findViewById(R.id.appBarLayoutLC);
//        collapsingToolbarLayout= findViewById(R.id.collapsingToolbarLayoutLC);
//        toolbar = findViewById(R.id.toolbarLC);
//        floatingActionButton = findViewById(R.id.fabLC);
        recycler_chapter = findViewById(R.id.recycler_chapter);
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

//    private void onClickBtnAdd(){
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(ListChapter.this,"click btn add", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lc,menu);
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        if(menu != null && (!isExpanded || menu.size() != 1))
        {
            mMenu.add("Add").setIcon(R.drawable.baseline_add_24).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }else{

        }
        return super.onPrepareOptionsMenu(mMenu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        if(item.getTitle() == "Add"){
            Toast.makeText(ListChapter.this,"click btn add2", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkIsFavorite(){
        IComicAPI iComicAPI =  retrofitService.getRetrofit().create(IComicAPI.class);

    }
}
//Log.v("TAG", "11111111=" + chapterList.get(0).name);