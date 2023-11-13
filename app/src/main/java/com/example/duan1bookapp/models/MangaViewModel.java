package com.example.duan1bookapp.models;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.duan1bookapp.retrofit.IComicAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MangaViewModel extends ViewModel {
    public final String TAG = "MangaViewModel";
    private MutableLiveData<List<MangaComment>> mComments = new MutableLiveData<>();

    public LiveData<List<MangaComment>> getComments() { return mComments; }
    private int mMangaId;
    private IComicAPI iComicAPI;
//    public MangaViewModel() {
//        super();
//        init();
//        initAnonService();
//    }
//
//    private void init() {
//        mComments = new MutableLiveData<>();
//    }
//
//    public void setmMangaId(int mMangaId) {
//        this.mMangaId = mMangaId;
//    }
//
    private void initAnonService() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        iComicAPI = retrofit.create(IComicAPI.class);
    }

//
//    public void createComment(String comment) {
//        //Make sure there is a Gist ID stored for use
//        if (String.valueOf(selectProduct.getValue().id).isEmpty()) {
//            return;
//        }
//
//        //Make sure the submitted comment isn't empty
//        if (comment.trim().isEmpty()) {
////            showError("You cannot create a blank comment");
//            return;
//        }
//        MangaComment mangaComment = new MangaComment();
//        mangaComment.setBody(comment);
//        iComicAPI.createCommentOnGist(selectProduct.getValue().id,mangaComment).enqueue(new Callback<MangaComment>() {
//            @Override
//            public void onResponse(Call<MangaComment> call, Response<MangaComment> response) {
//                if (!response.isSuccessful()) {
//                    Log.d(TAG, "onResponse: create new comment fail"+response.body());
//                    return;
//                }
//                List<MangaComment> currentList = mComments.getValue();
//                if (currentList == null) {
//                    currentList = new ArrayList<>();
//                }
//
//                List<MangaComment> comments = new ArrayList<>(currentList);
//                comments.add(0, response.body());
//                mComments.postValue(comments);
//            }
//
//            @Override
//            public void onFailure(Call<MangaComment> call, Throwable t) {
//                Log.d(TAG, "onResponse: create new comment fail"+t);
//            }
//        });
//    }

}
