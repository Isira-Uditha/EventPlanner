package com.example.eventplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventplanner.Database.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ShoppingDetails extends AppCompatActivity {

    ImageButton imageButton;
    Button button;
    ImageView imageView;

    TextView stot , sspent, sremaining;

    private ListView shoppingView;
    Context context;
    private DBHelper dbHelper;
    private List<ShoppingLists> sls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_details);
        //disable nagvi. Bar
        //getSupportActionBar().hide();


        context = this;
        dbHelper = new DBHelper(context);

        shoppingView = (ListView)findViewById(R.id.idShoppingView);
        sls = new ArrayList<>();
        sls = dbHelper.readAllShoppings();
        System.out.println("fffffff" + sls);

        ShoppingAdapter adapter = new ShoppingAdapter(context,R.layout.single_shopping,sls);
        shoppingView.setAdapter(adapter);

        //visit to the back page
      /*  imageButton = (ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShoppingDetails.this,BudgetOne.class);
                Context context = getApplicationContext();
                CharSequence text = context.getString(R.string.click_back);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();
                toast.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 10);
                startActivity(intent);
            }
        });*/

        //Add Shopping
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShoppingDetails.this, AddShoppingList.class);
                Context context = getApplicationContext();
                CharSequence text = context.getString(R.string.bd_toast);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();
                toast.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 10);
                startActivity(intent);
            }
        });
    }
}