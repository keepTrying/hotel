package com.dreamfactory.hotelmanager.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.dreamfactory.hotelmanager.module.Indent;
import com.dreamfactory.hotelmanager.adapter.IndentAdapter;
import com.dreamfactory.hotelmanager.module.IndentHash;
import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.module.User;
import com.dreamfactory.hotelmanager.tools.SeverManager;
import com.dreamfactory.hotelmanager.tools.UserManager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IndentActivity extends AppCompatActivity {

    private ExpandableListView elv = null;
    private IndentAdapter adapter = null;
    private final Context mContext = IndentActivity.this;
    private int status_offset;
    private User me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indent);

        me=UserManager
                .getInstance
                        (mContext).getUser();

        SeverManager.getInstance(mContext, new SeverManager.Sever_call_back() {
            @Override
            public void onResponseSuccess(String obj) {
                List<Indent> indents = JSON.parseArray(obj,Indent.class);
                final HashMap<String,List<Indent>> listHashMap = IndentHash.getData(indents);
                final List<String> titles = new ArrayList<String>(listHashMap.keySet());
                adapter = new IndentAdapter(mContext,titles,listHashMap);

                elv = (ExpandableListView)findViewById(R.id.indent_list);
                elv.setAdapter(adapter);

                //去掉箭头
//		elv.setGr oupIndicator(null);

                //收缩
                elv.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener()
                {

                    @Override
                    public void onGroupCollapse(int groupPosition)
                    {
//                        Toast.makeText(IndentActivity.this,titles.get(groupPosition) + " group collapse..... ", Toast.LENGTH_LONG).show();
                    }
                });
                //伸展
                elv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener()
                {
                    @Override
                    public void onGroupExpand(int groupPosition)
                    {
//                        Toast.makeText(IndentActivity.this,titles.get(groupPosition) +" group expand... ", Toast.LENGTH_SHORT).show();
                    }
                });
                //子条目点击
                elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent,
                                                View v,
                                                int groupPosition,
                                                final int childPosition,
                                                long id) {
//                        Toast.makeText(IndentActivity.this,groupPosition+"->"+childPosition+"click", Toast.LENGTH_SHORT).show();

                        if (groupPosition==1){
                            //未支付订单
                            final Indent indent = listHashMap.get("未支付").get(childPosition);
                            if (indent.getIndent_type()==1){
                                status_offset=1;
                            }else {
                                status_offset=2;
                            }

                            new  AlertDialog.Builder(mContext)
                                    .setTitle("订单操作")
                                    .setNegativeButton("取消订单", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            SeverManager.getInstance(mContext, new SeverManager.Sever_call_back() {
                                                @Override
                                                public void onResponseSuccess(String obj) {
                                                    Toast.makeText(mContext, "取消订单成功！", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                }

                                                @Override
                                                public void onResponseError(int code) {
                                                    Toast.makeText(mContext, "取消订单失败！错误码：" + code, Toast.LENGTH_SHORT).show();
                                                }

                                                @Override
                                                public void onErrorResponse(String volleyError) {
                                                    Toast.makeText(mContext, "取消订单失败！错误：" + volleyError, Toast.LENGTH_SHORT).show();
                                                }
                                            }).indent_alter(mContext, indent.getTime_begin(), indent.getTime_end(), indent.getRoom_num() + "", indent.getUser_id() + "", indent.getCost() + "", indent.getIndent_type() + "", 4 + "", indent.getIndent_id() + "");
                                        }
                                    })
                                    .setPositiveButton("付款", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //pay
                                            new  AlertDialog.Builder(mContext)
                                                    .setTitle("支付")
                                                    .setMessage("确认支付" + indent.getCost() + "元?")
                                                    .setNeutralButton("确认", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            //pay
                                                            SeverManager.getInstance(mContext, new SeverManager.Sever_call_back() {
                                                                @Override
                                                                public void onResponseSuccess(String obj) {
                                                                    //add point

                                                                    SeverManager.getInstance(mContext, new SeverManager.Sever_call_back() {
                                                                        @Override
                                                                        public void onResponseSuccess(String obj) {
                                                                            me.setUser_point(me.getUser_point()+(int)indent.getCost());
                                                                            UserManager
                                                                                    .getInstance
                                                                                            (mContext).setUser(me);
                                                                            Toast.makeText(mContext, "付款成功！", Toast.LENGTH_SHORT).show();
                                                                            finish();
                                                                        }

                                                                        @Override
                                                                        public void onResponseError(int code) {
                                                                            Toast.makeText
                                                                                    (mContext,
                                                                                            "付款成功！积分失败，错误码："+code, Toast.LENGTH_SHORT).show();
                                                                            finish();
                                                                        }

                                                                        @Override
                                                                        public void onErrorResponse(String volleyError) {
                                                                            Toast.makeText
                                                                                    (mContext,
                                                                                            "付款成功！积分失败，错误："+volleyError, Toast.LENGTH_SHORT).show();
                                                                            finish();
                                                                        }
                                                                    }).user_alter(mContext,
                                                                            me.getUser_nick(),
                                                                            me.getUser_gender() +"",
                                                                            me.getUser_years()+"",
                                                                            me.getUser_email(),
                                                                            me.getUser_phone()+"",
                                                                            me.getUser_id_num(),
                                                                            me.getUser_name(),
                                                                            me.getUser_img()+"",
                                                                            me.getUser_point()+(int)indent.getCost()+""
                                                                    );


                                                                }

                                                                @Override
                                                                public void onResponseError(int code) {
                                                                    Toast.makeText(mContext, "付款失败！错误码：" + code, Toast.LENGTH_SHORT).show();
                                                                }

                                                                @Override
                                                                public void onErrorResponse(String volleyError) {
                                                                    Toast.makeText(mContext, "付款失败！错误：" + volleyError, Toast.LENGTH_SHORT).show();
                                                                }
                                                            }).indent_alter(mContext, indent.getTime_begin(), indent.getTime_end(), indent.getRoom_num() + "", indent.getUser_id() + "", indent.getCost() + "", indent.getIndent_type() + "", indent.getIndent_status() + status_offset + "", indent.getIndent_id() + "");
                                                        }
                                                    })
                                                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {

                                                        }
                                                    })
                                                    .show();
                                        }
                                    })
                                    .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    })
                                    .show();
                        }else if (groupPosition==2){
                            //已过期
                        }

                        return false;
                    }
                });
            }

            @Override
            public void onResponseError(int code) {
                Toast.makeText(mContext,"查询订单失败，错误码:"+code,Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onErrorResponse(String volleyError) {
                Toast.makeText(mContext,"查询订单失败，错误:"+volleyError,Toast.LENGTH_SHORT).show();
                finish();
            }
        }).indent_query(mContext,"","","","","","","","","", UserManager.getInstance(mContext).getUser().getUser_id()+"","");

//        for(int i=0;i<10;i++){
//            Indent indent = new Indent(000001, new Date(23,1,2), Date.valueOf("2013-02-12"),i%5,100001,i%3,"2015年2月3日",20001,108.4f);
//            indents.add(indent);
//        }



    }
}
