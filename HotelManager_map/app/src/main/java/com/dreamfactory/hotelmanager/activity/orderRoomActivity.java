package com.dreamfactory.hotelmanager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.tools.SeverManager;
import com.dreamfactory.hotelmanager.tools.TimeHelper;
import com.dreamfactory.hotelmanager.tools.UserManager;

public class OrderRoomActivity extends AppCompatActivity {

    private TextView textView_room_num;
    private TextView textView_cost;
    private EditText editText_start;
    private EditText editText_end;
    private Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_room);
        final int room_num = getIntent().getIntExtra(RoomDetail.PUT_KEY_ROOM_NUM,0);
        final float room_cost = getIntent().getFloatExtra(RoomDetail.PUT_KEY_ROOM_COST, 0);

        textView_room_num=(TextView)findViewById(R.id.textView_room_num);
        textView_cost=(TextView)findViewById(R.id.textView_cost);
        editText_start=(EditText)findViewById(R.id.editText_begin);
        editText_end=(EditText)findViewById(R.id.editText_end);
        btn_confirm=(Button)findViewById(R.id.btn_confirm);

        textView_room_num.setText(room_num+"");
        textView_cost.setText(room_cost+"元/天");

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long days=0;

                try {
                    days = TimeHelper.getDays( editText_end.getText().toString(),editText_start.getText().toString());
                }catch (Exception e){
                    return;
                }
                SeverManager.getInstance(OrderRoomActivity.this, new SeverManager.Sever_call_back() {
                    @Override
                    public void onResponseSuccess(String obj) {
                        Toast.makeText(OrderRoomActivity.this,"预订成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onResponseError(int code) {
                        Toast.makeText(OrderRoomActivity.this,String.format("预订失败，错误码：%d",code),
                                Toast
                                .LENGTH_SHORT)
                                .show();
                    }

                    @Override
                public void onErrorResponse(String volleyError) {
                    Toast.makeText(OrderRoomActivity.this,String.format("预订失败，错误：%s",volleyError),
                            Toast.LENGTH_SHORT).show();
                }
            }).indent_order(OrderRoomActivity.this,
                        editText_start.getText().toString(),
                        editText_end.getText().toString(),
                        room_num+"",
                        UserManager.getInstance(OrderRoomActivity.this).getUser().getUser_id()+"",
                        room_cost*days+"",
                        1+"");
            }
        });
    }
}
