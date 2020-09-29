package com.example.eventplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.eventplanner.Database.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    ImageButton edit1;
    ImageButton edit2;
    public String myExtra = "text";
    Button btn;
    private ImageButton addBtn;
    private ListView listView;
    private TextView count , countIndoorView , countOutdoorView;
    Context context;
    private List<com.example.eventplanner.Database.Event> events;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle(R.string.e_home_name);

        context = this;

        DBHelper dbevent = new DBHelper(this);
        addBtn = (ImageButton)findViewById(R.id.button);
        listView = findViewById(R.id.eventList);
        count = findViewById(R.id.eventCount);
        countIndoorView = (TextView)findViewById(R.id.eventCountIndoor);
        countOutdoorView = findViewById(R.id.eventCountOutdoor);
        events = new ArrayList<>();

        events = dbevent.readAllEvent();

        EventsAdapter adapter = new EventsAdapter(context, R.layout.single_event,events);
        listView.setAdapter(adapter);

        int countEvents = dbevent.countEvents();
        count.setText("Total : "+countEvents);

        int countIndoor = dbevent.countIndoor("Indoor");
        countIndoorView.setText("Indoor :"+countIndoor);

        int countOutdoor = calculateOutdoor(countEvents,countIndoor);
        countOutdoorView.setText("Outdoor: "+countOutdoor);




        addBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent myIntent = new Intent(Home.this, EventAdd.class);

                myIntent.putExtra("MAIN_EXTRA", myExtra);
                startActivity(myIntent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;

    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    public int calculateOutdoor(int totalEvents , int indoorEvents){

        int outdoorEvents = totalEvents-indoorEvents;
        return outdoorEvents;

    }
}