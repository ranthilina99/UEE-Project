package com.example.ueeversion1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class AdminPanels extends AppCompatActivity {

    ImageView adminLogout,prof ;
    CardView cardView1,cardView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panels);
        getSupportActionBar().setTitle("Admin Panel");
        adminLogout=findViewById(R.id.logout);
        cardView1=findViewById(R.id.card_product_add_admin);
        cardView2=findViewById(R.id.card_admin_view_item);
        prof = findViewById(R.id.prof);

        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPanels.this,AdminUserList.class);
                startActivity(intent);
            }
        });


        adminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences=getSharedPreferences("Checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("remember","false");
                editor.apply();
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(AdminPanels.this,"Thank You Come Again Log Out...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();

            }
        });

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AdminCategory.class));
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AdminViewCategory.class));
            }
        });



    }
}