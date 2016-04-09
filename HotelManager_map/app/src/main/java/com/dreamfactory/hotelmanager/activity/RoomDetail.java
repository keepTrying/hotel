package com.dreamfactory.hotelmanager.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.toolbox.NetworkImageView;
import com.dreamfactory.hotelmanager.adapter.Comment_adapter;
import com.dreamfactory.hotelmanager.module.Indent;
import com.dreamfactory.hotelmanager.module.Room;
import com.dreamfactory.hotelmanager.tools.SeverManager;
import com.dreamfactory.hotelmanager.view.ListViewForScrollView;
import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.module.Comment;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;

public class RoomDetail extends AppCompatActivity {

    public static final String PUT_KEY_ROOM_NUM="room_num_from_roomdetail";
    public static final String PUT_KEY_ROOM_COST="room_cost_from_roomdetail";
    private List<Comment> mData = null;
    private Context mContext;
    private Comment_adapter mAdapter = null;
    private ListView list_comment;
    private Room room;

    private TextView tv_room_num ;
    private TextView tv_room_type ;
    private TextView tv_room_cost ;
    private TextView tv_room_area ;
    private TextView tv_room_facility ;

    private Button btn_order;

    private NetworkImageView imgview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

//        Bundle bundle = getIntent().getExtras();
//        if (bundle.getString("class").equals(QueryRoomResult.class.toString())){
//
//        }else{
//
//        }
        room=getIntent().getExtras().getParcelable(getString(R.string.room_detail_put_key));

        final String room_type = getResources().getStringArray(R.array.room_type)[room
                .getRoom_type()+1];

        tv_room_num =(TextView)findViewById(R.id.textView_room_num);
        tv_room_type =(TextView)findViewById(R.id.textView_room_type);
        tv_room_cost =(TextView)findViewById(R.id.textView_room_cost);
        tv_room_area =(TextView)findViewById(R.id.textView_room_area);
        tv_room_facility=(TextView)findViewById(R.id.textView_room_facility);
        imgview=(NetworkImageView)findViewById(R.id.imageView_room);

        tv_room_num.setText(room.getRoom_num()+"");
        tv_room_type.setText(room_type);
        tv_room_cost.setText(room.getRoom_cost()+"元/天");
        tv_room_area.setText(room.getRoom_area()+"平米");
        tv_room_facility.setText(room.getRoom_facility());

        //TODO set pic for imageview
        SeverManager.loadImage(RoomDetail.this,imgview,room.getRoom_img(),R.mipmap.ic_launcher,R.mipmap.ic_launcher);



        btn_order=(Button)findViewById(R.id.btn_order);
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RoomDetail.this,orderRoomActivity.class);
                intent.putExtra(PUT_KEY_ROOM_NUM,room.getRoom_num());
                intent.putExtra(PUT_KEY_ROOM_COST,room.getRoom_cost());
                startActivity(intent);
            }
        });

        SeverManager.getInstance(RoomDetail.this, new SeverManager.Sever_call_back() {
            @Override
            public void onResponseSuccess(String obj) {

                mData= JSON.parseArray(obj,Comment.class);
                ScrollView sv = (ScrollView) findViewById(R.id.scrollView5);
                sv.smoothScrollTo(0, 0);
                list_comment=(ListViewForScrollView)findViewById(R.id.comment_list_view);
                mContext = RoomDetail.this;
                final LayoutInflater inflater = LayoutInflater.from(RoomDetail.this);
//                mData = new LinkedList<Comment>();
//                mData.add(new Comment(100001,"2014年2月2日","当然你也可以在res/values/下另外创建一个ids.xml文件，把上面这段代码贴上去！ 除了这个还有一个要注意的地方，就是这个区分类别的标志要从0开始算，不然会报下面 这样的错误：",000001,999001,2,"当然你也可以在res/values/下另外创建一个ids.xml文件，把上面这段代码贴上去！ 除了这个还有一个要注意的地方，就是这个区分类别的标志要从0开始算，不然会报下面 这样的错误：",R.mipmap.ic_launcher,"小米"));
//                mData.add(new Comment(100001,"2014年2月2日","当然你也可以在res/values/下另外创建一个ids.xml文件，把上面这段代码贴上去！ 除了这个还有一个要注意的地方，就是这个区分类别的标志要从0开始算，不然会报下面 这样的错误：",000001,999001,2,"当然你也可以在res/values/下另外创建一个ids.xml文件，把上面这段代码贴上去！ 除了这个还有一个要注意的地方，就是这个区分类别的标志要从0开始算，不然会报下面 这样的错误：",R.mipmap.ic_launcher,"小米"));
//                mData.add(new Comment(100001,"2014年2月2日","当然你也可以在res/values/下另外创建一个ids.xml文件，把上面这段代码贴上去！ 除了这个还有一个要注意的地方，就是这个区分类别的标志要从0开始算，不然会报下面 这样的错误：",000001,999001,2,"当然你也可以在res/values/下另外创建一个ids.xml文件，把上面这段代码贴上去！ 除了这个还有一个要注意的地方，就是这个区分类别的标志要从0开始算，不然会报下面 这样的错误：",R.mipmap.ic_launcher,"小米"));
//                mData.add(new Comment(100001,"2014年2月2日","当然你也可以在res/values/下另外创建一个ids.xml文件，把上面这段代码贴上去！ 除了这个还有一个要注意的地方，就是这个区分类别的标志要从0开始算，不然会报下面 这样的错误：",000001,999001,2,"当然你也可以在res/values/下另外创建一个ids.xml文件，把上面这段代码贴上去！ 除了这个还有一个要注意的地方，就是这个区分类别的标志要从0开始算，不然会报下面 这样的错误：",R.mipmap.ic_launcher,"小米"));
//                mData.add(new Comment(100001,"2014年2月2日","当然你也可以在res/values/下另外创建一个ids.xml文件，把上面这段代码贴上去！ 除了这个还有一个要注意的地方，就是这个区分类别的标志要从0开始算，不然会报下面 这样的错误：",000001,999001,2,"当然你也可以在res/values/下另外创建一个ids.xml文件，把上面这段代码贴上去！ 除了这个还有一个要注意的地方，就是这个区分类别的标志要从0开始算，不然会报下面 这样的错误：",R.mipmap.ic_launcher,"小米"));
//                mData.add(new Comment(100001,"2014年2月2日","当然你也可以在res/values/下另外创建一个ids.xml文件，把上面这段代码贴上去！ 除了这个还有一个要注意的地方，就是这个区分类别的标志要从0开始算，不然会报下面 这样的错误：",000001,999001,2,"当然你也可以在res/values/下另外创建一个ids.xml文件，把上面这段代码贴上去！ 除了这个还有一个要注意的地方，就是这个区分类别的标志要从0开始算，不然会报下面 这样的错误：",R.mipmap.ic_launcher,"小米"));
                mAdapter = new Comment_adapter(mData, mContext);
                list_comment.setAdapter(mAdapter);
            }

            @Override
            public void onResponseError(int code) {
                Toast.makeText(RoomDetail.this,String.format("评论显示失败！错误码：%d",code),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onErrorResponse(String volleyError) {
                Toast.makeText(RoomDetail.this, String.format("评论显示失败！错误：%s",volleyError), Toast.LENGTH_SHORT).show();
            }
        }).comment_query(RoomDetail.this, "", "", room.getRoom_num() + "", "", "", "","");




    }

}
