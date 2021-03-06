package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        SharedPreferences account = getApplicationContext().getSharedPreferences("Account_details", MODE_PRIVATE);
        String userid = account.getString("userid", "");
        Log.d("userid", "onCreate: " + userid);
        DatabaseAccounts db = new DatabaseAccounts(this);
        Account userinfo = db.getaccount(userid);
        Log.d("Userename", "onCreate: " + userinfo.getUsername());
        Log.d("email", "onCreate: " + userinfo.getEmail());
        TextView email = (TextView) findViewById(R.id.emailtextview);
        email.setText(userinfo.getEmail());
        TextView username = (TextView) findViewById(R.id.nametextview);
        username.setText(userinfo.getUsername());

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
            case R.id.createPost:
                // Do Something
                Intent j = new Intent(this, createpost.class);
                startActivity(j);
                return true;
            case R.id.pref:
                Intent k = new Intent(this, MainActivity.class);
                startActivity(k);
                return true;
        }
        return false;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logoutbtn:
                SharedPreferences account = getApplicationContext().getSharedPreferences("Account_details", MODE_PRIVATE);
                SharedPreferences.Editor editor = account.edit();
                editor.remove("userid");
                Intent i = new Intent(this, login.class);
                startActivity(i);
                break;
        }
    }
}
