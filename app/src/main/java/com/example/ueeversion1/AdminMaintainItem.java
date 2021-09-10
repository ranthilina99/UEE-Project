package com.example.ueeversion1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class AdminMaintainItem extends AppCompatActivity  implements AdapterView.OnItemSelectedListener,View.OnClickListener{
    private Button updateButton,deleteButton;
    private EditText name,area,price,description,Idate;
    private ImageView imge1;
    private String productID = "",CategoryName;
    public  String ItemCategoryName1,ItemType1;
    private DatabaseReference itemRef;
    private RadioButton availbl,notAvail,stock1,stock2;
    private Spinner spinner;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_maintain_item);

        productID = getIntent().getExtras().get("pid").toString();
        CategoryName = getIntent().getExtras().get("category").toString();
        getSupportActionBar().setTitle("Maintain Product");
        itemRef = FirebaseDatabase.getInstance().getReference().child("Products").child(productID);

        updateButton = (Button)findViewById(R.id.update_button_I);
        name = (EditText) findViewById(R.id.item_name_m);
        area = (EditText)findViewById(R.id.Product_item__DeliveryArea);
        price = (EditText)findViewById(R.id.Product_item_Price);
        description = (EditText)findViewById(R.id.product_item_Description);
        imge1 = (ImageView)findViewById(R.id.item_image_m);
        deleteButton = (Button)findViewById(R.id.delete_button_I);
        availbl = (RadioButton)findViewById(R.id.rd_item_BtnAvailable);
        notAvail = (RadioButton)findViewById(R.id.rd_item_BtnNotAvailable);
        stock1 = (RadioButton)findViewById(R.id.rd_item_InStock);
        stock2 = (RadioButton)findViewById(R.id.rd_item_OutStock);
        Idate=(EditText)findViewById(R.id.Product_item__Date);
        spinner=(Spinner)findViewById(R.id.category_item__spinner);
        checkBox=(CheckBox)findViewById(R.id.new_check);

        Calendar calendar=Calendar.getInstance();
        final int year =calendar.get(Calendar.YEAR);
        final int month =calendar.get(Calendar.MONTH);
        final int day =calendar.get(Calendar.DAY_OF_MONTH);
        displayItemInfo();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyChanges();

            }
        });
        if(CategoryName.equals("Cakes")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Cake_category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Flower")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.flower_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(this);

        }
        if(CategoryName.equals("Foods")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.food_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("KidsCorner")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.kids_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Chocolates")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.chocolate_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(this);


        }
        if(CategoryName.equals("Electronics")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.electronic_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Gift")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.gift_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("TeddyBears")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.teddy_Bears_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Clothes")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.clothes_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Fruits")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.fruit_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Vegetables")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.vegetable_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Books")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.bookShop_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Jewelery")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.jewellery_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Cosmetics")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.cosmetic_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Models")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.model_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Pirikara")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.pirikara_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Music")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Music_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(this);
        }
//            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.flower_Category,android.R.layout.simple_spinner_item);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            spinner.setAdapter(adapter);
//
//            spinner.setOnItemSelectedListener(this);

        Idate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(AdminMaintainItem.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                month=month+1;
                                String date=day+"-"+month+"-"+year;
                                Idate.setText(date);
                            }
                        },year,month,day);
                datePickerDialog.show();
            }
        });
    }

    private void applyChanges() {

        String Iname =name.getText().toString();
        String Iarea =area.getText().toString();
        String Iprice =price.getText().toString();
        String Idescription =description.getText().toString();
        String R1 = availbl.getText().toString();
        String R2 = notAvail.getText().toString();
        String R3 = stock1.getText().toString();
        String R4 = stock2.getText().toString();
        String Ndate=Idate.getText().toString();
        ItemType1 = ItemCategoryName1;
        String status;
        String status1;
        String status2;

        if(availbl.isChecked()){
            status = R1;
        }
        else
        {
            status = R2;
        }
        if(stock1.isChecked()){
            status1 = R3;
        }
        else
        {
            status1 = R4;
        }

        if(checkBox.isChecked()){
            status2 = "new";
        }
        else
        {
            status2 = "old";
        }
        if(Iname.equals(""))
        {
            Toast.makeText(this, "Please Write product name", Toast.LENGTH_SHORT).show();

        }
        else if(Iarea.equals(""))
        {
            Toast.makeText(this, "Please Write delivery area", Toast.LENGTH_SHORT).show();

        }
        else if(Iprice.equals(""))
        {
            Toast.makeText(this, "Please Write product price", Toast.LENGTH_SHORT).show();

        }
        else if(Idescription.equals(""))
        {
            Toast.makeText(this, "Please Write product description", Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(status)) {
            Toast.makeText(this, "Please Select available or not", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(status1)) {
            Toast.makeText(this, "Please Select stock or not", Toast.LENGTH_SHORT).show();
        }
        else{

            HashMap<String, Object> itemMap = new HashMap<>();
            itemMap.put("pid", productID);
            itemMap.put("description", Idescription);
            itemMap.put("price", Iprice);
            itemMap.put("ProductName", Iname);
            itemMap.put("Area" , Iarea);
            itemMap.put("status" , status2);
            itemMap.put("ProductType",ItemType1);
            itemMap.put("available" , status);
            itemMap.put("Stock" , status1);
            itemMap.put("Date", Ndate);
            itemRef.updateChildren(itemMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(AdminMaintainItem.this,"Updated successfully",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AdminMaintainItem.this, AdminViewCategory.class);

                        startActivity(intent);
                        finish();

                    }

                }
            });
        }

    }


    private void displayItemInfo() {
        itemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    String Iname = dataSnapshot.child("ProductName").getValue().toString();
                    String Iarea = dataSnapshot.child("Area").getValue().toString();
                    String Iprice = dataSnapshot.child("price").getValue().toString();
                    String Idescription = dataSnapshot.child("description").getValue().toString();
                    String Iimage = dataSnapshot.child("image").getValue().toString();
                    String ssts = dataSnapshot.child("available").getValue().toString();
                    String Istocks = dataSnapshot.child("Stock").getValue().toString();
                    String Newdate = dataSnapshot.child("Date").getValue().toString();
                    String Inewproduct = dataSnapshot.child("status").getValue().toString();

                    if(ssts.equals("COD Available"))
                    {
                        availbl.toggle();
                    }
                    else
                    {
                        notAvail.toggle();
                    }
                    if(Istocks.equals("In Stock")){
                        stock1.toggle();
                    }else {
                        stock2.toggle();
                    }

                    if(Inewproduct.equals("new")){
                        checkBox.setChecked(true);
                    }

                    if(Inewproduct.equals("old")){
                        checkBox.setChecked(false);
                    }

                    name.setText(Iname);
                    area.setText(Iarea);
                    price.setText(Iprice);
                    description.setText(Idescription);
                    Idate.setText(Newdate);
                    Picasso.get().load(Iimage).into(imge1);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
        ItemCategoryName1 = parent.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete_button_I:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Are you sure about field?");
                builder.setMessage("Delete is permanent....");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DeleteUser();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog ad = builder.create();
                ad.show();
                ;
                break;
        }
    }

    private void DeleteUser() {
        itemRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Toast.makeText(AdminMaintainItem.this,"The Product Deleted successfully",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AdminMaintainItem.this, AdminViewCategory.class);
                startActivity(intent);
                finish();
            }
        });
    }
}