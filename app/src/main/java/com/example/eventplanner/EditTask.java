package com.example.eventplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventplanner.Database.DBHelper;
import com.example.eventplanner.Database.Task;

public class EditTask extends AppCompatActivity {

    Button b1;
    TextView editDescription , editTime;
    CheckBox editFinished;
    TextView editTaskName , editDate;

    private DBHelper dbHelper;
    private Context context;
    private Long updateDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setTitle(R.string.task1);

        context = this;
        dbHelper = new DBHelper(context);

        b1 = (Button)findViewById(R.id.button2);
        editTaskName = (TextView) findViewById(R.id.idEditTaskName);
        editDescription = (TextView)findViewById(R.id.idEditDescription);
        editDate = (TextView)findViewById(R.id.idEditDate);
        editTime = (TextView)findViewById(R.id.idEditTime);
        editFinished = (CheckBox) findViewById(R.id.idEditFinished);


        final String id = getIntent().getStringExtra("id");

        final Task task = dbHelper.getSingleTask(Integer.parseInt(id));

        getSupportActionBar().setTitle(task.getTaskName());
        editTaskName.setText(task.getTaskName());
        editDescription.setText(task.getDescription());
        editDate.setText(task.getDate());
        editTime.setText(task.getTime());

        int checkId = task.getFinished();




        if(checkId > 0){

            editFinished.setChecked(true);
        }
        System.out.println(id);
        System.out.println(task.getDescription());
        System.out.println(task.getFinished());






        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditTask.this , UpdateTask.class);
                intent.putExtra("id",String.valueOf(task.getId()));

                Context context = getApplicationContext();
                CharSequence text = "Update Your Task Here";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();

                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
                startActivity(intent);


            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){

            Intent intent = new Intent(EditTask.this,TaskHome.class);

            startActivity(intent);

        }
        return true;
    }
}