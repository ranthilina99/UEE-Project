package com.example.ueeversion1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ueeversion1.Model.Item;
import com.example.ueeversion1.ViewHolder.UserViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewProductView extends AppCompatActivity {
    private DatabaseReference itemRef;
    private RecyclerView recyclerView;
    private String NewProductName;
    private EditText search;
    private Button back;

    private ArrayList<Item> productList;
    private UserViewHolder userViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product_view);
        NewProductName = "new";
        getSupportActionBar().setTitle("For" +" "+ "New Product");
        itemRef = FirebaseDatabase.getInstance().getReference().child("Products");
        back=findViewById(R.id.back_new_item);
        recyclerView = findViewById(R.id.card_recycleNewProductView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        search = findViewById(R.id.searchNewProduct);

        loadNewProductALl(NewProductName);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
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
    }

    private void loadNewProductALl(String newProductName) {
        productList=new ArrayList<>();
        itemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    String product =""+ds.child("status").getValue();
                    if(newProductName.equals(product)){
                        Item item=ds.getValue(Item.class);
                        productList.add(item);
                    }
                }
                userViewHolder=new UserViewHolder(NewProductView.this,productList);
                recyclerView.setAdapter(userViewHolder);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}