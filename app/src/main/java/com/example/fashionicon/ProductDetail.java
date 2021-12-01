package com.example.fashionicon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import Helpers.DatabaseHelper;

public class ProductDetail extends AppCompatActivity {
    TextView prodNameTxt, priceTxt, quantityTxt, descTxt;
    Button minusBtn, plusBtn,addToCartBtn;
    ImageButton backBtn;
    ImageView prodImage;
    Integer quantity=1;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initViews();

       databaseHelper=new DatabaseHelper(this);

        Bundle bundle= getIntent().getExtras();
        String name=bundle.getString("NAME");
        int price=bundle.getInt("PRICE",1);
        String category=bundle.getString("CATEGORY");
        String description=bundle.getString("DESCRIPTION");
        String imageUrl=bundle.getString("IMAGEURL");
        String id=bundle.getString("ID");

        prodNameTxt.setText(name);
        priceTxt.setText(price+"");
        descTxt.setText(description);

        int drawableResourceId = this.getResources().getIdentifier(imageUrl, "drawable", this.getPackageName());

        Glide.with(this)
                .load(drawableResourceId)
                .into(prodImage);

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity==15){
                    plusBtn.setEnabled(false);
                    minusBtn.setEnabled(true);
                    quantityTxt.setText(""+quantity);
                    priceTxt.setText(""+price*quantity);
                }
                else {
                    plusBtn.setEnabled(true);
                    minusBtn.setEnabled(true);
                    quantity++;
                    quantityTxt.setText(""+quantity);
                    priceTxt.setText(""+price*quantity);
                }
            }
        });
         minusBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (quantity==1){
            minusBtn.setEnabled(false);
            plusBtn.setEnabled(true);
            quantityTxt.setText(""+quantity);
            priceTxt.setText(""+price*quantity);
        }
        else {
            minusBtn.setEnabled(true);
            plusBtn.setEnabled(true);
            quantity--;
            quantityTxt.setText(""+quantity);
            priceTxt.setText(""+price*quantity);
        }

    }

});
         addToCartBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                Integer amount= quantity * price;
                 Cursor res= databaseHelper.getCartItem(id);
                 if (res.getCount()==0){
                   boolean isInserted=databaseHelper.insertCartItem(id,name,imageUrl,quantity.toString(),amount.toString(),price+"");
                   if (isInserted){
                       Toast.makeText(ProductDetail.this, "Product is added to cart", Toast.LENGTH_SHORT).show();
                   }
                   else
                       Toast.makeText(ProductDetail.this, "Failed to add to cart", Toast.LENGTH_SHORT).show();
                 }
                 else{
                     boolean isUpdated=databaseHelper.updateCartItem(id,name,quantity.toString(),amount.toString(),price+"");
                     if (isUpdated){
                         Toast.makeText(ProductDetail.this, "Product is updated to cart", Toast.LENGTH_SHORT).show();
                     }
                     else
                         Toast.makeText(ProductDetail.this, "Failed to add to cart", Toast.LENGTH_SHORT).show();

                 }
             }
         });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ProductDetail.this, Category.class);
                intent.putExtra("CATEGORY", category);
                startActivity(intent);
                finish();
            }
        });

    }
    private void initViews(){
        prodNameTxt=findViewById(R.id.textView_prod_name);
        priceTxt=findViewById(R.id.text_view_prod_price);
        minusBtn=findViewById(R.id.minusBtn);
        plusBtn=findViewById(R.id.plusBtn);
        addToCartBtn=findViewById(R.id.button_add_to_cart);
        quantityTxt=findViewById(R.id.quantityTxt);
        descTxt=findViewById(R.id.textView_description);
        prodImage=findViewById(R.id.imageView_prod);
        backBtn=findViewById(R.id.button_back);
    }
}