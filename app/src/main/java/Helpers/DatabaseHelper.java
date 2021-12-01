package Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context)
    {
        super(context, "FIcon.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("Create Table Users(id TEXT primary key, fname TEXT, lname TEXT, username TEXT, password TEXT)");
        DB.execSQL("Create Table CartItems(id TEXT  primary key, name TEXT , url TEXT, quantity TEXT,amount TEXT, price TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Users");
        DB.execSQL("drop Table if exists CartItems");
    }

    public Boolean registerUser(String id, String fname, String lname, String username, String password){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("id",id);
        contentValues.put("fname",fname);
        contentValues.put("lname",lname);
        contentValues.put("username",username);
        contentValues.put("password",password);

        long result=DB.insert("Users",null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }

    }
    public  Cursor loginUser(String username, String password){
        SQLiteDatabase DB=this.getWritableDatabase();

        Cursor cursor=DB.rawQuery("Select * from Users where username=? and password=?", new String[]{username, password});
        return cursor;
    }

    //Cart
    public Boolean insertCartItem(String id, String name, String url, String quantity, String amount, String price){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("id",id);
        contentValues.put("name",name);
        contentValues.put("url",url);
        contentValues.put("quantity",quantity);
        contentValues.put("amount",amount);
        contentValues.put("price",price);

        long result=DB.insert("CartItems",null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }

    }
    //Getting Cart Items
    public Cursor getCartItems(){
        SQLiteDatabase DB=this.getWritableDatabase();

        Cursor cursor=DB.rawQuery("Select * from CartItems", null);
        return cursor;
    }

    public  Cursor getCartItem(String id){
        SQLiteDatabase DB=this.getWritableDatabase();

        Cursor cursor=DB.rawQuery("Select * from CartItems where id=?", new String[]{id});
        return cursor;
    }
    public Boolean updateCartItem(String id, String name, String quantity, String amount, String price){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("quantity",quantity);
        contentValues.put("amount",amount);
        contentValues.put("price",price);

        Cursor cursor=DB.rawQuery("Select * from CartItems where id=?", new String[]{id});
        if(cursor.getCount()>0) {
            long result = DB.update("CartItems", contentValues, "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    //Deleting cart Item
    public Boolean deleteCartItem(String id){
        SQLiteDatabase DB=this.getWritableDatabase();

        Cursor cursor=DB.rawQuery("Select * from CartItems where id=?", new String[]{id});
        if(cursor.getCount()>0) {
            long result = DB.delete("CartItems", "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public  void clearCart(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from CartItems");
        db.close();
    }
}
