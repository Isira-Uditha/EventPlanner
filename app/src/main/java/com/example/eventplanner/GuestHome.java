package com.example.eventplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Vector;

public class GuestHome extends AppCompatActivity {

    ImageButton edit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        edit1 = (ImageButton) findViewById(R.id.Edit1);

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
            startActivity(intent);
        }

        if(id == android.R.id.home){

            Intent intent = new Intent(GuestHome.this,EventHome.class);
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
                startActivity(intent);

            }


        });
    }
}