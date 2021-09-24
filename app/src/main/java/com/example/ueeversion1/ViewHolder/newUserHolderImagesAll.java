package com.example.ueeversion1.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ueeversion1.Model.Item;

import java.util.ArrayList;

import com.example.ueeversion1.ProductView;
import com.example.ueeversion1.R;
import com.example.ueeversion1.UserBuyProduct;
import com.squareup.picasso.Picasso;

public class newUserHolderImagesAll extends RecyclerView.Adapter<newUserHolderImagesAll.HolderAllProducts>{
    private final Context context;
    public ArrayList<Item> productListAll;

    public newUserHolderImagesAll(Context context, ArrayList<Item> productListAll) {
        this.context = context;
        this.productListAll = productListAll;
    }

    @Override
    public HolderAllProducts onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.newlayout,parent,false);
        return new newUserHolderImagesAll.HolderAllProducts(view);
    }

    @Override
    public void onBindViewHolder(@NonNull newUserHolderImagesAll.HolderAllProducts holder, int position) {
        Item item=productListAll.get(position);
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

        holder.btn_user_card_new1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserBuyProduct.class);
                intent.putExtra("pid", item.getPid());
                context.startActivity(intent);
            }
        });
        holder.btn_user_card_new2.setOnClickListener(new View.OnClickListener() {
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
        return 4;
    }

    class HolderAllProducts extends RecyclerView.ViewHolder{
        TextView txtItemNameUser,txtItemPriceUser;
        ImageView imageViewUser,newImage;
        Button btn_user_card_new1,btn_user_card_new2;

        public HolderAllProducts(@NonNull View itemView) {
            super(itemView);
            imageViewUser =(ImageView) itemView.findViewById(R.id.card_view_image_new);
            txtItemNameUser =(TextView) itemView.findViewById(R.id.card_view_name_new);
            txtItemPriceUser =(TextView) itemView.findViewById(R.id.card_view_price_new);
            newImage =(ImageView) itemView.findViewById(R.id.newTagImage);
            btn_user_card_new1=(Button) itemView.findViewById(R.id.BuyButton_new);
            btn_user_card_new2=(Button) itemView.findViewById(R.id.viewButton_new);
        }
    }
}
