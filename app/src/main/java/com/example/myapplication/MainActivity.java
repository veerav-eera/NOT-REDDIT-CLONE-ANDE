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

        DatabaseAccounts db = new DatabaseAccounts(this);
        TextView list = findViewById(R.id.list);
        List<Account> accountList = db.getAllAccounts();
        String log = "";
        for (Account cn : accountList) {
            log += "user_id: " + cn.getUser_id() + " , username: " + cn.getUsername() + " , Email: " +
                    cn.getEmail() + "\n";
        }

        list.setText(log);
    }

}