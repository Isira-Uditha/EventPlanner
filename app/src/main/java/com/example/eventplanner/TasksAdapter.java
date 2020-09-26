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

        //TextView title = row.findViewById((R.id.title));
        TextView description = row.findViewById(R.id.idDescription);
        ImageView imageView = row.findViewById(R.id.idCheck);
        ImageButton edit = row.findViewById(R.id.idEditBtn);
        final ImageButton delete = row.findViewById(R.id.idDeleteBtn);
        TextView taskView = row.findViewById((R.id.idTaskName));


      final Task task = tasks.get(position);
      taskView.setText(task.getTaskName());
      description.setText(task.getDescription());
      imageView.setVisibility(row.INVISIBLE);

     // delete.setTag(position);
      delete.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

             dbHelper = new DBHelper(context);
              //Task task = tasks.get(position);
              dbHelper.deleteTask(task.getId());

             Intent myIntent = new Intent(context,TaskHome.class);
             context.startActivity(myIntent);

          }
      });

     edit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              Intent intent = new Intent(context , EditTask.class);
              intent.putExtra("id",String.valueOf(task.getId()));
              context.startActivity(intent);

          }
      });



      if(task.getFinished() > 0){
          imageView.setVisibility(View.VISIBLE);
      }

      return row;


    }

    public TasksAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
}
