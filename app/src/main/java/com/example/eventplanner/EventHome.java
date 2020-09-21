package com.example.eventplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
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

import java.util.List;



//=======


public class EventHome extends AppCompatActivity {
  
    Button taskHome;
    ProgressBar taskProgress;
    //Context context;
    private DBHelper dbHelper;
    private List<Task> tasks;
    TextView showCount;

    Button btn;
    private DBHelper dbGuest;
    private Context context;
    TextView totalGuests;
    TextView invitedGuests;
//>>>>>>> Integration

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn = (Button) findViewById(R.id.button4);
//<<<<<<< Kasuni
        taskHome = (Button) findViewById(R.id.button8);
        taskProgress = (ProgressBar) findViewById(R.id.idprogressBarTask);
        showCount = (TextView)findViewById(R.id.idShowCountTasks);


        context = this;
        dbHelper = new DBHelper(context);

       int countTasks = dbHelper.countTasks();
      taskProgress.setMax(countTasks);

      int finished = dbHelper.countFinished(1);

      taskProgress.setProgress(finished);

      showCount.setText(finished + "/" + countTasks);


//=======
        totalGuests = findViewById(R.id.editTextTextPersonName15);
        invitedGuests = findViewById(R.id.editTextTextPersonName16);

        //context = this;
        dbGuest = new DBHelper(context);

        int totalGuestsCount = dbGuest.totalGuest();
        int invitedGuestsCount = dbGuest.invitedGuest();

        totalGuests.setText(String.valueOf(totalGuestsCount));
        invitedGuests.setText(String.valueOf(invitedGuestsCount));
//>>>>>>> Integration



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

        taskHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventHome.this,TaskHome.class);

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


}