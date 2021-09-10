package com.example.ueeversion1.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ueeversion1.AdminMaintainItem;
import com.example.ueeversion1.FilterProductAdmin;
import com.example.ueeversion1.Model.Item;
import com.example.ueeversion1.R;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminViewHolder extends RecyclerView.Adapter<AdminViewHolder.HolderProductAdmin> implements Filterable {

    private final Context context;
    public ArrayList<Item> productListAdmin,filterListAdmin;
    private FilterProductAdmin filter;

    public AdminViewHolder(Context context, ArrayList<Item> productListAdmin) {
        this.context = context;
        this.productListAdmin = productListAdmin;
        this.filterListAdmin = productListAdmin;
    }

    @Override
    public HolderProductAdmin onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.admin_card,parent,false);
        return new AdminViewHolder.HolderProductAdmin(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminViewHolder.HolderProductAdmin holder, int position) {
        Item item=productListAdmin.get(position);
        String id=item.getPid();
        String productName=item.getProductName();
        String price=item.getPrice();
        String productCategory=item.getProductCategory();
        String image=item.getImage();
        String status=item.getStatus();

        holder.txtItemNameAdmin.setText(productName);
        holder.txtItemPriceAdmin.setText("Rs:"+price+".00");
        Picasso.get().load(image).into(holder.imageViewAdmin);

        if(status.equals("old")){
            holder.newImage_admin.setVisibility(View.INVISIBLE);
        }

        holder.btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AdminMaintainItem.class);
                intent.putExtra("pid", item.getPid());
                intent.putExtra("category", item.getProductCategory());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productListAdmin.size();
    }

    @Override
    public Filter getFilter() {
        if(filter==null){
            filter=new FilterProductAdmin(this,filterListAdmin);
        }
        return filter;
    }


    class HolderProductAdmin extends RecyclerView.ViewHolder{
        TextView txtItemNameAdmin,txtItemPriceAdmin;
        ImageView imageViewAdmin,newImage_admin;
        Button btn_admin;

        public HolderProductAdmin(@NonNull  View itemView) {
            super(itemView);
            imageViewAdmin =(ImageView) itemView.findViewById(R.id.card_admin_image_new);
            txtItemNameAdmin =(TextView) itemView.findViewById(R.id.card_admin_name_new);
            txtItemPriceAdmin =(TextView) itemView.findViewById(R.id.card_admin_price_new);
            newImage_admin =(ImageView) itemView.findViewById(R.id.newTagImage_admin);
            btn_admin =(Button) itemView.findViewById(R.id.updateButton_admin);
        }
    }
}
