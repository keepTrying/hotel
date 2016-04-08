package com.dreamfactory.hotelmanager.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.adapter.Room_list_adapter;
import com.dreamfactory.hotelmanager.module.Room;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class QueryRoomResult extends AppCompatActivity implements AdapterView.OnItemClickListener {

//    public static final String KEY_PUT = "queryroomresult_put";

    private List<Room> mData = null;
    private Context mContext;
    private Room_list_adapter mAdapter = null;
    private ListView list_room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_room_result);

        mData = getIntent().getExtras().getParcelableArrayList(QueryRoomActivity.KEY_PUT);

        mContext = QueryRoomResult.this;
        final LayoutInflater inflater = LayoutInflater.from(this);
        View headView = inflater.inflate(R.layout.list_head, null, false);
        list_room = (ListView) findViewById(R.id.listView);
        list_room.addHeaderView(headView);
//        mData = new LinkedList<Room>();
//        mData.add(new Room(999008,1,999.0f,100, "img","什么都有"));
//        mData.add(new Room(999008,1,999.0f,100, "img","什么都有"));
//        mData.add(new Room(999008,1,999.0f,100, "img","什么都有"));
//        mData.add(new Room(999008,1,999.0f,100, "img","什么都有"));
//        mData.add(new Room(999008,1,999.0f,100, "img","什么都有"));
        mAdapter = new Room_list_adapter((LinkedList<Room>) mData, mContext);
        list_room.setAdapter(mAdapter);
        list_room.setOnItemClickListener(this);
    }

    public void onListItemClick(ListMenuItemView view){

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Room room = mData.get(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.room_detail_put_key),room);
        Intent intent =new Intent(this,RoomDetail.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
