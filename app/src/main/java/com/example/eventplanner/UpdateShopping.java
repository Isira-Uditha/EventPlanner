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
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventplanner.Database.DBHelper;

public class UpdateShopping extends AppCompatActivity {


    Button button1;
    TextView eSN;

    EditText eSName, eSQty,eSPrice,eSNote;
    private DBHelper dbShopping;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_shopping);
        String title = getIntent().getExtras().getString("title");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Shopping List : " + title);

        context =this;
        dbShopping = new DBHelper(context);


        eSName = findViewById(R.id.idEditSName);
        eSQty = findViewById(R.id.idEditSQty);
        eSPrice =findViewById(R.id.idEditSPrice);
        eSNote =findViewById(R.id.idEditSNote);

       final String ids = getIntent().getStringExtra("ids");
        ShoppingLists shoppingLists= dbShopping.getSingleShopping(Integer.parseInt(ids));

        //eSN.setText(shoppingLists.getShoppingName());
        eSName.setText(shoppingLists.getShoppingName());
        eSQty.setText(shoppingLists.getQty());
        eSPrice.setText(shoppingLists.getPrice());
        eSNote.setText(shoppingLists.getNote());



        //update Shopping Details
        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputValidatorHelper inputValidatorHelper = new InputValidatorHelper();

                boolean allowSave = true;

                if(inputValidatorHelper.isNullOrEmpty(eSName.getText().toString())){

                    // errMsg.append("Task Name Should not be Empty.\n");
                    Toast.makeText(context, "Shipping Name Should not be Empty", Toast.LENGTH_SHORT).show();
                    allowSave = false;

                }


                if(inputValidatorHelper.isNullOrEmpty(eSQty.getText().toString())){

                    // errMsg.append("Task Name Should not be Empty.\n");
                    Toast.makeText(context, "Quantity Should not be Empty", Toast.LENGTH_SHORT).show();
                    allowSave = false;

                }

                if(!inputValidatorHelper.isNumeric(eSQty.getText().toString())){

                    // errMsg.append("Task Name Should not be Empty.\n");
                    Toast.makeText( context,"Enter only numeric values", Toast.LENGTH_SHORT).show();
                    allowSave = false;

                }


                if(inputValidatorHelper.isNullOrEmpty(eSPrice.getText().toString())){

                    // errMsg.append("Task Name Should not be Empty.\n");
                    Toast.makeText(context, "Price Should not be Empty", Toast.LENGTH_SHORT).show();
                    allowSave = false;

                }

                if(!inputValidatorHelper.isNumeric(eSPrice.getText().toString())){

                    // errMsg.append("Task Name Should not be Empty.\n");
                    Toast.makeText(context, "Enter only numeric values", Toast.LENGTH_SHORT).show();
                    allowSave = false;

                }

                String s_name= eSName.getText().toString();
                String s_qty= eSQty.getText().toString();
                String s_price= eSPrice.getText().toString();
                String s_note= eSNote.getText().toString();
                int id2 = Integer.parseInt(ids);

                ShoppingLists shoppingList = new ShoppingLists(id2,s_name,s_qty,s_price,s_note);

                if(allowSave) {

                    int status = dbShopping.updateShopping(shoppingList);
                    Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(UpdateShopping.this, ShoppingDetails.class);
                    String title = getIntent().getExtras().getString("title");
                    final String ids = getIntent().getStringExtra("id");
                    intent.putExtra("id", id2);
                    intent.putExtra("title", title);
                    startActivity(intent);

                }
            }
        });


    }
    //back button
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int ids = item.getItemId();

        if(ids == android.R.id.home){

            Intent intent = new Intent(UpdateShopping.this,EditShoppingList.class);
            String title = getIntent().getExtras().getString("title");
            final String id2 = getIntent().getStringExtra("ids");
            intent.putExtra("ids",id2);
            intent.putExtra("title",title);

            Context context = getApplicationContext();
            CharSequence text = "Nothing Updated";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
            toast.show();

            startActivity(intent);

        }
        return true;
    }
}