package com.breworks.dreamy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.breworks.dreamy.model.Dream;
import com.breworks.dreamy.model.Todo;
import com.breworks.dreamy.model.dreamyAccount;
import com.breworks.dreamy.model.milestone;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    private static final String DREAM_NAME = "dreaming";
    private static final String DREAM_STATUS = "dreamstatus";

    // Account Table - column names
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String ACCOUNT_NAME = "AccountName";

    // Milestone Table - column names
    private static final String MILESTONE_NAME = "milestoneName";
    private static final String MILESTONE_STATUS = "milestoneStatus";

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

    // Dream table create statement
    private static final String CREATE_TABLE_DREAM = "CREATE TABLE "
            + TABLE_DREAM + "(" + KEY_ID + " INTEGER PRIMARY KEY," + DREAM_NAME
            + " TEXT," + DREAM_STATUS + " INTEGER," + CREATED_AT
            + " DATETIME" + ")";

    // Account table create statement
    private static final String CREATE_TABLE_ACCOUNT = "CREATE TABLE "
            + TABLE_ACCOUNT + "(" + KEY_ID + " INTEGER PRIMARY KEY," + ACCOUNT_NAME
            + " TEXT," + EMAIL + " TEXT," + PASSWORD + " TEXT," + CREATED_AT
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
    // Table Milestone To do Connector  create statement
    private static final String CREATE_TABLE_MILESTONE_TODO = "CREATE TABLE "
            + TABLE_MILESTONE_TODO + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + MILESTONE_ID_2 + " INTEGER," + TODO_ID + " INTEGER,"
            + CREATED_AT + " DATETIME" + ")";

    // Table Dream Account Connector Create Statements
    private static final String CREATE_TABLE_DREAM_ACCOUNT = "CREATE TABLE "
            + TABLE_ACCOUNT_DREAM + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + ACCOUNT_ID + " INTEGER," + DREAM_ID + " INTEGER,"
            + CREATED_AT + " DATETIME" + ")";

    //Table Dream Milestone Create Statements.
    private static final String CREATE_TABLE_DREAM_MILESTONE = "CREATE TABLE "
            + TABLE_DREAM_MILESTONE + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + DREAM_ID_2 + " INTEGER," + MILESTONE_ID + " INTEGER,"
            + CREATED_AT + " DATETIME" + ")";

    //constructor
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

// ------------------------ "todos" table methods ----------------//

    /*
     * Creating a to do
     */
    public long createToDo(Todo todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TODO_NAME, todo.getName());
        values.put(TODO_STATUS, todo.getStatus());
        values.put(CREATED_AT, getDateTime());

        // insert row
        long todo_id = db.insert(TABLE_TODO, null, values);

        return todo_id;
    }

    // Fetching single To do
    // With ID
    public Todo getTodowithID(long todo_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_TODO + " WHERE "
                + KEY_ID + " = " + todo_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Todo td = new Todo();
        td.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        td.setName((c.getString(c.getColumnIndex(TODO_NAME))));
        td.setCreatedAt(c.getString(c.getColumnIndex(CREATED_AT)));

        return td;
    }

    // With Name
    public Todo getTodowithName(String todo_name) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_TODO + " WHERE "
                + TODO_NAME + " = " + todo_name;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Todo td = new Todo();
        td.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        td.setName((c.getString(c.getColumnIndex(TODO_NAME))));
        td.setCreatedAt(c.getString(c.getColumnIndex(CREATED_AT)));

        return td;
    }

    /*
     * getting all todos
     * */
    public List<Todo> getAllToDos() {
        List<Todo> todos = new ArrayList<Todo>();
        String selectQuery = "SELECT  * FROM " + TABLE_TODO;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Todo td = new Todo();
                td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                td.setName((c.getString(c.getColumnIndex(TODO_NAME))));
                td.setCreatedAt(c.getString(c.getColumnIndex(CREATED_AT)));

                // adding to to do list
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }

   /*
    * getting all todos under single milestone
    * */
    public List<Todo> getAllToDosByMilestone(String milestone_name) {
        List<Todo> todos = new ArrayList<Todo>();

        String selectQuery = "SELECT  * FROM " + TABLE_TODO + " td, "
                + TABLE_MILESTONE + " ms, " + TABLE_MILESTONE_TODO + " mt WHERE ms."
                + MILESTONE_NAME + " = '" + milestone_name + "'" + " AND ms." + KEY_ID
                + " = " + "mt." + MILESTONE_ID_2 + " AND td." + KEY_ID + " = "
                + "mt." + TODO_ID;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Todo td = new Todo();
                td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                td.setName((c.getString(c.getColumnIndex(TODO_NAME))));
                td.setCreatedAt(c.getString(c.getColumnIndex(CREATED_AT)));

                // adding to to do list
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }

    /*
     * Updating a to do
     */
    public int updateToDo(Todo todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TODO_NAME, todo.getName());
        values.put(TODO_STATUS, todo.getStatus());

        // updating row
        return db.update(TABLE_TODO, values, KEY_ID + " = ?",
                new String[] { String.valueOf(todo.getId()) });
    }

    /*
     * Deleting a to do
     */
    public void deleteToDo(long todo_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TODO, KEY_ID + " = ?",
                new String[] { String.valueOf(todo_id) });
    }

