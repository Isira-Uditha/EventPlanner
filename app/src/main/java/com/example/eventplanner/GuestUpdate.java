package com.example.eventplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventplanner.Database.DBGuest;
import com.example.eventplanner.Database.GuestModel;

public class GuestUpdate extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //button
    Button btn;
    private DBGuest dbGuest;
    private Context context;
    private  Long updatedDate;
    TextView guest_Name;
    TextView guest_Contact;
    TextView guest_Email;
    TextView guest_Note;
    //TextView guest_Gender;
    //TextView guest_Age;
    CheckBox guest_Invited;
    String title;
    String guest_spin1;
    int checked;
    String s1;
    String s2;
    Spinner spinner1;
    Spinner spinner2;
    String compareValueGender;
    String compareValueAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_update);
        title = getIntent().getExtras().getString("title");
        getSupportActionBar().setTitle(title);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn = (Button) findViewById(R.id.g_update);

        context = this;
        dbGuest = new DBGuest(context);
        guest_Name = findViewById(R.id.guest_Name);
        guest_Contact = findViewById(R.id.guest_Contact);
        guest_Email = findViewById(R.id.guest_Email);
        guest_Note = findViewById(R.id.guest_Note);
        guest_Invited = findViewById(R.id.checkInvited);
        guest_Invited.setClickable(true);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);

        final String id = getIntent().getStringExtra("id");
        final GuestModel guest = dbGuest.readSelectedGuest(Integer.parseInt(id));

        compareValueGender = guest.getGuestGender();
        compareValueAge = guest.getGuestAge();
//Spinner Gender
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.Spinner_Gender, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        if (compareValueGender != null) {
            int spinnerPosition = adapter1.getPosition(compareValueGender);
            spinner1.setSelection(spinnerPosition);
        }
        spinner1.setOnItemSelectedListener(this);
//Spinner Age
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.Spinner_Age, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        if (compareValueAge != null) {
            int spinnerPosition = adapter2.getPosition(compareValueAge);
            spinner2.setSelection(spinnerPosition);
        }
        spinner2.setOnItemSelectedListener(this);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s1 = adapterView.getItemAtPosition(spinner1.getSelectedItemPosition()).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s2 = adapterView.getItemAtPosition(spinner2.getSelectedItemPosition()).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        guest_Name.setText(guest.getGuestName());
        guest_Contact.setText(guest.getGuestContact());
        guest_Email.setText(guest.getGuestEmail());
        //guest_Gender.setText(guest.getGuestGender());
        //guest_Age.setText(guest.getGuestAge());


        //guest_Invited.setText(guest.getGuestCheck());
        guest_Note.setText(guest.getGuestNote());

        //System.out.println(id);

        guest_Invited.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked == true){
                    checked = 1;
                } else{
                    checked = 0;
                }
            }
        });

        if(guest.getGuestCheck() > 0){
            guest_Invited.setChecked(true);
            checked = 1;
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String g_name = guest_Name.getText().toString();
                String g_contact = guest_Contact.getText().toString();
                String g_email = guest_Email.getText().toString();
                String g_gender = s1;
                String g_age = s2;
                String g_note = guest_Note.getText().toString();
                int g_invited = checked;
                int id1 = Integer.parseInt(id);


                GuestModel guest = new GuestModel(id1,g_name,g_gender,g_age,g_contact,g_email,g_invited,g_note);
                int status = dbGuest.updateGuest(guest);
                //System.out.println("xxxxxxxxxxxxxx" + status);
                startActivity(new Intent(context,GuestHome.class));



                /*Intent intent = new Intent(GuestUpdate.this,GuestEdit.class);

                Context context = getApplicationContext();
                CharSequence text = context.getString(R.string.g_toast_update);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
                toast.show();

                startActivity(intent);*/
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){

        int id1 = item.getItemId();
        context = this;
        dbGuest = new DBGuest(context);
        final String id = getIntent().getStringExtra("id");
        final GuestModel guest = dbGuest.readSelectedGuest(Integer.parseInt(id));

        if (id1 == android.R.id.home) {


            Intent intent = new Intent(GuestUpdate.this, GuestEdit.class);
            intent.putExtra("id",id);

            startActivity(intent);

        }
        return true;

    }
}