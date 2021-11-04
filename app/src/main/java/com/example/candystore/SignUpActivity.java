package com.example.candystore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        dbManager = new DatabaseManager(this);

    }

    public void signUp(View v) {

        EditText emailET = findViewById(R.id.input_email);
        String emailString = emailET.getText().toString();

        EditText passET = findViewById(R.id.input_email);
        String passString = passET.getText().toString();


        EditText fnameET = findViewById(R.id.input_fname);
        String fnameString = fnameET.getText().toString();


        EditText lnameET = findViewById(R.id.input_lname);
        String lnameString = lnameET.getText().toString();


        User user = new User(emailString, passString, fnameString, lnameString);
        dbManager.insertUser(user);
        Toast.makeText(this, fnameString + " is inserted to DB table", Toast.LENGTH_LONG).show();

        Intent intentStore = new Intent(SignUpActivity.this, StoreActivity.class);
        startActivity(intentStore);
    }


    public void goBack (View v){
        this.finish();

    }

}