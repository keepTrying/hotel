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
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.module.Indent;
import com.dreamfactory.hotelmanager.module.User;
import com.dreamfactory.hotelmanager.module.UserHistory;
import com.dreamfactory.hotelmanager.tools.SeverManager;
import com.dreamfactory.hotelmanager.tools.TimeHelper;
import com.dreamfactory.hotelmanager.tools.UserManager;

import java.io.Serializable;
import java.util.List;


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

        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor mEditor = sp.edit();
        mEditor.putBoolean("login", false);
        mEditor.commit();

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
            SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
            if (!sp.getBoolean("login",false)){
                Toast.makeText(HomeActivity.this,"请先登录！",Toast.LENGTH_SHORT).show();
                intent=new Intent(HomeActivity.this,LoginActivity.class);
            }else {
                intent = new Intent(HomeActivity.this, UserInfoActivity.class);
            }
            startActivity(intent);
        }else if(v==btn_history){
            SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
            if (!sp.getBoolean("login",false)){
                Toast.makeText(HomeActivity.this,"请先登录！",Toast.LENGTH_SHORT).show();
                intent=new Intent(HomeActivity.this,LoginActivity.class);
            }else {
                intent = new Intent(HomeActivity.this, IndentActivity.class);
            }
            startActivity(intent);
        }else if(v==btn_point){
            SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
            if (!sp.getBoolean("login",false)){
                Toast.makeText(HomeActivity.this,"请先登录！",Toast.LENGTH_SHORT).show();
                intent=new Intent(HomeActivity.this,LoginActivity.class);
            }else {
                intent = new Intent(HomeActivity.this, PointActivity.class);
            }
            startActivity(intent);
        }else if(v==btn_room){
            SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
            if (!sp.getBoolean("login",false)){
                Toast.makeText(HomeActivity.this,"请先登录！",Toast.LENGTH_SHORT).show();
                intent=new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(intent);
            }else {
                //if living in a room now
                User user= UserManager.getInstance(HomeActivity.this).getUser();
//                List<UserHistory> histories = user.getUser_history();
//                for (UserHistory history : histories){
//                    String time_begin = history.getTime_begin();
//                    String time_end = history.getTime_end();
//                    if (TimeHelper.isNowBetweenDates(time_begin,time_end)){
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("myroom", history);
//                        intent = new Intent(HomeActivity.this, MyRoomActivity.class);
//                        intent.putExtras(bundle);
//                        startActivity(intent);
//                        return;
//                    }
//                }
                SeverManager.getInstance(HomeActivity.this, new SeverManager.Sever_call_back() {
                    @Override
                    public void onResponseSuccess(String obj) {
                        List<Indent> indents = JSON.parseArray(obj, Indent.class);
                        Indent indent = indents.get(0);
                        UserHistory history = new UserHistory(indent.getTime_begin(), indent.getTime_end(), indent.getRoom_num());
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("myroom", history);
                        intent = new Intent(HomeActivity.this, MyRoomActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                    @Override
                    public void onResponseError(int code) {
                        Toast.makeText(HomeActivity.this, "对不起，您当前没有入住我们的酒店。", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onErrorResponse(String volleyError) {
                        Toast.makeText(HomeActivity.this,"对不起，请检查您的网络连接！",Toast.LENGTH_SHORT).show();
                    }
                }).indent_query(HomeActivity.this, "", "", "", "", "", "", "", "", "6", user.getUser_id() + "", 1 + "");

            }

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
                SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
                if (sp.getBoolean("login",false)==false) {
                    intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                    return true;
                }else{
                    //TODO logout
                    SharedPreferences.Editor mEditor = sp.edit();
                    mEditor.putBoolean("login",false);
                    mEditor.commit();
                    Toast.makeText(HomeActivity.this,"注销成功！",Toast.LENGTH_SHORT).show();
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);

        MenuItem item = menu.findItem(R.id.action_login);
        item.setTitle(sp.getBoolean("login", false) ? "注销" : "登录");

        return super.onPrepareOptionsMenu(menu);
    }


}