// ------------------------ "dreams" table methods ----------------//

    /*
     * Creating a dream
     */
    public long createDream(Dream dream) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DREAM_NAME, dream.getName());
        values.put(DREAM_STATUS, dream.getStatus());
        values.put(CREATED_AT, getDateTime());

        // insert row
        long dream_id = db.insert(TABLE_DREAM, null, values);

        // assigning tags to to do
       /* for (long milestone_ids : milestone_id) {
            createDreamMilestone(dream_id, milestone_ids);
        }
        */
        return dream_id;
    }

    // Fetching single Dream
    // With ID
    public Dream getDreamwithID(long dream_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_DREAM + " WHERE "
                + KEY_ID + " = " + dream_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Dream dr = new Dream();
        dr.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        dr.setName((c.getString(c.getColumnIndex(DREAM_NAME))));
        dr.setCreatedAt(c.getString(c.getColumnIndex(CREATED_AT)));

        return dr;
    }

    // With Name
    public Dream getDreamwithName(String dream_name) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_DREAM + " WHERE "
                + DREAM_NAME + " = " + dream_name;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Dream dr = new Dream();
        dr.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        dr.setName((c.getString(c.getColumnIndex(DREAM_NAME))));
        dr.setCreatedAt(c.getString(c.getColumnIndex(CREATED_AT)));

        return dr;
    }

    /*
     * getting all dreams
     * */
    public List<Dream> getAllDreams() {
        List<Dream> dreams = new ArrayList<Dream>();
        String selectQuery = "SELECT * FROM " + TABLE_DREAM;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Dream dr = new Dream();
                dr.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                dr.setName((c.getString(c.getColumnIndex(DREAM_NAME))));
                dr.setStatus((c.getInt(c.getColumnIndex(DREAM_STATUS))));
                dr.setCreatedAt(c.getString(c.getColumnIndex(CREATED_AT)));

                // adding to dreams list
                dreams.add(dr);
            } while (c.moveToNext());
        }

        return dreams;
    }

    /*
     * getting all dreams under single accounts
     * */
    public List<Dream> getAllDreamsByAccount(String account_name) {
        List<Dream> dreams = new ArrayList<Dream>();

        String selectQuery = "SELECT  * FROM " + TABLE_DREAM + " dr, "
                + TABLE_ACCOUNT + " ac, " + TABLE_ACCOUNT_DREAM + " ad WHERE ac."
                + ACCOUNT_NAME + " = '" + account_name + "'" + " AND ac." + KEY_ID
                + " = " + "ad." + ACCOUNT_ID + " AND dr." + DREAM_ID + " = "
                + "ad." + DREAM_ID_2;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Dream dr = new Dream();
                dr.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                dr.setName((c.getString(c.getColumnIndex(TODO_NAME))));
                dr.setCreatedAt(c.getString(c.getColumnIndex(CREATED_AT)));

                // adding to dreams
                dreams.add(dr);
            } while (c.moveToNext());
        }
        return dreams;
    }

    /*
     * Updating a dream
     */
    public int updateDreams(Dream dream) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DREAM_NAME, dream.getName());
        values.put(DREAM_STATUS, dream.getStatus());

        // updating row
        return db.update(TABLE_DREAM, values, KEY_ID + " = ?",
                new String[] { String.valueOf(dream.getId()) });
    }

    /*
     * Deleting a dream
     */
    public void deleteDreams(long dream_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DREAM, KEY_ID + " = ?",
                new String[] { String.valueOf(dream_id) });
    }

    /*
     * Deleting all dreams
     */
    public void deleteAllDreams() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DREAM,null,null);
    }

