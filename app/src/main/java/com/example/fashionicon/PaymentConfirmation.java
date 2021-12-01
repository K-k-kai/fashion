package com.example.fashionicon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaymentConfirmation extends AppCompatActivity {
Button okayBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_confirmation);
        okayBtn=findViewById(R.id.okayBtn);
        okayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(PaymentConfirmation.this, Menu.class);
                startActivity(intent);
                finish();
            }
        });
    }
}