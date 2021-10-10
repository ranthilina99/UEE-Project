package com.example.ueeversion1.Review;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ueeversion1.CartActivity;
import com.example.ueeversion1.MainActivity;
import com.example.ueeversion1.Review.ReviewPopupAdd;
import com.example.ueeversion1.Review.ReviewPopupUpdate;
import com.example.ueeversion1.YasojaRecyclerViewAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import com.example.ueeversion1.R;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

public class ShowReview extends AppCompatActivity implements View.OnClickListener{

    DatabaseReference dbRef,itemRef;
    ImageView image1;
    TextView t,item;
    RatingBar r;
    String pID,uID;
    int count=0;
    float total = (float) 0.0;
    Button b;
    Bundle extras;

    ImageView back;

    FirebaseAuth FAuthYas;
    FirebaseFirestore FStoreYas;
    String name = "";


    Boolean exists = false;

    FloatingActionButton fb;

    private static final String TAG = "Show";
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mNames2 = new ArrayList<>();
    private ArrayList<Float> mRating = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_review);

        getSupportActionBar().setTitle("Review");
        image1 = findViewById(R.id.yas_select_product_image);
        item = findViewById(R.id.yas_textView5);
        t = findViewById(R.id.yas_textView4);

        back = findViewById(R.id.yas_back_viewReview);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        r = (RatingBar)findViewById(R.id.yas_ratingBar5);
        LayerDrawable stars = (LayerDrawable) r.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.rgb(255, 87, 34), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);


        fb = findViewById(R.id.yas_popButton);

        pID = getIntent().getStringExtra("pID");

//        FAuthYas = FirebaseAuth.getInstance();
//        FStoreYas = FirebaseFirestore.getInstance();
////        userYas = FAuthYas.getCurrentUser();
//        String userIds;
//        String userID = FAuthYas.getCurrentUser().getUid();
//        final DocumentReference documentReference = FStoreYas.collection("Users").document(userID);
//        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                uID = value.getString("FirstName");
//            }
//        });


        uID = "Kamal";

        ///
        itemRef = FirebaseDatabase.getInstance().getReference().child("Products").child(pID);

        itemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    String IName = dataSnapshot.child("ProductName").getValue().toString();
                    String Iimage = dataSnapshot.child("image").getValue().toString();

                    Picasso.get().load(Iimage).into(image1);
                    item.setText(IName);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ////




        dbRef = FirebaseDatabase.getInstance().getReference();
        Query showQuery = dbRef.child("Review").orderByChild("productID").equalTo(pID);

        showQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot showSnapshot : dataSnapshot.getChildren()) {

                    float temp= Float.parseFloat(showSnapshot.child("rating").getValue().toString());
                    Rate(temp);


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        isHere();
        initImageBitmaps();


    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        Intent refresh = new Intent(this, ShowReview.class);
//        startActivity(refresh);
//        this.finish();
//    }

    private void initImageBitmaps() {




        dbRef = FirebaseDatabase.getInstance().getReference();
        Query showQuery = dbRef.child("Review").orderByChild("productID").equalTo(pID);

        showQuery.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot showSnapshot: dataSnapshot.getChildren()) {
                    Log.d(TAG, "initImageBitmaps: started");
                    mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/ueeproject-b75f7.appspot.com/o/users%2FjndtL369TCXQ4buVAs5KpH44K352%2Fprofile.jpg?alt=media&token=c8e6959f-a684-4b03-b3da-4e1733cc6253");
                    mNames.add(showSnapshot.child("userID").getValue().toString());
                    mNames2.add(showSnapshot.child("review").getValue().toString());
                    mRating.add(Float.parseFloat(showSnapshot.child("rating").getValue().toString()));
                    initRecyclerView();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: started");
        RecyclerView recyclerView = findViewById(R.id.yas_recycler_view);
        YasojaRecyclerViewAdapter adapter = new YasojaRecyclerViewAdapter(mNames,mNames2,mRating,mImageUrls,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void Rate(float tot) {


        count += 1;
        total += tot;

        if(count == 0){
            count = 1;
        }

        System.out.println("Count:" + count);
        System.out.println("Total:" + total);

        r.setRating(total/count);
        t.setText(( (total/count)) + "");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.yas_popButton: Submit2();
                break;


        }
    }

    public void number(){
        if(count == 0){

        }
    }


    public void Submit2() {




        extras = new Bundle();
        extras.putString("PRODUCT_ID", pID);
        extras.putString("USER_ID", uID);



        Intent intent;
        if(exists) {
            intent = new Intent(this, ReviewPopupUpdate.class);
            intent.putExtras(extras);
            startActivity(intent);

        }else {

            intent = new Intent(this, ReviewPopupAdd.class);
            intent.putExtras(extras);
            startActivity(intent);

        }




    }

    public Boolean isHere(){

        dbRef = FirebaseDatabase.getInstance().getReference();
        Query checkQuery = dbRef.child("Review").orderByChild("userID").equalTo(uID);



        checkQuery.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot checkSnapshot : dataSnapshot.getChildren()) {
                    if(Objects.requireNonNull(checkSnapshot.child("productID").getValue()).toString().equals(pID)){
                        exists = true;

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        return exists;
    }
}