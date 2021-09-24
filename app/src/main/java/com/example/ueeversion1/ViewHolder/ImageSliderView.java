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
import com.example.ueeversion1.ProductView;
import com.example.ueeversion1.R;
import com.example.ueeversion1.UserBuyProduct;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageSliderView extends RecyclerView.Adapter<ImageSliderView.HolderProductSlider>{
    private final Context context;
    public ArrayList<Item> productListSlider;

    public ImageSliderView(Context context, ArrayList<Item> productListSlider) {
        this.context = context;
        this.productListSlider = productListSlider;
    }

    @Override
    public HolderProductSlider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.user_cardview_item,parent,false);
        return new ImageSliderView.HolderProductSlider(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSliderView.HolderProductSlider holder, int position) {
        Item item=productListSlider.get(position);
        String id=item.getPid();
        String productName=item.getProductName();
        String price=item.getPrice();
        String productCategory=item.getProductCategory();
        String image=item.getImage();
        String status=item.getStatus();

        holder.txtItemNameSlider.setText(productName);
        holder.txtItemPriceSlider.setText("Rs:"+price+".00");
        Picasso.get().load(image).into(holder.imageViewSlider);

        if(status.equals("old")){
            holder.newImage.setVisibility(View.INVISIBLE);
        }

        holder.btn_user_card_slider1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductView.class);
                intent.putExtra("pid", item.getPid());
                context.startActivity(intent);
            }
        });
        holder.btn_user_card_slider2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserBuyProduct.class);
                intent.putExtra("pid", item.getPid());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return Math.min(productListSlider.size(), 4);
    }

    class HolderProductSlider extends RecyclerView.ViewHolder{
        TextView txtItemNameSlider,txtItemPriceSlider;
        ImageView imageViewSlider,newImage;
        Button btn_user_card_slider1,btn_user_card_slider2;

        public HolderProductSlider(@NonNull View itemView) {
            super(itemView);
            imageViewSlider =(ImageView) itemView.findViewById(R.id.card_view_image_user);
            txtItemNameSlider =(TextView) itemView.findViewById(R.id.card_view_name_user);
            txtItemPriceSlider =(TextView) itemView.findViewById(R.id.card_view_price_user);
            newImage =(ImageView) itemView.findViewById(R.id.newTagImage_user);
            btn_user_card_slider1=(Button) itemView.findViewById(R.id.ViewButton_user);
            btn_user_card_slider2=(Button) itemView.findViewById(R.id.BuyButton_user);
        }
    }
}