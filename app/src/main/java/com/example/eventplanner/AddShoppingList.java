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

public class AddShoppingList extends AppCompatActivity {

    Button button1;
    ImageButton imageButton;

    EditText etASName, etASQty, etASPrice, etASNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shopping_list);
        //disable nagivation  bar
        getSupportActionBar().hide();

        etASName=findViewById(R.id.etASName);
        etASQty=findViewById(R.id.etASQty);
        etASPrice=findViewById(R.id.etASPrice);
        etASNote=findViewById(R.id.etASNote);


        //back button
        imageButton =(ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddShoppingList.this, BudgetOne.class);
                Context context = getApplicationContext();
                CharSequence text = context.getString(R.string.click_back);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();
                toast.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 10);
                startActivity(intent);


            }
        });

        //Afer adding list details
        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddShoppingList.this, BudgetDetails.class);
                Context context = getApplicationContext();
                CharSequence text = context.getString(R.string.adds_toast);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();
                toast.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 10);
                startActivity(intent);
            }
        });


    }
    public void addSData(View view){
        DBHelper dbHelper= new DBHelper(this);
        //long val= dbHelper.addSInfo(etASNamee.getText().toString(),etASQty.getText().toString(),etASPrice.getText().toString(),etASNote.getText().toString());

    }
}