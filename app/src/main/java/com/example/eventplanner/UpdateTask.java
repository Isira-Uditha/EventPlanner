//This class is for the Update Task
package com.example.eventplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eventplanner.Database.DBHelper;
import com.example.eventplanner.Database.Task;

public class UpdateTask extends AppCompatActivity {

    //Create objects of the elements that are used in the xml file
    Button b1;
    EditText updateTaskName , updateDate , updateTime , updateDescription ;
    CheckBox updateFinished;

    private DBHelper dbHelper;
    private Context context;
    private Long updateDate1;
    int checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        context = this;
        dbHelper = new DBHelper(context);

        b1 = (Button) findViewById(R.id.button4);
        updateTaskName = (EditText) findViewById(R.id.idUpdateTaskName);
        updateDate = (EditText) findViewById(R.id.idUpdateDate);
        updateTime = (EditText) findViewById(R.id.idUpdateTime);
        updateDescription = (EditText) findViewById(R.id.idUpdateDescription);
        updateFinished = (CheckBox) findViewById(R.id.idUpdateFinished);

        final String id = getIntent().getStringExtra("id");
        final Task task = dbHelper.getSingleTask(Integer.parseInt(id));
        getSupportActionBar().setTitle(task.getTaskName());

        updateTaskName.setText(task.getTaskName());
        updateDescription.setText(task.getDescription());
        updateDate.setText(task.getDate());
        updateTime.setText(task.getTime());

        int checkId = task.getFinished();

        //If the task is done the value of the checkId is greater than 0. If it is Checkbox marked as checked
        if (checkId > 0) {

            updateFinished.setChecked(true);
        }

        //Set the check button values.If user checks the button, checked will be equal to 1.
        updateFinished.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    checked = 1;
                }else{
                    checked = 0;
                }

            }
        });

        if (checkId > 0) {

            updateFinished.setChecked(true);
            checked = 1;
        }


        //This executes when user clicks on Update button in the Update Task interface
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //To add validations , crete an object from the InputValidatorHelper class
                InputValidatorHelper inputTaskValidatorHelper = new InputValidatorHelper();
                boolean allowSave = true;

                //Get the values that are entered by the user
                String taskName = updateTaskName.getText().toString();
                String date = updateDate.getText().toString();
                String time = updateTime.getText().toString();
                String description = updateDescription.getText().toString();

                //Validate the taskName.It should not be null
                if (inputTaskValidatorHelper.isNullOrEmpty(taskName)) {

                    Toast.makeText(context, "Task Name Should Not Be Empty", Toast.LENGTH_SHORT).show();
                    allowSave = false;
                }
                //Validate the taskName.Task Name should be has at least 5 characters.
                if(inputTaskValidatorHelper.ischeckText(taskName)){

                    // errMsg.append("Task Name Should not be Empty.\n");
                    Toast.makeText(context, "Task Name Should be more than 5 characters", Toast.LENGTH_SHORT).show();
                    allowSave = false;

                }
                //Validate the description.Description should not be null.
                if(inputTaskValidatorHelper.isNullOrEmpty(description)){
                    Toast.makeText(context, "Description Should Not Be Empty", Toast.LENGTH_SHORT).show();
                    allowSave = false;
                }

                if (allowSave) {


                    Task task = new Task(Integer.parseInt(id), taskName, date, time, description, checked);

                    //Update the task by calling UpdateTask function inside the DBHelper class
                    int state = dbHelper.updateTask(task);
                    System.out.println(state);

                    if (state > 0) {
                        //If the Update is successful user navigates to the TaskHome.class
                        Intent intent = new Intent(context, TaskHome.class);
                        Toast.makeText(context, "Data successfully Updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context, TaskHome.class));
                    }


                }
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){

        int id1 = item.getItemId();
        context = this;
        dbHelper = new DBHelper(context);
        final String id = getIntent().getStringExtra("id");
        final Task task = dbHelper.getSingleTask(Integer.parseInt(id));


        //If user clicks on back button user navigates to the EditTask page
        if (id1 == android.R.id.home) {


            Intent intent = new Intent(UpdateTask.this, EditTask.class);
            intent.putExtra("id", String.valueOf(task.getId()));

            startActivity(intent);

        }
        return true;

    }
}