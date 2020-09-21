package com.example.eventplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.eventplanner.Database.DBHelper;

public class EventHome extends AppCompatActivity {

    Button btn,btnEvent;
    DBHelper dbevent;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn = (Button) findViewById(R.id.button4);
        btnEvent = (Button) findViewById(R.id.btnEvent);

        context = this;
        dbevent = new DBHelper(context);

        final String id = getIntent().getStringExtra("id");
        final com.example.eventplanner.Event event = dbevent.getSingleEvent(Integer.parseInt(id));

        btnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent myIntent = new Intent(EventHome.this,EventEdit.class);
                //  Intent myIntent = new Intent(EventEdit.this, EventUpdate.class);
                myIntent.putExtra("id",String.valueOf(event.getId()));
                Context context = getApplicationContext();
                CharSequence text = "Go to Event Details";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
                toast.show();

                startActivity(myIntent);
            }
        });
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


    }
}