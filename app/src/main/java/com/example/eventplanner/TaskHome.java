package com.example.eventplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventplanner.Database.DBHelper;
import com.example.eventplanner.Database.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskHome extends AppCompatActivity {

    private ListView taskView;
    private TextView count;
    Context context;
    private DBHelper dbHelper;
    private List<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_home);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("To Do List");

        context = this;
        dbHelper = new DBHelper(context);
        taskView = (ListView)findViewById(R.id.idTaskView);
        count = (TextView)findViewById(R.id.idTaskCount);
        tasks = new ArrayList<>();

        tasks = dbHelper.readAll();
        TasksAdapter adapter = new TasksAdapter(context , R.layout.single_task, tasks);
        taskView.setAdapter(adapter);

        //get the number of tasks from the table
        int countTasks = dbHelper.countTasks();
        count.setText("You have "+countTasks+" Tasks");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.add){

            Intent intent = new Intent(TaskHome.this,AddTask.class);

            Context context = getApplicationContext();
            CharSequence text = "Add Your Task Here..";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context,text,duration);
            toast.show();

            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);


            startActivity(intent);
        }

        if(id == android.R.id.home){

            Intent intent = new Intent(TaskHome.this,EventHome.class);
            final String id1 = getIntent().getStringExtra("id");
            intent.putExtra("id",id1);


            startActivity(intent);

        }


        return true;
    }
}