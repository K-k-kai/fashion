package com.example.fashionicon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Helpers.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
EditText usernameEdt, passwordEdt;
TextView registerTxt;
Button loginBtn;
DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        databaseHelper=new DatabaseHelper(this);

        registerTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=usernameEdt.getText().toString();
                String pass=passwordEdt.getText().toString();
                if (username.isEmpty()){
                    usernameEdt.setError("Required");
                    return;
                }
                if (pass.isEmpty()){
                    passwordEdt.setError("Required");
                    return;
                }
                Cursor result=databaseHelper.loginUser(username, pass);
                if (result.getCount()==1){

                    Intent intent= new Intent(MainActivity.this,Menu.class);
                    startActivity(intent);
                    finish();
                }
                else
                 Toast.makeText(MainActivity.this,"Incorrect Username or Password",Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void initializeViews(){
        usernameEdt=findViewById(R.id.usernameEdt);
        passwordEdt=findViewById(R.id.loginPasswordEdt);
        registerTxt=findViewById(R.id.registerTxt);
        loginBtn=findViewById(R.id.loginBtn);
    }
}