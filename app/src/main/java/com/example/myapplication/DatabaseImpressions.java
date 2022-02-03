package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseImpressions extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "NOT-REDDIT-CLONE-ANDE";
    private static final String DATABASE_TABLE = "impressions";

    public DatabaseImpressions(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
        this.getWritableDatabase();
    }

    // Creating Posts Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_IMPRESSIONS_TABLE = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + " (" +
                "\"impression_id\" INTEGER NOT NULL UNIQUE," +
                "\"user_id\" INTEGER NOT NULL," +
                "\"post_id\" INTEGER NOT NULL," +
                "\"impression_status\" INTEGER DEFAULT NULL," +
                "PRIMARY KEY(\"impression_id\" AUTOINCREMENT)," +
                "FOREIGN KEY(\"post_id\") REFERENCES \"post\"(\"post_id\")," +
                "FOREIGN KEY(\"user_id\") REFERENCES \"accounts\"(\"user_id\")" +
                ");";

        db.execSQL(CREATE_IMPRESSIONS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

        // Create tables again
        onCreate(db);
    }

    // code to modify likes under condition
    public void modifyLikes(int post_idINT, int user_idINT) {
        String post_id = Integer.toString(post_idINT);
        String user_id = Integer.toString(user_idINT);

        SQLiteDatabase db = this.getWritableDatabase();
        String SQLQuery = "SELECT * FROM " + DATABASE_TABLE + " WHERE post_id = ? AND user_id = ?";

        Cursor cursor = db.rawQuery(SQLQuery, new String[]{post_id, user_id});

        if (!cursor.moveToFirst()) {
            Log.d("FIRSTLIKE", "modifyLikes: FIRST TIME LIKING");
            // if user is first time liking
            SQLQuery = "INSERT INTO " + DATABASE_TABLE + "(user_id, post_id, impression_status) VALUES (?, ?, '1')";
            db.execSQL(SQLQuery, new String[]{user_id, post_id});

            SQLQuery = "UPDATE post SET post_likes = post_likes + 1 WHERE post_id = ?";
            db.execSQL(SQLQuery, new String[]{post_id});

        } else if (cursor.getInt(3) == 0) {
            Log.d("DISLIKETOLIKE", "modifyLikes: DISLIKETOLIKE");
            // If user has disliked before and is now pressing like
            SQLQuery = "UPDATE " + DATABASE_TABLE + " SET impression_status = '1' WHERE user_id = ? AND post_id = ?";
            db.execSQL(SQLQuery, new String[]{user_id, post_id});

            SQLQuery = "UPDATE post SET post_dislikes = post_dislikes - 1, post_likes = post_likes + 1 WHERE post_id = ?";
            db.execSQL(SQLQuery, new String[]{post_id});

        } else {
            Log.d("LIKEREMOVE", "modifyLikes: LIKEREMOVE");
            // If user has liked before, remove the like
            SQLQuery = "DELETE FROM impressions WHERE user_id = ? AND post_id = ?";
            db.execSQL(SQLQuery, new String[]{user_id, post_id});

            SQLQuery = "UPDATE post SET post_likes = post_likes - 1 WHERE post_id = ?";
            db.execSQL(SQLQuery, new String[]{post_id});

        }

        cursor.close();
    }

    // code to modify dislikes under condition
    public void modifyDislikes(int post_idINT, int user_idINT) {
        String post_id = Integer.toString(post_idINT);
        String user_id = Integer.toString(user_idINT);

        SQLiteDatabase db = this.getWritableDatabase();
        String SQLQuery = "SELECT * FROM " + DATABASE_TABLE + " WHERE post_id = ? AND user_id = ?";

        Cursor cursor = db.rawQuery(SQLQuery, new String[]{post_id, user_id});

        if (!cursor.moveToFirst()) {
            Log.d("FIRSTDISLIKE", "modifyLikes: FIRST TIME DISLIKING");
            // if user is first time disliking
            SQLQuery = "INSERT INTO " + DATABASE_TABLE + "(user_id, post_id, impression_status) VALUES (?, ?, '0')";
            db.execSQL(SQLQuery, new String[]{user_id, post_id});

            SQLQuery = "UPDATE post SET post_dislikes = post_dislikes + 1 WHERE post_id = ?";
            db.execSQL(SQLQuery, new String[]{post_id});

        } else if (cursor.getInt(3) == 1) {
            Log.d("LIKETODISLIKE", "modifyLikes: LIKETODISLIKE");
            // If user has liked before and is now pressing dislike
            SQLQuery = "UPDATE " + DATABASE_TABLE + " SET impression_status = '0' WHERE user_id = ? AND post_id = ?";
            db.execSQL(SQLQuery, new String[]{user_id, post_id});

            SQLQuery = "UPDATE post SET post_likes = post_likes - 1, post_dislikes = post_dislikes + 1 WHERE post_id = ?";
            db.execSQL(SQLQuery, new String[]{post_id});

        } else {
            Log.d("DISLIKEREMOVE", "modifyLikes: DISLIKEREMOVE");
            // If user has disliked before, remove the dislike
            SQLQuery = "DELETE FROM impressions WHERE user_id = ? AND post_id = ?";
            db.execSQL(SQLQuery, new String[]{user_id, post_id});

            SQLQuery = "UPDATE post SET post_dislikes = post_dislikes - 1 WHERE post_id = ?";
            db.execSQL(SQLQuery, new String[]{post_id});

        }

        cursor.close();
    }
}
