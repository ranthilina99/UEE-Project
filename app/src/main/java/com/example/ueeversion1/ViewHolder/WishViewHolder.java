package com.example.ueeversion1.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ueeversion1.Interface.ItemClickListner;
import com.example.ueeversion1.R;

public class WishViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txtProductName, txtProductQuantity, txtProductPrice;
    public ItemClickListner itemClickListner;
    public ImageView removeWish;
    public ImageView wishImage,wishTo;

    public WishViewHolder(@NonNull View itemView) {
        super(itemView);
        //        imageView = (ImageView) itemView.findViewById(R.id.product_image);
        txtProductName = (TextView) itemView.findViewById(R.id.wish_product_name);
//        txtProductQuantity = (TextView) itemView.findViewById(R.id.wish_product_quantity);
        txtProductPrice = (TextView) itemView.findViewById(R.id.wish_product_price);
        removeWish = (ImageView) itemView.findViewById(R.id.close_wish);
        wishImage = (ImageView) itemView.findViewById(R.id.wish_product_image);
        wishTo = (ImageView) itemView.findViewById(R.id.wishToPro);
    }

    @Override
    public void onClick(View view) {

        itemClickListner.onClick(view,getAdapterPosition(),false);

    }

    public void setItemClickListner(ItemClickListner itemClickListner){
        this.itemClickListner = itemClickListner;
    }
}
