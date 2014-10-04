package com.breworks.dreamy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ryan on 04/10/2014.
 */

public class DBHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "DreamyDB";

    // Table Names
    private static final String TABLE_DREAM = "dream";
    private static final String TABLE_ACCOUNT = "dreamy_account";
    private static final String TABLE_MILESTONE = "milestone";
    private static final String TABLE_TODO = "todolist";

    // Connector Tables
    private static final String TABLE_ACCOUNT_DREAM = "account_dream";
    private static final String TABLE_DREAM_MILESTONE = "dream_milestone";
    private static final String TABLE_MILESTONE_TODO = "milestone_todo";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String CREATED_AT = "created_at";

    // Dream Table - column names
    private static final String DREAM_NAME = "dream";
    private static final String DREAM_STATUS = "dream";

    // Account Table - column names
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String ACCOUNT_NAME = "AccountName";

    // Milestone Table - column names
    private static final String MILESTONE_NAME = "milestoneName";
    private static final String MILESTONE_STATUS = "status";

    // To Do List Table - column names
    private static final String TODO_NAME = "todo";
    private static final String TODO_STATUS = "todo_status";

    // Dream - Account Connect Tables
    private static final String DREAM_ID = "dream_id";
    private static final String ACCOUNT_ID = "account_id";

    // Dream - Milestone Connect Tables
    private static final String DREAM_ID_2 = "dream_id";
    private static final String MILESTONE_ID = "milestone_id";

    // Milestone - To do Connect Tables
    private static final String MILESTONE_ID_2 = "milestone_id";
    private static final String TODO_ID = "todo_id";

    // Table Create Statements

    // Dream table create statement
    private static final String CREATE_TABLE_DREAM = "CREATE TABLE "
            + TABLE_DREAM + "(" + KEY_ID + " INTEGER PRIMARY KEY," + DREAM_NAME
            + " TEXT," + DREAM_STATUS + " INTEGER," + CREATED_AT
            + " DATETIME" + ")";

    // Account table create statement
    private static final String CREATE_TABLE_ACCOUNT = "CREATE TABLE "
            + TABLE_ACCOUNT + "(" + KEY_ID + " INTEGER PRIMARY KEY," + ACCOUNT_NAME
            + " TEXT," + EMAIL +" TEXT,"+ PASSWORD + " TEXT," + CREATED_AT
            + " DATETIME" + ")";

    // Milestone table create statement
    private static final String CREATE_TABLE_MILESTONE = "CREATE TABLE "
            + TABLE_MILESTONE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + MILESTONE_NAME
            + " TEXT," + MILESTONE_STATUS + " INTEGER," + CREATED_AT
            + " DATETIME" + ")";

    // Milestone table create statement
    private static final String CREATE_TABLE_TODO = "CREATE TABLE "
            + TABLE_TODO + "(" + KEY_ID + " INTEGER PRIMARY KEY," + TODO_NAME
            + " TEXT," + TODO_STATUS + " INTEGER," + CREATED_AT
            + " DATETIME" + ")";

    // Connector Create Statement

    // todo_tag table create statement
    private static final String CREATE_TABLE_DREAM_ACCOUNT = "CREATE TABLE "
            + TABLE_ACCOUNT_DREAM + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + ACCOUNT_ID + " INTEGER," + DREAM_ID + " INTEGER,"
            + CREATED_AT + " DATETIME" + ")";

    // todo_tag table create statement
    private static final String CREATE_TABLE_DREAM_MILESTONE = "CREATE TABLE "
            + TABLE_DREAM_MILESTONE + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + DREAM_ID_2 + " INTEGER," + MILESTONE_ID + " INTEGER,"
            + CREATED_AT + " DATETIME" + ")";

    // todo_tag table create statement
    private static final String CREATE_TABLE_MILESTONE_TODO = "CREATE TABLE "
            + TABLE_MILESTONE_TODO + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + MILESTONE_ID_2 + " INTEGER," + TODO_ID + " INTEGER,"
            + CREATED_AT + " DATETIME" + ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_TODO);
        db.execSQL(CREATE_TABLE_ACCOUNT);
        db.execSQL(CREATE_TABLE_DREAM);
        db.execSQL(CREATE_TABLE_MILESTONE);
        db.execSQL(CREATE_TABLE_DREAM_ACCOUNT);
        db.execSQL(CREATE_TABLE_DREAM_MILESTONE);
        db.execSQL(CREATE_TABLE_MILESTONE_TODO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DREAM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MILESTONE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DREAM_MILESTONE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MILESTONE_TODO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT_DREAM);

        // create new tables
        onCreate(db);
    }