package com.example.duan1bookapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.duan1bookapp.R;
import com.example.duan1bookapp.adapters.MyComicPageAdapter;
import com.example.duan1bookapp.models.Chapter;
import com.example.duan1bookapp.models.Link;
import com.example.duan1bookapp.retrofit.IComicAPI;
import com.example.duan1bookapp.retrofit.RetrofitService;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListChapterContent extends AppCompatActivity {
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;

    private Menu mMenu;
    private Boolean isExpanded = true;
    RetrofitService retrofitService = new RetrofitService();

    private List<Link> linkList;
    private RecyclerView recycler_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listchaptercontent);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        Chapter chapter = (Chapter) bundle.get("object_chapter");

        initUi();
        initToolbar();
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

    private void initUi(){
        appBarLayout= findViewById(R.id.appBarLayoutLCC);
        collapsingToolbarLayout= findViewById(R.id.collapsingToolbarLayoutLCC);
        toolbar = findViewById(R.id.toolbarLCC);
//        floatingActionButton = findViewById(R.id.fabLC);
        recycler_link = findViewById(R.id.recycler_link);
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lcc,menu);
        mMenu = menu;
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        if(menu != null && (!isExpanded || menu.size() != 1))
        {
            mMenu.add("Add2").setIcon(R.drawable.baseline_add_24).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
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
        if(item.getTitle() == "Add2"){
            Toast.makeText(ListChapterContent.this,"click btn add2", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}