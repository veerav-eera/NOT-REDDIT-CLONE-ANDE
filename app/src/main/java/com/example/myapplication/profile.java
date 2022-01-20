package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        SharedPreferences account = getApplicationContext().getSharedPreferences("Account_details", MODE_PRIVATE);
        String userid = account.getString("userid", "");
        Log.d("userid", "onCreate: "+userid);
        DatabaseAccounts db = new DatabaseAccounts(this);
        Account userinfo = db.getaccount(userid);
        Log.d("Userename", "onCreate: "+userinfo.getUsername());
        Log.d("email", "onCreate: "+userinfo.getEmail());
        TextView email = (TextView) findViewById(R.id.emailtextview);
        email.setText(userinfo.getEmail());
        TextView username = (TextView) findViewById(R.id.nametextview);
        username.setText(userinfo.getUsername());

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
