package com.example.eventplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    //Create objects of the elements that are used in the xml file
    private ListView taskView;
    private TextView count , completed , incompleted;
    Context context;
    private DBHelper dbHelper;
    private List<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_home);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("To Do List");

        SharedPreferences prf = getSharedPreferences("eid",MODE_PRIVATE);
        String eid = prf.getString("eid", "No ID");

        context = this;
        dbHelper = new DBHelper(context);

        taskView = (ListView)findViewById(R.id.idTaskView);
        count = (TextView)findViewById(R.id.idTaskCount);
        completed = (TextView)findViewById(R.id.idTaskCompleted);
        incompleted = (TextView)findViewById(R.id.idTaskIncompleted);

        tasks = new ArrayList<>();

        //Call to the readAll function that is in the DBHelper class to get the task records in the table
        tasks = dbHelper.readAll(eid);

        //Assign the tasks into the adapter view
        TasksAdapter adapter = new TasksAdapter(context , R.layout.single_task, tasks);
        taskView.setAdapter(adapter);

        //count the all tasks from the table by calling countTasks method inside the DBHelper class
        int countTasks = dbHelper.countTasks(eid);
        count.setText("Total: "+countTasks);//Set the number of tasks into the text View

        //count the tasks that are completed by calling the completedTasks method inside the DBHelper class
        int completedTasks = dbHelper.countFinished(1,eid);
        completed.setText("Completed:"+completedTasks);

        //count the tasks that are not done up to now by calling the TasksToDo method inside the TaskHome.java(this class)
        int ToDo = TasksToDo(countTasks , completedTasks);
        incompleted.setText("Incompleted: " + ToDo);

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

        //When click on the Add button in the interface user navigates to the Add task interface
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

        //If user clicks on back button it navigates to the EventHome interface
        if(id == android.R.id.home){

            Intent intent = new Intent(TaskHome.this,EventHome.class);

            //Passed the selected Event Id of the task
            SharedPreferences prf = getSharedPreferences("eid",MODE_PRIVATE);
            String eid = prf.getString("eid", "No ID");
            intent.putExtra("id",eid);

            startActivity(intent);

        }


        return true;
    }

    //Calculates the amount of tasks that are not done
    public int TasksToDo(int allTasks , int completedTasks){

        int toDo = allTasks - completedTasks;
        return toDo;
    }
}