// ------------------------ "Milestones" table methods ----------------//

    /*
     * Creating a milestone
     */
    public long createMilestone(milestone miles) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MILESTONE_NAME, miles.getName());
        values.put(MILESTONE_STATUS, miles.getStatus());
        values.put(CREATED_AT, getDateTime());

        // insert row
        long dream_id = db.insert(TABLE_DREAM, null, values);

        // assigning tags to to do
       /* for (long milestone_ids : milestone_id) {
            createDreamMilestone(dream_id, milestone_ids);
        }
        */
        return dream_id;
    }

    // Fetching single Dream
    // With ID
    public milestone getMilestonewithID(long miles_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_MILESTONE + " WHERE "
                + KEY_ID + " = " + miles_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        milestone ms = new milestone();
        ms.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        ms.setName((c.getString(c.getColumnIndex(MILESTONE_NAME))));
        ms.setStatus((c.getInt(c.getColumnIndex(MILESTONE_STATUS))));
        ms.setCreatedAt(c.getString(c.getColumnIndex(CREATED_AT)));

        return ms;
    }

    // With Name
    public milestone getMilestonewithName(String miles_name) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_MILESTONE + " WHERE "
                + MILESTONE_NAME + " = " + miles_name;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        milestone ms = new milestone();
        ms.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        ms.setName((c.getString(c.getColumnIndex(MILESTONE_NAME))));
        ms.setStatus((c.getInt(c.getColumnIndex(MILESTONE_STATUS))));
        ms.setCreatedAt(c.getString(c.getColumnIndex(CREATED_AT)));

        return ms;
    }

    /*
     * getting all milestones under single dreams
     * */

     public List<milestone> getAllMilestonesByDreams(String dream_name) {
        List<milestone> miles = new ArrayList<milestone>();

        String selectQuery = "SELECT  * FROM " + TABLE_MILESTONE + " ms, "
                + TABLE_DREAM + " dr, " + TABLE_DREAM_MILESTONE + " dm WHERE dr."
                + DREAM_NAME + " = '" + dream_name + "'" + " AND dr." + KEY_ID
                + " = " + "dm." + DREAM_ID_2 + " AND ms." + MILESTONE_ID + " = "
                + "dm." + MILESTONE_ID;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                milestone ms = new milestone();
                ms.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                ms.setName((c.getString(c.getColumnIndex(MILESTONE_NAME))));
                ms.setStatus((c.getInt(c.getColumnIndex(MILESTONE_STATUS))));
                ms.setCreatedAt(c.getString(c.getColumnIndex(CREATED_AT)));

                // adding to dreams
                miles.add(ms);
            } while (c.moveToNext());
        }
        return miles;
    }

    /*
     * Updating a milestone
     */
    public int updateMilestone(milestone miles) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MILESTONE_NAME, miles.getName());
        values.put(MILESTONE_STATUS, miles.getStatus());

        // updating row
        return db.update(TABLE_MILESTONE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(miles.getId()) });
    }

    /*
     * Deleting a milestone
     */
    public void deleteMilestone(long miles_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MILESTONE, KEY_ID + " = ?",
                new String[] { String.valueOf(miles_id) });
    }


