package com.example.davidbezalellaoli.mwa14.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.davidbezalellaoli.mwa14.R;

public class notes extends AppCompatActivity {

    EditText userInput;
    TextView recordsTextView;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        this.setTitle("Notes");

        userInput = (EditText) findViewById(R.id.user_Input);
        recordsTextView = (TextView) findViewById(R.id.records_TextView);
        /* Can pass nulls because of the constants in the helper.
         * the 1 means version 1 so don't run update.
         */
        dbHandler = new MyDBHandler(this, null, null, 1);
        printDatabase();
    }

    //Print the database
    public void printDatabase(){
        String dbString = dbHandler.databaseToString();
        recordsTextView.setText(dbString);
        userInput.setText("");
    }

    //add your elements onclick methods.
    //Add a product to the database
    public void addButtonClicked(View view){
        // dbHandler.add needs an object parameter.
        Products product = new Products(userInput.getText().toString());
        dbHandler.addProduct(product);
        printDatabase();
    }

    //Delete items
    public void deleteButtonClicked(View view){
        // dbHandler delete needs string to find in the db
        String inputText = userInput.getText().toString();
        dbHandler.deleteProduct(inputText);
        printDatabase();
    }

    public void OCBack(View view){
        onBackPressed();
    }

}
