package com.example.candystore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        dbManager = new DatabaseManager(this);

    }

    public void signIn(View v) {
        Intent intentStore = new Intent(SignInActivity.this, StoreActivity.class);
        startActivity(intentStore);
    }


    public void goBack (View v){
        this.finish();

    }

}
