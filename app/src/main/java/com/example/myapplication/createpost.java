package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class createpost extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createpost);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createPost:
                SharedPreferences account = getApplicationContext().getSharedPreferences("Account_details", MODE_PRIVATE);
                String userid = account.getString("userid", "");
                EditText title = (EditText) findViewById(R.id.createTitle);
                EditText content = (EditText) findViewById(R.id.createContent);
                DatabasePosts db = new DatabasePosts(this);
                db.createpost(title.getText().toString(), content.getText().toString(), userid);
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;

//                DatabaseAccounts db = new DatabaseAccounts(this);
//                Log.d("Insert Data : ", "Inserting ..");
//                EditText name = (EditText) findViewById(R.id.username);
//                EditText password = (EditText) findViewById(R.id.userpassword);
//                String x = db.checkLogin(name.getText().toString(), password.getText().toString());
//                Log.d("Insert Data : ", "");
//                Log.d("Insert Data : ", "");
//                Log.d("Insert Data : ", "");
//                Log.d("Insert Data : ", "");
//                Log.d("Insert Data : ", "");
//                if (x != "failed") {
//                    SharedPreferences account = getSharedPreferences("Account_details", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = account.edit();
//                    Log.d("TAG", "onCreate: "+x);
//                    editor.putString("userid", x);
//                    editor.commit();
//                    Intent i1 = new Intent(this, MainActivity.class);
//                    startActivity(i1);
//                    break;
//                } else if (x == "failed") {
//                    TextView textView = (TextView) findViewById(R.id.errortext);
//                    textView.setText(x);
//                }
        }
    }
}
