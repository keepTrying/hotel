package com.dreamfactory.hotelmanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.adapter.Recommend_grid_adapter;
import com.dreamfactory.hotelmanager.module.Room;
import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.tools.SeverManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dreamfactory.hotelmanager.R.string.room_detail_put_key;

public class RoomGridActivity extends Activity{

    private GridView gridView;
//    private List<Map<String,Object>> data_list;
//    private SimpleAdapter simpleAdapter;
//    private List icons;
//    private List prices;
//    private List nums;
    private ArrayList<Room> rooms;

//    public static final String PUT_KEY = "room_from_roomgridactivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_grid);

        rooms = getIntent().getExtras().getParcelableArrayList(RecommendActivity.PUT_KEY);

//        List arr=new ArrayList();
//        int i;
//        for (i = 0;i<20;i++){
//            arr.add(R.mipmap.ic_launcher);
//        }
//        icons=arr;
//
//        List arr2=new ArrayList();
//        int i2;
//        for (i2 = 0;i2<20;i2++){
//            arr2.add("184元/天");
//        }
//        prices=arr2;
//
//        List arr3=new ArrayList();
//        int i3;
//        for (i3 = 0;i3<20;i3++){
//            arr3.add("100567");
//        }
//        nums=arr3;
//
        gridView= (GridView) findViewById(R.id.gridView);
//        data_list = new ArrayList<Map<String,Object>>();
//        getData();
//        String[] from = {"image","num","price"};
//        int[] to ={R.id.room_grid_item_img,R.id.room_grid_item_room_num,R.id.room_grid_item_price};
//        simpleAdapter = new SimpleAdapter(this,data_list,R.layout.room_grid_item,from,to);
//        gridView.setAdapter(simpleAdapter);
//        gridView.setOnItemClickListener(this);


        Recommend_grid_adapter adapter = new Recommend_grid_adapter(RoomGridActivity.this,rooms);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(getResources().getString(room_detail_put_key),rooms.get(position));
                Intent intent = new Intent(RoomGridActivity.this,RoomDetail.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

//    public List<Map<String,Object>> getData(){
//        for (int i=0;i<icons.size();i++){
//            Map<String,Object> map = new HashMap<>();
//            SeverManager.loadImage(RoomGridActivity.this,);
//            map.put("image", icons.get(i));
//            map.put("num",nums.get(i));
//            map.put("price",prices.get(i));
//            data_list.add(map);
//        }
//
//        return data_list;
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent = new Intent(this,RoomDetail.class);
//        startActivity(intent);
//    }
}
