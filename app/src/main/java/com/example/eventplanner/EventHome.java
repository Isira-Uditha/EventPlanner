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

//<<<<<<< Kasuni
import android.widget.ProgressBar;

import android.widget.TextView;
import android.widget.Toast;
//import android.widget.TextView;
//import android.widget.Toast;

import com.example.eventplanner.Database.DBGuest;
import com.example.eventplanner.Database.GuestModel;

import com.example.eventplanner.Database.DBHelper;
import com.example.eventplanner.Database.Task;

import java.sql.SQLOutput;
import java.util.List;



//=======


import com.example.eventplanner.Database.DBHelper;

public class EventHome extends AppCompatActivity {

    Button taskHome;
    ProgressBar taskProgress;
    //Context context;
    private DBHelper dbHelper;
    private List<Task> tasks;
    TextView showCount;


    Button btnEvent;
    DBHelper dbevent;
    private Context context;
    TextView viewEventDateTime,viewEventLocation;

    Button btn;
    private DBHelper dbGuest;
    TextView totalGuests;
    TextView invitedGuests;
//>>>>>>> Integration


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String eid = getIntent().getStringExtra("id");
        SharedPreferences prf = getSharedPreferences("eid",MODE_PRIVATE);
        SharedPreferences.Editor editor = prf.edit();
        editor.putString("eid",eid);
        editor.commit();

        //String eid1 = prf.getString("eid","no ID");
        //System.out.println("EVENT HOME ID :" + eid1);

        btn = (Button) findViewById(R.id.button4);

        btnEvent = (Button) findViewById(R.id.btnEvent);
        viewEventDateTime = (TextView) findViewById(R.id.idViewEventDateTime);
        viewEventLocation = (TextView) findViewById(R.id.idViewEventLocation);

        context = this;
        dbevent = new DBHelper(context);

        final String id = getIntent().getStringExtra("id");
        final com.example.eventplanner.Database.Event event = dbevent.getSingleEvent(Integer.parseInt(id));
        getSupportActionBar().setTitle(event.getEventName());

        viewEventDateTime.setText(event.getDate() + " " + event.getTime());
        viewEventLocation.setText(event.getLocation());

        btnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(EventHome.this,EventEdit.class);
                myIntent.putExtra("id",String.valueOf(eid));
                Context context = getApplicationContext();
                CharSequence text = "Go to Event Details";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
                toast.show();

                startActivity(myIntent);
            }
        });


        taskHome = (Button) findViewById(R.id.button8);
        taskProgress = (ProgressBar) findViewById(R.id.idprogressBarTask);
        showCount = (TextView)findViewById(R.id.idShowCountTasks);


        //context = this;
        dbHelper = new DBHelper(context);

       int countTasks = dbHelper.countTasks();
      taskProgress.setMax(countTasks);

      int finished = dbHelper.countFinished(1);

      taskProgress.setProgress(finished);

      showCount.setText(finished + "/" + countTasks);



        totalGuests = findViewById(R.id.editTextTextPersonName15);
        invitedGuests = findViewById(R.id.editTextTextPersonName16);

        //context = this;
        dbGuest = new DBHelper(context);
        SharedPreferences prf1 = getSharedPreferences("eid",MODE_PRIVATE);
        String eid1 = prf.getString("eid","no ID");


        int totalGuestsCount = dbGuest.totalGuest(eid);
        int invitedGuestsCount = dbGuest.invitedGuest(eid);

        totalGuests.setText(String.valueOf(totalGuestsCount));
        invitedGuests.setText(String.valueOf(invitedGuestsCount));





    }

    @Override
    protected void onResume() {
        super.onResume();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventHome.this,GuestHome.class);
                final String id = getIntent().getStringExtra("id");
                intent.putExtra("id",id);

                Context context = getApplicationContext();
                CharSequence text = context.getString(R.string.g_toast_click);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
                toast.show();

                startActivity(intent);
            }
        });
      
              taskHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventHome.this,TaskHome.class);
                final String id = getIntent().getStringExtra("id");
                intent.putExtra("id",id);

                Context context = getApplicationContext();
                CharSequence text = "Manage your Tasks Here..";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
                toast.show();

                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){

            Intent intent = new Intent(EventHome.this,Home.class);

            startActivity(intent);

        }
        return true;
    
    }


}