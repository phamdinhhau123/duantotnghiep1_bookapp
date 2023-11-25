package com.example.duan1bookapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1bookapp.R;
import com.example.duan1bookapp.models.Order;

import java.text.DateFormat;
import java.util.List;


public class AdapterOrder extends RecyclerView.Adapter<AdapterOrder.MyOrderViewHolder> {
    private List<Order> orderList;
    public AdapterOrder() {
    }

    public AdapterOrder(List<Order> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public MyOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment,parent,false);
        return new AdapterOrder.MyOrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        if(orderList == null){
            return;
        }
        //Set the order ID time
        holder.mAuthorNameText.setText(String.valueOf(order.getId()));
        //Set the created time
        String createdOnTime = DateFormat.getDateTimeInstance().format(order.getCreated_at());
        holder.mCreatedText.setText(createdOnTime);
        //Set the order body
        holder.mCommentText.setText("magiao dich qua momo: "+order.getMomo()+"\n"+ order.getNoidung());
        //Set the order image
        if (order.isDa_thanh_toan()){
            holder.mAuthorAvatarImage.setImageResource(android.R.drawable.checkbox_on_background);
        }else{
            holder.mAuthorAvatarImage.setImageResource(android.R.drawable.checkbox_off_background);
        }
    }

    @Override
    public int getItemCount() {
        return orderList != null ? orderList.size() : 0;

    }

    public static class  MyOrderViewHolder extends RecyclerView.ViewHolder{
        private ImageView mAuthorAvatarImage;
        private TextView mAuthorNameText;
         TextView mCreatedText;
         TextView mCommentText;
        public MyOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            mAuthorAvatarImage = (ImageView) itemView.findViewById(R.id.image_comment_user_avatar);
            mAuthorNameText = (TextView) itemView.findViewById(R.id.text_comment_username);
            mCreatedText =  (TextView)itemView.findViewById(R.id.text_comment_created);
            mCommentText =  (TextView)itemView.findViewById(R.id.text_comment);
        }
    }
}
