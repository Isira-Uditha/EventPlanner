package com.example.eventplanner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eventplanner.Database.DBHelper;
import com.example.eventplanner.Event;
import com.example.eventplanner.R;

public class EventUpdate extends AppCompatActivity {
    public String myExtra = "text";
    Button btn;
    EditText updateEventName , updateDate , updateTime , updateLocation , updateTheme , updatePhotographer , updateDescription , updateDressCode ;
    private DBHelper dbevent;
    private Context context;
    RadioGroup radioGroup;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_update);
        //  getSupportActionBar().setTitle(R.string.string2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        radioGroup=(RadioGroup)findViewById(R.id.updateradioGroup);
        radioButton1 = (RadioButton)findViewById(R.id.updateIndoor);
        radioButton2 = (RadioButton)findViewById(R.id.updateOutdoor);
        textView=findViewById(R.id.selectedtxt);
        //  textView=findViewById(R.id.selectedtxt);

      /*  Button buttonApply = findViewById(R.id.choicebtn);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                //textView.setText("selected :" + radioButton.getText());

            }
        });*/

        context = this;
        dbevent = new DBHelper(context);

        btn = (Button) findViewById(R.id.button2);
        updateDate = (EditText) findViewById(R.id.idUpdateDate);
        updateEventName = (EditText)findViewById(R.id.idUpdateEventName);
        updateTime = (EditText) findViewById(R.id.idUpdateEventTime);
        updateLocation = (EditText)findViewById(R.id.idUpdateLocation);
        updateTheme =  (EditText)findViewById(R.id.idUpdateTheme);
        updatePhotographer  =  (EditText)findViewById(R.id.idUpdatePhotographer);
        updateDescription =  (EditText)findViewById(R.id.idUpdateDescription);
        updateDressCode =  (EditText)findViewById(R.id.idUpdateDressCode);
        // updateIndoor =  (EditText)findViewById(R.id.idUpdateOutdoor);
        Button buttonApply = findViewById(R.id.choicebtn1);

        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                System.out.println(radioId);
                textView.setText("selected :" + radioButton.getText());

            }
        });

        final String id = getIntent().getStringExtra("id");
        System.out.println(id);
        final Event event = dbevent.getSingleEvent(Integer.parseInt(id));
        getSupportActionBar().setTitle(event.getEventName());

        updateEventName.setText(event.getEventName());
        updateDate.setText(event.getDate());
        updateTime.setText(event.getTime());
        updateLocation.setText(event.getLocation());
        updateTheme.setText(event.getTheme());
        updateDressCode .setText(event.getDressCode());
        updatePhotographer .setText(event.getPhotographer());
        updateDescription .setText(event.getDescription());

        //String place = event.getPlace();
        //System.out.println(place);
        //int radioId ;
        //CharSequence a = radioButton.getText();

        if( event.getPlace().equals("Indoor")){
            System.out.println( "xxxxxxxxxx "+ event.getPlace());
            //radioButton = (RadioButton)findViewById(radioButton1);
            radioButton1.setChecked(true);
            // radioButton = (RadioButton)findViewById(R.id.updateIndoor);
            // radioButton.setChecked(true);
        } else{
            // radioId = 2;
            System.out.println("kasuni");
            //radioButton = (RadioButton)findViewById(R.id.updateOutdoor);
            //System.out.println( "xxxxxxxxxx "+ event.getPlace());
            radioButton2.setChecked(true);
            //radioButton = (RadioButton)findViewById(R.id.updateOutdoor);
            //radioButton.setChecked(true);
            //radioButton2.setChecked(true);
        }
        //radioButton = (RadioButton)findViewById(radioId);
        // radioButton.setChecked(true);

        // System.out.println(radioButton);
        /*public void checkButton(View view){
            int radioId = radioGroup.getCheckedRadioButtonId();
            radioButton = findViewById(radioId);
            Toast.makeText(this, "Selected: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
        }*/




    }

    public void checkButton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "Selected: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Intent myIntent = new Intent(EventUpdate.this, MainActivity.class);
                Context context = getApplicationContext();
                CharSequence text = context.getString(R.string.update);
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();*/

                /*toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 10);
                myIntent.putExtra("MAIN_EXTRA", myExtra);
                startActivity(myIntent);*/

                final String id = getIntent().getStringExtra("id");

                String eventName = updateEventName.getText().toString();
                String eventDate = updateDate.getText().toString();
                String eventTime = updateTime.getText().toString();
                String eventLocation = updateLocation.getText().toString();
                String eventTheme = updateTheme.getText().toString();
                String eventDressCode = updateDressCode.getText().toString();
                String eventPhotographer = updatePhotographer.getText().toString();
                String eventDescription = updateDescription.getText().toString();
                String place = radioButton.getText().toString();

                System.out.println("newwwwwwwwww " + place);

                Event event = new Event(Integer.parseInt(id), eventName, eventDate, eventTime, eventLocation, eventTheme, eventDressCode, eventPhotographer, eventDescription , place);
                int state = dbevent.updateSingleEvent(event);
                System.out.println(state);

                if(state>0){
                    Intent intent = new Intent(context , Home.class);
                    Toast.makeText(context,"Data successfully Updated",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(context,Home.class));
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){

        int id1 = item.getItemId();
        context = this;
        dbevent = new DBHelper(context);
        final String id = getIntent().getStringExtra("id");
        /*final Event event = dbHelper.getSingleEvent(Integer.parseInt(id));

        if (id1 == android.R.id.home) {


            Intent intent = new Intent(EventUpdate.this, EventEdit.class);
            intent.putExtra("id", String.valueOf(event.getId()));

            startActivity(intent);

        }*/
        return true;

    }
}