package com.example.duan1bookapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1bookapp.R;
import com.example.duan1bookapp.models.BookComment;

import java.util.List;

public class MyCommentAdapter2 extends RecyclerView.Adapter<MyCommentAdapter2.MyCommentViewHolder> {
    private List<BookComment> commentList;

    public MyCommentAdapter2(List<BookComment> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public MyCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment,parent,false);

        return new MyCommentAdapter2.MyCommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCommentViewHolder holder, int position) {
        BookComment comment = commentList.get(position);
        if(commentList == null){
            return;
        }
        //Set the created time
        holder.mCreatedText.setText(comment.created_at);
        //Set the comment body
        holder.mCommentText.setText(comment.body);

    }

    @Override
    public int getItemCount() {
        if(commentList == null){
            return commentList.size();
        }
        return 0;
    }

    public static class MyCommentViewHolder extends RecyclerView.ViewHolder{
        TextView mCreatedText;
        TextView mCommentText;
        public MyCommentViewHolder(@NonNull View itemView) {
            super(itemView);
            mCreatedText =  (TextView)itemView.findViewById(R.id.text_comment_created);
            mCommentText =  (TextView)itemView.findViewById(R.id.text_comment);
        }
    }
}
