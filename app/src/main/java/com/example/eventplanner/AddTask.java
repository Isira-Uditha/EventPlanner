//This class is to take the user input values and inserts those are into the database
package com.example.eventplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eventplanner.Database.DBHelper;

public class AddTask extends AppCompatActivity {

    //Create objects of the elements that are used in the xml file
    Button b1;
    EditText taskName , date , time , description;
    CheckBox addFinished;
    int checked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Task");


        b1 = (Button)findViewById(R.id.idAddButton);
        taskName = (EditText)findViewById(R.id.idAddTaskName);
        date = (EditText)findViewById(R.id.idAddTaskDate);
        time = (EditText)findViewById(R.id.idAddTime);
        description = (EditText)findViewById(R.id.idAddDescription);
        addFinished = (CheckBox)findViewById(R.id.idAddFinished);

        //Set the check button values.If user checks the button, checked will be equal to 1.
        addFinished.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

    //This function executes when user clicks the Add button in the view
    public void addData(View view){

        DBHelper dbHelper = new DBHelper(this);

        SharedPreferences prf = getSharedPreferences("eid",MODE_PRIVATE);
        String eid = prf.getString("eid", "No ID");

        InputValidatorHelper inputTaskValidatorHelper = new InputValidatorHelper();


        //Validate and Save
        boolean allowSave = true;
        //Validate the taskName.It should not be null
        if(inputTaskValidatorHelper.isNullOrEmpty(taskName.getText().toString())){


            Toast.makeText(this, "Task Name Should not be Empty", Toast.LENGTH_SHORT).show();
            allowSave = false;

        }

        //Validate the taskName.Task Name should be has at least 5 characters.
        if(inputTaskValidatorHelper.ischeckText(taskName.getText().toString())){


            Toast.makeText(this, "Task Name Should be more than 5 characters", Toast.LENGTH_SHORT).show();
            allowSave = false;

        }

        //Validate the description.Description should not be null.
        if(inputTaskValidatorHelper.isNullOrEmpty(description.getText().toString())){
            Toast.makeText(this, "Description Should Not Be Empty", Toast.LENGTH_SHORT).show();
            allowSave = false;
        }

        if(allowSave) {

            //Call to the addInfo function inside the DBHelper to inserts the  data that are entered bu user into the database
            long value = dbHelper.addInfo(taskName.getText().toString(), date.getText().toString(), time.getText().toString(), description.getText().toString(), checked, Integer.parseInt(eid));


            if (value > 0) {
                Intent intent = new Intent(AddTask.this, TaskHome.class);
                Toast.makeText(this, "Data successfully inserted", Toast.LENGTH_SHORT).show();

                startActivity(intent);


            } else {
                Toast.makeText(this, "Data not successfully inserted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_update,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        //If user clicks on the back button
        if(id == android.R.id.home){

            Intent intent = new Intent(AddTask.this,TaskHome.class);

            startActivity(intent);
        }

        //If user clicks on the cross button in the top right corner of AddTask interface
        if(id == R.id.cancel){

            finish();
            startActivity(getIntent());

        }
        return true;
    }
}