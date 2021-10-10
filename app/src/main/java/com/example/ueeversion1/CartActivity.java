package com.example.ueeversion1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.ueeversion1.Delivery.DeliveryAdd;
import com.example.ueeversion1.Model.Cart;
import com.example.ueeversion1.ViewHolder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button NextProcessBtn;
    private TextView txtTotalAmount;
    private ImageView close_wish,wishImage,edit1;
    int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        getSupportActionBar().setTitle("Cart");
        recyclerView = findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        NextProcessBtn = (Button) findViewById(R.id.next_process_btn);
        txtTotalAmount = (TextView) findViewById(R.id.total_price);

        close_wish = findViewById(R.id.close_cart);
        edit1 = findViewById(R.id.cartEdit1);

        NextProcessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeliveryAdd.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");

        FirebaseRecyclerOptions<Cart> options = new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(cartListRef.child("User View")
                        .child("Products"),Cart.class).build();

        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter =
                new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {



                    @Override
                    protected void onBindViewHolder(@NonNull CartViewHolder holder, int i, @NonNull Cart model) {

                        holder.txtProductName.setText(model.getPname());
                        holder.txtProductQuantity.setText(String.valueOf(model.getPquantity()));
                        holder.txtProductPrice.setText(String.valueOf(model.getTotPrice()));
                        String Iimage = model.getPimage();

                        //load image
                        Picasso.get().load(Iimage).into(holder.cartImage);

                        int e =  model.getTotPrice();

                        totalPrice = totalPrice + e;
                        txtTotalAmount.setText("Total : LKR. " +String.valueOf(totalPrice));


                        //Edit Cart 01
                        holder.editCart1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent intent = new Intent(CartActivity.this, UserBuyProduct.class);
                                intent.putExtra("pid",model.getPid());
                                startActivity(intent);

                            }
                        });

                        //Edit Cart 02
                        holder.editCart2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent intent = new Intent(CartActivity.this, UserBuyProduct.class);
                                intent.putExtra("pid",model.getPid());
                                startActivity(intent);

                            }
                        });


                        //Remove wishlist item
                        holder.removeCart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                                builder.setTitle("Are you sure?");
                                builder.setMessage("Item will be removed from the Cart!");

                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        cartListRef.child("User View").child("Products")
                                                .child(model.getPid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(CartActivity.this, "Deleted Succesfully", Toast.LENGTH_SHORT).show();

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


//                        //If user click on a cart item
//                        holder.itemView.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//
//                                CharSequence options[] = new CharSequence[]
//                                        {
//                                            "Edit",
//                                            "Remove"
//                                        };
//
//                                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
//                                builder.setTitle("Cart Options:");
//
//                                builder.setItems(options, new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                                        if(i == 0){
//                                            Intent intent = new Intent(CartActivity.this, ProductDetailsActivity.class);
//                                            intent.putExtra("pid",model.getPid());
//                                            startActivity(intent);
//                                        }
//                                        if (i == 1){
//                                            cartListRef.child("User View")
//                                                    .child(Prevalent.currentOnlineUser.getPhone())
//                                                    .child("Products")
//                                                    .child(model.getPid()).removeValue()
//                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                        @Override
//                                                        public void onComplete(@NonNull Task<Void> task) {
//                                                           if (task.isSuccessful()){
//                                                               Toast.makeText(CartActivity.this, "Deleted Succesfully", Toast.LENGTH_SHORT).show();
//                                                           }
//                                                        }
//                                                    });
//                                        }
//
//                                    }
//                                });
//                                builder.show();
//                            }
//                        });

                    }

                    @NonNull
                    @Override
                    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout, parent, false);
                        CartViewHolder holder = new CartViewHolder(view);
                        return holder;
                    }

                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
}