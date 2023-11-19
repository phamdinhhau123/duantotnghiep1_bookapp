package com.example.duan1bookapp.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.duan1bookapp.R;
import com.example.duan1bookapp.models.BookComment;



import java.util.List;


public class MyCommentAdapter extends RecyclerView.Adapter<MyCommentAdapter.MyCommentViewHolder> {
    private List<BookComment> commentList;
    public MyCommentAdapter() {
    }

    public MyCommentAdapter(List<BookComment> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public MyCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment,parent,false);
        return new MyCommentAdapter.MyCommentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCommentViewHolder holder, int position) {
        BookComment comment = commentList.get(position);
        if(commentList == null){
            return;
        }

//        Customer user = comment.getUser();
//        if (user != null) {
//            //Set the author login name
//            holder.mAuthorAvatarImage.setImageResource(user.getLocalimageAvartar());
//            holder.mAuthorNameText.setText(user.getCustomerName());
//            //Set the author avatar image
//            RequestOptions options = new RequestOptions().placeholder(R.mipmap.user_2);
//            Glide.with(holder.mAuthorAvatarImage)
//                    .load(user.getAvatar_url())
//                    .apply(options)
//                    .into(holder.mAuthorAvatarImage);
//        }
//        else {
//            holder.mAuthorNameText.setText("ERROR - NO USER");
//        }

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

    public static class  MyCommentViewHolder extends RecyclerView.ViewHolder{
//        private ImageView mAuthorAvatarImage;
//        private TextView mAuthorNameText;
         TextView mCreatedText;
         TextView mCommentText;

        public MyCommentViewHolder(@NonNull View itemView) {
            super(itemView);
//            mAuthorAvatarImage = (ImageView) itemView.findViewById(R.id.image_comment_user_avatar);
//            mAuthorNameText = (TextView) itemView.findViewById(R.id.text_comment_username);
            mCreatedText =  (TextView)itemView.findViewById(R.id.text_comment_created);
            mCommentText =  (TextView)itemView.findViewById(R.id.text_comment);
        }
    }
}
