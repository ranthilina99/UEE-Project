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
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SlideHolder> {
    private final Context context;
    public ArrayList<Item> productListSlider2;
    public ArrayList<Item> productListSlider3;

    public SliderAdapter(Context context, ArrayList<Item> productListSlider2, ArrayList<Item> productListSlider3) {
        this.context = context;
        this.productListSlider2 = productListSlider2;
        this.productListSlider3 = productListSlider3;
    }

    @Override
    public SlideHolder onCreateViewHolder(ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.image_slider,parent,false);
        return new SlideHolder(view);
    }

    @Override
    public void onBindViewHolder(SlideHolder viewHolder, int position) {
        Item item=productListSlider2.get(position);
        String id=item.getPid();
        String productName=item.getProductName();
        String price=item.getPrice();
        String productCategory=item.getProductCategory();
        String image=item.getImage();
        String status=item.getStatus();

        viewHolder.txtItemNameSlider.setText(productName);
        viewHolder.txtItemPriceSlider.setText("Rs:"+price+".00");
        Picasso.get().load(image).into(viewHolder.imageViewSlider1);

        if(status.equals("old")){
            viewHolder.newImageSlider.setVisibility(View.INVISIBLE);
        }

        Item item1=productListSlider3.get(position);
        String id1=item1.getPid();
        String productName1=item1.getProductName();
        String price1=item1.getPrice();
        String productCategory1=item1.getProductCategory();
        String image1=item1.getImage();
        String status1=item1.getStatus();

        viewHolder.txtItemNameSlider1.setText(productName1);
        viewHolder.txtItemPriceSlider1.setText("Rs:"+price1+".00");
        Picasso.get().load(image1).into(viewHolder.imageViewSlider2);

        if(status1.equals("old")){
            viewHolder.newImageSlider1.setVisibility(View.INVISIBLE);
        }
        viewHolder.imageViewSlider1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserBuyProduct.class);
                intent.putExtra("pid", item.getPid());
                context.startActivity(intent);
            }
        });
        viewHolder.imageViewSlider2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserBuyProduct.class);
                intent.putExtra("pid", item.getPid());
                context.startActivity(intent);
            }
        });
        viewHolder.btn_Slider_card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserBuyProduct.class);
                intent.putExtra("pid", item.getPid());
                context.startActivity(intent);
            }
        });
        viewHolder.btn_Slider_card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserBuyProduct.class);
                intent.putExtra("pid", item.getPid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getCount() {
        return Math.min(productListSlider2.size(), 8);
    }

    class SlideHolder extends SliderViewAdapter.ViewHolder{

        TextView txtItemNameSlider,txtItemPriceSlider;
        ImageView imageViewSlider1,newImageSlider;
        Button btn_Slider_card1;

        TextView txtItemNameSlider1,txtItemPriceSlider1;
        ImageView imageViewSlider2,newImageSlider1;
        Button btn_Slider_card2;

        public SlideHolder(@NonNull View itemView) {
            super(itemView);
            imageViewSlider1 =(ImageView) itemView.findViewById(R.id.card_view_image_slider);
            txtItemNameSlider =(TextView) itemView.findViewById(R.id.card_view_name_slider);
            txtItemPriceSlider =(TextView) itemView.findViewById(R.id.card_view_price_slider);
            newImageSlider =(ImageView) itemView.findViewById(R.id.newTagImage_slider);
            btn_Slider_card1=(Button) itemView.findViewById(R.id.HotButton_slider);

            imageViewSlider2 =(ImageView) itemView.findViewById(R.id.card_view_image_slider1);
            txtItemNameSlider1 =(TextView) itemView.findViewById(R.id.card_view_name_slider1);
            txtItemPriceSlider1 =(TextView) itemView.findViewById(R.id.card_view_price_slider1);
            newImageSlider1 =(ImageView) itemView.findViewById(R.id.newTagImage_Slider1);
            btn_Slider_card2=(Button) itemView.findViewById(R.id.HotButton_slider1);
        }
    }
}
