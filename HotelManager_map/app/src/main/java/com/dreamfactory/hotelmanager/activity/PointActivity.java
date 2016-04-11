package com.dreamfactory.hotelmanager.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.module.User;
import com.dreamfactory.hotelmanager.tools.SeverManager;
import com.dreamfactory.hotelmanager.tools.UserManager;

public class PointActivity extends AppCompatActivity implements View.OnClickListener{


    private TextView point_tv;
    private Button btn_reward_0;
    private Button btn_reward_1;
    private Button btn_reward_2;
    private Button btn_reward_3;
    private Integer point;
    private User me;
    private Integer reward;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);

        me = UserManager.getInstance(PointActivity.this).getUser();
        point=me.getUser_point();

        point_tv=(TextView)findViewById(R.id.textView_point);
        point_tv.setText(point+"");

        btn_reward_0=(Button)findViewById(R.id.btn_reward_0);
        btn_reward_1=(Button)findViewById(R.id.btn_reward_1);
        btn_reward_2=(Button)findViewById(R.id.btn_reward_2);
        btn_reward_3=(Button)findViewById(R.id.btn_reward_3);

        btn_reward_0.setOnClickListener(this);
        btn_reward_1.setOnClickListener(this);
        btn_reward_2.setOnClickListener(this);
        btn_reward_3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==btn_reward_0){
            reward=999999;
        }else if (v==btn_reward_1){
            reward=99999;
        }else if (v==btn_reward_2){
            reward=9999;
        }else if (v==btn_reward_3){
            reward=999;
        }
        if ((point-reward)<0){
            Toast.makeText(PointActivity.this,"积分不足",Toast.LENGTH_SHORT).show();
        }else{


            SeverManager.getInstance(PointActivity.this, new SeverManager.Sever_call_back() {
                @Override
                public void onResponseSuccess(String obj) {
                    Toast.makeText(PointActivity.this,"恭喜您，兑换成功，请尽快到前台领取礼品！",Toast.LENGTH_SHORT).show();
                    point-=reward;
                    point_tv.setText(point.toString());
                    me.setUser_point(point);
                    UserManager.getInstance(PointActivity.this).setUser(me);
                }

                @Override
                public void onResponseError(int code) {
                    Toast.makeText(PointActivity.this,String.format("兑换失败，错误码：%d",code),Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onErrorResponse(String volleyError) {
                    Toast.makeText(PointActivity.this,"无法连接服务器：",Toast.LENGTH_SHORT).show();

                }
            }).user_alter(PointActivity.this,me.getUser_nick(),me.getUser_gender()+"",me
                    .getUser_years()+"",me.getUser_email(),me.getUser_phone()+"",me
                    .getUser_id_num(),me.getUser_name(),me.getUser_img()+"",point+"");
        }
    }
}
