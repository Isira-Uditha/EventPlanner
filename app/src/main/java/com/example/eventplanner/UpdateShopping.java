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

public class UpdateShopping extends AppCompatActivity {

    ImageButton imageButton;
    Button button1;

    EditText eSName, eSQty,eSPrice,eSNote;
    private DBHelper dbShopping;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_shopping);

        context =this;
        dbShopping = new DBHelper(context);

        eSName = findViewById(R.id.idEditSName);
        eSQty = findViewById(R.id.idEditSQty);
        eSPrice =findViewById(R.id.idEditSPrice);
        eSNote =findViewById(R.id.idEditSNote);

       final String ids = getIntent().getStringExtra("ids");
        ShoppingLists shoppingLists= dbShopping.getSingleShopping(Integer.parseInt(ids));

        eSName.setText(shoppingLists.getShoppingName());
        eSQty.setText(shoppingLists.getQty());
        eSPrice.setText(shoppingLists.getPrice());
        eSNote.setText(shoppingLists.getNote());

        //visit to the back page
        imageButton = (ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateShopping.this, EditShoppingList.class);
                Context context = getApplicationContext();
                CharSequence text = context.getString(R.string.click_back);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();
                toast.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 10);
                startActivity(intent);
            }
        });

        //update Shopping Details
        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s_name= eSName.getText().toString();
                String s_qty= eSQty.getText().toString();
                String s_price= eSPrice.getText().toString();
                String s_note= eSNote.getText().toString();
                int id2 = Integer.parseInt(ids);

                ShoppingLists shoppingList = new ShoppingLists(id2,s_name,s_qty,s_price,s_note);
                int status = dbShopping.updateShopping(shoppingList);

                Intent intent = new Intent(UpdateShopping.this,ShoppingDetails.class);
                intent.putExtra("ids",ids);
                startActivity(intent);


            }
        });

    }
}