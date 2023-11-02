package com.example.duan1bookapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.duan1bookapp.R;
import com.example.duan1bookapp.a_interface.IClickItemProductListener;
import com.example.duan1bookapp.models.Product;
import com.example.duan1bookapp.models.slideShow;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapterExample extends SliderViewAdapter<SliderAdapterExample.MyViewHolder1> {
    private List<Product> comicList;
    private IClickItemProductListener iClickItemProductListener;
    private List<slideShow> mSliderItems = new ArrayList<>();
    private Context context;

    public SliderAdapterExample(Context context, List<slideShow> mSliderItems) {
        this.context = context;
        this.mSliderItems = mSliderItems;

    }
    public void renewItems(List<slideShow> sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }
    public void deleteItem(int position) {
        this.mSliderItems.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(slideShow sliderItem) {
        this.mSliderItems.add(sliderItem);
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder1 onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_slide_show, null);
        return new MyViewHolder1(inflate);
    }

    public void onBindViewHolder(MyViewHolder1 holder, int position) {
        Product product= comicList.get(position);
        if (product== null){
            return;
        }
        String url = "http://192.168.1.11:8080/api/v1/product/image/"+product.productImageName;
        Glide.with(holder.imageView.getContext()).load(url).into(holder.imageView);
        holder.textView.setText(product.productName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemProductListener.onClickItemUser(product);
            }
        });

    }
    public int getItemCount() {
        return comicList.size();
    }




    class MyViewHolder1 extends SliderViewAdapter.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MyViewHolder1(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
            textView = (TextView) itemView.findViewById(R.id.manga_name);

        }
    }


}
