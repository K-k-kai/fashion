package com.example.fashionicon;

import static Helpers.Constants.BUSINESS_SHORT_CODE;
import static Helpers.Constants.CALLBACKURL;
import static Helpers.Constants.PARTYB;
import static Helpers.Constants.PASSKEY;
import static Helpers.Constants.TRANSACTION_TYPE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Helpers.DatabaseHelper;
import Models.AccessToken;
import Models.CartItem;
import Models.STKPush;
import Service.DarajaApiClient;
import Service.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckOut extends AppCompatActivity {
    TextView amountTxt, taxTxt, deliveryTxt, totalAmountTxt;
    EditText addressEdt, mpesaNumberEdt;
    ImageButton backBtn;

    private DarajaApiClient mApiClient;
    private ProgressDialog mProgressDialog;
    Button proceedToPaymentBtn;
    private DatabaseHelper _dbHelper;
    private ArrayList<CartItem> _cartItems;
    Integer amount=0;
    Integer itemsQuantity=0;
    Integer totalAmount=0;
    Integer deliveryPrice=0;
    Double taxAmount=0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        initializeViews();
        _dbHelper= new DatabaseHelper(getApplicationContext());
        _cartItems= new ArrayList<>();
        mProgressDialog = new ProgressDialog(this);
        mApiClient = new DarajaApiClient();
        mApiClient.setIsDebug(true); //Set True to enable logging, false to disable.

        getCartItems();
        for (CartItem item:_cartItems) {
            System.out.println(item.toString());
            amount+= Integer.parseInt(item.getAmount());
            itemsQuantity +=Integer.parseInt(item.getQuantity());
        }
        amountTxt.setText("KSH "+amount.toString());
        setDeliveryCost(itemsQuantity);
        setTax(amount);
        setTotalAmount(amount,deliveryPrice,taxAmount);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(CheckOut.this, Cart.class);
                startActivity(intent);
                finish();
            }
        });
        proceedToPaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAccessToken();
                String location=addressEdt.getText().toString();
                String phoneNumber=mpesaNumberEdt.getText().toString();
                if (location.isEmpty()){
                    addressEdt.setError("Required");
                    return;
                }
                if (phoneNumber.isEmpty() || phoneNumber.length()<10){
                    mpesaNumberEdt.setError("Required");
                    return;
                }

               performSTKPush(phoneNumber,totalAmount.toString());

            }
        });
    }

    private void initializeViews(){

        backBtn=findViewById(R.id.backBtn);
        amountTxt= findViewById(R.id.amount);
        taxTxt=findViewById(R.id.tax);
        deliveryTxt= findViewById(R.id.deliveryCost);
        totalAmountTxt=findViewById(R.id.totalAmount);
        addressEdt = findViewById(R.id.address);
        mpesaNumberEdt=findViewById(R.id.editTextMpesaNumber);
        proceedToPaymentBtn=findViewById(R.id.button_make_payment);

    }
    private void  setDeliveryCost(int quantity){

        if (quantity <=10 )
        {
            deliveryPrice=100;
            deliveryTxt.setText("KSH "+deliveryPrice.toString());
        }
        else if (quantity >10 && quantity<20){
            deliveryPrice=170;
            deliveryTxt.setText("KSH "+deliveryPrice.toString());
        }
        else if (quantity >20 && quantity <30){
            deliveryPrice=220;
            deliveryTxt.setText("KSH "+deliveryPrice.toString());
        }
        else if (quantity >30 && quantity <50){
            deliveryPrice=290;
            deliveryTxt.setText("KSH "+deliveryPrice.toString());
        }
        else {
            deliveryPrice=360;
            deliveryTxt.setText("KSH "+deliveryPrice.toString());
        }

    }
    private  void setTax(int amount){
        taxAmount= amount*0.05;
        taxTxt.setText("KSH "+taxAmount.toString());
    }
    private void setTotalAmount( int amount, int deliveryCost, double tax){
        int total =amount+deliveryCost+ (int)Math.round(tax);
        totalAmount = total;
        totalAmountTxt.setText("KSH "+totalAmount);
    }
    public void performSTKPush(String phone_number,String amount) {
        mProgressDialog.setMessage("Processing Payment....");
        mProgressDialog.setTitle("Please Wait...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.show();
        String timestamp = Utils.getTimestamp();
        STKPush stkPush = new STKPush(
                BUSINESS_SHORT_CODE,
                Utils.getPassword(BUSINESS_SHORT_CODE, PASSKEY, timestamp),
                timestamp,
                TRANSACTION_TYPE,
                String.valueOf(amount),
                Utils.sanitizePhoneNumber(phone_number),
                PARTYB,
                Utils.sanitizePhoneNumber(phone_number),
                CALLBACKURL,
                "Fashion Icon", //Account reference
                "Testing"  //Transaction description
        );

        mApiClient.setGetAccessToken(false);

        //Sending the data to the Mpesa API, remember to remove the logging when in production.
        mApiClient.mpesaService().sendPush(stkPush).enqueue(new Callback<STKPush>() {
            @Override
            public void onResponse(@NonNull Call<STKPush> call, @NonNull Response<STKPush> response) {
                mProgressDialog.dismiss();
                try {
                    if (response.isSuccessful()) {
                        System.out.println("post submitted to API. %s "+response.body());

                        _dbHelper.clearCart();
                        Intent intent=new Intent(CheckOut.this, PaymentConfirmation.class);
                        startActivity(intent);
                        finish();
                    } else {
                        System.out.println("Response %s "+response.errorBody().string());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<STKPush> call, @NonNull Throwable t) {
                mProgressDialog.dismiss();
            }
        });
    }
    public void getAccessToken() {
        mApiClient.setGetAccessToken(true);
        mApiClient.mpesaService().getAccessToken().enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(@NonNull Call<AccessToken> call, @NonNull Response<AccessToken> response) {

                if (response.isSuccessful()) {
                    mApiClient.setAuthToken(response.body().accessToken);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AccessToken> call, @NonNull Throwable t) {

            }
        });
    }
    private void getCartItems(){
        Cursor res=_dbHelper.getCartItems();
        _cartItems.clear();
        if (res.getCount()==0){
            Toast.makeText(this,"No Cart Items",Toast.LENGTH_SHORT).show();
        }
        while(res.moveToNext()) {
            _cartItems.add(new CartItem(res.getString(0),res.getString(1),
                    res.getString(2),
                    res.getString(3), res.getString(4), res.getString(5)));

        }
    }
}