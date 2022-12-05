package com.uni.plovdiv.hapnitopni.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.uni.plovdiv.hapnitopni.entities.Users;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "hapniTopni.db";

    //table USER info
    public static final String TABLE_USERS_NAME = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_NAME = "name";
//    public static final String COLUMN_IS_CURRENT_USER = "isCurrentUser";


    public MyDBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + TABLE_USERS_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EMAIL + " TEXT ," +
                COLUMN_PASSWORD + " TEXT ," +
                COLUMN_NAME + " TEXT " +
//                COLUMN_IS_CURRENT_USER + " TEXT " +
                ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS_NAME);
        onCreate(sqLiteDatabase);
    }


    //add a new row to the table Users
    public Boolean Registration(Users user) {
        String bool = "false";
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());
//        values.put(COLUMN_IS_CURRENT_USER, bool);
        SQLiteDatabase db = getWritableDatabase();
        //db.insert returns type long
        long result = db.insert(TABLE_USERS_NAME, null, values);
        db.close();

        if (result == -1)
            return false;
        else
            return true;
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


        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

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