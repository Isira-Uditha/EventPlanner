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

public class UpdateBudget extends AppCompatActivity {


    Button button1;
    Button button3;

    EditText eBName, eBPadiA, eBAmount, eBNote;
    TextView eB;

    private DBHelper dbBudget;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_budget);
        String title = getIntent().getExtras().getString("title");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Budget :" + title);

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



        //Update Budget Details
        button3 = (Button)findViewById(R.id.button1);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputValidatorHelper inputValidatorHelper = new InputValidatorHelper();

                boolean allowSave = true;

                if(inputValidatorHelper.isNullOrEmpty(eBName.getText().toString())){

                    // errMsg.append("Task Name Should not be Empty.\n");
                    Toast.makeText(context, "Budget Name Should not be Empty", Toast.LENGTH_SHORT).show();
                    allowSave = false;

                }


                if(inputValidatorHelper.isNullOrEmpty(eBAmount.getText().toString())){

                    // errMsg.append("Task Name Should not be Empty.\n");
                    Toast.makeText(context, "Total Amount Should not be Empty", Toast.LENGTH_SHORT).show();
                    allowSave = false;

                }

                if(!inputValidatorHelper.isNumeric(eBAmount.getText().toString())){

                    // errMsg.append("Task Name Should not be Empty.\n");
                    Toast.makeText( context,"Enter only numeric values", Toast.LENGTH_SHORT).show();
                    allowSave = false;

                }


                if(inputValidatorHelper.isNullOrEmpty(eBPadiA.getText().toString())){

                    // errMsg.append("Task Name Should not be Empty.\n");
                    Toast.makeText(context, "Paid Amount Should not be Empty", Toast.LENGTH_SHORT).show();
                    allowSave = false;

                }

                if(!inputValidatorHelper.isNumeric(eBPadiA.getText().toString())){

                    // errMsg.append("Task Name Should not be Empty.\n");
                    Toast.makeText(context, "Enter only numeric values", Toast.LENGTH_SHORT).show();
                    allowSave = false;

                }


                String b_name = eBName.getText().toString();
                String b_pamount = eBPadiA.getText().toString();
                String b_amount = eBAmount.getText().toString();
                String b_note = eBNote.getText().toString();
                int id1 = Integer.parseInt(id);

                System.out.println(b_name +", " + b_amount+ ", " + b_pamount  +  ", " + id1);

                Budgets budget = new Budgets(id1,b_name,b_pamount,b_amount,b_note);

                if(allowSave) {

                    int status = dbBudget.updateBudget(budget);
                    //System.out.println("xxxxxxxxxxxxxx" + status);
                    startActivity(new Intent(context, BudgetDetails.class));

                }


                /*Intent intent = new Intent(UpdateBudget.this, BudgetDetails.class);
                Context context = getApplicationContext();
                CharSequence text = context.getString(R.string.ebu_up_toast);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();
                toast.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 10);
                startActivity(intent);*/
            }
        });

    }
    //back button
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){

            Intent intent = new Intent(UpdateBudget.this,EditBudget.class);
            String title = getIntent().getExtras().getString("title");
            final String id1 = getIntent().getStringExtra("id");
            intent.putExtra("id",id1);
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