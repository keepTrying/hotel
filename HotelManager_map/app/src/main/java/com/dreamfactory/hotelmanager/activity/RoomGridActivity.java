package com.dreamfactory.hotelmanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.dreamfactory.hotelmanager.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomGridActivity extends Activity implements AdapterView.OnItemClickListener {

    private GridView gridView;
    private List<Map<String,Object>> data_list;
    private SimpleAdapter simpleAdapter;
    private List icons;
    private List prices;
    private List nums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_grid);

        List arr=new ArrayList();
        int i;
        for (i = 0;i<20;i++){
            arr.add(R.mipmap.ic_launcher);
        }
        icons=arr;

        List arr2=new ArrayList();
        int i2;
        for (i2 = 0;i2<20;i2++){
            arr2.add("184元/天");
        }
        prices=arr2;

        List arr3=new ArrayList();
        int i3;
        for (i3 = 0;i3<20;i3++){
            arr3.add("100567");
        }
        nums=arr3;

        gridView= (GridView) findViewById(R.id.gridView);
        data_list = new ArrayList<Map<String,Object>>();
        getData();
        String[] from = {"image","num","price"};
        int[] to ={R.id.room_grid_item_img,R.id.room_grid_item_room_num,R.id.room_grid_item_price};
        simpleAdapter = new SimpleAdapter(this,data_list,R.layout.room_grid_item,from,to);
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(this);


    }

    public List<Map<String,Object>> getData(){
        for (int i=0;i<icons.size();i++){
            Map<String,Object> map = new HashMap<>();
            map.put("image",icons.get(i));
            map.put("num",nums.get(i));
            map.put("price",prices.get(i));
            data_list.add(map);
        }

        return data_list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,RoomDetail.class);
        startActivity(intent);
    }
}
