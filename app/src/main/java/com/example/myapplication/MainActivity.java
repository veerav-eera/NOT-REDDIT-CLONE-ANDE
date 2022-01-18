package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabasePosts db = new DatabasePosts(this);
        List<Post> posts = db.getAllPosts();

        RecyclerView rv = (RecyclerView) findViewById(R.id.postList);
        rv.setAdapter(new PostAdapter(posts));
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

}