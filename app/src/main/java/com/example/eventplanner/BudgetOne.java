package com.example.eventplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class BudgetOne extends AppCompatActivity {

    ImageButton imageButton;
    Button button1;
    Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_one);
        //disable nagivation  bar
        getSupportActionBar().hide();

        //visit to the back page
        imageButton = (ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BudgetOne.this,MainActivity.class);
                startActivity(intent);
            }
        });
        //Visit to the budget details' page
        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BudgetOne.this,BudgetDetails.class);

                startActivity(intent);
            }
        });

        //Visit to the Add shopping page
        button2 = (Button)findViewById(R.id.buttonAS);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BudgetOne.this,AddShoppingList.class);
                        Context context = getApplicationContext();
                        CharSequence text = context.getString(R.string.asl_toast);
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context,text,duration);
                        toast.show();
                        toast.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 10);
                        startActivity(intent);
                    }
                });
    }
}