package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.loginbtn:
                DatabaseAccounts db = new DatabaseAccounts(this);
                Log.d("Insert Data : ", "Inserting ..");
                EditText name = (EditText) findViewById(R.id.username);
                EditText password = (EditText) findViewById(R.id.userpassword);
                String x = db.checkLogin(name.getText().toString(), password.getText().toString());
                Log.d("Insert Data : ", "");
                Log.d("Insert Data : ", "");
                Log.d("Insert Data : ", "");
                Log.d("Insert Data : ", "");
                Log.d("Insert Data : ", "");
                if (x == "login"){
                    Intent i = new Intent(this, MainActivity.class);
                    startActivity(i);
                    break;
                }else if( x == "failed"){
                    TextView textView =  (TextView) findViewById(R.id.errortext);
                    textView.setText(x);
                }

        }
    }
}
