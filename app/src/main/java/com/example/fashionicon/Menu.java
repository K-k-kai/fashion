package com.example.fashionicon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {
Button dressBtn, shortsBtn, skirtsBtn,topsBtn,hatsBtn, cartBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initViews();
        dressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Menu.this,Category.class);
                intent.putExtra("CATEGORY", "Dresses");
                startActivity(intent);
            }
        });
        shortsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Menu.this,Category.class);
                intent.putExtra("CATEGORY", "Shorts");
                startActivity(intent);
            }
        });
        skirtsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Menu.this,Category.class);
                intent.putExtra("CATEGORY", "Skirts");
                startActivity(intent);
            }
        });
       topsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Menu.this,Category.class);
                intent.putExtra("CATEGORY", "Tops");
                startActivity(intent);
            }
        });
        hatsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Menu.this,Category.class);
                intent.putExtra("CATEGORY", "Hats");
                startActivity(intent);
            }
        });
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Menu.this, Cart.class);
                startActivity(intent);
            }
        });

    }

    private  void initViews(){
        dressBtn=findViewById(R.id.dressesBtn);
        shortsBtn=findViewById(R.id.shortsBtn);
        skirtsBtn=findViewById(R.id.skirtsBtn);
        topsBtn=findViewById(R.id.topBtn);
        hatsBtn= findViewById(R.id.hatsBtn);
        cartBtn=findViewById(R.id.viewCartBtn);
    }
}