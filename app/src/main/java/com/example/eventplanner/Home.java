package com.example.eventplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class Home extends AppCompatActivity {

    ImageButton edit1;
    ImageButton edit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle(R.string.e_home_name);
        edit1 = (ImageButton) findViewById(R.id.edit);
        edit2 = (ImageButton) findViewById(R.id.imageButton23);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;

    }


    @Override
    protected void onResume() {
        super.onResume();


        edit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this, GuestHome.class);
                startActivity(intent);

            }
        });

        edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this, EventHome.class);
                startActivity(intent);

            }
        });
    }
}