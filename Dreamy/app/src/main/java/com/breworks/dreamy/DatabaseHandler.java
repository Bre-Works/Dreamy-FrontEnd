package com.breworks.dreamy;

import java.util.ArrayList;
import java.util.List;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Luck Eater on 10/4/2014.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "dreamyDB";

    //account table name
    private static final String TABLE_ACCOUNTS = "Dreamy_account";

    //account table column names
    private static final String KEY_ACCOUNT = "accountName";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASS = "password";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    //Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + TABLE_ACCOUNTS + "(" + KEY_ACCOUNT +
                " TEXT PRIMARY KEY NOT NULL UNIQUE,"+ KEY_EMAIL + "TEST NOT NULL UNIQUE," +
                KEY_PASS + "TEXT NOT NULL" +")";
        db.execSQL(CREATE_ACCOUNTS_TABLE);
    }
    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS"+ TABLE_ACCOUNTS);

        //Create tables again
        onCreate(db);
    }

    //adding new account
    public void addAccount(Account account){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ACCOUNT,dreamyAccount.getAccount());
        values.put(KEY_EMAIL,dreamyAccount.getEmail());
        values.put(KEY_PASS,dreamyAccount.getPassword());

        //inserting row
        db.insert(TABLE_ACCOUNTS,null,values);
        db.close();//close database connection
    }

    //updating single account
    public int updateAccount(Account account){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ACCOUNT, dreamyAccount.getAccount());
        values.put(KEY_EMAIL,dreamyAccount.getEmail());
        values.put(KEY_PASS,dreamyAccount.getPassword());

        //updating row
        return db.update(TABLE_ACCOUNTS,values,KEY_ACCOUNT+ "=?",
                new String[]{String.valueOf(dreamyAccount.getAccount())});
    }

    //deleting single account
    public void deleteAccount(Account account){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACCOUNTS,KEY_ACCOUNT + "= ?",
                new String[]{String.valueOf(dreamyAccount.getAccount())});
        db.close();
    }





}