// ------------------------ "accounts" table methods ----------------//

    /*
     * Creating an Accounts
     */
    public long createAccounts(dreamyAccount acc) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ACCOUNT_NAME, acc.getUserID());
        values.put(EMAIL, acc.getUserID());
        values.put(PASSWORD, acc.getPassword());
        values.put(CREATED_AT, getDateTime());

        // insert row
        long acc_id = db.insert(TABLE_ACCOUNT, null, values);

        return acc_id;
    }

    // Fetching single Account
    // With ID
    public dreamyAccount getAccountwithID(long acc_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_ACCOUNT + " WHERE "
                + KEY_ID + " = " + acc_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        dreamyAccount acc = new dreamyAccount();
        acc.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        acc.setEmail((c.getString(c.getColumnIndex(EMAIL))));
        acc.setUserID((c.getString(c.getColumnIndex(ACCOUNT_NAME))));
        acc.setPassword((c.getString(c.getColumnIndex(PASSWORD))));
        acc.setCreatedAt(c.getString(c.getColumnIndex(CREATED_AT)));

        return acc;
    }

    // With Name
    public dreamyAccount getAccountwithUsername(String acc_name) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_ACCOUNT + " WHERE "
                + ACCOUNT_NAME + " = " + acc_name;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        dreamyAccount acc = new dreamyAccount();
        acc.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        acc.setEmail((c.getString(c.getColumnIndex(EMAIL))));
        acc.setUserID((c.getString(c.getColumnIndex(ACCOUNT_NAME))));
        acc.setPassword((c.getString(c.getColumnIndex(PASSWORD))));
        acc.setCreatedAt(c.getString(c.getColumnIndex(CREATED_AT)));

        return acc;
    }

    /*
     * getting all accounts
     * */
    public List<dreamyAccount> getAllAccounts() {
        List<dreamyAccount> accounts = new ArrayList<dreamyAccount>();
        String selectQuery = "SELECT  * FROM " + TABLE_ACCOUNT;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                dreamyAccount acc = new dreamyAccount();
                acc.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                acc.setUserID((c.getString(c.getColumnIndex(ACCOUNT_NAME))));
                acc.setEmail((c.getString(c.getColumnIndex(EMAIL))));
                acc.setPassword((c.getString(c.getColumnIndex(PASSWORD))));
                acc.setCreatedAt(c.getString(c.getColumnIndex(CREATED_AT)));

                // adding to dreams list
                accounts.add(acc);
            } while (c.moveToNext());
        }

        return accounts;
    }

    /*
     * Updating a accounts
     */
    public int updateAccounts(dreamyAccount account) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ACCOUNT_NAME,account.getUserID());
        values.put(EMAIL, account.getEmail());
        values.put(PASSWORD,account.getPassword());

        // updating row
        return db.update(TABLE_ACCOUNT, values, KEY_ID + " = ?",
                new String[] { String.valueOf(account.getId()) });
    }

    /*
     * Deleting a account
     */
    public void deleteAccount(long acc_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACCOUNT, KEY_ID + " = ?",
                new String[] { String.valueOf(acc_id) });
    }

// ------------------------ "dreams_milestone" table methods ----------------//

    /*
     * Creating dream_milestone
     */
    public long createDreamMilestone(long dream_id, long milestone_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DREAM_ID_2, dream_id);
        values.put(MILESTONE_ID, milestone_id);
        values.put(CREATED_AT, getDateTime());

        long id = db.insert(TABLE_DREAM_MILESTONE, null, values);

        return id;
    }

    /**
     * Deleting a dream_milestone
     */
    public void deleteDreamMilestone(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DREAM_MILESTONE, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    /**
     * get datetime
     */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }


}