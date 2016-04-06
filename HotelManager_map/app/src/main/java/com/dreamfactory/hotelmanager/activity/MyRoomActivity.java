package com.dreamfactory.hotelmanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dreamfactory.hotelmanager.R;

public class MyRoomActivity extends Activity implements View.OnClickListener {

    private Button btn_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_room);
        btn_comment = (Button)findViewById(R.id.btn_comment);
        btn_comment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==btn_comment){
            Intent intent = new Intent(this,CommentActivity.class);
            startActivity(intent);
        }
    }
}
