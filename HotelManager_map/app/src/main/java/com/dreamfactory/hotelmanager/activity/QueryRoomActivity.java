package com.dreamfactory.hotelmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.dreamfactory.hotelmanager.R;

public class QueryRoomActivity extends AppCompatActivity implements View.OnClickListener {

    private Button query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_room);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        query = (Button)findViewById(R.id.btn_query_room);
        query.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v==query){
            Intent intent = new Intent(this,QueryRoomResult.class);
            startActivity(intent);
        }
    }
}
