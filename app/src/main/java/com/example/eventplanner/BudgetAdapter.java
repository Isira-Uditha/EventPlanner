package com.example.eventplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.eventplanner.Database.DBHelper;

import java.util.List;

public class BudgetAdapter extends ArrayAdapter<Budgets> {

    private Context context;
    private int resource;
    List<Budgets> budgetz;
    DBHelper dbHelper;

    public BudgetAdapter(@NonNull Context context, int resource, List<Budgets> budgetz) {
        super(context, resource, budgetz);
        this.context = context;
        this.resource = resource;
        this.budgetz = budgetz;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        //TextView title = row.findViewById((R.id.title));
        TextView budgetname = row.findViewById(R.id.idBudgetName);
        //ImageView imageView = row.findViewById(R.id.idCheck);
        ImageButton edit = row.findViewById(R.id.idEditBtn);
        //final ImageButton delete = row.findViewById(R.id.idDeleteBtn);
        TextView paidAmount = row.findViewById((R.id.idPaidAmount));




        final Budgets bud = budgetz.get(position);
        budgetname.setText(bud.getBudgetName());
        paidAmount.setText(bud.getPadiAmount());
        //imageView.setVisibility(row.INVISIBLE);

        // delete.setTag(position);
        /*delete.setOnClickListener(new View.OnClickListener() {
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
        }*/

        return row;


    }
}
