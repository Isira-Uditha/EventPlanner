package com.example.eventplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Vector;

public class GuestHome extends AppCompatActivity {

    ImageButton edit1;
    ImageButton delete1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        edit1 = (ImageButton) findViewById(R.id.Edit1);
        delete1 = (ImageButton) findViewById(R.id.imageButton2);

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

            Intent intent = new Intent(GuestHome.this,GuestAdd.class);

            Context context = getApplicationContext();
            CharSequence text = context.getString(R.string.g_toast_add);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
            toast.show();

            startActivity(intent);
        }

        if(id == android.R.id.home){

            Intent intent = new Intent(GuestHome.this,EventHome.class);

            Context context = getApplicationContext();
            CharSequence text = context.getString(R.string.g_toast_redirect);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
            toast.show();

            startActivity(intent);

        }
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(GuestHome.this,GuestEdit.class);

                Context context = getApplicationContext();
                CharSequence text = context.getString(R.string.g_toast_views);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
                toast.show();

                startActivity(intent);

            }


        });

        delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(GuestHome.this,GuestHome.class);

                Context context = getApplicationContext();
                CharSequence text = context.getString(R.string.g_toast_delete);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
                toast.show();

                startActivity(intent);

            }
        });
    }
}