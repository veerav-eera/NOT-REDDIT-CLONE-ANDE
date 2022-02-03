package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register2);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createaccount:
                DatabaseAccounts db = new DatabaseAccounts(this);
                Log.d("Insert Data : ", "Inserting ..");

                EditText name = (EditText) findViewById(R.id.username2);
                EditText Email = (EditText) findViewById(R.id.UserEmail2);
                EditText password = (EditText) findViewById(R.id.userpassword2);

                db.createuser(name.getText().toString(), Email.getText().toString(), password.getText().toString());

                Intent i = new Intent(this, login.class);
                startActivity(i);
                break;
            case R.id.Gobacktologin:
                i = new Intent(this, login.class);
                startActivity(i);
                break;
        }
    }
}
