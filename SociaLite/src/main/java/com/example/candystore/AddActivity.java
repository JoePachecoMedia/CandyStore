package com.example.candystore;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        dbManager = new DatabaseManager(this);

    }

    public void add (View v){

        EditText priceET = findViewById(R.id.input_price);
        String priceString = priceET.getText().toString();

        EditText nameET = findViewById(R.id.input_name);
        String nameString = nameET.getText().toString();


        try {
            double price = Double.parseDouble(priceString);
            Candy candy = new Candy(0,nameString,price);
            dbManager.insertCandy(candy);
            Toast.makeText(this,nameString + " is inserted to DB table", Toast.LENGTH_LONG).show();
        } catch (NumberFormatException ne) {
            Toast.makeText(this, "Price error", Toast.LENGTH_LONG).show();
        }

        priceET.setText("");
        nameET.setText("");

    }


    public void goBack (View v){
        this.finish();

    }

}
