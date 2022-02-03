package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabasePosts extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "NOT-REDDIT-CLONE-ANDE";
    private static final String DATABASE_TABLE = "post";
    private  String KEY_Title = "post_title" ;
    private String KEY_Content = "post_content";
    private String KEY_Likes = "post_likes";
    private  String KEY_Dislikes = "post_dislikes" ;
    private String KEY_Creator = "post_creator";

    public DatabasePosts(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
        this.getWritableDatabase();
    }

    // Creating Posts Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_POSTS_TABLE = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + " (" +
                "\"post_id\" INTEGER NOT NULL UNIQUE," +
                "\"post_title\" TEXT NOT NULL," +
                "\"post_content\" TEXT NOT NULL," +
                "\"post_likes\" INTEGER NOT NULL DEFAULT 0," +
                "\"post_dislikes\" INTEGER NOT NULL DEFAULT 0," +
                "\"post_creator\" INTEGER NOT NULL," +
                "PRIMARY KEY(\"post_id\" AUTOINCREMENT)" +
                ");";

        db.execSQL(CREATE_POSTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

        // Create tables again
        onCreate(db);
    }

    // code to get all posts in a list view
    public List<Post> getAllPosts() {
        List<Post> postList = new ArrayList<Post>();
        // Select All Query
        String selectQuery = "SELECT post.*, accounts.username FROM " + DATABASE_TABLE + " INNER JOIN accounts ON (accounts.user_id = post.post_creator) ORDER BY post_id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Post post = new Post();
                post.setPost_id(Integer.parseInt(cursor.getString(0)));
                post.setPost_title(cursor.getString(1));
                post.setPost_content(cursor.getString(2));
                post.setPost_likes(cursor.getInt(3));
                post.setPost_dislikes(cursor.getInt(4));
                post.setPost_creator(cursor.getString(6));

                // Adding contact to list
                postList.add(post);
            } while (cursor.moveToNext());
        } else {
            Log.d("NO DATA", "getAllPosts: NO DATA");
        }

        // return post list
        return postList;
    }

    public String createpost(String post_title, String post_content, String post_creator) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Title, post_title);
        values.put(KEY_Content, post_content);
        values.put(KEY_Likes, 0);
        values.put(KEY_Dislikes, 0);
        values.put(KEY_Creator, post_creator);

        // Inserting Row
        db.insert(DATABASE_TABLE, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection

        return "done";
    }
}
