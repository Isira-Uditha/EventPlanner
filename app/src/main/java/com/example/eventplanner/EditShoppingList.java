package com.example.eventplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventplanner.Database.DBHelper;

public class EditShoppingList extends AppCompatActivity {

    Button button1;
    Button button4;
    ImageButton imageButton;

    TextView eSName, eSQty,eSPrice,eSNote;

    private DBHelper dbShopping;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shopping_list);
        getSupportActionBar().hide();

        context= this;
        dbShopping = new DBHelper(context);

        eSName = findViewById(R.id.idEditSName);
        eSQty = findViewById(R.id.idEditSQty);
        eSPrice =findViewById(R.id.idEditSPrice);
        eSNote =findViewById(R.id.idEditSNote);

        final String ids = getIntent().getStringExtra("ids");
        System.out.println( "Check xxxxxxxxx " + ids);
       ShoppingLists shoppingLists= dbShopping.getSingleShopping(Integer.parseInt(ids));

       eSName.setText(shoppingLists.getShoppingName());
        eSQty.setText(shoppingLists.getQty());
        eSPrice.setText(shoppingLists.getPrice());
        eSNote.setText(shoppingLists.getNote());

        //Update Shopping List Details
        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(EditShoppingList.this,UpdateShopping.class);
                intent.putExtra("ids",ids);
                Context context = getApplicationContext();
                CharSequence text ="Update Shopping Details";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();
                toast.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 10);
                startActivity(intent);
            }
        });





        //back button
        imageButton =(ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditShoppingList.this, ShoppingDetails.class);
                Context context = getApplicationContext();
                CharSequence text = context.getString(R.string.click_back);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();
                toast.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 10);
                startActivity(intent);
            }
        });
    }
}