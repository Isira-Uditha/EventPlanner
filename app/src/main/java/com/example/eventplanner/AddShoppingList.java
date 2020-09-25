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

public class AddShoppingList extends AppCompatActivity {

    Button button1;
    ImageButton imageButton;

    EditText etASName, etASQty, etASPrice, etASNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shopping_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Shopiing List");

        etASName=findViewById(R.id.etASName);
        etASQty=findViewById(R.id.etASQty);
        etASPrice=findViewById(R.id.etASPrice);
        etASNote=findViewById(R.id.etASNote);


    }
    public void addSData(View view){
        DBHelper dbHelper= new DBHelper(this);
        long val= dbHelper.addSLInfo(etASName.getText().toString(),etASQty.getText().toString(),etASPrice.getText().toString(),etASNote.getText().toString());

        if(val>0){
            Intent intent = new Intent(AddShoppingList.this,ShoppingDetails.class);
            Toast.makeText(this, "New Shopping List Details inserted", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Shopping Details not inserted", Toast.LENGTH_SHORT).show();
        }
    }
    //back button
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){

            Intent intent = new Intent(AddShoppingList.this,ShoppingDetails.class);

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