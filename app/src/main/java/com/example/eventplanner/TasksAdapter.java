package com.example.eventplanner;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.eventplanner.Database.EventsMaster;
import com.example.eventplanner.Database.Task;
import com.example.eventplanner.Database.Task;
import com.example.eventplanner.Database.DBHelper;

import java.util.List;

public class TasksAdapter extends ArrayAdapter<Task> {

    private Context context;
    private int resource;
    List<Task> tasks;
    DBHelper dbHelper;

    public TasksAdapter(@NonNull Context context, int resource, List<Task> tasks) {
        super(context, resource, tasks);
        this.context = context;
        this.resource = resource;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

       //Create objects from the elements that are in the single_task xml file and set their id numbers.
        TextView description = row.findViewById(R.id.idDescription);
        ImageView imageView = row.findViewById(R.id.idCheck);
        ImageButton edit = row.findViewById(R.id.idEditBtn);
        final ImageButton delete = row.findViewById(R.id.idDeleteBtn);
        TextView taskView = row.findViewById((R.id.idTaskName));


      final Task task = tasks.get(position); //Get the row details of the task records inside the Task table in the database

      //Set details of that Task record into the text views in the single_task xml file
      taskView.setText(task.getTaskName());
      description.setText(task.getDescription());

      //Make imageView of the checkIcon invisible to the user
      imageView.setVisibility(row.INVISIBLE);

      //This executes when user clicks on delete icon in the interface
      delete.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              //Creates a object from DBHelper class
             dbHelper = new DBHelper(context);

             //Call the deleteTask function inside the DBHelper class
             dbHelper.deleteTask(task.getId());

             //Reload the same interface after delete a task
             Intent myIntent = new Intent(context,TaskHome.class);
             context.startActivity(myIntent);

          }
      });


    //This executes when user clicks on edit icon in the interface
     edit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              //User navigates to the EditTask interface with the details of a selected Task
              Intent intent = new Intent(context , EditTask.class);
              intent.putExtra("id",String.valueOf(task.getId()));
              context.startActivity(intent);

          }
      });


     //If a task is done(If a task is checked), the check icon visible to the user
      if(task.getFinished() > 0){
          imageView.setVisibility(View.VISIBLE);
      }

      return row;


    }

    public TasksAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
}
