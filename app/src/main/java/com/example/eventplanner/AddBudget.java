package com.example.eventplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.eventplanner.Database.DBHelper;

public class AddBudget extends AppCompatActivity {

    ImageButton imageButton;
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

        //disable nagivation  bar
        getSupportActionBar().hide();

        //visit to the back page
        imageButton = (ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddBudget.this, BudgetDetails.class);
                Context context = getApplicationContext();
                CharSequence text = context.getString(R.string.click_back);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();
                toast.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 10);
                startActivity(intent);
            }
        });
        //add information
        //button1 =(Button)findViewById(R.id.button1);
        /*button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddBudget.this,BudgetDetails.class);
                Context context = getApplicationContext();
                CharSequence text = context.getString(R.string.adde_toast);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();
                toast.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 10);
                startActivity(intent);
            }
        });*/



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

}