package com.example.ueeversion1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ueeversion1.Model.Item;
import com.example.ueeversion1.ViewHolder.SliderAdapter;
import com.example.ueeversion1.ViewHolder.UserViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class UserItemView extends AppCompatActivity {

    private DatabaseReference itemReff;
    private RecyclerView recyclerView;
    private Button back;
    private String CategoryName,NewProductName;
    private EditText search;
    private ImageButton button;
    private TextView textView,textView1;
    private SliderView sliderView;
    private ArrayList<Item> productList;
    private ArrayList<Item> productSlider;
    private ArrayList<Item> productSlider1;

    private UserViewHolder userViewHolder;
    private SliderViewAdapter sliderViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_item_view);

        CategoryName = getIntent().getExtras().get("Category").toString();

        getSupportActionBar().setTitle("For" +" "+ CategoryName);

        recyclerView = findViewById(R.id.card_recycleView);
        search = findViewById(R.id.searchProduct);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        button = findViewById(R.id.filter_button);
        textView=findViewById(R.id.searchText);
        textView1=findViewById(R.id.searchText1);
        sliderView = findViewById(R.id.image_slider);
        back=findViewById(R.id.back_item_button);


        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        //sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.startAutoCycle();
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        itemReff = FirebaseDatabase.getInstance().getReference().child("Products");
        //loadAllProducts();
        loadCategorySelect(CategoryName);
        loadImageSlider(CategoryName);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),UserCategory.class));
            }
        });

        textView.setText(CategoryName);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    userViewHolder.getFilter().filter(s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //this not work after deign editing for the code
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(
                            UserItemView.this
                    );
                if(CategoryName.equals("Cakes")){
                    builder.setTitle("Select Category")
                            .setItems(Constants.cakeCategory, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String selected = Constants.cakeCategory[which];
                                    textView1.setText(selected);
                                    if(selected.equals("All")){
                                        loadCategorySelect(CategoryName);
                                    }else {
                                        LoadFilterProducts(selected);
                                    }
                                }
                            }).show();
                }
                if(CategoryName.equals("Fruits")){
                    builder.setTitle("Select Category")
                            .setItems(Constants.fruitCategory, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String selected = Constants.fruitCategory[which];
                                    textView1.setText(selected);
                                    if(selected.equals("All")){
                                        loadCategorySelect(CategoryName);
                                    }else {
                                        LoadFilterProducts(selected);
                                    }
                                }
                            }).show();
                }
                if(CategoryName.equals("Flower")){
                    builder.setTitle("Select Category")
                            .setItems(Constants.flowerCategory, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String selected = Constants.flowerCategory[which];
                                    textView1.setText(selected);
                                    if(selected.equals("All")){
                                        loadCategorySelect(CategoryName);
                                    }else {
                                        LoadFilterProducts(selected);
                                    }
                                }
                            }).show();
                }
                if(CategoryName.equals("Foods")){
                    builder.setTitle("Select Category")
                            .setItems(Constants.foodsCategory, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String selected = Constants.foodsCategory[which];
                                    textView1.setText(selected);
                                    if(selected.equals("All")){
                                        loadCategorySelect(CategoryName);
                                    }else {
                                        LoadFilterProducts(selected);
                                    }
                                }
                            }).show();
                }
                if(CategoryName.equals("Chocolates")){
                    builder.setTitle("Select Category")
                            .setItems(Constants.chocolateCategory, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String selected = Constants.chocolateCategory[which];
                                    textView1.setText(selected);
                                    if(selected.equals("All")){
                                        loadCategorySelect(CategoryName);
                                    }else {
                                        LoadFilterProducts(selected);
                                    }
                                }
                            }).show();
                }
                if(CategoryName.equals("Electronics")){
                    builder.setTitle("Select Category")
                            .setItems(Constants.electronicCategory, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String selected = Constants.electronicCategory[which];
                                    textView1.setText(selected);
                                    if(selected.equals("All")){
                                        loadCategorySelect(CategoryName);
                                    }else {
                                        LoadFilterProducts(selected);
                                    }
                                }
                            }).show();
                }
                if(CategoryName.equals("KidsCorner")){
                    builder.setTitle("Select Category")
                            .setItems(Constants.kidsCategory, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String selected = Constants.kidsCategory[which];
                                    textView1.setText(selected);
                                    if(selected.equals("All")){
                                        loadCategorySelect(CategoryName);
                                    }else {
                                        LoadFilterProducts(selected);
                                    }
                                }
                            }).show();
                }
                if(CategoryName.equals("Gift")){
                    builder.setTitle("Select Category")
                            .setItems(Constants.giftCategory, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String selected = Constants.giftCategory[which];
                                    textView1.setText(selected);
                                    if(selected.equals("All")){
                                        loadCategorySelect(CategoryName);
                                    }else {
                                        LoadFilterProducts(selected);
                                    }
                                }
                            }).show();
                }
                if(CategoryName.equals("TeddyBears")){
                    builder.setTitle("Select Category")
                            .setItems(Constants.teddyBeardCategory, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String selected = Constants.teddyBeardCategory[which];
                                    textView1.setText(selected);
                                    if(selected.equals("All")){
                                        loadCategorySelect(CategoryName);
                                    }else {
                                        LoadFilterProducts(selected);
                                    }
                                }
                            }).show();
                }
                if(CategoryName.equals("Clothes")){
                    builder.setTitle("Select Category")
                            .setItems(Constants.clothesCategory, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String selected = Constants.clothesCategory[which];
                                    textView1.setText(selected);
                                    if(selected.equals("All")){
                                        loadCategorySelect(CategoryName);
                                    }else {
                                        LoadFilterProducts(selected);
                                    }
                                }
                            }).show();
                }
                if(CategoryName.equals("Vegetables")){
                    builder.setTitle("Select Category")
                            .setItems(Constants.vegetableCategory, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String selected = Constants.vegetableCategory[which];
                                    textView1.setText(selected);
                                    if(selected.equals("All")){
                                        loadCategorySelect(CategoryName);
                                    }else {
                                        LoadFilterProducts(selected);
                                    }
                                }
                            }).show();
                }
                if(CategoryName.equals("Jewelery")){
                    builder.setTitle("Select Category")
                            .setItems(Constants.jewelleryCategory, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String selected = Constants.jewelleryCategory[which];
                                    textView1.setText(selected);
                                    if(selected.equals("All")){
                                        loadCategorySelect(CategoryName);
                                    }else {
                                        LoadFilterProducts(selected);
                                    }
                                }
                            }).show();
                }
                if(CategoryName.equals("Models")){
                    builder.setTitle("Select Category")
                            .setItems(Constants.modelCategory, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String selected = Constants.modelCategory[which];
                                    textView1.setText(selected);
                                    if(selected.equals("All")){
                                        loadCategorySelect(CategoryName);
                                    }else {
                                        LoadFilterProducts(selected);
                                    }
                                }
                            }).show();
                }
                if(CategoryName.equals("Cosmetics")){
                    builder.setTitle("Select Category")
                            .setItems(Constants.cosmeticCategory, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String selected = Constants.cosmeticCategory[which];
                                    textView1.setText(selected);
                                    if(selected.equals("All")){
                                        loadCategorySelect(CategoryName);
                                    }else {
                                        LoadFilterProducts(selected);
                                    }
                                }
                            }).show();
                }
                if(CategoryName.equals("Pirikara")){
                    builder.setTitle("Select Category")
                            .setItems(Constants.pirikaraCategory, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String selected = Constants.pirikaraCategory[which];
                                    textView1.setText(selected);
                                    if(selected.equals("All")){
                                        loadCategorySelect(CategoryName);
                                    }else {
                                        LoadFilterProducts(selected);
                                    }
                                }
                            }).show();
                }
                if(CategoryName.equals("Music")){
                    builder.setTitle("Select Category")
                            .setItems(Constants.musicCategory, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String selected = Constants.musicCategory[which];
                                    textView1.setText(selected);
                                    if(selected.equals("All")){
                                        loadCategorySelect(CategoryName);
                                    }else {
                                        LoadFilterProducts(selected);
                                    }
                                }
                            }).show();
                }
            }
        });



