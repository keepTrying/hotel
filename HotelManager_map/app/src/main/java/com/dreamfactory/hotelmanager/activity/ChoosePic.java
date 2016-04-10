package com.dreamfactory.hotelmanager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.dreamfactory.hotelmanager.R;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChoosePic extends Activity {

    private GridView gridView;
    private List<Map<String,Object>> data_list;
    private SimpleAdapter simpleAdapter;
    private final int[] icons ={R.drawable.n1,R.drawable.n2,R.drawable.n3,R.drawable.n4,R.drawable.n5,R.drawable.n6,R.drawable.n7,R.drawable.n8,R.drawable.n9,R.drawable.n10};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pic);
        gridView = (GridView)findViewById(R.id.choose_grid);
        


    }
}
