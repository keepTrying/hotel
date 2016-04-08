package com.dreamfactory.hotelmanager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.toolbox.NetworkImageView;
import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.module.Room;
import com.dreamfactory.hotelmanager.module.UserHistory;
import com.dreamfactory.hotelmanager.tools.SeverManager;

public class MyRoomActivity extends Activity implements View.OnClickListener {

    private NetworkImageView imageView;
    private TextView tv_room_num;
    private TextView tv_room_type;
    private TextView tv_room_cost;
    private TextView tv_room_area;
    private TextView tv_room_facility;

    private Button btn_breakfast;
    private Button btn_knead;
    private Button btn_continue;
    private Button btn_comment;
    private Button btn_refund;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_room);
        UserHistory history=(UserHistory)getIntent().getExtras().getSerializable("myroom");

        imageView=(NetworkImageView)findViewById(R.id.imageView_room);
        tv_room_num=(TextView)findViewById(R.id.textView_room_num);
        tv_room_type=(TextView)findViewById(R.id.textView_room_type);
        tv_room_cost=(TextView)findViewById(R.id.textView_room_cost);
        tv_room_area=(TextView)findViewById(R.id.textView_room_area);
        tv_room_facility=(TextView)findViewById(R.id.textView_room_facility);

        btn_breakfast = (Button)findViewById(R.id.btn_order);
        btn_breakfast.setOnClickListener(this);
        btn_knead = (Button)findViewById(R.id.btn_knead);
        btn_knead.setOnClickListener(this);
        btn_continue = (Button)findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(this);
        btn_comment = (Button)findViewById(R.id.btn_comment);
        btn_comment.setOnClickListener(this);
        btn_refund = (Button)findViewById(R.id.btn_refund);
        btn_refund.setOnClickListener(this);

        SeverManager.getInstance(MyRoomActivity.this, new SeverManager.Sever_call_back() {
            @Override
            public void onResponseSuccess(String obj) {

                Room room = JSON.parseArray(obj,Room.class).get(0);

                int type = room.getRoom_type();
                String room_type="";
                switch (type){
                    case 1:
                        room_type="单人间";
                        break;
                    case 2:
                        room_type="双人间";
                        break;
                    case 3:
                        room_type="三人间";
                        break;
                    default:
                        break;
                }

                //TODO set image
                SeverManager.loadImage(MyRoomActivity.this,imageView,room.getRoom_img(),R
                        .mipmap.ic_launcher,R.mipmap.ic_launcher);

                tv_room_num.setText(room.getRoom_num());
                tv_room_type.setText(room_type);
                tv_room_cost.setText(room.getRoom_cost()+"元/天");
                tv_room_area.setText(room.getRoom_area()+"平米");
                tv_room_facility.setText(room.getRoom_facility());


            }

            @Override
            public void onResponseError(int code) {
                Toast.makeText(MyRoomActivity.this,String.format("错误码：%d",code),Toast.LENGTH_SHORT)
                .show();
                finish();
            }

            @Override
            public void onErrorResponse(String volleyError) {
                Toast.makeText(MyRoomActivity.this,String.format("错误：%s",volleyError),Toast.LENGTH_SHORT)
                        .show();
                finish();
            }
        }).room_query(MyRoomActivity.this, history.getRoom_num() + "", "", "", "", "","");


    }

    @Override
    public void onClick(View v) {
        //TODO create indent

        switch (v.getId()){
            case R.id.btn_order:
                break;
            case R.id.btn_knead :
                break;
            case R.id.btn_continue :
                break;
            case R.id.btn_comment :
                break;
            case R.id.btn_refund :
                break;
            default:
                break;
        }
    }
}
