package com.example.duan1bookapp.fragment;

import static android.widget.GridLayout.HORIZONTAL;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.duan1bookapp.R;
import com.example.duan1bookapp.adapters.MyCommentAdapter;
import com.example.duan1bookapp.models.MangaComment;
import com.example.duan1bookapp.retrofit.IComicAPI;
import com.example.duan1bookapp.retrofit.RetrofitService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CommentFragment extends Fragment {
    public final String TAG = "MangaViewModel";
    private ProgressBar mProgressBar;
    private Button mCreateCommentButton;
    private TextInputEditText mCommentInput;
    private RecyclerView mCommentListRecycler;
    public List<MangaComment> mangaCommentList ;

    private RetrofitService retrofitService = new RetrofitService();
    IComicAPI iComicAPI =  retrofitService.getRetrofit().create(IComicAPI.class);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_comment, container, false);
        //Views
        mProgressBar = v.findViewById(R.id.progress_gists);
        mProgressBar.setVisibility(View.GONE);
        mCommentListRecycler = v.findViewById(R.id.list_manga_comments);
        mCreateCommentButton = v.findViewById(R.id.button_gist_comments_create);
        mCommentInput = v.findViewById(R.id.input_gist_comment);
        mCreateCommentButton.setOnClickListener(this::onCreateCommentButtonClick);
        //VIEWS END
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        mCommentListRecycler.setLayoutManager(linearLayoutManager);
        MyCommentAdapter myCommentAdapter = new MyCommentAdapter(mangaCommentList);
        DividerItemDecoration itemDecor = new DividerItemDecoration(getActivity(), HORIZONTAL);
        mCommentListRecycler.addItemDecoration(itemDecor);
        mCommentListRecycler.setHasFixedSize(true);
        mCommentListRecycler.setAdapter(myCommentAdapter);

        //Add a listener to the comment input field so that the button to create the comment
        // is only enabled when there is text in the field
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


        return  v;
    }
    public void onCreateCommentButtonClick(View view) {
                //Clear any field errors
        mCommentInput.setError(null);
        //Make sure text was submitted and the field isn't blank
        Editable comment = mCommentInput.getText();
        if (comment == null) {
            mCommentInput.setError("You cannot create a blank comment");
            return;
        }
        //Clear the field
        mCommentInput.setText("");
        //Try to create the comment
        Toast.makeText(getActivity(),comment.toString(), Toast.LENGTH_SHORT).show();
        createComment(comment.toString());
      }
    public void createComment(String comment) {
        //Make sure there is a Gist ID stored for use
        if (String.valueOf(1).isEmpty()) {
            return;
        }

        //Make sure the submitted comment isn't empty
        if (comment.trim().isEmpty()) {
//            showError("You cannot create a blank comment");
            return;
        }
        MangaComment mangaComment = new MangaComment();
        mangaComment.setBody(comment);
        mangaComment.setMangaid(1);
        iComicAPI.createCommentOnGist(1, mangaComment).enqueue(new Callback<MangaComment>() {
            @Override
            public void onResponse(Call<MangaComment> call, Response<MangaComment> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: create new comment Success"+response.body());
                    return;
                }

                Log.d(TAG, "onResponse: create new comment faild"+response.body());
//
//                List<MangaComment> currentList = mComments.getValue();
//                if (currentList == null) {
//                    currentList = new ArrayList<>();
//                }
//
//                List<MangaComment> comments = new ArrayList<>(currentList);
//                comments.add(0, response.body());
//                mComments.postValue(comments);
            }

            @Override
            public void onFailure(Call<MangaComment> call, Throwable t) {
                Log.d(TAG, "onResponse: create new comment fail"+t);
            }
        });
    }


}