//    private void LoadFilterProducts(String selected) {
//        productList=new ArrayList<>();
//        itemReff.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot ds:snapshot.getChildren()){
//                    String Category =""+ds.child("ProductType").getValue();
//                    if(selected.equals(Category)){
//                        Item item=ds.getValue(Item.class);
//                        productList.add(item);
//                    }
//                }
//                userViewHolder=new UserViewAdapter(UserItemView.this,productList);
//                recyclerView.setAdapter(userViewHolder);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

    }


    private void LoadFilterProducts(String selected) {
        productList=new ArrayList<>();
        itemReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    String Category =""+ds.child("ProductType").getValue();
                    if(selected.equals(Category)){
                        Item item=ds.getValue(Item.class);
                        productList.add(item);
                    }
                }
                userViewHolder=new UserViewHolder(UserItemView.this,productList);
                recyclerView.setAdapter(userViewHolder);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadCategorySelect(String categoryName) {
        productList=new ArrayList<>();
        itemReff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds:snapshot.getChildren()){
                                String Category =""+ds.child("ProductCategory").getValue();
                            if(categoryName.equals(Category)){
                                Item item=ds.getValue(Item.class);
                                productList.add(item);
                            }
                        }
                        userViewHolder=new UserViewHolder(UserItemView.this,productList);
                        recyclerView.setAdapter(userViewHolder);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void loadImageSlider(String categoryName) {
        productSlider=new ArrayList<>();
        productSlider1=new ArrayList<>();
        itemReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    String Category =""+ds.child("ProductCategory").getValue();
                    if(categoryName.equals(Category)){
                        Item item=ds.getValue(Item.class);
                        Collections.shuffle(productSlider);
                        productSlider.add(item);
                       if(!productSlider.equals(productSlider1)){
                           Collections.shuffle(productSlider1);
                           productSlider1.add(item);
                       }
                    }
                }
                sliderViewAdapter=new SliderAdapter(UserItemView.this,productSlider,productSlider1);
                sliderView.setSliderAdapter(sliderViewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



//    private void loadAllProducts() {
//        productList=new ArrayList<>();
//        itemReff.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for(DataSnapshot ds:snapshot.getChildren()){
//                            Item item=ds.getValue(Item.class);
//                            productList.add(item);
//                        }
//                        userViewHolder=new UserViewHolder(UserItemView.this,productList);
//                        recyclerView.setAdapter(userViewHolder);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//    }
}