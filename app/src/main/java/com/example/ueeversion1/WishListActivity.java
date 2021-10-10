package com.example.ueeversion1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ueeversion1.Model.Wish;
import com.example.ueeversion1.ViewHolder.WishViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class WishListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button homeNavBtn;
    private ImageView close_wish,wishImage;
    String idd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        getSupportActionBar().setTitle("Wish List");
        recyclerView = findViewById(R.id.wish_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        close_wish = findViewById(R.id.close_wish);

        homeNavBtn = findViewById(R.id.back_home_btn);

        wishImage = (ImageView) findViewById(R.id.wish_product_image);

        homeNavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WishListActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        final DatabaseReference wishListRef = FirebaseDatabase.getInstance().getReference().child("Wish List");

        FirebaseRecyclerOptions<Wish> options = new FirebaseRecyclerOptions.Builder<Wish>()
                .setQuery(wishListRef.child("User View")
                        .child("Products"),Wish.class).build();

        FirebaseRecyclerAdapter<Wish, WishViewHolder> adapter =
                new FirebaseRecyclerAdapter<Wish, WishViewHolder>(options) {



                    @Override
                    protected void onBindViewHolder(@NonNull WishViewHolder holder, int i, @NonNull Wish model) {

                        holder.txtProductName.setText(model.getPname());
//                      holder.txtProductQuantity.setText(model.getQuantity());
                        holder.txtProductPrice.setText(model.getPrice());
                        String Iimage = model.getPimage();
                        idd = model.getPid();


                        //load image
                        Picasso.get().load(Iimage).into(holder.wishImage);

                        //////
                        //goto pro
                        holder.wishTo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(WishListActivity.this, UserBuyProduct.class);
                                intent.putExtra("pid",model.getPid());
                                startActivity(intent);
                            }
                        });
                        //////


                        //Remove wishlist item
                        holder.removeWish.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                AlertDialog.Builder builder = new AlertDialog.Builder(WishListActivity.this);
                                builder.setTitle("Are you sure?");
                                builder.setMessage("Item will be removed from your WishList!");

                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        wishListRef.child("User View").child("Products")
                                                .child(idd).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(WishListActivity.this, "Deleted Succesfully", Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                        });

                                    }
                                });
                                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {



                                    }
                                });
                                AlertDialog ad = builder.create();
                                ad.show();
                                ;

                            }
                        });



                    }

                    @NonNull
                    @Override
                    public WishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_layout, parent, false);
                        WishViewHolder holder = new WishViewHolder(view);
                        return holder;
                    }

                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();



    }


}