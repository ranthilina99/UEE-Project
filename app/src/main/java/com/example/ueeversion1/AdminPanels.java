package com.example.ueeversion1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class AdminPanels extends AppCompatActivity {

    ImageView adminLogout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panels);
        adminLogout=findViewById(R.id.logout);


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



    }
}