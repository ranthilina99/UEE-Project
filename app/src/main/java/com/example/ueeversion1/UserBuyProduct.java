package com.example.ueeversion1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ueeversion1.Model.Item;
import com.example.ueeversion1.Review.ShowReview;
import com.example.ueeversion1.ViewHolder.ImageSliderView;
import com.example.ueeversion1.ViewHolder.RecycleViewAdapter;
import com.example.ueeversion1.ViewHolder.SliderAdapter;
import com.example.ueeversion1.ViewHolder.newUserHolderImagesAll;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class UserBuyProduct extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
    private Button back;
    private TextView name, area, price, description, date, stock, cod,qty,ratingText;
    private ImageView image1,ImageCOD,ImageInStock;
    private String productID = "";
    private DatabaseReference itemRef,itemReff,itemReff1,dbRef;
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

    private ImageView add,remove;
    private Button addCart;
    int totQty = 1;
    int totalPrice = 0;
    int i;
    String pp;


    private RatingBar rating;
    private float count,total;

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
        back=(Button)findViewById(R.id.back_buy_product);
        addCart=findViewById(R.id.pd_add_to_cart);
        displayItemInfo();

        add = findViewById(R.id.addItem);
        remove = findViewById(R.id.removeItem);
        qty = findViewById(R.id.qty);

        /////////////////
        rating = findViewById(R.id.rating_buy);
        ratingText = findViewById(R.id.rating_text_buy);

        rating.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    goToReview();
                }
                return true;
            }
        });

        dbRef = FirebaseDatabase.getInstance().getReference();
        Query showQuery = dbRef.child("Review").orderByChild("productID").equalTo(productID);

        showQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot showSnapshot : dataSnapshot.getChildren()) {

                    float temp= Float.parseFloat(showSnapshot.child("rating").getValue().toString());
                    Rate(temp);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Change qty
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totQty < 10){
                    totQty++;
                    qty.setText(String.valueOf(totQty));

                }

            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totQty < 10){
                    totQty--;
                    qty.setText(String.valueOf(totQty));
                }

            }
        });

        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addingToCartList();
            }
        });


//        recyclerView.setHasFixedSize(true);
//        layoutManager= new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        adapter=new RecycleViewAdapter(this,categories);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),UserCategory.class));
            }
        });
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        //sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.startAutoCycle();
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        //layoutManager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        layoutManager1= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager1);

        recyclerView1.setLayoutManager(new GridLayoutManager(this, 2));
        loadAllProduct();
        loadImageSlider();
    }

    private void goToReview(){
        Intent intentReview = new Intent(this, ShowReview.class);
        intentReview.putExtra("pID",productID);
        startActivity(intentReview);
    }

    public void Rate(float tot) {


        count += 1;
        total += tot;

        if(count == 0){
            count = 1;
        }

        rating.setRating(total/count);
        ratingText.setText(( (total/count)) + "");
    }

    private void addingToCartList() {

        itemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    String IName = dataSnapshot.child("ProductName").getValue().toString();
                    String Iimage = dataSnapshot.child("image").getValue().toString();
                    String Iprice = dataSnapshot.child("price").getValue().toString();

                    DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("Products");

                    int oneItemTotalPrice=((Integer.valueOf(Iprice)));
                    int tottot = oneItemTotalPrice * totQty;

//                    String tem1 = String.valueOf(totQty);
//                    String tem2 = String.valueOf(tottot);


                    //get values related to that ID
                    itemRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if (dataSnapshot.exists()){
                                Item item = dataSnapshot.getValue(Item.class);

                                String saveCurrentTime, saveCurrentDate;

                                Calendar calForDate = Calendar.getInstance();
                                SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
                                saveCurrentDate = currentDate.format(calForDate.getTime());

                                Calendar calForTime = Calendar.getInstance();
                                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                                saveCurrentTime = currentTime.format(calForTime.getTime());

                                //create ref in db
                                final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");

                                //Put details in the Map
                                final HashMap<String,Object> cartMap = new HashMap<>();



                                cartMap.put("pid",productID);
                                cartMap.put("pname",name.getText().toString());
                                cartMap.put("price",price.getText().toString());
                                cartMap.put("pdate",saveCurrentDate);
                                cartMap.put("ptime",saveCurrentTime);
                                cartMap.put("pquantity",totQty);
                                cartMap.put("pimage",Iimage);
                                cartMap.put("totPrice",tottot);


                                cartListRef.child("User View").child("Products").child(productID).updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(UserBuyProduct.this, "Added to Cart!.", Toast.LENGTH_SHORT).show();

//                                Intent intent = new Intent(ProductDetailsActivity.this, HomeActivity.class);
//                                startActivity(intent);
                                        }
                                    }
                                });

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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