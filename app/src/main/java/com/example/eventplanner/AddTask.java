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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eventplanner.Database.DBHelper;

public class AddTask extends AppCompatActivity {

    Button b1;
    EditText taskName , date , time , description;
    CheckBox addFinished;
    int checked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_task);

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

       /* b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddTask.this , MainActivity.class);

                Context context = getApplicationContext();
                CharSequence text = context.getString(R.string.toast_add);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();

                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);

                startActivity(intent);
            }
        });*/




    }

    public void addData(View view){

        DBHelper dbHelper = new DBHelper(this);

        long value = dbHelper.addInfo( taskName.getText().toString(),date.getText().toString() ,time.getText().toString(),description.getText().toString(),checked);

        if (value>0)
        {
            Intent intent = new Intent(AddTask.this , TaskHome.class);
            Toast.makeText(this,"Data successfully inserted",Toast.LENGTH_SHORT).show();
            //List unames = dbHelper.readInfo("tasks");

            // Toast.makeText(this,  unames.toString(),Toast.LENGTH_SHORT).show();
            startActivity(intent);





        }
        else {
            Toast.makeText(this,"Data not successfully inserted",Toast.LENGTH_SHORT).show();
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

        if(id == android.R.id.home){

            Intent intent = new Intent(AddTask.this,MainActivity.class);

            startActivity(intent);

        }
        return true;
    }
}