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

public class EditBudget extends AppCompatActivity {


    Button button1;
    Button button3;

    TextView eBName, eBPadiA, eBAmount, eBNote;
    TextView eB;

    private DBHelper dbBudget;
    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_budget);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String title = getIntent().getExtras().getString("title");
        getSupportActionBar().setTitle("Budget : " + title);


        context =this;
        dbBudget = new DBHelper(context);

        //eB= findViewById(R.id.nb);
        eBName=findViewById(R.id.ideditBudgetName);
        eBPadiA = findViewById(R.id.idEditPaidAmount);
        eBAmount =findViewById(R.id.idEditAmount);
        eBNote = findViewById(R.id.idEditNote);

        final String id = getIntent().getStringExtra("id");
        System.out.println( "Check xxxxxxxxx " + id);
        Budgets budgets = dbBudget.getSingleBudget(Integer.parseInt(id));

        //eB.setText(budgets.getBudgetName());
        eBName.setText(budgets.getBudgetName());
        eBPadiA.setText(budgets.getPadiAmount());
        eBAmount.setText(budgets.getAmount());
        eBNote.setText(budgets.getNote());
        System.out.println(id);


        //Update Budget Details
        button3 = (Button)findViewById(R.id.button1);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = getIntent().getExtras().getString("title");
                Intent intent = new Intent(EditBudget.this, UpdateBudget.class);
                intent.putExtra("id",id);
                intent.putExtra("title",title);
                Context context = getApplicationContext();
                CharSequence text = context.getString(R.string.ebu_up_toast);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();
                toast.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 10);
                startActivity(intent);
            }
        });


    }

    //back button
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){

            Intent intent = new Intent(EditBudget.this,BudgetDetails.class);
            startActivity(intent);

        }
        return true;
    }
}