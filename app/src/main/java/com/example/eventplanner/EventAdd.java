//This class is the EventAdd.java class.
//Getting user inputs for all event details and insert it in the database is done by this class.
package com.example.eventplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventplanner.Database.DBHelper;

public class EventAdd extends AppCompatActivity {

    //create objects
    EditText etEventName,etEventDate,etEventTime,tietLocation,tietTheme,etDressCode,etPhotographer,etDescription;
    public String myExtra = "text";
    Button btn;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        textView=findViewById(R.id.selectedtxt);

        //link java button with xml button
        Button buttonApply = findViewById(R.id.choicebtn);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                textView.setText("selected :" + radioButton.getText());

            }
        });

        //Call to a function by a reference of the View class
        etEventName=findViewById(R.id.etEventName);
        etEventDate=findViewById(R.id.etEventDate);
        etEventTime=findViewById(R.id.etEventTime);
        tietLocation=findViewById(R.id.tietLocation);
        tietTheme=findViewById(R.id.tietTheme);
        etDressCode=findViewById(R.id.etDressCode);
        etPhotographer=findViewById(R.id.etPhotographer);
        etDescription=findViewById(R.id.etDescription);
        btn = findViewById(R.id.addEvent);

    }

    //select the radio button option
    public void checkButton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "Selected: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }


    //check the validations and display relevant toast messages.
    public void addData(View view){
        DBHelper dbevent = new DBHelper(this);

        InputValidatorHelper inputEventValidatorHelper = new InputValidatorHelper();
        boolean allowSave = true;

        //validate the event name.Event name can not be null.
        if(inputEventValidatorHelper.isNullOrEmpty(etEventName.getText().toString())){


            Toast.makeText(this, "Please enter the event name", Toast.LENGTH_SHORT).show();
            allowSave = false;

        }

        //validate the event name. There should have at least five characters.
        if(inputEventValidatorHelper.ischeckText(etEventName.getText().toString())){


            Toast.makeText(this, "Event name should have at least five characters ", Toast.LENGTH_SHORT).show();
            allowSave = false;

        }

        //validate the location. It can not be null.
        if(inputEventValidatorHelper.isNullOrEmpty(tietLocation.getText().toString())){


            Toast.makeText(this, "Please enter your location", Toast.LENGTH_SHORT).show();
            allowSave = false;

        }

        //validate the event date. Event date can not be null.
        if(inputEventValidatorHelper.isNullOrEmpty(etEventDate.getText().toString())){


            Toast.makeText(this, "Please enter the event date", Toast.LENGTH_SHORT).show();
            allowSave = false;

        }

        //validate the event time. It can not be null.
        if(inputEventValidatorHelper.isNullOrEmpty(etEventTime.getText().toString())){


            Toast.makeText(this, "Please enter the Time", Toast.LENGTH_SHORT).show();
            allowSave = false;

        }



        if(allowSave) {
            long val = dbevent.addInfo_event(etEventName.getText().toString(), etEventDate.getText().toString(), etEventTime.getText().toString(), tietLocation.getText().toString(), tietTheme.getText().toString(), etDressCode.getText().toString(), etPhotographer.getText().toString(), etDescription.getText().toString(), radioButton.getText().toString());
            if (val > 0) {
                Intent intent = new Intent(EventAdd.this, Home.class);
                Toast.makeText(this, "Data successfully inserted", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            } else {
                Toast.makeText(this, "Data not inserted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_update, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        //click back arrow in the navigation bar
        if(id == android.R.id.home){

            Intent intent = new Intent(EventAdd.this,Home.class);

            startActivity(intent);

        }

        //click cross icon in the navigation bar
        if(id == R.id.cancel){

            finish();
            startActivity(getIntent());

        }
        return true;
    }

}