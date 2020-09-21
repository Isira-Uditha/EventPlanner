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

import com.example.eventplanner.Database.DBHelper;

import java.util.List;

public class EventsAdapter extends ArrayAdapter<com.example.eventplanner.Event>{



        private Context context;
        private int resource;
        List<com.example.eventplanner.Event> events;
        DBHelper dbevent;

        public EventsAdapter(@NonNull Context context, int resource, List<com.example.eventplanner.Event> events) {
            super(context, resource, events);
            this.context = context;
            this.resource = resource;
            this.events = events;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(context);
            View row = inflater.inflate(resource,parent,false);

            TextView eventName = row.findViewById(R.id.eventDisplay);
            TextView description = row.findViewById((R.id.descriptionDisplay));
            ImageView completed = row.findViewById(R.id.completed);
            ImageButton edit = row.findViewById(R.id.editImgBtn);
            //ImageView.setVisibility(row. INVISIBLE);
            final ImageButton delete = row.findViewById(R.id.deleteImgBtn);



           final com.example.eventplanner.Event event = events.get(position);
           eventName.setText(event.getEventName());
           description.setText(event.getDescription());
           completed.setVisibility(row.INVISIBLE);

            // delete.setTag(position);
           delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dbevent = new DBHelper(context);

                    dbevent.deleteEvent(event.getId());

                    Intent myIntent = new Intent(context,Home.class);
                    context.startActivity(myIntent);

                }
            });

         /* edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context , EventEdit.class);
                    intent.putExtra("id",String.valueOf(event.getId()));
                    context.startActivity(intent);

                }
            });*/



            /*if(event.getFinished() > 0){
                imageView.setVisibility(View.VISIBLE);
            }*/

            return row;


        }

        public EventsAdapter(@NonNull Context context, int resource) {
            super(context, resource);
        }

}
