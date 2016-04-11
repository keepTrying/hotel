package com.dreamfactory.hotelmanager.activity;

import android.app.Activity;
import android.content.Context;
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
import java.util.Objects;

public class ChoosePic extends Activity {

    private GridView gridView;
    private List<Map<String,Object>> data_list;
    private SimpleAdapter simpleAdapter;
    private final Context mContext=ChoosePic.this;
    public static final int PUT_KEY = 0x3423;
    public static final int[] icons ={R.drawable.n1,R.drawable.n2,R.drawable.n3,R.drawable.n4,R.drawable.n5,R.drawable.n6,R.drawable.n7,R.drawable.n8,R.drawable.n9,R.drawable.n10};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pic);
        gridView = (GridView)findViewById(R.id.choose_grid);

        data_list = new ArrayList<Map<String,Object>>();
        getData();
        String[] from ={"image"};
        int[] to = {R.id.choose_img};
        simpleAdapter= new SimpleAdapter(mContext,data_list,R.layout.choose_item,from,to);
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(mContext,UserInfoActivity.class);
                intent.putExtra("image",position);
                setResult(PUT_KEY,intent);
                finish();
            }
        });


    }

    public List<Map<String, Object>> getData(){
        //cion和iconName的长度是相同的，这里任选其一都可以
        for(int i=0;i<icons.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icons[i]);
            data_list.add(map);
        }

        return data_list;
    }

}
