package com.dreamfactory.hotelmanager.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.toolbox.NetworkImageView;
import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.module.Indent;
import com.dreamfactory.hotelmanager.module.Room;
import com.dreamfactory.hotelmanager.module.User;
import com.dreamfactory.hotelmanager.module.UserHistory;
import com.dreamfactory.hotelmanager.tools.SeverManager;
import com.dreamfactory.hotelmanager.tools.TimeHelper;
import com.dreamfactory.hotelmanager.tools.UserManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyRoomActivity extends AppCompatActivity implements View.OnClickListener {

    private NetworkImageView imageView;
    private TextView tv_room_num;
    private TextView tv_room_type;
    private TextView tv_room_cost;
    private TextView tv_room_area;
    private TextView tv_room_facility;
    private TextView tv_room_rent_time;

    private Button btn_breakfast;
    private Button btn_knead;
    private Button btn_continue;
    private Button btn_comment;
    private Button btn_refund;

    private String room_num;

    private Room room;
    private Indent indent;

    private UserHistory history;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_room);
        history=(UserHistory)getIntent().getExtras().getSerializable("myroom");

        room_num=history.getRoom_num()+"";

        imageView=(NetworkImageView)findViewById(R.id.imageView_room);
        tv_room_num=(TextView)findViewById(R.id.textView_room_num);
        tv_room_type=(TextView)findViewById(R.id.textView_room_type);
        tv_room_cost=(TextView)findViewById(R.id.textView_room_cost);
        tv_room_area=(TextView)findViewById(R.id.textView_room_area);
        tv_room_facility=(TextView)findViewById(R.id.textView_room_facility);
        tv_room_rent_time=(TextView)findViewById(R.id.textView_rent_time);

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

                room = JSON.parseArray(obj,Room.class).get(0);

                int type = room.getRoom_type();
                String room_type="";
                switch (type){
                    case 1:
                        room_type="单人间";
                        break;
                    case 2:
                        room_type="标准间";
                        break;
                    case 3:
                        room_type="商务间";
                        break;
                    case 4:
                        room_type="行政间";
                        break;
                    case 5:
                        room_type="套间";
                        break;
                    default:
                        break;
                }

                //TODO set image
                SeverManager.loadImage(MyRoomActivity.this, imageView, room.getRoom_img(), R
                        .drawable.nopig, R.drawable.nopig);

                tv_room_num.setText(room.getRoom_num() + "");
                tv_room_type.setText(room_type);
                tv_room_cost.setText(room.getRoom_cost()+"元/天");
                tv_room_area.setText(room.getRoom_area()+"平米");
                tv_room_facility.setText(room.getRoom_facility());

                String rent_time=history.getTime_begin()+" ~ "+history.getTime_end();
                tv_room_rent_time.setText(rent_time);

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
        String text;
        switch (v.getId()){
            case R.id.btn_order:
                new AlertDialog.Builder(MyRoomActivity.this)
                        .setTitle("早餐")
                        .setMessage("确认要申请早餐服务？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SeverManager.getInstance(MyRoomActivity.this, new SeverManager.Sever_call_back() {
                                    @Override
                                    public void onResponseSuccess(String obj) {
                                        Toast.makeText(MyRoomActivity.this,"申请成功!",Toast
                                                .LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onResponseError(int code) {
                                        Toast.makeText(MyRoomActivity.this,String.format("申请失败！错误码:%d",code),Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onErrorResponse(String volleyError) {
                                        Toast.makeText(MyRoomActivity.this,String.format("申请失败！错误:%s",volleyError),Toast.LENGTH_SHORT).show();

                                    }
                                }).indent_order(MyRoomActivity.this,TimeHelper.getStringDateShort(),TimeHelper.getStringDateShort(),room_num, UserManager.getInstance(MyRoomActivity.this).getUser().getUser_id()+"",10+"",2+"");
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                break;
            case R.id.btn_knead :
                new AlertDialog.Builder(MyRoomActivity.this)
                        .setTitle("按摩")
                        .setMessage("确认要申请按摩服务？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SeverManager.getInstance(MyRoomActivity.this, new SeverManager.Sever_call_back() {
                                    @Override
                                    public void onResponseSuccess(String obj) {
                                        Toast.makeText(MyRoomActivity.this,"申请成功！",Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onResponseError(int code) {
                                        Toast.makeText(MyRoomActivity.this,String.format("申请失败！错误码:%d",code),Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onErrorResponse(String volleyError) {
                                        Toast.makeText(MyRoomActivity.this,String.format("申请失败！错误:%s",volleyError),Toast.LENGTH_SHORT).show();

                                    }
                                }).indent_order(MyRoomActivity.this,TimeHelper.getStringDateShort(),TimeHelper.getStringDateShort(),room_num, UserManager.getInstance(MyRoomActivity.this).getUser().getUser_id()+"",100+"",3+"");
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                break;
            case R.id.btn_continue :
                LayoutInflater factory = LayoutInflater.from(MyRoomActivity.this);
                final View textEntryView = factory.inflate(R.layout.continue_room_dialog, null);
                final EditText editText = (EditText) textEntryView.findViewById(R.id.editText_time);
                final Calendar c = Calendar.getInstance();
                editText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog dialog = new DatePickerDialog(MyRoomActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                c.set(year, monthOfYear, dayOfMonth);
                                editText.setText(DateFormat.format("yyy-MM-dd", c));
                            }
                        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                        dialog.show();
                    }
                });
                AlertDialog dlg = new AlertDialog.Builder(MyRoomActivity.this)
                        .setView(textEntryView)
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {



                                final String input = editText.getText().toString();
                                try {
                                    if (TimeHelper.getDays(history.getTime_end(),input)>0)
                                    {
                                        Toast.makeText(MyRoomActivity.this,"请核实您输入的日期!",Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }catch (Exception e) {
                                    Toast.makeText(MyRoomActivity.this, "您输入的日期格式有误!", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                SeverManager.getInstance(MyRoomActivity.this, new SeverManager.Sever_call_back() {
                                    @Override
                                    public void onResponseSuccess(String obj) {
                                        List<Indent> indents = JSON.parseArray(obj, Indent.class);
                                        List<Indent> filter_indents = new ArrayList<Indent>();
                                        for (Indent var : indents) {
                                            if (var.getUser_id() == UserManager.getInstance(MyRoomActivity.this).getUser().getUser_id() && var.getTime_end().equals(history.getTime_end()) && var.getIndent_type() == 1 && var.getIndent_status() == 6) {
                                                indent = var;
                                                continue;
                                            } else if (var.getIndent_type() != 1 || var.getIndent_status() ==3 ||var.getIndent_status() ==4 ||var.getIndent_status() == 5 || TimeHelper.getDays(var.getTime_begin(), history.getTime_end()) < 0) {
                                                continue;
                                            }

                                            filter_indents.add(var);
                                        }

                                        for (Indent var1 : filter_indents) {
                                            if (TimeHelper.getDays(var1.getTime_begin(), input) <
                                                    0) {
                                                Toast.makeText(MyRoomActivity.this, String.format
                                                        ("对不起，%s 至 %s 已经有人预定本房间！", var1.getTime_begin(), var1.getTime_end()), Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        }

                                        AlertDialog dialog2 = new AlertDialog.Builder(MyRoomActivity.this).setMessage(String.format("确认支付%.2f元？", TimeHelper.getDays(input, history.getTime_end()) * room.getRoom_cost())).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                SeverManager.getInstance(MyRoomActivity.this, new SeverManager.Sever_call_back() {
                                                    @Override
                                                    public void onResponseSuccess(String obj) {

                                                        User user = UserManager.getInstance(MyRoomActivity.this).getUser();
                                                        List<UserHistory> histories = user.getUser_history();
                                                        histories.remove(history);
                                                        history.setTime_end(input);
                                                        histories.add(history);
                                                        user.setUser_history(histories);
                                                        UserManager.getInstance(MyRoomActivity.this).setUser(user);

                                                        tv_room_rent_time.setText(String.format("%s ~ %s", history.getTime_begin(), history.getTime_end()));

                                                        Toast.makeText(MyRoomActivity.this, "支付成功!", Toast.LENGTH_SHORT).show();
                                                    }

                                                    @Override
                                                    public void onResponseError(int code) {
                                                        Toast.makeText(MyRoomActivity.this, String.format("支付失败，错误码：%d", code), Toast.LENGTH_SHORT).show();
                                                    }

                                                    @Override
                                                    public void onErrorResponse(String volleyError) {
                                                        Toast.makeText(MyRoomActivity.this, String.format("支付失败，错误：%s", volleyError), Toast.LENGTH_SHORT).show();
                                                    }
                                                }).indent_alter(MyRoomActivity.this, indent.getTime_begin(), input, indent.getRoom_num() + "", indent.getUser_id() + "", indent.getCost() + TimeHelper.getDays(input, history.getTime_end()) * room.getRoom_cost() + "", indent.getIndent_type() + "", indent.getIndent_status() + "", indent.getIndent_id() + "");
                                            }
                                        }).show();

                                    }

                                    @Override
                                    public void onResponseError(int code) {
                                        Toast.makeText(MyRoomActivity.this, String.format("续租失败，错误码：%d", code), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onErrorResponse(String volleyError) {
                                        Toast.makeText(MyRoomActivity.this, String.format("续租失败，错误：%s", volleyError), Toast.LENGTH_SHORT).show();
                                    }
                                }).indent_query(MyRoomActivity.this,"","","","","","",room_num,"","","",1+"");



                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {


                            }
                        })
                        .create();
                dlg.show();
                break;

            case R.id.btn_comment :
                SeverManager.getInstance(MyRoomActivity.this, new SeverManager.Sever_call_back() {
                    @Override
                    public void onResponseSuccess(String obj) {
                        Toast.makeText(MyRoomActivity.this, "对不起，您已经评价过了！", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponseError(int code) {
                        if (code == 433) {
                            Intent intent = new Intent(MyRoomActivity.this, CommentActivity.class);
                            intent.putExtra("room_num", room_num);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MyRoomActivity.this, "对不起，网络连接失败！", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onErrorResponse(String volleyError) {
                        Toast.makeText(MyRoomActivity.this, "对不起，网络连接失败！", Toast.LENGTH_SHORT).show();
                    }
                }).comment_query(MyRoomActivity.this, UserManager.getInstance(MyRoomActivity.this).getUser().getUser_id() + "", "", room_num, "", TimeHelper.date2Time(history.getTime_begin()), TimeHelper.date2Time(history.getTime_end()), "");
                break;
            case R.id.btn_refund :

                    SeverManager.getInstance(MyRoomActivity.this, new SeverManager.Sever_call_back() {
                        @Override
                        public void onResponseSuccess(String obj) {
                            try {
                                Indent indent = (Indent) JSON.parseArray(obj,Indent.class).get(0);

                                SeverManager.getInstance(MyRoomActivity.this, new SeverManager.Sever_call_back() {
                                    @Override
                                    public void onResponseSuccess(String obj) {
                                        Toast.makeText(MyRoomActivity.this, "退房成功，请到前台办理手续!", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }

                                    @Override
                                    public void onResponseError(int code) {
                                        Toast.makeText(MyRoomActivity.this, String.format("退房失败，错误码：%d", code), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onErrorResponse(String volleyError) {
                                        Toast.makeText(MyRoomActivity.this, String.format("退房失败，错误：%s", volleyError), Toast.LENGTH_SHORT).show();
                                    }
                                }).indent_alter(MyRoomActivity.this, indent.getTime_begin(), indent.getTime_end(), indent.getRoom_num() + "", indent.getUser_id() + "", indent.getCost() + "", indent.getIndent_type() + "", 3 + "", indent.getIndent_id() + "");
                            }catch(Exception e) {
                                Toast.makeText(MyRoomActivity.this, "退房成功，请到前台办理手续!", Toast.LENGTH_SHORT).show();
                                finish();
                                return;
                            }

                        }

                        @Override
                        public void onResponseError(int code) {
                            Toast.makeText(MyRoomActivity.this,String.format("退房失败，错误码：%d",code),Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onErrorResponse(String volleyError) {
                            Toast.makeText(MyRoomActivity.this,String.format("退房失败，错误：%s",volleyError),Toast.LENGTH_SHORT).show();
                        }
                    }).indent_query(MyRoomActivity.this, "", "", "", "", "", "", room_num, "", 6 + "", UserManager.getInstance(MyRoomActivity.this).getUser().getUser_id() + "", 1 + "");


                break;
            default:
                break;
        }
    }
}
