package com.dreamfactory.hotelmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.module.Room;
import com.dreamfactory.hotelmanager.tools.SeverManager;

import java.util.ArrayList;
import java.util.List;

public class QueryRoomActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String KEY_PUT = "room_query_results";
    private Button query;
    private EditText et_room_num;
    private EditText et_room_cost_min;
    private EditText et_room_cost_max;
    private EditText et_room_area_min;
    private EditText et_room_area_max;
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_room);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//            }
//        });

        et_room_num = (EditText)findViewById(R.id.textView_room_num);
        et_room_cost_min = (EditText)findViewById(R.id.editText_room_cost_min);
        et_room_cost_max = (EditText)findViewById(R.id.editText_room_cost_max);
        et_room_area_min = (EditText)findViewById(R.id.editText_room_area_min);
        et_room_area_max = (EditText)findViewById(R.id.editText_room_area_max);
        spinner=(Spinner)findViewById(R.id.spinner_room_type);

        query = (Button)findViewById(R.id.btn_query_room);
        query.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v==query){

            SeverManager.getInstance(QueryRoomActivity.this, new SeverManager.Sever_call_back() {
                @Override
                public void onResponseSuccess(String obj) {
                    List<Room> rooms = JSON.parseArray(obj,Room.class);
                    Bundle bundle =new Bundle();
                    bundle.putParcelableArrayList(KEY_PUT, (ArrayList<? extends Parcelable>) rooms);
                    Intent intent = new Intent(QueryRoomActivity.this,QueryRoomResult.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }

                @Override
                public void onResponseError(int code) {
                    Toast.makeText(QueryRoomActivity.this,String.format("查询失败,错误码：%d",code),Toast
                            .LENGTH_SHORT).show();
                }

                @Override
                public void onErrorResponse(String volleyError) {
                    Toast.makeText(QueryRoomActivity.this,String.format("查询失败,错误：%s",volleyError),Toast
                            .LENGTH_SHORT).show();
                }
            }).room_query(QueryRoomActivity.this,
                    et_room_num.getText().toString(),
                    (spinner.getSelectedItemPosition()+1)+"",
                    et_room_area_min.getText().toString(),
                    et_room_area_max.getText().toString(),
                    et_room_cost_min.getText().toString(),
                    et_room_cost_max.getText().toString()
            );

        }
    }
}
