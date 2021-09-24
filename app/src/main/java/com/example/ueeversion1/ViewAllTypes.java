package com.example.ueeversion1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class ViewAllTypes extends AppCompatActivity {
    private DatabaseReference itemReff;
    private RecyclerView recyclerView;
    private String CategoryName,ProductType;
    private EditText search1;
    private ImageButton button;
    private TextView textView,textView1;
    private SliderView sliderView;
    private ArrayList<Item> productList;
    private ArrayList<Item> productSlider;
    private ArrayList<Item> productSlider1;
    private Button back;

    private UserViewHolder userViewHolder;
    private SliderViewAdapter sliderViewAdapter_new;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_types);

        CategoryName = getIntent().getExtras().get("Category").toString();
        ProductType = getIntent().getExtras().get("pType").toString();
        getSupportActionBar().setTitle("For" + ProductType);
        

        recyclerView = findViewById(R.id.card_recycleView1);
        search1= findViewById(R.id.searchProduct2);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        textView=findViewById(R.id.searchText2);
        textView1=findViewById(R.id.searchText3);
        sliderView = findViewById(R.id.image_slider1);
        back=findViewById(R.id.all_type_back_button);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        //sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.startAutoCycle();
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        itemReff = FirebaseDatabase.getInstance().getReference().child("Products");
        //loadAllProducts();
        LoadFilterProducts(ProductType,CategoryName);
        loadImageSlider(CategoryName);

        textView.setText(CategoryName);
        textView1.setText(ProductType);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),UserCategory.class));
            }
        });
        search1.addTextChangedListener(new TextWatcher() {
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
    }


    private void LoadFilterProducts(String selected,String CategoryName) {
        productList=new ArrayList<>();
        itemReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(CategoryName.equals("Cakes")) {
                    for(DataSnapshot ds:snapshot.getChildren()){
                        String Category =""+ds.child("ProductType").getValue();
                        if(selected.equals(Category)){
                            Item item=ds.getValue(Item.class);
                            productList.add(item);
                        }
                    }
                }
                if(CategoryName.equals("Fruits")) {
                    for(DataSnapshot ds:snapshot.getChildren()){
                        String Category =""+ds.child("ProductType").getValue();
                        if(selected.equals(Category)){
                            Item item=ds.getValue(Item.class);
                            productList.add(item);
                        }
                    }
                }
                if(CategoryName.equals("Flower")) {
                    for(DataSnapshot ds:snapshot.getChildren()){
                        String Category =""+ds.child("ProductType").getValue();
                        if(selected.equals(Category)){
                            Item item=ds.getValue(Item.class);
                            productList.add(item);
                        }
                    }
                }
                if(CategoryName.equals("KidsCorner")) {
                    for(DataSnapshot ds:snapshot.getChildren()){
                        String Category =""+ds.child("ProductType").getValue();
                        if(selected.equals(Category)){
                            Item item=ds.getValue(Item.class);
                            productList.add(item);
                        }
                    }
                }
                if(CategoryName.equals("Foods")) {
                    for(DataSnapshot ds:snapshot.getChildren()){
                        String Category =""+ds.child("ProductType").getValue();
                        if(selected.equals(Category)){
                            Item item=ds.getValue(Item.class);
                            productList.add(item);
                        }
                    }
                }
                if(CategoryName.equals("Chocolates")) {
                    for(DataSnapshot ds:snapshot.getChildren()){
                        String Category =""+ds.child("ProductType").getValue();
                        if(selected.equals(Category)){
                            Item item=ds.getValue(Item.class);
                            productList.add(item);
                        }
                    }
                }
                if(CategoryName.equals("Electronics")) {
                    for(DataSnapshot ds:snapshot.getChildren()){
                        String Category =""+ds.child("ProductType").getValue();
                        if(selected.equals(Category)){
                            Item item=ds.getValue(Item.class);
                            productList.add(item);
                        }
                    }
                }
                if(CategoryName.equals("Gift")) {
                    for(DataSnapshot ds:snapshot.getChildren()){
                        String Category =""+ds.child("ProductType").getValue();
                        if(selected.equals(Category)){
                            Item item=ds.getValue(Item.class);
                            productList.add(item);
                        }
                    }
                }
                if(CategoryName.equals("TeddyBears")) {
                    for(DataSnapshot ds:snapshot.getChildren()){
                        String Category =""+ds.child("ProductType").getValue();
                        if(selected.equals(Category)){
                            Item item=ds.getValue(Item.class);
                            productList.add(item);
                        }
                    }
                }
                if(CategoryName.equals("Clothes")) {
                    for(DataSnapshot ds:snapshot.getChildren()){
                        String Category =""+ds.child("ProductType").getValue();
                        if(selected.equals(Category)){
                            Item item=ds.getValue(Item.class);
                            productList.add(item);
                        }
                    }
                }
                if(CategoryName.equals("Vegetables")) {
                    for(DataSnapshot ds:snapshot.getChildren()){
                        String Category =""+ds.child("ProductType").getValue();
                        if(selected.equals(Category)){
                            Item item=ds.getValue(Item.class);
                            productList.add(item);
                        }
                    }
                }
                if(CategoryName.equals("Jewelery")) {
                    for(DataSnapshot ds:snapshot.getChildren()){
                        String Category =""+ds.child("ProductType").getValue();
                        if(selected.equals(Category)){
                            Item item=ds.getValue(Item.class);
                            productList.add(item);
                        }
                    }
                }
                if(CategoryName.equals("Models")) {
                    for(DataSnapshot ds:snapshot.getChildren()){
                        String Category =""+ds.child("ProductType").getValue();
                        if(selected.equals(Category)){
                            Item item=ds.getValue(Item.class);
                            productList.add(item);
                        }
                    }
                }
                if(CategoryName.equals("Cosmetics")) {
                    for(DataSnapshot ds:snapshot.getChildren()){
                        String Category =""+ds.child("ProductType").getValue();
                        if(selected.equals(Category)){
                            Item item=ds.getValue(Item.class);
                            productList.add(item);
                        }
                    }
                }
                if(CategoryName.equals("Pirikara")) {
                    for(DataSnapshot ds:snapshot.getChildren()){
                        String Category =""+ds.child("ProductType").getValue();
                        if(selected.equals(Category)){
                            Item item=ds.getValue(Item.class);
                            productList.add(item);
                        }
                    }
                }
                if(CategoryName.equals("Music")) {
                    for(DataSnapshot ds:snapshot.getChildren()){
                        String Category =""+ds.child("ProductType").getValue();
                        if(selected.equals(Category)){
                            Item item=ds.getValue(Item.class);
                            productList.add(item);
                        }
                    }
                }

                userViewHolder=new UserViewHolder(ViewAllTypes.this,productList);
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
                sliderViewAdapter_new=new SliderAdapter(ViewAllTypes.this,productSlider,productSlider1);
                sliderView.setSliderAdapter(sliderViewAdapter_new);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}