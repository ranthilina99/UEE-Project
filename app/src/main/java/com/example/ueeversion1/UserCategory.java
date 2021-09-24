package com.example.ueeversion1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;

public class UserCategory extends AppCompatActivity {

    private CardView flower1,cake,foods,chocolates,electronics,kidsCorner,gift,
            vouchers,teddyBears,clothes,fruits,vegetables,watches,babyProducts,
            books,jewelery,models,cosmetics,pirikara,music;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_user_category);

        getSupportActionBar().setTitle("User View Category");

        flower1= findViewById(R.id.card_user_flower);
        cake= findViewById(R.id.card_user_cake);
        fruits=findViewById(R.id.card_user_fruit);
        foods=findViewById(R.id.card_user_food);
        chocolates=findViewById(R.id.card_user_Chocolate);
        electronics=findViewById(R.id.card_user_electronic);
        kidsCorner=findViewById(R.id.card_user_Kids);
        gift=findViewById(R.id.card_user_gift);
        teddyBears=findViewById(R.id.card_user_teddy);
        clothes=findViewById(R.id.card_user_clothes);
        vegetables=findViewById(R.id.card_user_vegetables);
        books=findViewById(R.id.card_user_Books);
        jewelery=findViewById(R.id.card_user_Jewellery);
        models=findViewById(R.id.card_user_Model);
        cosmetics=findViewById(R.id.card_user_Cosmetics);
        pirikara=findViewById(R.id.card_user_Pirikara);
        music=findViewById(R.id.card_user_Music);
        back=findViewById(R.id.back_new);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        flower1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserItemView.class);
                intent.putExtra("Category","Flower");
                startActivity(intent);            }
        });
        cake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserItemView.class);
                intent.putExtra("Category","Cakes");
                startActivity(intent);
            }
        });
        fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserItemView.class);
                intent.putExtra("Category","Fruits");
                startActivity(intent);
            }
        });
        foods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserItemView.class);
                intent.putExtra("Category","Foods");
                startActivity(intent);
            }
        });
        chocolates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserItemView.class);
                intent.putExtra("Category","Chocolates");
                startActivity(intent);
            }
        });
        electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserItemView.class);
                intent.putExtra("Category","Electronics");
                startActivity(intent);
            }
        });
        kidsCorner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserItemView.class);
                intent.putExtra("Category","KidsCorner");
                startActivity(intent);
            }
        });
        gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserItemView.class);
                intent.putExtra("Category","Gift");
                startActivity(intent);
            }
        });
        teddyBears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserItemView.class);
                intent.putExtra("Category","TeddyBears");
                startActivity(intent);
            }
        });
        clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserItemView.class);
                intent.putExtra("Category","Clothes");
                startActivity(intent);
            }
        });
        vegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserItemView.class);
                intent.putExtra("Category","Vegetables");
                startActivity(intent);
            }
        });
        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserItemView.class);
                intent.putExtra("Category","Books");
                startActivity(intent);
            }
        });
        jewelery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserItemView.class);
                intent.putExtra("Category","Jewelery");
                startActivity(intent);
            }
        });
        models.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserItemView.class);
                intent.putExtra("Category","Models");
                startActivity(intent);
            }
        });
        cosmetics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserItemView.class);
                intent.putExtra("Category","Cosmetics");
                startActivity(intent);
            }
        });
        pirikara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserItemView.class);
                intent.putExtra("Category","Pirikara");
                startActivity(intent);
            }
        });
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserItemView.class);
                intent.putExtra("Category","Music");
                startActivity(intent);
            }
        });

    }
}