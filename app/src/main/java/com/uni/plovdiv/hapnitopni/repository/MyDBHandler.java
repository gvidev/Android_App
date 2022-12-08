package com.uni.plovdiv.hapnitopni.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.uni.plovdiv.hapnitopni.entities.Products;
import com.uni.plovdiv.hapnitopni.entities.Users;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "hapniTopni.db";

    //table USERS info
    public static final String TABLE_USERS_NAME = "users";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_NAME = "name";
//    public static final String COLUMN_IS_CURRENT_USER = "isCurrentUser";

    //table PRODUCTS info
    public static final String TABLE_PRODUCTS_NAME = "products";
    public static final String COLUMN_PRODUCT_ID = "id";
    public static final String COLUMN_IMAGE_ID = "image_id";
    public static final String COLUMN_PRODUCT_NAME = "product_name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRICE = "price";

    //table ORDERS info
//    public static final String TABLE_ORDERS_NAME = "orders";
//    public static final String COLUMN_ORDER_ID = "id";
//    public static final String COLUMN_USER_TO_ORDERS_ID = "user_id";
//    public static final String COLUMN_PRODUCT_TO_ORDERS_ID = "product_ID";
//    public static final String COLUMN_QUANTITY = "quantity";



    public MyDBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + TABLE_USERS_NAME + "(" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EMAIL + " TEXT ," +
                COLUMN_PASSWORD + " TEXT ," +
                COLUMN_NAME + " TEXT " +
//                COLUMN_IS_CURRENT_USER + " TEXT " +
                ");";


        //if price is string could be easiest for retrieving data and put in the fragment using TextView
        String query2 = "CREATE TABLE " + TABLE_PRODUCTS_NAME + "(" +
                COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_IMAGE_ID + " INTEGER ," +
                COLUMN_PRODUCT_NAME + " TEXT ," +
                COLUMN_DESCRIPTION + " TEXT ," +
                COLUMN_PRICE + " TEXT " +
                ");";

//        String query3 = "CREATE TABLE " + TABLE_ORDERS_NAME + "(" +
//                COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                COLUMN_USER_TO_ORDERS_ID + " INTEGER ," +
//                COLUMN_PRODUCT_TO_ORDERS_ID + " INTEGER ," +
//                COLUMN_QUANTITY + " INTEGER " +
//                ");";

        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.execSQL(query2);
//        sqLiteDatabase.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS_NAME);
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS_NAME);
        onCreate(sqLiteDatabase);
    }

    //add a new product in table Products
    public void addProduct(Products product) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMAGE_ID, product.getImage());
        values.put(COLUMN_PRODUCT_NAME, product.getName());
        values.put(COLUMN_DESCRIPTION, product.getDescription());
        values.put(COLUMN_PRICE, product.getPrice());

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_PRODUCTS_NAME, null, values);
        db.close();
    }

    //check if already have a product with the same name
    public Boolean checkProductExist(Products product) {
        SQLiteDatabase db = getWritableDatabase();

        //rawQuery method ask for query and array where to put the selected info
        Cursor cursor = db.rawQuery("Select * from products where product_name = ?",
                new String[]{product.getName()});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    // we have created a new method for reading all the courses.
    public ArrayList<Products> allProducts() {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + TABLE_PRODUCTS_NAME;
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Products> productsArrayList= new ArrayList<>();

        // from first to last row in table products
        if (cursor.moveToFirst()) {
            do {
                //adding the data
                productsArrayList.add(new Products(cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)));
            } while (cursor.moveToNext());

        }

        cursor.close();
        //return ArrayList for easiest use
        return productsArrayList;
    }





    //add a new row to the table Users
    public Boolean Registration(Users user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());
//        values.put(COLUMN_IS_CURRENT_USER, bool);
        SQLiteDatabase db = getWritableDatabase();
        //db.insert returns type long
        long result = db.insert(TABLE_USERS_NAME, null, values);
        db.close();

        return result != -1;
    }

    //I use this for registration logic
    //this method check if the email already exists in the table users
    public Boolean checkRegistrationExist(Users user) {
        SQLiteDatabase db = getWritableDatabase();

        //rawQuery method ask for query and array where to put the selected info
        Cursor cursor = db.rawQuery("Select * from users where email = ?",
                new String[]{user.getEmail()});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    //Here I use this method for login purpose
    ////this method should check if the email and password was in the database
    public Boolean checkLoginParametersExist(Users user) {
        SQLiteDatabase db = getWritableDatabase();


        //rawQuery method ask for query and array where to put the selected info
        Cursor cursor = db.rawQuery("Select * from users where email = ? and password = ?",
                new String[]{user.getEmail(), user.getPassword()});

        return cursor.getCount() > 0;

    }

    public String[] getUserEmail(String user_id) {
        SQLiteDatabase db = getWritableDatabase();
        String email= "";
        String[] result = new String[1];
        Cursor cursor = db.rawQuery("Select email from users where id =" + user_id ,null);

        if(cursor.moveToFirst()){
            email += cursor.getString(0);

        }else{
            email = "not found";
        }

        result[0] = email;

        return result;
    }

    public String[] getUserName(String user_id) {
        SQLiteDatabase db = getWritableDatabase();
        String name= "";
        String[] result = new String[1];
        Cursor cursor = db.rawQuery("Select name from users where id =" + user_id ,null);

        if(cursor.moveToFirst()){
            name += cursor.getString(0);

        }else{
            name = "not found";
        }

        result[0] = name;

        return result;
    }

    public String getUserId(String email, String password) {
        SQLiteDatabase db = getWritableDatabase();
        String result = "";
        Cursor cursor = db.rawQuery("Select id from users where email = ? and password = ?",
                new String[]{email, password});

        if (cursor.moveToFirst()) {
                result = result + cursor.getString(0);
            } else {
                result = result + "result not found";
            }

        return result;
    }

}