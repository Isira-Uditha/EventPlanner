// This is the class use for add a new Guest to the application
package com.example.eventplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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



//This is the onCreate function in GuestAdd class
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_add);
        getSupportActionBar().setTitle(R.string.g_home_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Call to a function by a reference of the View class
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

        //This is the onClickListner of spinner1
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s1 = adapterView.getItemAtPosition(spinner1.getSelectedItemPosition()).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //This is the onClickListner of spinner2
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

    //This code segment is required to display the navigation bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_update,menu);
        return true;

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //This function is use to add a new Guest record
    public void addData(View view){
        DBHelper dbguest = new DBHelper(this);

        //This code segment is required to generate a shared variable by the Event ID
        SharedPreferences prf = getSharedPreferences("eid",MODE_PRIVATE);
        String eid = prf.getString("eid", "No ID");

        InputValidatorHelper inputValidatorHelper = new InputValidatorHelper();

        boolean allowSave = true;

        //This is a validation used for the Guest name
        if(inputValidatorHelper.isNullOrEmpty(etGuestName.getText().toString())){

            // errMsg.append("Task Name Should not be Empty.\n");
            Toast.makeText(this, "Guest Name Should not be Empty", Toast.LENGTH_SHORT).show();
            allowSave = false;

        }

        //This is a validation used for the Contact number
        if(inputValidatorHelper.ischeckContact(etGuestContact.getText().toString())){

            // errMsg.append("Task Name Should not be Empty.\n");
            Toast.makeText(this, "Please Insert a Correct Contact Number", Toast.LENGTH_SHORT).show();
            allowSave = false;

        }

        //This is a validation used for the Email address
        if(!inputValidatorHelper.isValidGuestEmail(etGuestEmail.getText().toString())){

            // errMsg.append("Task Name Should not be Empty.\n");
            Toast.makeText(this, "Please Insert a Correct E-mail Address", Toast.LENGTH_SHORT).show();
            allowSave = false;

        }


        //This condition statement is check whether the allowSave is True
        if(allowSave) {

            //calling the addInfo_guest function
            long val = dbguest.addInfo_guest(etGuestName.getText().toString(), s1, s2, etGuestContact.getText().toString(), etGuestEmail.getText().toString(), checked, etGuestNote.getText().toString(), Integer.parseInt(eid));

            intent = new Intent(GuestAdd.this, GuestHome.class);
            startActivity(intent);

            //This condition statement is check whether the val is grater than 0
            if (val > 0) {
                Toast.makeText(this, "Successfully Inserted", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Insertion Unsuccessful", Toast.LENGTH_SHORT).show();
            }

        }


    }

    //This code segment is required to functioning the Navigation Bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){

            Intent intent = new Intent(GuestAdd.this,GuestHome.class);

            startActivity(intent);

        }
        if(id == R.id.cancel){

            finish();
            startActivity(getIntent());

        }
        return true;
    }
}