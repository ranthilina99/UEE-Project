package com.example.ueeversion1.Delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ueeversion1.CartActivity;
import com.example.ueeversion1.R;


public class DeliveryAdd extends AppCompatActivity implements View.OnClickListener{

    EditText t,t2,t3;
    Button b;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_add);

        getSupportActionBar().setTitle("Delivery");
        t = findViewById(R.id.yas_editTextTextPersonName2);
        t2 = findViewById(R.id.yas_editTextTextPersonName6);
        t3 = findViewById(R.id.yas_editTextTextPersonName5);

        back = findViewById(R.id.yas_back_viewDeilivery3III);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.yas_addReviewBtn2: Go();
                break;
        }
    }

    public void Go(){


        if (TextUtils.isEmpty(t.getText().toString()))
            Toast.makeText(getApplicationContext(), "Empty Name", Toast.LENGTH_SHORT).show();
        else if(TextUtils.isEmpty(t2.getText().toString()))
            Toast.makeText(getApplicationContext(), "Empty Telephone No", Toast.LENGTH_SHORT).show();
        else if(t2.getText().toString().length() != 10)
            Toast.makeText(getApplicationContext(), "Invalid Telephone No", Toast.LENGTH_SHORT).show();
        else if(TextUtils.isEmpty(t3.getText().toString()))
            Toast.makeText(getApplicationContext(), "Empty Message", Toast.LENGTH_SHORT).show();
        else {
            Intent intent = new Intent(this, DeliveryAdd2.class);
            startActivity(intent);
        }
    }
}