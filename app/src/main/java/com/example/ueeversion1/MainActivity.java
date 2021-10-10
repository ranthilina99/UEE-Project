package com.example.ueeversion1;

import androidx.annotation.NonNull;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ueeversion1.Model.Item;
import com.example.ueeversion1.ViewHolder.ImageSliderView;
import com.example.ueeversion1.ViewHolder.newUserHolderImagesAll;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    Button btn,btn1,btn3,btn4;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private FirebaseStorage storage;
    private FirebaseFirestore FStoreSal;
    private StorageReference storageReference;
    private NavigationView nav;
    private FirebaseAuth FAuthSal;
    private ArrayList<Item> productList;
    private ArrayList<Item> productList1,productList2,productList3;
    private DatabaseReference itemReff;
    private newUserHolderImagesAll userViewHolder1;
    private ImageSliderView userViewHolder2;
    private RecyclerView.LayoutManager layoutManager;
    private TextView textView1,textView2,textView3;
    private RecyclerView recyclerView1,recyclerView2,recyclerView3,recyclerView4,recyclerView5;
    private ImageView headerProfile;
    private TextView firstNames,email;
    private String userIds;



    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        itemReff = FirebaseDatabase.getInstance().getReference().child("Products");

//
//        btn=findViewById(R.id.adminBtn);
//        btn1=findViewById(R.id.userBtn);
//        btn3=findViewById(R.id.newProduct_btn);
//        btn4=findViewById(R.id.registerBtn);

        recyclerView1=findViewById(R.id.home_recycleView);
        recyclerView2=findViewById(R.id.home_recycleView_cake);
        recyclerView3=findViewById(R.id.home_recycleView_flower);
        recyclerView4=findViewById(R.id.home_recycleView_teadyBears);

        textView1=findViewById(R.id.seeAll1);
        textView2=findViewById(R.id.seeAll2);
        textView3=findViewById(R.id.seeAll3);

        recyclerView1.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView2.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView3.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView4.setLayoutManager(new GridLayoutManager(this, 2));

        storage =FirebaseStorage.getInstance();
        storageReference =storage.getReference();
        headerProfile = findViewById(R.id.header_profile);
        firstNames = findViewById(R.id.fn);
        email =  findViewById(R.id.textView2);
        FAuthSal = FirebaseAuth.getInstance();
        FStoreSal = FirebaseFirestore.getInstance();


//        userIds = FAuthSal.getCurrentUser().getUid();
//        final DocumentReference documentReference = FStoreSal.collection("Users").document(userIds);
//        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//
//                firstNames.setText(value.getString("FirstName"));
//
//            }
//        });

//        firstNames.setText("ff");





        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserItemView.class);
                intent.putExtra("Category","Flower");
                startActivity(intent);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserItemView.class);
                intent.putExtra("Category","Cakes");
                startActivity(intent);
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserItemView.class);
                intent.putExtra("Category","TeddyBears");
                startActivity(intent);
            }
        });

        loadAllProduct();
        loadCategoryCakeSelect();
        loadCategoryFlowerSelect();
        loadCategoryTeddyBearSelect();


        drawerLayout=findViewById(R.id.drawer);

        mToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        nav=findViewById(R.id.navMenu);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.db:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.admin:
                        startActivity(new Intent(getApplicationContext(), CartActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.newproduct:
                        startActivity(new Intent(getApplicationContext(), NewProductView.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.userCategory:
                        startActivity(new Intent(getApplicationContext(), UserCategory.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.activities:
                        startActivity(new Intent(getApplicationContext(), WishListActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.Setting1:
                        startActivity(new Intent(getApplicationContext(), Changeprofile.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.logout:

                        SharedPreferences preferences=getSharedPreferences("Checkbox",MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("remember","false");
                        editor.apply();

                        Toast.makeText(MainActivity.this, "Thank You Come Again Log Out...", Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(), Login.class));
                        finish();

                        break;

                    default:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });
    }
    private void loadAllProduct() {
        productList=new ArrayList<>();
        itemReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    Item item=ds.getValue(Item.class);
                    productList.add(item);
                    Collections.shuffle(productList);
                }
                userViewHolder1=new newUserHolderImagesAll(MainActivity.this,productList);
                recyclerView1.setAdapter(userViewHolder1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void loadCategoryCakeSelect() {
        productList1 = new ArrayList<>();
        itemReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String Category = "" + ds.child("ProductCategory").getValue();
                    if (Category.equals("Cakes")) {
                        Item item = ds.getValue(Item.class);
                        productList1.add(item);
                        Collections.shuffle(productList1);
                    }
                }
                userViewHolder2 = new ImageSliderView(MainActivity.this, productList1);
                recyclerView2.setAdapter(userViewHolder2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void loadCategoryFlowerSelect() {
        productList2 = new ArrayList<>();
        itemReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String Category = "" + ds.child("ProductCategory").getValue();
                    if (Category.equals("Flower")) {
                        Item item = ds.getValue(Item.class);
                        productList2.add(item);
                        Collections.shuffle(productList2);
                    }
                }
                userViewHolder2 = new ImageSliderView(MainActivity.this, productList2);
                recyclerView3.setAdapter(userViewHolder2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void loadCategoryTeddyBearSelect() {
        productList3 = new ArrayList<>();
        itemReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String Category = "" + ds.child("ProductCategory").getValue();
                    if (Category.equals("TeddyBears")) {
                        Item item = ds.getValue(Item.class);
                        productList3.add(item);
                        Collections.shuffle(productList3);
                    }
                }
                userViewHolder2 = new ImageSliderView(MainActivity.this, productList3);
                recyclerView4.setAdapter(userViewHolder2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}