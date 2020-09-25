package com.example.eventplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.eventplanner.Database.DBHelper;

public class AddBudget extends AppCompatActivity {

    Button button1;

    EditText etABName,etABPaidA,etABAmount,etABNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_budget);

        etABName=findViewById(R.id.etABName);
        etABPaidA = findViewById(R.id.etABPaidA);
        etABAmount =findViewById(R.id.etABAmount);
        etABNote = findViewById(R.id.etABNote);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Budget Details");

    }
    public void addData(View view){
        DBHelper dbHelper = new DBHelper(this);
        long val= dbHelper.addInfo(etABName.getText().toString(),etABPaidA.getText().toString(),etABAmount.getText().toString(),etABNote.getText().toString());

        if (val>0){
            Intent intent = new Intent(AddBudget.this , BudgetDetails.class);
            Toast.makeText(this, "New Budget Details inserted", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Data not inserted", Toast.LENGTH_SHORT).show();
        }
    }

    //back button
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){

            Intent intent = new Intent(AddBudget.this,BudgetDetails.class);

            Context context = getApplicationContext();
            CharSequence text = "Nothing Added";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
            toast.show();

            startActivity(intent);

        }
        return true;
    }

}