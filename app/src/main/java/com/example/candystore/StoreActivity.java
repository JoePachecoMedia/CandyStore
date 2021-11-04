package com.example.candystore;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class StoreActivity extends AppCompatActivity {

    private DatabaseManager dbManager;
    private ScrollView scrollView;
    private double total;
    private int with;
    public final DecimalFormat MONEY = new DecimalFormat( "$#,##0.00" );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // content View
        setContentView(R.layout.activity_store);

        dbManager = new DatabaseManager( this );
        total = 0.0;
        scrollView = ( ScrollView ) findViewById( R.id.scrollView );
        Point size = new Point( );
        getWindowManager().getDefaultDisplay().getSize(size);
        with = size.x / 2;

        // update view
        updateView();
    }

    public void updateView( ) {

        ArrayList<Candy> candies = dbManager.selectAll( );

        if( candies.size( ) > 0 ) {
            // remove subviews inside scrollView if necessary
            scrollView.removeAllViewsInLayout( );

            GridLayout grid = new GridLayout(this);
            grid.setRowCount(candies.size());
            grid.setColumnCount(2);

            //create and array of candy buttons
            CandyButton[] buttons = new CandyButton[candies.size()];
            ButtonHandler bh = new ButtonHandler();



            // fill the grid
            int i = 0; String candyInfo;
            for ( Candy candy : candies ) {
                buttons[i] = new CandyButton(this, candy);
                candyInfo = candy.getName() + "\n" + MONEY.format(candy.getPrice());
                buttons[i].setText(candyInfo);
                buttons[i].setOnClickListener(bh);

                // add the button to grid
                grid.addView(buttons[i], with, GridLayout.LayoutParams.WRAP_CONTENT);
                i++;

            }

            // create a back button
            Button btnBack = new Button(this);
            btnBack.setText("Back");
            btnBack.setBackgroundColor(0xFF6200EE);
            btnBack.setTextColor(Color.WHITE);





            TextView emptyText = new TextView(this);
            grid.addView(emptyText,( int ) ( with ), ViewGroup.LayoutParams.WRAP_CONTENT );
            grid.addView( btnBack, ( int ) ( with ), ViewGroup.LayoutParams.WRAP_CONTENT );

            btnBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // close the activity
                    StoreActivity.this.finish();

                }
            });

            scrollView.addView(grid);
        }
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick( View v ) {
            // retrieve price of the candy and add it to total
            total += ((CandyButton) v).getPrice();
            String pay = NumberFormat.getCurrencyInstance().format(total);
            Toast.makeText(StoreActivity.this, "Total price is " + pay, Toast.LENGTH_LONG).show();


        }
    }

}