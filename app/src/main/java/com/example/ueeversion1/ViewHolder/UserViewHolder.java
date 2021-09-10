package com.example.ueeversion1.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ueeversion1.FilterProduct;
import com.example.ueeversion1.Model.Item;
import com.example.ueeversion1.ProductView;
import com.example.ueeversion1.R;
import com.example.ueeversion1.UserBuyProduct;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserViewHolder extends RecyclerView.Adapter<UserViewHolder.HolderProductUser> implements Filterable {

    private final Context context;
    public ArrayList<Item> productList,filterList;
    private FilterProduct filter;

    public UserViewHolder(@NonNull Context context, ArrayList<Item> productList) {
        this.context = context;
        this.productList = productList;
        this.filterList=productList;
    }

    @Override
    public HolderProductUser onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.newlayout,parent,false);
        return new HolderProductUser(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  UserViewHolder.HolderProductUser holder, int position) {
        Item item=productList.get(position);
        String id=item.getPid();
        String productName=item.getProductName();
        String price=item.getPrice();
        String productCategory=item.getProductCategory();
        String image=item.getImage();
        String status=item.getStatus();

        holder.txtItemNameUser.setText(productName);
        holder.txtItemPriceUser.setText("Rs:"+price+".00");
        Picasso.get().load(image).into(holder.imageViewUser);

        if(status.equals("old")){
            holder.newImage.setVisibility(View.INVISIBLE);
        }

        holder.btn_user_card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserBuyProduct.class);
                intent.putExtra("pid", item.getPid());
                context.startActivity(intent);
            }
        });
        holder.btn_user_card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductView.class);
                intent.putExtra("pid", item.getPid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public Filter getFilter() {
        if(filter==null){
            filter=new FilterProduct(this,filterList);
        }
        return filter;
    }

    class HolderProductUser extends RecyclerView.ViewHolder{
          TextView txtItemNameUser,txtItemPriceUser;
         ImageView imageViewUser,newImage;
         Button btn_user_card1,btn_user_card2;

        public HolderProductUser(@NonNull  View itemView) {
            super(itemView);
            imageViewUser =(ImageView) itemView.findViewById(R.id.card_view_image_new);
            txtItemNameUser =(TextView) itemView.findViewById(R.id.card_view_name_new);
            txtItemPriceUser =(TextView) itemView.findViewById(R.id.card_view_price_new);
            newImage =(ImageView) itemView.findViewById(R.id.newTagImage);
            btn_user_card1=(Button) itemView.findViewById(R.id.BuyButton_new);
            btn_user_card2=(Button) itemView.findViewById(R.id.viewButton_new);
        }
    }
}
