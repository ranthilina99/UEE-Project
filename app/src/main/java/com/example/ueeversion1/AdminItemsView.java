package com.example.ueeversion1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.ueeversion1.Model.Item;
import com.example.ueeversion1.ViewHolder.AdminViewHolder;
import com.example.ueeversion1.ViewHolder.UserViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdminItemsView extends AppCompatActivity {

    private DatabaseReference itemReff;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private String stts ="";
    private String CategoryName;
    private EditText search;

    private ArrayList<Item> productListAdmin;
    private AdminViewHolder adminViewHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_items_view);


        itemReff = FirebaseDatabase.getInstance().getReference().child("Products");
        CategoryName = getIntent().getExtras().get("Category").toString();
        getSupportActionBar().setTitle("For" +" "+ CategoryName);
        recyclerView = findViewById(R.id.recycler_menu_adm);
        search=findViewById(R.id.searchProduct1);
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        //loadAllProducts();
        loadCategorySelect(CategoryName);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    adminViewHolder.getFilter().filter(s);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void loadCategorySelect(String categoryName) {
        productListAdmin=new ArrayList<>();
        itemReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    String Category =""+ds.child("ProductCategory").getValue();
                    if(categoryName.equals(Category)){
                        Item item=ds.getValue(Item.class);
                        productListAdmin.add(item);
                    }
                }
                adminViewHolder=new AdminViewHolder(AdminItemsView.this,productListAdmin);
                recyclerView.setAdapter(adminViewHolder);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}