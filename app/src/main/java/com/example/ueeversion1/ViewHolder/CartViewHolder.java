package com.example.ueeversion1.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ueeversion1.Interface.ItemClickListner;
import com.example.ueeversion1.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    public TextView txtProductName, txtProductQuantity, txtProductPrice, txtTotal;
    public ItemClickListner itemClickListner;
    public ImageView cartImage;
    public ImageView removeCart,editCart1,editCart2;
    int totalPrice = 0 ;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        cartImage = (ImageView) itemView.findViewById(R.id.cart_image);
        txtProductName = (TextView) itemView.findViewById(R.id.cart_product_name);
        txtProductQuantity = (TextView) itemView.findViewById(R.id.cart_product_quantity);
        txtProductPrice = (TextView) itemView.findViewById(R.id.cart_product_price);
        txtTotal = (TextView) itemView.findViewById(R.id.total_price);
        removeCart = (ImageView) itemView.findViewById(R.id.close_cart);
        editCart1 = (ImageView) itemView.findViewById(R.id.cartEdit1);
        editCart2 = (ImageView) itemView.findViewById(R.id.cartEdit2);
    }

    @Override
    public void onClick(View view) {

        itemClickListner.onClick(view,getAdapterPosition(),false);

    }

    public void setItemClickListner(ItemClickListner itemClickListner){
        this.itemClickListner = itemClickListner;
    }
}
