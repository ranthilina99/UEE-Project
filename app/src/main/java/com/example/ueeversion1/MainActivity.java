package com.example.ueeversion1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

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
                        startActivity(new Intent(getApplicationContext(), CartActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.newproduct:
                        startActivity(new Intent(getApplicationContext(), NewProductView.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.register:
                        startActivity(new Intent(getApplicationContext(), Registration.class));
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
//                Intent intent=new Intent(getApplicationContext(),Registration.class);
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