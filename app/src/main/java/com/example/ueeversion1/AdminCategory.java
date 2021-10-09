package com.example.ueeversion1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AdminCategory extends AppCompatActivity {
    private CardView flower,foods,cakes,chocolates,electronics,kidsCorner,gift,
            vouchers,teddyBears,clothes,fruits,vegetables,watches,babyProducts,
            books,jewelery,models,cosmetics,pirikara,music;
    private Button AdminViewProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        getSupportActionBar().setTitle("Admin View Category");
        flower=findViewById(R.id.img_flower);
        foods=findViewById(R.id.img_food);
        cakes=findViewById(R.id.img_cake);
        chocolates=findViewById(R.id.img_chocolate);
        electronics=findViewById(R.id.img_electronic);
        kidsCorner=findViewById(R.id.img_kids);
        gift=findViewById(R.id.img_gift);
        vouchers=findViewById(R.id.img_vouchers);
        teddyBears=findViewById(R.id.img_teddy);
        clothes=findViewById(R.id.img_clothes);
        fruits=findViewById(R.id.img_fruit);
        vegetables=findViewById(R.id.img_vegetable);
        watches=findViewById(R.id.img_watch);
        babyProducts=findViewById(R.id.img_baby);
        books=findViewById(R.id.img_books);
        jewelery=findViewById(R.id.img_jewellery);
        cosmetics=findViewById(R.id.img_cosmetics);
        models=findViewById(R.id.img_model);
        pirikara=findViewById(R.id.img_pirikara);
        music=findViewById(R.id.img_music);


        flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddProduct.class);
                intent.putExtra("Category","Flower");
                startActivity(intent);
            }
        });
        foods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddProduct.class);
                intent.putExtra("Category","Foods");
                startActivity(intent);
            }
        });
        cakes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddProduct.class);
                intent.putExtra("Category","Cakes");
                startActivity(intent);
            }
        });
        kidsCorner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddProduct.class);
                intent.putExtra("Category","KidsCorner");
                startActivity(intent);
            }
        });
        chocolates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddProduct.class);
                intent.putExtra("Category","Chocolates");
                startActivity(intent);
            }
        });
        electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddProduct.class);
                intent.putExtra("Category","Electronics");
                startActivity(intent);
            }
        });
        gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddProduct.class);
                intent.putExtra("Category","Gift");
                startActivity(intent);
            }
        });
        vouchers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddProduct.class);
                intent.putExtra("Category","Vouchers");
                startActivity(intent);
            }
        });
        teddyBears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddProduct.class);
                intent.putExtra("Category","TeddyBears");
                startActivity(intent);
            }
        });
        clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddProduct.class);
                intent.putExtra("Category","Clothes");
                startActivity(intent);
            }
        });
        fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddProduct.class);
                intent.putExtra("Category","Fruits");
                startActivity(intent);
            }
        });
        vegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddProduct.class);
                intent.putExtra("Category","Vegetables");
                startActivity(intent);
            }
        });
        watches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddProduct.class);
                intent.putExtra("Category","Watches");
                startActivity(intent);
            }
        });
        babyProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddProduct.class);
                intent.putExtra("Category","BabyProducts");
                startActivity(intent);
            }
        });
        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddProduct.class);
                intent.putExtra("Category","Books");
                startActivity(intent);
            }
        });
        jewelery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddProduct.class);
                intent.putExtra("Category","Jewelery");
                startActivity(intent);
            }
        });
        cosmetics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddProduct.class);
                intent.putExtra("Category","Cosmetics");
                startActivity(intent);
            }
        });
        models.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddProduct.class);
                intent.putExtra("Category","Models");
                startActivity(intent);
            }
        });
        pirikara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddProduct.class);
                intent.putExtra("Category","Pirikara");
                startActivity(intent);
            }
        });
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddProduct.class);
                intent.putExtra("Category","Music");
                startActivity(intent);
            }
        });
    }
}