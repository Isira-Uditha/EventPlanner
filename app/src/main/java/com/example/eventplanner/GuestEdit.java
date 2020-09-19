package com.example.eventplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventplanner.Database.DBGuest;
import com.example.eventplanner.Database.GuestModel;

public class GuestEdit extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btn1;
    private DBGuest dbGuest;
    private Context context;
    private  Long updatedDate;
    TextView guest_Name;
    TextView guest_Contact;
    TextView guest_Email;
    TextView guest_Note;
    TextView guest_Gender;
    TextView guest_Age;
    CheckBox guest_Invited;
    String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_edit);

        title = getIntent().getExtras().getString("g_name");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this;
        dbGuest = new DBGuest(context);

        btn1 = (Button) findViewById(R.id.button2);
        guest_Name = findViewById(R.id.guest_Name);
        guest_Contact = findViewById(R.id.guest_Contact);
        guest_Email = findViewById(R.id.guest_Email);
        guest_Note = findViewById(R.id.guest_Note);
        guest_Invited = findViewById(R.id.checkInvited);
        guest_Invited.setClickable(false);
        guest_Gender = findViewById(R.id.guest_Gender);
        //guest_Gender.setEnabled(false);
        guest_Age = findViewById(R.id.guest_Age);
       // guest_Age.setEnabled(false);


        /*Spinner spinner1 = findViewById(R.id.spinner1);
        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.Spinner_Gender, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.Spinner_Age, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);*/

        final String id = getIntent().getStringExtra("id");
        GuestModel guest = dbGuest.readSelectedGuest(Integer.parseInt(id));

        guest_Name.setText(guest.getGuestName());
        guest_Contact.setText(guest.getGuestContact());
        guest_Email.setText(guest.getGuestEmail());
        guest_Gender.setText(guest.getGuestGender());
        guest_Age.setText(guest.getGuestAge());

        if(guest.getGuestCheck() > 0){
            guest_Invited.setChecked(true);
        }
        //guest_Invited.setText(guest.getGuestCheck());
        guest_Note.setText(guest.getGuestNote());

        //System.out.println(id);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String id = getIntent().getStringExtra("id");
                final String title = getIntent().getExtras().getString("g_name");
                Intent intent = new Intent(GuestEdit.this,GuestUpdate.class);
                intent.putExtra("id",id);
                intent.putExtra("title",title);

                Context context = getApplicationContext();
                CharSequence text = context.getString(R.string.g_toast_edit);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
                toast.show();

                startActivity(intent);
            }
        });
    }
}