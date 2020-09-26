package com.example.eventplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
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


    Button button;
    ImageView imageView;

    TextView stot , sspent, sremaining;

    private ListView shoppingView;
    Context context;
    private DBHelper dbHelper;
    private List<ShoppingLists> sls;

    private  int sOverTot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_details);
        //disable nagvi. Bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Shopping Info");
        //getSupportActionBar().hide();


        context = this;
        dbHelper = new DBHelper(context);

        stot = findViewById(R.id.tvSTot);

        shoppingView = (ListView)findViewById(R.id.idShoppingView);
        sls = new ArrayList<>();
        SharedPreferences prf = getSharedPreferences("eid",MODE_PRIVATE);
        String eid = prf.getString("eid", "No ID");
        sls = dbHelper.readAllShoppings(eid);
        System.out.println("fffffff" + sls);

        sOverTot =dbHelper.sumShopping(eid);
        stot.setText(String.valueOf(sOverTot));

        ShoppingAdapter adapter = new ShoppingAdapter(context,R.layout.single_shopping,sls);
        shoppingView.setAdapter(adapter);



        //Add Shopping
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShoppingDetails.this, AddShoppingList.class);
                Context context = getApplicationContext();
                CharSequence text = "Now you can Shooping Details";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();
                toast.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 10);
                startActivity(intent);
            }
        });
    }
    //visit to the back page
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){

            Intent intent = new Intent(ShoppingDetails.this,BudgetOne.class);

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