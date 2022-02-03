package com.example.myapplication;

import com.example.myapplication.Account;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccounts extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "NOT-REDDIT-CLONE-ANDE";
    private static final String DATABASE_TABLE = "accounts";
    private String KEY_NAME = "username";
    private String KEY_Emial = "email";
    private String KEY_Password = "password";

    public DatabaseAccounts(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Accounts Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table ONLY when it does not exist
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + " (" +
                "\"user_id\" INTEGER NOT NULL UNIQUE," +
                "\"username\" TEXT NOT NULL UNIQUE," +
                "\"password\" TEXT NOT NULL," +
                "\"email\" TEXT NOT NULL UNIQUE," +
                "PRIMARY KEY(\"user_id\" AUTOINCREMENT)" +
                ");";

        db.execSQL(CREATE_ACCOUNTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

        // Create tables again
        onCreate(db);
    }

    // code to get all accounts in a list view
    public List<Account> getAllAccounts() {
        List<Account> accountList = new ArrayList<Account>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DATABASE_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Account account = new Account();
                account.setUser_id(Integer.parseInt(cursor.getString(0)));
                account.setUsername(cursor.getString(1));
                account.setPassword(cursor.getString(2));
                account.setEmail(cursor.getString(3));

                // Adding contact to list
                accountList.add(account);
            } while (cursor.moveToNext());
        }

        // return contact list
        return accountList;
    }


    public String checkLogin(String username, String password) {
        Log.d("Username", "checkLogin: " + username);
        String selectQuery = "SELECT  user_id FROM " + DATABASE_TABLE + " WHERE username ='" + username + "'and password = '" + password + "'";
        String logincheck = "failed";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //user_id
        String user_id = "";
        if (cursor.moveToFirst()) {
            do {
                Log.d("hello", "checkLogin: " + cursor.getString(0));
                user_id = cursor.getString(0);
            } while (cursor.moveToNext());
        }

        if (user_id != "") {
            logincheck = user_id;
        }
        return logincheck;


    }

    public Account getaccount(String userid) {
        Account loggedin = new Account();
        Log.d("Username", "checkLogin: " + userid);
        String selectQuery = "SELECT username,email FROM " + DATABASE_TABLE + " WHERE user_id ='" + userid + "'";
        String logincheck = "failed";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //user_id
        if (cursor.moveToFirst()) {
            do {
                Log.d("hello", "checkLogin: " + cursor.getString(0));
                Log.d("Insert Data : ", "");
                Log.d("Insert Data : ", "");
                Log.d("Insert Data : ", "");
                Log.d("Insert Data : ", "");
                Log.d("Insert Data : ", "");
                loggedin.setUsername(cursor.getString(0));
                loggedin.setEmail(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return loggedin;
    }

    public String createuser(String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, username);
        values.put(KEY_Emial, email);
        values.put(KEY_Password, password);

        // Inserting Row
        db.insert(DATABASE_TABLE, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection

        return "done";

    }
}

