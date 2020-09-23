package com.example.eventplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventplanner.Database.DBHelper;

public class EventHome extends AppCompatActivity {

    Button btn;
    Button button;


    TextView btot, bpaid, bamount ;
    private DBHelper dbHelper;
    private int overTotal = 0 ;
    private  int overPaid=0;

    private  int overDue= 0;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn = (Button) findViewById(R.id.button4);

        //visit to the budget page
        button = (Button) findViewById(R.id.button9);


        btot = findViewById(R.id.editTextTextPersonName11);
        bpaid = findViewById(R.id.editTextTextPersonName12);
        bamount = findViewById(R.id.editTextTextPersonName13);

        context = this;
         dbHelper = new DBHelper(context);

        overTotal = dbHelper.sumBudget();
        btot.setText(String.valueOf(overTotal));
        System.out.println( "Summmmmmmmmmmmmmmmmmmmmm " + overTotal);

        overPaid = dbHelper.sumBPaid();
        bpaid.setText(String.valueOf(overPaid));

        overDue = overTotal- overPaid ;
        bamount.setText(String.valueOf(overDue));


    }

    @Override
    protected void onResume() {
        super.onResume();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventHome.this,GuestHome.class);

                Context context = getApplicationContext();
                CharSequence text = context.getString(R.string.g_toast_click);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
                toast.show();

                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventHome.this,BudgetOne.class);
                startActivity(intent);
            }
        });
    }
}