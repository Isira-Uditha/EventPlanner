// This is the class use for display all Guest records related to the specific event
package com.example.eventplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventplanner.Database.DBGuest;
import com.example.eventplanner.Database.DBHelper;
import com.example.eventplanner.Database.GuestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GuestHome extends AppCompatActivity {

    //ImageButton edit1;
    //ImageButton delete1;
    private List<GuestModel> guests;
    private DBHelper dbGuest;
    private ListView guestList;
    Context context;
    ImageButton delete;
    TextView Total,Invited,NotInvited;
    int total,invited,notInvited;

    public GuestHome() {
    }

    //This is the onCreate function in GuestHome class
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.g_home_name);
        //edit1 = (ImageButton) findViewById(R.id.Edit1);
        //delete1 = (ImageButton) findViewById(R.id.imageButton2);

        SharedPreferences prf = getSharedPreferences("eid",MODE_PRIVATE);
        String eid = prf.getString("eid", "No ID");

        //Call to a function by a reference of the View class
        Total = findViewById(R.id.idGuestCount);
        Invited = findViewById(R.id.idGuestInvited);
        NotInvited = findViewById(R.id.idGuestNotInvited);
        delete = findViewById(R.id.idDeleteBtn);
        context = this;
        dbGuest = new DBHelper(context);
        guestList = findViewById(R.id.guestList);
        guests = new ArrayList<>();
        guests = dbGuest.readAllGuest(eid);

        //This is an object created by GuestAdapter class
        GuestAdapter adapter = new GuestAdapter(context,R.layout.single_guest,guests);

        guestList.setAdapter(adapter);

        //calling the totalGuest function to get the count of guests in a specific event
        total = dbGuest.totalGuest(eid);
        //calling the invitedGuest function to get the count of invited guests in a specific event
        invited = dbGuest.invitedGuest(eid);
        //calling the invitedGuest function to calculate Not_invited guests in a specific event
        notInvited = GuestNotInvited(total,invited);
        //Display the details in the View page
        Total.setText(String.valueOf("Total : " + total));
        Invited.setText(String.valueOf("Invited : " + invited));
        NotInvited.setText(String.valueOf("Not Invited : " + notInvited));



    }
    //This code segment is required to display the navigation bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;

    }

    //This code segment is required to functioning the Navigation Bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.add){

            Intent intent = new Intent(GuestHome.this,GuestAdd.class);

            Context context = getApplicationContext();
            CharSequence text = context.getString(R.string.g_toast_add);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
            toast.show();

            startActivity(intent);
        }

        if(id == android.R.id.home){

            Intent intent = new Intent(GuestHome.this,EventHome.class);
            //final String id1 = getIntent().getStringExtra("id");
            SharedPreferences prf = getSharedPreferences("eid",MODE_PRIVATE);
            String eid = prf.getString("eid", "No ID");
            intent.putExtra("id",eid);


            Context context = getApplicationContext();
            CharSequence text = context.getString(R.string.g_toast_redirect);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
            toast.show();

            startActivity(intent);

        }
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        /*edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(GuestHome.this,GuestEdit.class);

                Context context = getApplicationContext();
                CharSequence text = context.getString(R.string.g_toast_views);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
                toast.show();

                startActivity(intent);

            }


        });

        delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(GuestHome.this,GuestHome.class);

                Context context = getApplicationContext();
                CharSequence text = context.getString(R.string.g_toast_delete);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
                toast.show();

                startActivity(intent);

            }
        });*/


    }

    //This function is required to calculate the total number of guests which are not invited to the event
    public int GuestNotInvited(int x , int y){

        int notinvited = x - y;
        return notinvited;
    }
}