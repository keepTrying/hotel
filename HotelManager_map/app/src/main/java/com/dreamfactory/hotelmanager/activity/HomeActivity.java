package com.dreamfactory.hotelmanager.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.dreamfactory.hotelmanager.R;


public class HomeActivity extends AppCompatActivity implements OnClickListener {

    // UI references.
    private Button btn_recommend;
    private Button btn_query;
    private Button btn_my;
    private Button btn_history;
    private Button btn_point;
    private Button btn_room;
    private Button btn_surround;
    private Button btn_about;

    Intent intent =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        this.btn_recommend=(Button)findViewById(R.id.btn_recommend);
        this.btn_query=(Button)findViewById(R.id.btn_query);
        this.btn_my=(Button)findViewById(R.id.btn_my);
        this.btn_history=(Button)findViewById(R.id.btn_history);
        this.btn_point=(Button)findViewById(R.id.btn_point);
        this.btn_room=(Button)findViewById(R.id.btn_room);
        this.btn_surround=(Button)findViewById(R.id.btn_surround);
        this.btn_about=(Button)findViewById(R.id.btn_about);

        btn_recommend.setOnClickListener(this);
        btn_query.setOnClickListener(this);
        btn_my.setOnClickListener(this);
        btn_history.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_room.setOnClickListener(this);
        btn_surround.setOnClickListener(this);
        btn_about.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        if (v==btn_recommend){
            intent= new Intent(HomeActivity.this,RecommendActivity.class);
            startActivity(intent);
        }else if(v==btn_query){
            intent= new Intent(HomeActivity.this,QueryRoomActivity.class);
            startActivity(intent);
        }else if(v==btn_my){
            intent= new Intent(HomeActivity.this,UserInfoActivity.class);
            startActivity(intent);
        }else if(v==btn_history){
            intent= new Intent(HomeActivity.this,IndentActivity.class);
            startActivity(intent);
        }else if(v==btn_point){
            intent= new Intent(HomeActivity.this,PointActivity.class);
            startActivity(intent);
        }else if(v==btn_room){
            intent= new Intent(HomeActivity.this,MyRoomActivity.class);
            startActivity(intent);
        }else if(v==btn_surround){
            intent= new Intent(HomeActivity.this,NearbyActivity.class);
            startActivity(intent);
        }else if(v==btn_about){
            intent= new Intent(HomeActivity.this,AboutActivity.class);
            startActivity(intent);
        }
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 为ActionBar扩展菜单项
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_login:
                SharedPreferences sp = getSharedPreferences("HomeActivity",MODE_PRIVATE);
                SharedPreferences.Editor mEditor = sp.edit();
                mEditor.putBoolean("login", !sp.getBoolean("login", true));
                mEditor.commit();
                if (!sp.getBoolean("login",false)) {
                    intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                    return true;
                }else{
                    //TODO logout
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}