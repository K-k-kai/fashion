package com.example.fashionicon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import Adapters.ProductAdapter;
import Models.Product;

public class Category extends AppCompatActivity implements ProductAdapter.OnProductItemClickListener {
ProductAdapter adapter;
String prodCategory="";
ImageButton backBtn;
TextView categoryName;
RecyclerView rvProducts;
    private LinearLayoutManager categoryLinearLyout;
ArrayList<Product> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        rvProducts=findViewById(R.id.rv_products);
        categoryName= findViewById(R.id.categoryName);
        backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Category.this, Menu.class);
                startActivity(intent);
                finish();
            }
        });
        products=new ArrayList<>();

        categoryLinearLyout= new GridLayoutManager(Category.this,2);
        rvProducts.setLayoutManager(categoryLinearLyout);

        prodCategory=getIntent().getStringExtra("CATEGORY");

        if (prodCategory.equalsIgnoreCase("Dresses")){
            categoryName.setText(prodCategory);
            System.out.println(prodCategory);
            loadDresses();
            adapter= new ProductAdapter(products, getApplicationContext(),this);
            rvProducts.setAdapter(adapter);
        }
       else if (prodCategory.equalsIgnoreCase("Shorts")){
            categoryName.setText(prodCategory);
            System.out.println(prodCategory);
            loadShorts();
            adapter= new ProductAdapter(products, getApplicationContext(),this);
            rvProducts.setAdapter(adapter);
        }
       else if (prodCategory.equalsIgnoreCase("Skirts")){
            categoryName.setText(prodCategory);
            System.out.println(prodCategory);
            loadSkirts();
            adapter= new ProductAdapter(products, getApplicationContext(),this);
            rvProducts.setAdapter(adapter);
        }
       else if (prodCategory.equalsIgnoreCase("Tops")){
            categoryName.setText(prodCategory);
            System.out.println(prodCategory);
            loadTops();
            adapter= new ProductAdapter(products, getApplicationContext(),this);
            rvProducts.setAdapter(adapter);
        }
       else if (prodCategory.equalsIgnoreCase("Hats")){
            categoryName.setText(prodCategory);
            System.out.println(prodCategory);
            loadHats();
            adapter= new ProductAdapter(products, getApplicationContext(),this);
            rvProducts.setAdapter(adapter);
        }
    }

    private void loadDresses(){
        products.add(new Product("dress2","Dress Name","Its just a dress","Dresses",400));
        products.add(new Product("dress3","Dress Name","Its just a dress","Dresses",400));
        products.add(new Product("dress4","Dress Name","Its just a dress","Dresses",400));
        products.add(new Product("dress5","Dress Name","Its just a dress","Dresses",400));
        products.add(new Product("dress6","Dress Name","Its just a dress","Dresses",400));
        products.add(new Product("dress7","Dress Name","Its just a dress","Dresses",400));
        products.add(new Product("dress8","Dress Name","Its just a dress","Dresses",400));
        products.add(new Product("dress2","Dress Name","Its just a dress","Dresses",400));
        products.add(new Product("dress3","Dress Name","Its just a dress","Dresses",400));
        products.add(new Product("dress4","Dress Name","Its just a dress","Dresses",400));
        products.add(new Product("dress5","Dress Name","Its just a dress","Dresses",400));

    }
    private void loadShorts(){
        products.add(new Product("short1","Short","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","Shorts",350));
        products.add(new Product("short2","Short","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","Shorts",650));
        products.add(new Product("short3","Short","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","Shorts",250));
        products.add(new Product("short4","Short","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","Shorts",850));
        products.add(new Product("short1","Short","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","Shorts",350));
        products.add(new Product("short2","Short","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","Shorts",630));
        products.add(new Product("short3","Short","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","Shorts",550));
        products.add(new Product("short4","Short","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","Shorts",650));

    }
    private void loadSkirts(){
        products.add(new Product("skirt1","Skirts","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","Skirts",350));
        products.add(new Product("skirt2","Skirts","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","Skirts",650));
        products.add(new Product("skirt3","Skirts","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","Skirts",250));
        products.add(new Product("skirt4","Skirts","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","Skirts",850));
        products.add(new Product("skirt5","Skirts","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","Skirts",350));

    }
    private void loadTops(){
        products.add(new Product("top1","Top Name","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","Tops",500));
        products.add(new Product("top2","Top Name","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","Tops",400));
        products.add(new Product("top3","Top Name","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","Tops",300));
        products.add(new Product("top1","Top Name","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","Tops",250));
        products.add(new Product("top3","Top Name","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","Tops",650));
        products.add(new Product("top2","Top Name","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","Tops",450));

    }
    private void loadHats(){
        products.add(new Product("hat1","Hat Name","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","Hats",500));
        products.add(new Product("hat2","Hat Name","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","Hats",350));
        products.add(new Product("hat3","Hat Name","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","Hats",800));
        products.add(new Product("hat4","Hat Name","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","Hats",750));
        products.add(new Product("hat5","Hat Name","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.","Hats",750));

    }

    @Override
    public void onProductItemClick(Product product, int position) {

        Bundle bundle=new Bundle();
        Intent intent=new Intent(Category.this, ProductDetail.class);

        bundle.putString("NAME",product.getProdname());
        bundle.putString("ID",product.getId());
        bundle.putString("CATEGORY",product.getCategory());
        bundle.putString("DESCRIPTION",product.getDescription());
        bundle.putString("IMAGEURL",product.getImageUrl());
        bundle.putInt("PRICE",product.getPrice());
        intent.putExtras(bundle);

        startActivity(intent);
    }
}