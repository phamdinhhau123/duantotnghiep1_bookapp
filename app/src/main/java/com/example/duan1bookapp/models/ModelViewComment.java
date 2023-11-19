package com.example.duan1bookapp.models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.duan1bookapp.R;
import java.util.ArrayList;
import java.util.List;

public class ModelViewComment extends ViewModel {
    private MutableLiveData<List<BookComment>> listMutableLiveData;
    private List<BookComment> commentList;


    public ModelViewComment(){
        listMutableLiveData= new MutableLiveData<>();
        initData();
    }

    private void initData(){

        commentList = new ArrayList<>();
        commentList.add(new BookComment("my first comment", new Customer("abc",R.mipmap.user_1),"2011-04-18 23:23:56"));
        commentList.add(new BookComment("my first comment", new Customer("abc",R.mipmap.user_1),"2011-04-18 23:23:56"));
        commentList.add(new BookComment("my first comment", new Customer("abc",R.mipmap.user_1),"2011-04-18 23:23:56"));
        listMutableLiveData.setValue(commentList);
    }

    public MutableLiveData<List<BookComment>> getListMutableLiveData() {
        return listMutableLiveData;
    }
}
