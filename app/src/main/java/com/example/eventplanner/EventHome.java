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

import com.example.eventplanner.Database.DBGuest;
import com.example.eventplanner.Database.GuestModel;

public class EventHome extends AppCompatActivity {

    Button btn;
    private DBGuest dbGuest;
    private Context context;
    TextView totalGuests;
    TextView invitedGuests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn = (Button) findViewById(R.id.button4);
        totalGuests = findViewById(R.id.editTextTextPersonName15);
        invitedGuests = findViewById(R.id.editTextTextPersonName16);

        context = this;
        dbGuest = new DBGuest(context);

        int totalGuestsCount = dbGuest.totalGuest();
        int invitedGuestsCount = dbGuest.invitedGuest();

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