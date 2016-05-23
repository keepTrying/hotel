package com.dreamfactory.hotelmanager.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.adapter.RecommendAdapter;
import com.dreamfactory.hotelmanager.module.RecommendItem;
import com.dreamfactory.hotelmanager.module.Room;
import com.dreamfactory.hotelmanager.tools.SeverManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RecommendActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    public final static String PUT_KEY ="room_from_recommendactivity";

    private List<RecommendItem> mData = null;
    private Context mContext;
    private RecommendAdapter mAdapter = null;
    private ListView list_recommend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        list_recommend = (ListView) findViewById(R.id.recomment_list);
        mContext=RecommendActivity.this;
        LayoutInflater inflater = LayoutInflater.from(this);
        mData = new LinkedList<RecommendItem>();
        mData.add(new RecommendItem("单人间","单人间：一间面积为16~20平方米的房间，内有卫生间和其他附属设备组成。房内设一张单人床。"));
        mData.add(new RecommendItem("标准间","标准间：房内设两张单人床或一张双人床的叫标准间，这样的房间适合住两位客人和夫妻同住，适合旅游团体住用。"));
        mData.add(new RecommendItem("商务间","房内设两张单人床或一张双人床，房内可以上网，满足商务客人的需求。"));
        mData.add(new RecommendItem("行政间","多为一张双人床，此类型房间单独为一楼层，并配有专用的商务中心，咖啡厅。"));
        mData.add(new RecommendItem("套间","是由两间或两间以上的房间（内有卫生间和其他附属设施）组成。"));
        mAdapter = new RecommendAdapter(mContext,(LinkedList<RecommendItem>)mData);
        list_recommend.setAdapter(mAdapter);
        list_recommend.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SeverManager.getInstance(RecommendActivity.this, new SeverManager.Sever_call_back() {
            @Override
            public void onResponseSuccess(String obj) {
                List<Room> rooms= JSON.parseArray(obj,Room.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(PUT_KEY, (ArrayList<? extends Parcelable>) rooms);
                Intent intent = new Intent(RecommendActivity.this,RoomGridActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }

            @Override
            public void onResponseError(int code) {
                Toast.makeText(RecommendActivity.this,"查询失败！",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onErrorResponse(String volleyError) {
                Toast.makeText(RecommendActivity.this,"查询失败！",Toast.LENGTH_SHORT).show();
            }
        }).room_query(RecommendActivity.this, "", position+1 + "", "", "", "", "");

    }
}
