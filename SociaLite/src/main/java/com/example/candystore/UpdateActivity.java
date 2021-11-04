
package com.example.candystore;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    DatabaseManager dbManager;

    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        // instance of DB Manager
        dbManager = new DatabaseManager(this);

        // update screen
        updateView();
    }

    // Build a View dynamically with all the candies
    //@SuppressWarnings("deprecation")
    @SuppressWarnings("deprecation")
    public void updateView( ) {

        // Get all candies form the db table
        ArrayList<Candy> candies = dbManager.selectAll();


        if( candies.size() > 0 ) {
            // create ScrollView and GridLayout
            ScrollView scrollView = new ScrollView( this );

            // Use gridLayout
            GridLayout grid = new GridLayout(this);
            // set columns and rows
            grid.setRowCount(candies.size());
            grid.setColumnCount(4);

            // create arrays of components
            TextView[] ids = new TextView[candies.size( )];
            EditText[][] namesAndPrices = new EditText[candies.size( )][2];
            Button[] buttons = new Button[candies.size( )];
            ButtonHandler bh = new ButtonHandler( );

            // retrieve width of screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;


            int i = 0;

            for ( Candy candy: candies ) {

                // create the TextView for the candy's id
                    ids[i] = new TextView( this );
                    ids[i].setGravity( Gravity.CENTER );
                    ids[i].setText("" + (i + 1));

                // set id here

                // create the two EditTexts for the candy's name and price
                // dreate EditText for both name and price
                    namesAndPrices[i][0] = new EditText(this);
                    namesAndPrices[i][1] = new EditText(this);
                    namesAndPrices[i][0].setText( candy.getName() );
                    namesAndPrices[i][1].setText( "" + candy.getPrice());
                    namesAndPrices[i][1].setInputType( InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    namesAndPrices[i][0].setId( 10 * candy.getId( ) );
                    namesAndPrices[i][1].setId( 10 * candy.getId( ) + 1 );

                    // create the button
                    buttons[i] = new Button( this );
                    buttons[i].setText( "Update" );
                    buttons[i].setId( candy.getId( ) );

                    // set up event handling
                    buttons[i].setOnClickListener( bh );

                    // add the elements to grid
                    grid.addView( ids[i], width / 10, ViewGroup.LayoutParams.WRAP_CONTENT );
                    grid.addView( namesAndPrices[i][0], ( int ) ( width * .4 ),
                            ViewGroup.LayoutParams.WRAP_CONTENT );
                    grid.addView( namesAndPrices[i][1], (int) ( width * .25 ),
                            ViewGroup.LayoutParams.WRAP_CONTENT );
                    grid.addView( buttons[i], ( int ) ( width * .25 ),
                            ViewGroup.LayoutParams.WRAP_CONTENT );

                    i++;
        }

        // create a back button
            Button backButton = new Button(this);
            backButton.setText("Back");
            backButton.setBackgroundColor(0xFF6200EE);
            backButton.setTextColor(Color.WHITE);

        //

        TextView emptyText = new TextView(this);
        grid.addView(emptyText,( int ) ( width / 10 ), ViewGroup.LayoutParams.WRAP_CONTENT );
        grid.addView( backButton, ( int ) ( width * .15 ),ViewGroup.LayoutParams.WRAP_CONTENT );

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // finish the current update activity
                UpdateActivity.this.finish();
            }
        });

        // Add views
            scrollView.addView(grid);
            setContentView(scrollView);
    }
}

private class ButtonHandler implements View.OnClickListener {
    public void onClick( View v ) {
        // retrieve name and price of the candy
        int candyId = v.getId();
        EditText nameET = findViewById(10 * candyId);
        EditText priceET = findViewById(10 * candyId + 1);

        String name = nameET.getText().toString();
        String priceString = priceET.getText().toString();

        // update candy in database
        try {
            Double price = Double.parseDouble(priceString);
            dbManager.updateById(candyId, name, price);

            Toast.makeText(UpdateActivity.this, name + " is updated", Toast.LENGTH_LONG).show();
            // update screen
            updateView();

        } catch( NumberFormatException nfe ) {
            Toast.makeText( UpdateActivity.this, "Price error", Toast.LENGTH_LONG ).show( );
        }

    }
}
}