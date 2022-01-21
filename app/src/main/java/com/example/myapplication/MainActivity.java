package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences account = getApplicationContext().getSharedPreferences("Account_details", MODE_PRIVATE);

        DatabasePosts db = new DatabasePosts(this);
        List<Post> posts = db.getAllPosts();

        RecyclerView rv = (RecyclerView) findViewById(R.id.postList);
        rv.setAdapter(new PostAdapter(posts, account.getString("userid", ""), this));
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                // Do Something
                Intent i = new Intent(this, profile.class);
                startActivity(i);
                return true;
            case R.id.help:
                // Do Something
                Toast.makeText(getApplicationContext(), "Help...", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.pref:
                // Do Something
                Toast.makeText(getApplicationContext(), "Preference...", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }
}