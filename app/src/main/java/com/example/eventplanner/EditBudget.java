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
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventplanner.Database.DBHelper;

public class EditBudget extends AppCompatActivity {

    ImageButton imageButton;
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
        getSupportActionBar().hide();

        //visit to the back page
        imageButton = (ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditBudget.this, BudgetDetails.class);
                Context context = getApplicationContext();
                CharSequence text = context.getString(R.string.click_back);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();
                toast.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 10);
                startActivity(intent);
            }
        });

        context =this;
        dbBudget = new DBHelper(context);

        eB= findViewById(R.id.nb);
        eBName=findViewById(R.id.ideditBudgetName);
        eBPadiA = findViewById(R.id.idEditPaidAmount);
        eBAmount =findViewById(R.id.idEditAmount);
        eBNote = findViewById(R.id.idEditNote);

        final String id = getIntent().getStringExtra("id");
        Budgets budgets = dbBudget.getSingleBudget(Integer.parseInt(id));

        eB.setText(budgets.getBudgetName());
        eBName.setText(budgets.getBudgetName());
        eBPadiA.setText(budgets.getPadiAmount());
        eBAmount.setText(budgets.getAmount());
        eBNote.setText(budgets.getNote());
        System.out.println(id);

        /*
        //Delete Budget
        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditBudget.this, BudgetDetails.class);
                Context context = getApplicationContext();
                CharSequence text = context.getString(R.string.ebd_dle_toast);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();
                toast.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 10);
                startActivity(intent);
            }
        });*/

        //Update Budget Details
        button3 = (Button)findViewById(R.id.button1);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditBudget.this, UpdateBudget.class);
                intent.putExtra("id",id);
                Context context = getApplicationContext();
                CharSequence text = context.getString(R.string.ebu_up_toast);
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
                Intent intent = new Intent(EditBudget.this, BudgetDetails.class);
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