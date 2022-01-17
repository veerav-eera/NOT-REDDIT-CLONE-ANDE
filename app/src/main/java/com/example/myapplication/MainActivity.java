package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabasePosts db = new DatabasePosts(this);

        List<Post> posts = db.getAllPosts();
        String log = "";
        for (Post p : posts) {
            log += "post_id: " + p.getPost_id() + "\npost_title: " + p.getPost_title() + "\npost_content: " + p.getPost_content() + "\npost_likes: " + p.getPost_likes() + "\npost_dislikes: " + p.getPost_dislikes() + "\npost_creator: " + p.getPost_creator() + "\n\n";
        }

        TextView tv = (TextView) findViewById(R.id.textView3);
        tv.setText(log);
    }

}