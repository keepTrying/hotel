package com.dreamfactory.hotelmanager.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.tools.SeverManager;
import com.dreamfactory.hotelmanager.tools.TimeHelper;
import com.dreamfactory.hotelmanager.tools.UserManager;

import java.util.Calendar;

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

        final Calendar c = Calendar.getInstance();
        editText_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(OrderRoomActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        c.set(year, monthOfYear, dayOfMonth);
                        editText_start.setText(DateFormat.format("yyy-MM-dd", c));
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
        editText_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(OrderRoomActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        c.set(year, monthOfYear, dayOfMonth);
                        editText_end.setText(DateFormat.format("yyy-MM-dd", c));
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });





        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long days=0;
                long daysfromnow=0;
                try {
                    days = TimeHelper.getDays( editText_end.getText().toString(),editText_start.getText().toString());
                    daysfromnow=TimeHelper.getDaysFromNow(editText_start.getText().toString());
                    if(days<=0||daysfromnow<=0){
                        Toast.makeText(OrderRoomActivity.this,"您所输入的日期错误，请核对！",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }catch (Exception e){

                    return;
                }
                SeverManager.getInstance(OrderRoomActivity.this, new SeverManager.Sever_call_back() {
                    @Override
                    public void onResponseSuccess(String obj) {
                        Toast.makeText(OrderRoomActivity.this,"预订成功,请在40分钟内支付，否则失效!",Toast
                                .LENGTH_SHORT).show();
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
