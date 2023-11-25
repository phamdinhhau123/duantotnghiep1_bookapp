package com.example.duan1bookapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1bookapp.R;
import com.example.duan1bookapp.a_interface.IClickItemChapterLinstener;
import com.example.duan1bookapp.models.Chapter;
import com.example.duan1bookapp.models.MangaComment;

import java.util.List;

public class MyCommentAdapter extends RecyclerView.Adapter<MyCommentAdapter.MyCommentViewHolder> {
    private List<MangaComment> mangaCommentList;

    public MyCommentAdapter(List<MangaComment> mangaCommentList) {
        this.mangaCommentList = mangaCommentList;
    }

    @NonNull
    @Override
    public MyCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment,parent,false);
        return new MyCommentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCommentViewHolder holder, int position) {
        MangaComment mangaComment = mangaCommentList.get(position);
        if(mangaComment == null){
            return;
        }
//        holder.textView1.setText(mangaComment.getUser().getCustomerName());
        holder.textView2.setText(mangaComment.getBody());
//        holder.textView3.setText(mangaComment.getCreated_at().toString());

    }

    @Override
    public int getItemCount() {
        return mangaCommentList.size();
    }

    public static class MyCommentViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;
        TextView textView3;
        public MyCommentViewHolder(@NonNull View itemView) {
            super(itemView);
//            textView1 = (TextView) itemView.findViewById(R.id.text_comment_username);
            textView2 = (TextView) itemView.findViewById(R.id.text_comment);
//            textView3 = (TextView) itemView.findViewById(R.id.text_comment_created_date);

        }
    }
}
