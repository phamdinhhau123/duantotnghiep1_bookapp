package com.example.duan1bookapp.activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import com.example.duan1bookapp.R;
import com.example.duan1bookapp.adapters.AdapterComment;
import com.example.duan1bookapp.models.ProductComment;
import com.example.duan1bookapp.models.Customer;
import com.example.duan1bookapp.models.ModelViewComment;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class Comment extends AppCompatActivity {
    private Button mCreateCommentButton;
    private TextInputEditText mCommentInput;

    private ModelViewComment modelViewComment;
    private AdapterComment myCommentAdapter;
    private RecyclerView recyclerViewcomment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        ImageButton backBtn2 = findViewById(R.id.backBtnFromcomment);
        backBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Quay lại trang trước
            }
        });

        mCreateCommentButton = findViewById(R.id.button_book_comments_create);
        mCreateCommentButton.setOnClickListener(this::onCreateCommentButtonClick);
        mCommentInput = findViewById(R.id.input_gist_comment);
//        Add a listener to the comment input field so that the button to create the comment
//         is only enabled when there is text in the field
        mCommentInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                mCreateCommentButton.setEnabled(editable.length() > 0);
            }
        });
        recyclerViewcomment = findViewById(R.id.recycler_book_comments);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewcomment.setHasFixedSize(true);
        recyclerViewcomment.setLayoutManager(linearLayoutManager);
        modelViewComment = new ViewModelProvider(this).get(ModelViewComment.class);
        modelViewComment.getListMutableLiveData().observe(this, new Observer<List<ProductComment>>() {
            @Override
            public void onChanged(List<ProductComment> bookComments) {
                myCommentAdapter = new AdapterComment(bookComments);
                recyclerViewcomment.setAdapter(myCommentAdapter);
            }
        });

    }

    private void onCreateCommentButtonClick(View view) {
        //Clear any field errors
        mCommentInput.setError(null);
        //Make sure text was submitted and the field isn't blank
        Editable comment = mCommentInput.getText();
        if (comment == null) {
            mCommentInput.setError("You cannot create a blank comment");
            return;
        }

        //Hide the keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View currentFocus = getCurrentFocus();
        if (currentFocus == null) {
            currentFocus = new View(this);
        }
        imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);

        //Clear the field
        mCommentInput.setText("");

        //Try to create the comment
        ProductComment bookComment= new ProductComment(comment.toString(), new Customer("abc",R.mipmap.user_1),"2011-04-18 23:23:56");

        modelViewComment.addComment(bookComment);
    }



}