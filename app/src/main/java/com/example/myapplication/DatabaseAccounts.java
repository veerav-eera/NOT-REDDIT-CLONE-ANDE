package com.example.myapplication;

import com.example.myapplication.Account;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccounts extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "NOT-REDDIT-CLONE-ANDE";
    private static final String DATABASE_TABLE = "accounts";

    public DatabaseAccounts(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Accounts Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String selectQuery = "SELECT  * FROM " + DATABASE_TABLE;
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Create table ONLY when it does not exist
        if (!cursor.moveToFirst()) {
            String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + DATABASE_TABLE + " (" +
                    "\"user_id\" INTEGER NOT NULL UNIQUE," +
                    "\"username\" TEXT NOT NULL UNIQUE," +
                    "\"password\" TEXT NOT NULL," +
                    "\"email\" TEXT NOT NULL UNIQUE," +
                    "PRIMARY KEY(\"user_id\" AUTOINCREMENT)" +
                    ");";

            db.execSQL(CREATE_ACCOUNTS_TABLE);
        }
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

    //code to create a account
    public void Createaccounts(){

    }
}

