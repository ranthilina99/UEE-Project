package com.example.ueeversion1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ueeversion1.Model.Item;
import com.example.ueeversion1.ViewHolder.ImageSliderView;
import com.example.ueeversion1.ViewHolder.RecycleViewAdapter;
import com.example.ueeversion1.ViewHolder.SliderAdapter;
import com.example.ueeversion1.ViewHolder.newUserHolderImagesAll;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class UserBuyProduct extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
    private Button addToCart;
    private TextView name, area, price, description, date, stock, cod;
    private ImageView image1,ImageCOD,ImageInStock;
    private String productID = "";
    private DatabaseReference itemRef,itemReff,itemReff1;
    private RecyclerView recyclerView,recyclerView1,recyclerView2;
    private RecyclerView.LayoutManager layoutManager1;
    private LinearLayout mainLayout;
    private SliderView sliderView;
    boolean valid = true;

    private ArrayList<Item> productList;
    private ArrayList<Item> productList1;
    private newUserHolderImagesAll userViewHolder1;
    private ImageSliderView userViewHolder2;
    private SliderViewAdapter sliderViewAdapter_buy;
    private ArrayList<Item> productSlider_buy;
    private ArrayList<Item> productSlider1_buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_buy_product);

        productID = getIntent().getStringExtra("pid");

        getSupportActionBar().setTitle("Buy Product");
        itemRef = FirebaseDatabase.getInstance().getReference().child("Products").child(productID);
        itemReff = FirebaseDatabase.getInstance().getReference().child("Products");

        name = (TextView) findViewById(R.id.product_buy_name);
        area = (TextView) findViewById(R.id.product_buy_Area);
        price = (TextView) findViewById(R.id.product_buy_price);
        description = (TextView) findViewById(R.id.product_buy_Description);
        image1 = (ImageView) findViewById(R.id.product_buy_Image);
        stock = (TextView) findViewById(R.id.product_buy_inStock);
        cod = (TextView) findViewById(R.id.product_buy_COD_available);
        date=(TextView) findViewById(R.id.product_buy_date);
        recyclerView = findViewById(R.id.item_buy_subcategory);
        recyclerView1=findViewById(R.id.item_buy_slider1);
        recyclerView2=findViewById(R.id.item_buy_slider);
        ImageCOD = (ImageView) findViewById(R.id.Image_COD_buy);
        ImageInStock = (ImageView) findViewById(R.id.Image_inStock_buy);
        mainLayout=(LinearLayout)this.findViewById(R.id.ViewLayer3_buy);
        sliderView = findViewById(R.id.image_slider_buy);
        displayItemInfo();


//        recyclerView.setHasFixedSize(true);
//        layoutManager= new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        adapter=new RecycleViewAdapter(this,categories);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        //sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.startAutoCycle();
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        //layoutManager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        layoutManager1= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager1);

        recyclerView1.setLayoutManager(new GridLayoutManager(this, 3));
        loadAllProduct();
        loadImageSlider();
    }


    private void displayItemInfo() {
        itemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    String IName = dataSnapshot.child("ProductName").getValue().toString();
                    String Iimage = dataSnapshot.child("image").getValue().toString();
                    String Iarea = dataSnapshot.child("Area").getValue().toString();
                    String Iprice = dataSnapshot.child("price").getValue().toString();
                    String Idescription = dataSnapshot.child("description").getValue().toString();
                    String Newdate = dataSnapshot.child("Date").getValue().toString();
                    String ssts = dataSnapshot.child("available").getValue().toString();
                    String Istocks = dataSnapshot.child("Stock").getValue().toString();
                    String rece= dataSnapshot.child("receptions").getValue().toString();
                    String CategoryName =dataSnapshot.child("ProductCategory").getValue().toString();

                    Picasso.get().load(Iimage).into(image1);
                    name.setText(IName);
                    price.setText(Iprice+"."+"00");
                    area.setText(Iarea);
                    description.setText(Idescription);
                    date.setText(Newdate);

                    if(ssts.equals("COD Available")){
                        cod.setText(ssts);
                    }else {
                        cod.setVisibility(View.INVISIBLE);
                        ImageCOD.setVisibility(View.INVISIBLE);
                        mainLayout.setVisibility(LinearLayout.GONE);
                    }
                    if(Istocks.equals("In Stock")){
                        ImageInStock.setImageResource(R.drawable.in);
                    }else {
                        ImageInStock.setImageResource(R.drawable.out);
                    }
                    stock.setText(Istocks);

                    loadCategorySelect(CategoryName);
//                    CategoryReceptions(IName);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void loadAllProduct() {
        productList1=new ArrayList<>();
        Random ran=new Random();
        itemReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    Item item=ds.getValue(Item.class);
                    productList1.add(item);
                    Collections.shuffle(productList1);
                }
                userViewHolder1=new newUserHolderImagesAll(UserBuyProduct.this,productList1);
                recyclerView1.setAdapter(userViewHolder1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void loadCategorySelect(String categoryName) {
        productList = new ArrayList<>();
        itemReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String Category = "" + ds.child("ProductCategory").getValue();
                    if (categoryName.equals(Category)) {
                        Item item = ds.getValue(Item.class);
                        productList.add(item);
                        Collections.shuffle(productList);
                    }
                }
                userViewHolder2 = new ImageSliderView(UserBuyProduct.this, productList);
                recyclerView2.setAdapter(userViewHolder2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void loadImageSlider() {
        productSlider_buy=new ArrayList<>();
        productSlider1_buy=new ArrayList<>();
        itemReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    Item item=ds.getValue(Item.class);
                    Collections.shuffle(productSlider_buy);
                    productSlider_buy.add(item);
                    if(!productSlider_buy.equals(productSlider1_buy)){
                        Collections.shuffle(productSlider1_buy);
                        productSlider1_buy.add(item);
                    }
                }
                sliderViewAdapter_buy=new SliderAdapter(UserBuyProduct.this,productSlider_buy,productSlider1_buy);
                sliderView.setSliderAdapter(sliderViewAdapter_buy);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}