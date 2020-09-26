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

        Button buttonApply = findViewById(R.id.choicebtn);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                textView.setText("selected :" + radioButton.getText());

            }
        });


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

    public void checkButton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "Selected: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }



    public void addData(View view){
        DBHelper dbevent = new DBHelper(this);

        InputValidatorHelper inputEventValidatorHelper = new InputValidatorHelper();
        boolean allowSave = true;

        if(inputEventValidatorHelper.isNullOrEmpty(etEventName.getText().toString())){


            Toast.makeText(this, "Event  Name Should not be Empty", Toast.LENGTH_SHORT).show();
            allowSave = false;

        }

        if(inputEventValidatorHelper.ischeckText(etEventName.getText().toString())){


            Toast.makeText(this, "Event name should have at least five characters ", Toast.LENGTH_SHORT).show();
            allowSave = false;

        }

        if(inputEventValidatorHelper.isNullOrEmpty(etDescription.getText().toString())){


            Toast.makeText(this, "Description Should not be Empty", Toast.LENGTH_SHORT).show();
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

        if(id == android.R.id.home){

            Intent intent = new Intent(EventAdd.this,Home.class);

            startActivity(intent);

        }
        if(id == R.id.cancel){

            finish();
            startActivity(getIntent());

        }
        return true;
    }

}