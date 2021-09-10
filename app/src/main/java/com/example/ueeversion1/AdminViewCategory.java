package com.example.ueeversion1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminViewCategory extends AppCompatActivity {
    private ImageView flower,foods,cakes,chocolates,electronics,kidsCorner,gift,
            vouchers,teddyBears,clothes,fruits,vegetables,watches,babyProducts,
            books,jewelery,models,cosmetics,pirikara,music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_category);

        getSupportActionBar().setTitle("Admin View Category");

        flower=findViewById(R.id.img_flower1);
        foods=findViewById(R.id.img_food1);
        cakes=findViewById(R.id.img_cake1);
        chocolates=findViewById(R.id.img_chocolate1);
        electronics=findViewById(R.id.img_electronic1);
        kidsCorner=findViewById(R.id.img_kids1);
        gift=findViewById(R.id.img_gift1);
        vouchers=findViewById(R.id.img_vouchers1);
        teddyBears=findViewById(R.id.img_teddy1);
        clothes=findViewById(R.id.img_clothes1);
        fruits=findViewById(R.id.img_fruit1);
        vegetables=findViewById(R.id.img_vegetable1);
        watches=findViewById(R.id.img_watch1);
        babyProducts=findViewById(R.id.img_baby1);
        books=findViewById(R.id.img_books1);
        jewelery=findViewById(R.id.img_jewellery1);
        cosmetics=findViewById(R.id.img_cosmetics1);
        models=findViewById(R.id.img_model1);
        pirikara=findViewById(R.id.img_pirikara1);
        music=findViewById(R.id.img_music1);

        flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminItemsView.class);
                intent.putExtra("Category","Flower");
                startActivity(intent);
            }
        });
        foods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminItemsView.class);
                intent.putExtra("Category","Foods");
                startActivity(intent);
            }
        });
        cakes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminItemsView.class);
                intent.putExtra("Category","Cakes");
                startActivity(intent);
            }
        });
        kidsCorner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminItemsView.class);
                intent.putExtra("Category","KidsCorner");
                startActivity(intent);
            }
        });
        chocolates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminItemsView.class);
                intent.putExtra("Category","Chocolates");
                startActivity(intent);
            }
        });
        electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminItemsView.class);
                intent.putExtra("Category","Electronics");
                startActivity(intent);
            }
        });
        gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminItemsView.class);
                intent.putExtra("Category","Gift");
                startActivity(intent);
            }
        });
        vouchers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminItemsView.class);
                intent.putExtra("Category","Vouchers");
                startActivity(intent);
            }
        });
        teddyBears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminItemsView.class);
                intent.putExtra("Category","TeddyBears");
                startActivity(intent);
            }
        });
        clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminItemsView.class);
                intent.putExtra("Category","Clothes");
                startActivity(intent);
            }
        });
        fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminItemsView.class);
                intent.putExtra("Category","Fruits");
                startActivity(intent);
            }
        });
        vegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminItemsView.class);
                intent.putExtra("Category","Vegetables");
                startActivity(intent);
            }
        });
        watches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminItemsView.class);
                intent.putExtra("Category","Watches");
                startActivity(intent);
            }
        });
        babyProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminItemsView.class);
                intent.putExtra("Category","BabyProducts");
                startActivity(intent);
            }
        });
        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminItemsView.class);
                intent.putExtra("Category","Books");
                startActivity(intent);
            }
        });
        jewelery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminItemsView.class);
                intent.putExtra("Category","Jewelery");
                startActivity(intent);
            }
        });
        cosmetics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminItemsView.class);
                intent.putExtra("Category","Cosmetics");
                startActivity(intent);
            }
        });
        models.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminItemsView.class);
                intent.putExtra("Category","Models");
                startActivity(intent);
            }
        });
        pirikara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminItemsView.class);
                intent.putExtra("Category","Pirikara");
                startActivity(intent);
            }
        });
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminItemsView.class);
                intent.putExtra("Category","Music");
                startActivity(intent);
            }
        });
    }

}