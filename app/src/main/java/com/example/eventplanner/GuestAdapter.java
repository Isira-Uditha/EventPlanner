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

import com.example.eventplanner.Database.DBGuest;
import com.example.eventplanner.Database.EventsMaster;
import com.example.eventplanner.Database.GuestModel;

import java.util.List;

public class GuestAdapter extends ArrayAdapter<GuestModel> {

    private Context context;
    private int resource;
    List<GuestModel> guests;
    private DBGuest dbGuest;



    public GuestAdapter( Context context, int resource,  List<GuestModel> guests) {
        super(context, resource, guests);
        this.context = context;
        this.resource = resource;
        this.guests = guests;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView title = row.findViewById((R.id.title));
        TextView description = row.findViewById(R.id.idDescription);
        ImageView imageView = row.findViewById(R.id.idCheck);
        ImageButton edit = row.findViewById(R.id.idEditBtn);
        ImageButton delete = row.findViewById(R.id.idDeleteBtn);
        TextView taskView = row.findViewById((R.id.idTaskName));


        final GuestModel g_model = guests.get(position);
        taskView.setText(g_model.getGuestName());
        description.setText(g_model.getGuestContact());
        imageView.setVisibility(row.INVISIBLE);

        if(g_model.getGuestCheck() > 0){
            imageView.setVisibility(View.VISIBLE);
        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbGuest = new DBGuest(context);
                dbGuest.deleteGuest(g_model.getGuestID());
                Intent myIntent = new Intent(context,GuestHome.class);
                context.startActivity(myIntent);


            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context , GuestEdit.class);
                intent.putExtra("id",String.valueOf(g_model.getGuestID()));
                intent.putExtra("g_name",g_model.getGuestName());
                context.startActivity(intent);

            }
        });

        return row;
    }


}
