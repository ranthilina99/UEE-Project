package com.example.ueeversion1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import com.example.ueeversion1.ViewHolder.UserViewHolder;
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

public class ProductView extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
    private Button viewBtn;
    private TextView name, area, price, description, date, stock, cod,productType;
    private ImageView image1,ImageCOD,ImageInStock;
    private String productID = "",PType,ProductCategory;
    private DatabaseReference itemRef,itemReff,itemReff1;
    private RecyclerView recyclerView,recyclerView1,recyclerView2;
    private RecyclerView.LayoutManager layoutManager,layoutManager1;
    private LinearLayout mainLayout;
    private SliderView sliderView;
    boolean valid = true;

    private ArrayList<Item> productList;
    private ArrayList<Item> productList1;
    private newUserHolderImagesAll userViewHolder1;
    private ImageSliderView userViewHolder2;
    private SliderViewAdapter sliderViewAdapter_view;
    private ArrayList<Item> productSlider_view;
    private ArrayList<Item> productSlider1_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        productID = getIntent().getStringExtra("pid");
        getSupportActionBar().setTitle("Product View");

        itemRef = FirebaseDatabase.getInstance().getReference().child("Products").child(productID);
        itemReff = FirebaseDatabase.getInstance().getReference().child("Products");

        name = (TextView) findViewById(R.id.product_view_name);
        area = (TextView) findViewById(R.id.product_view_Area);
        price = (TextView) findViewById(R.id.product_view_price);
        description = (TextView) findViewById(R.id.product_view_Description);
        image1 = (ImageView) findViewById(R.id.product_view_Image);
        stock = (TextView) findViewById(R.id.product_view_inStock);
        cod = (TextView) findViewById(R.id.product_view_COD_available);
        date=(TextView) findViewById(R.id.product_view_date);
        recyclerView = findViewById(R.id.item_view_subcategory);
        recyclerView1=findViewById(R.id.item_view_slider1);
        recyclerView2=findViewById(R.id.item_view_slider);
        ImageCOD = (ImageView) findViewById(R.id.Image_COD);
        ImageInStock = (ImageView) findViewById(R.id.Image_inStock);
        mainLayout=(LinearLayout)this.findViewById(R.id.ViewLayer3);
        viewBtn=(Button) findViewById(R.id.productType_view_btn);
        productType=(TextView) findViewById(R.id.productType_view_name);
        sliderView = findViewById(R.id.image_slider_view);
        displayItemInfo();


//        recyclerView.setHasFixedSize(true);
//        layoutManager= new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        adapter=new RecycleViewAdapter(this,categories);

        //layoutManager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        layoutManager1= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager1);

        recyclerView1.setLayoutManager(new GridLayoutManager(this, 3));
        loadAllProduct();
        loadImageSlider();

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        //sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.startAutoCycle();
        sliderView.setIndicatorUnselectedColor(Color.GRAY);

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewAllTypes.class);
                intent.putExtra("pType", PType);
                intent.putExtra("Category", ProductCategory);
                startActivity(intent);
            }
        });

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
                    PType = dataSnapshot.child("ProductType").getValue().toString();

                    Picasso.get().load(Iimage).into(image1);
                    name.setText(IName);
                    price.setText(Iprice+"."+"00");
                    area.setText(Iarea);
                    description.setText(Idescription);
                    date.setText(Newdate);
                    productType.setText(PType);

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
                    ProductCategory=CategoryName;

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
                userViewHolder1=new newUserHolderImagesAll(ProductView.this,productList1);
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
                userViewHolder2 = new ImageSliderView(ProductView.this, productList);
                recyclerView2.setAdapter(userViewHolder2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
//    private void CategoryReceptions(String ProductName) {
//        categories=new ArrayList<>();
//        itemReff.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot ds : snapshot.getChildren()) {
//                    String Category = "" + ds.child("ProductName").getValue();
//                    if (ProductName.equals(Category)) {
//                        Item item = ds.getValue(Item.class);
//                        categories.add(item);
//                    }
//                }
//                userViewHolder3 = new RecycleViewAdapter(ProductView.this, categories);
//                recyclerView.setAdapter(userViewHolder3);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
    private void loadImageSlider() {
        productSlider_view=new ArrayList<>();
        productSlider1_view=new ArrayList<>();
        itemReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                        Item item=ds.getValue(Item.class);
                        Collections.shuffle(productSlider_view);
                    productSlider_view.add(item);
                        if(!productSlider_view.equals(productSlider1_view)){
                            Collections.shuffle(productSlider1_view);
                            productSlider1_view.add(item);
                        }
                }
                sliderViewAdapter_view=new SliderAdapter(ProductView.this,productSlider_view,productSlider1_view);
                sliderView.setSliderAdapter(sliderViewAdapter_view);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}