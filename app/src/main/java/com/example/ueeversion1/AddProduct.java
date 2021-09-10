package com.example.ueeversion1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;

public class AddProduct extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    private TextView receptions,Title;
    private EditText PDate,PName,PPrice,PArea,PDescription;
    private Spinner spin;
    private ImageView image;
    private Button PBtn;
    private CheckBox checkBox;
    private RadioButton radio1,radio2,rdStock1,rdStock2;
    private String CategoryName,Name,Area,Description,Price,R1,R2,R3,R4,Date,status,status1,status2,saveCurrentDate,saveCurrentTime;
    private String ItemCategoryName,ItemType,receptions1;
    boolean[] selectedReceptions;
    ArrayList<Integer> receptionList= new ArrayList<>();
    String[] receptionArray = {"For GirlFriend","For BoyFriend","For Wife","For Husband"
            ,"For Mother","For Father","For Sister","For Brother","For Best Friend","For Teacher"
            ,"For Son","For Daughter","For Baby","For Aunty","For Uncle"};

    public Uri ImageUri;

    private String productRandomKey, downloadImageUrl;
    private StorageReference productImagesRef;
    private DatabaseReference productsRef;
    private ProgressDialog loadingBar;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        CategoryName = getIntent().getExtras().get("Category").toString();
        getSupportActionBar().setTitle("Admin Add Product");
//        getSupportActionBar().setTitle("For "+CategoryName);
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        productImagesRef = FirebaseStorage.getInstance().getReference().child("Product Images");
        productsRef = FirebaseDatabase.getInstance().getReference().child("Products");

        receptions=findViewById(R.id.receptions);
        spin = findViewById(R.id.category_spinner);
        PDate=findViewById(R.id.Product_Date);
        image = findViewById(R.id.select_product_image);
        PName= findViewById(R.id.Product_name);
        PPrice=findViewById(R.id.Product_Price);
        PArea=findViewById(R.id.Product_DeliveryArea);
        PDescription=findViewById(R.id.productDescription);
        PBtn=findViewById(R.id.product_addButton);
        radio1=findViewById(R.id.rdBtnAvailable);
        radio2=findViewById(R.id.rdBtnNotAvailable);
        rdStock1=findViewById(R.id.rdInStock);
        rdStock2=findViewById(R.id.rdOutStock);
        checkBox=findViewById(R.id.new_check);
        Title=findViewById(R.id.admin_add_name1);

        Title.setText(CategoryName);

        Calendar calendar=Calendar.getInstance();
        final int year =calendar.get(Calendar.YEAR);
        final int month =calendar.get(Calendar.MONTH);
        final int day =calendar.get(Calendar.DAY_OF_MONTH);

        if(CategoryName.equals("Cakes")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Cake_category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);

            spin.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Flower")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.flower_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);

            spin.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Foods")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.food_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);

            spin.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("KidsCorner")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.kids_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);

            spin.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Chocolates")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.chocolate_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);

            spin.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Electronics")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.electronic_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);

            spin.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Gift")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.gift_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);

            spin.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("TeddyBears")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.teddy_Bears_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);

            spin.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Clothes")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.clothes_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);

            spin.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Fruits")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.fruit_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);

            spin.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Vegetables")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.vegetable_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);

            spin.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Books")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.bookShop_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);

            spin.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Jewelery")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.jewellery_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);

            spin.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Cosmetics")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.cosmetic_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);

            spin.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Models")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.model_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);

            spin.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Pirikara")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.pirikara_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);

            spin.setOnItemSelectedListener(this);
        }
        if(CategoryName.equals("Music")){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Music_Category,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);

            spin.setOnItemSelectedListener(this);
        }
        loadingBar = new ProgressDialog(this);

        //Initialize selected receptions array
        receptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        AddProduct.this
                );
                builder.setTitle("Select Receptions");

                builder.setCancelable(false);

                builder.setMultiChoiceItems(receptionArray, selectedReceptions, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean b) {
                        if(b){
                            receptionList.add(i);

                            Collections.sort(receptionList);
                        }else {
                            receptionList.remove(i);
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        StringBuilder stringBuilder=new StringBuilder();

                        for(int j=0;j<receptionList.size();j++){
                            stringBuilder.append(receptionArray[receptionList.get(j)]);

                            if(j != receptionList.size()-1){
                                stringBuilder.append(", ");
                            }
                        }
                        receptions.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenPhoneGallery();
            }
        });
        PDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(AddProduct.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                month=month+1;
                                String date=day+"-"+month+"-"+year;
                                PDate.setText(date);
                            }
                        },year,month,day);
                datePickerDialog.show();
            }
        });
        PBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateProductData();
            }
        });
    }
    private void OpenPhoneGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(galleryIntent.createChooser(galleryIntent,"select picture"), 1);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            ImageUri = data.getData();
            image.setImageURI(ImageUri);

        }

    }
    private void ValidateProductData() {
        Name= PName.getText().toString();
        Price=PPrice.getText().toString();
        Area= PArea.getText().toString();
        Description=PDescription.getText().toString();
        ItemType = ItemCategoryName;
        Date=PDate.getText().toString();
        receptions1=receptions.getText().toString();
        R1=rdStock1.getText().toString();
        R2=rdStock2.getText().toString();
        R3=radio1.getText().toString();
        R4=radio2.getText().toString();

        if(rdStock1.isChecked()){
            status = R1;
        }
        else
        {
            status = R2;
        }
        if(radio1.isChecked()){
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


        if (ImageUri == null) {

            Toast.makeText(this, "Select Product Image", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Description)) {
            Toast.makeText(this, "Please Write product discription", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(Price)) {
            Toast.makeText(this, "Please Write product price", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(Name)) {
            Toast.makeText(this, "Please Write product name", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(status)) {
            Toast.makeText(this, "Please Select stock or not", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(status1)) {
            Toast.makeText(this, "Please Select available or not", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Area)) {
            Toast.makeText(this, "Please Write Area", Toast.LENGTH_SHORT).show();

        }

        else {
            StoreProductInformation();
        }

    }
    private void StoreProductInformation() {
        loadingBar.setTitle("Add New Product");
        loadingBar.setMessage("Please wait");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();


        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd,yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;

        final StorageReference filePath = productImagesRef.child(ImageUri.getLastPathSegment() + productRandomKey + ".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(AddProduct .this, "Error:" + message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AddProduct.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();

                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();

                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            downloadImageUrl = task.getResult().toString();
                            Toast.makeText(AddProduct.this, "getting product image Url successfully", Toast.LENGTH_SHORT).show();

                            SaveProductInfortoDatabase();
                        }
                    }
                });
            }
        });
    }

    private void SaveProductInfortoDatabase() {
        HashMap<String, Object> productMap = new HashMap<>();
        productMap.put("pid", productRandomKey);
        productMap.put("currentDate", saveCurrentDate);
        productMap.put("time", saveCurrentTime);
        productMap.put("description", Description);
        productMap.put("image", downloadImageUrl);
        productMap.put("ProductCategory", CategoryName);
        productMap.put("price", Price);
        productMap.put("Date", Date);
        productMap.put("status", status2);
        productMap.put("ProductName", Name);
        productMap.put("ProductType",ItemType);
        productMap.put("Area" , Area);
        productMap.put("Stock" , status);
        productMap.put("available", status1);
        productMap.put("receptions" , receptions1);

        productsRef.child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(AddProduct.this, AdminCategory.class);
                            startActivity(intent);
                            loadingBar.dismiss();
                            Toast.makeText(AddProduct.this, "Product is added successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(AddProduct.this, "Error:" + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
        ItemCategoryName = parent.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}