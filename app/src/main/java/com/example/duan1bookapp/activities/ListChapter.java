package com.example.duan1bookapp.activities;

import static android.widget.GridLayout.HORIZONTAL;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duan1bookapp.R;
import com.example.duan1bookapp.a_interface.IClickItemChapterLinstener;
import com.example.duan1bookapp.adapters.MyChapterAdapter;
import com.example.duan1bookapp.fragment.CommentFragment;
import com.example.duan1bookapp.models.Chapter;
import com.example.duan1bookapp.models.Product;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListChapter extends AppCompatActivity  {
    public final String TAG = "LISTCHAPTER";
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private TextView textView1,textView2;
    private ImageView imageView;
    private Product product = new Product();
    private Menu mMenu;
    private Boolean isExpanded = true;
    private List<Chapter> chapterList;
    private RecyclerView recycler_chapter;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chapter);

        //Configure toolbar
        initUi();
        initToolbar();
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        Product product = (Product) bundle.get("object_product");
        chapterList = product.getChapter();
        String url = "http://192.168.1.11:8080/api/v1/product/image/"+product.productImageName;
        Glide.with(imageView.getContext()).load(url).into(imageView);
        textView1.setText(product.productName);
        textView2.setText(product.categories.get(0).categoryName);
        MyChapterAdapter chapterAdapter = new MyChapterAdapter(chapterList, new IClickItemChapterLinstener() {
            @Override
            public void onClickItemChapter(Chapter chapter) {
                onClickGoToLinkList(chapter);
            }
        });
        recycler_chapter = findViewById(R.id.recycler_chapter);
        DividerItemDecoration itemDecor = new DividerItemDecoration(this, HORIZONTAL);
        recycler_chapter.addItemDecoration(itemDecor);
        recycler_chapter.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_chapter.setLayoutManager(linearLayoutManager);
        recycler_chapter.setAdapter(chapterAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListChapter.this, Comment1Activity.class);
                int idproduct = product.getId();
                i.putExtra("VALUE_I_NEED", idproduct);
                startActivity(i);
            }
        });

    }

    private void initUi(){
        textView1 = findViewById(R.id.ls_volume_txt1);
        textView2 = findViewById(R.id.lc_singel_textview1);
        imageView = findViewById(R.id.ls_image_view);
        appBarLayout= findViewById(R.id.appBarLayoutLC);
        toolbar = findViewById(R.id.toolbar_manga_detail_top);
        floatingActionButton = findViewById(R.id.list_chapter_fab);
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
    private void onClickGoToLinkList(Chapter chapter){
        Intent intent = new Intent(this, ListChapterContent.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_chapter",chapter);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
//Log.v("TAG", "11111111=" + chapterList.get(0).name);
