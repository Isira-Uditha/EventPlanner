package com.example.eventplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.eventplanner.Database.DBGuest;
import com.example.eventplanner.Database.DBHelper;
import com.example.eventplanner.Database.GuestModel;

import java.util.ArrayList;
import java.util.List;

public class GuestAdd extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Button btn;
    EditText etGuestName;
    EditText etGuestContact;
    EditText etGuestEmail;
    EditText etGuestNote;
    String s1;
    String s2;
    Spinner spinner1;
    Spinner spinner2;
    int checked = 0;
    CheckBox ch;
    Intent intent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_add);
        getSupportActionBar().setTitle(R.string.g_home_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //btn = (Button) findViewById(R.id.g_update);
        etGuestName = findViewById(R.id.edtxtname);

        etGuestContact = findViewById(R.id.edtxtcontact);
        etGuestEmail = findViewById(R.id.edtxtemail);
        etGuestNote = findViewById(R.id.edtxtnote);

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.Spinner_Gender, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.Spinner_Age, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
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

        ch = (CheckBox) findViewById(R.id.checkInvited);
        ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if(isChecked){
                        checked = 1;
                    }else{
                        checked = 0;
                    }

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_update,menu);
        return true;

    }

   /* @Override
    protected void onResume() {
        super.onResume();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuestAdd.this,GuestHome.class);

                Context context = getApplicationContext();
                CharSequence text = context.getString(R.string.g_toast_add_successful);
                //int duration = Toast.LENGTH_SHORT;
                //Toast toast = Toast.makeText(context, text, duration);
                //toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
                //toast.show();

                startActivity(intent);
            }
        });
    }*/




    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



    public void addData(View view){
        DBHelper dbguest = new DBHelper(this);

        SharedPreferences prf = getSharedPreferences("eid",MODE_PRIVATE);
        String eid = prf.getString("eid", "No ID");

        //Toast.makeText(this, s1 + " Successfully Inserted", Toast.LENGTH_SHORT).show();


        long val = dbguest.addInfo_guest(etGuestName.getText().toString(),s1,s2,etGuestContact.getText().toString(),etGuestEmail.getText().toString(),checked,etGuestNote.getText().toString(),Integer.parseInt(eid));

        intent = new Intent(GuestAdd.this,GuestHome.class);
        startActivity(intent);


        if(val>0){
            Toast.makeText(this, "Successfully Inserted", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Insertion Unsuccessful", Toast.LENGTH_SHORT).show();
        }


    }
}