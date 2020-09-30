package com.example.eventplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class BudgetOne extends AppCompatActivity {


    Button button1;
    Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_one);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Budget Information");

        //getSupportActionBar().hide();


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
                Intent intent = new Intent(BudgetOne.this,ShoppingDetails.class);
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



    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){

            Intent intent = new Intent(BudgetOne.this,EventHome.class);
            SharedPreferences prf = getSharedPreferences("eid",MODE_PRIVATE);
            String eid = prf.getString("eid", "No ID");
            intent.putExtra("id",eid);

            /*Context context = getApplicationContext();
            CharSequence text = context.getString(R.string.g_toast_redirect);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
            toast.show();*/

            startActivity(intent);

        }
        return true;
    }
}