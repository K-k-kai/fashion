package com.example.fashionicon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.UUID;

import Helpers.DatabaseHelper;

public class Register extends AppCompatActivity {
EditText fnameEdt, lnameEdt,usernameEdt,passwordEdt, confirmPasswordEdt;
Button registerBtn;
TextView loginTxt;
DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeViews();
        databaseHelper=new DatabaseHelper(this);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname= fnameEdt.getText().toString();
                String lname= lnameEdt.getText().toString();
                String username= usernameEdt.getText().toString();
                String password= passwordEdt.getText().toString();
                String confirmPassword= confirmPasswordEdt.getText().toString();


                if (fname.isEmpty()){
                    fnameEdt.setError("Firstname is required\"");
                    return;
                }
                if (lname.isEmpty()){
                    lnameEdt.setError("Lastname is required");
                    return;
                }
                if (username.isEmpty()){
                    usernameEdt.setError("Username is required");
                    return;
                }
                if (password.length()<8){
                    passwordEdt.setError("Minimum Length is 8 characters");
                    return;
                }
                if (confirmPassword.isEmpty()){
                    confirmPasswordEdt.setError("required");
                    return;
                }
                if (!password.matches(confirmPassword)){
                    Toast.makeText(Register.this,"Passwords don't match",Toast.LENGTH_SHORT).show();
                    return;
                }


                String id= UUID.randomUUID().toString();
                boolean isRegistered=databaseHelper.registerUser(id,fname,lname,username,password);

                if (isRegistered){
                    Toast.makeText(Register.this,"Registration is successful",Toast.LENGTH_SHORT).show();
                    clearFields();

                    Intent intent= new Intent(Register.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(Register.this,"Registration failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
        loginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Register.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initializeViews(){
        fnameEdt=findViewById(R.id.fname);
        lnameEdt=findViewById(R.id.lname);
        usernameEdt=findViewById(R.id.username);
        passwordEdt=findViewById(R.id.password);
        confirmPasswordEdt=findViewById(R.id.confirmPassword);
        registerBtn=findViewById(R.id.registerBtn);
        loginTxt=findViewById(R.id.loginTxt);
    }
    private void clearFields(){
        fnameEdt.setText("");
        lnameEdt.setText("");
        usernameEdt.setText("");
        passwordEdt.setText("");
        confirmPasswordEdt.setText("");
    }
}