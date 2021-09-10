package com.example.ueeversion1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    Button btn,btn1,btn3,btn4;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView nav;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
//
//        btn=findViewById(R.id.adminBtn);
//        btn1=findViewById(R.id.userBtn);
//        btn3=findViewById(R.id.newProduct_btn);
//        btn4=findViewById(R.id.registerBtn);
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
                        startActivity(new Intent(getApplicationContext(), UserCategory.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.admin:
                        startActivity(new Intent(getApplicationContext(), Admin.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.newproduct:
                        startActivity(new Intent(getApplicationContext(), NewProductView.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.register:
                        startActivity(new Intent(getApplicationContext(), Register.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    default:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });


//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),Admin.class));
//            }
//        });
//
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),UserCategory.class));
//            }
//        });
//
//
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(),NewProductView.class);
//                startActivity(intent);
//            }
//        });
//
//        btn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(),Register.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}