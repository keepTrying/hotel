package com.dreamfactory.hotelmanager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.poi.BaiduMapPoiSearch;
import com.baidu.mapapi.utils.poi.PoiParaOption;
import com.dreamfactory.hotelmanager.R;

public class NearbyActivity extends Activity implements View.OnClickListener{
    private Button btn_food;
    private Button btn_movie;
    private Button btn_market;
    private Button btn_medicine;
    private Button btn_bank;

    // 天安门坐标
    final double mLat1 = 39.915291;
    final double mLon1 = 116.403857;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);
        btn_food = (Button) findViewById(R.id.button_food);
        btn_movie = (Button) findViewById(R.id.button_movie);
        btn_market = (Button) findViewById(R.id.button_market);
        btn_medicine = (Button) findViewById(R.id.button_medicine);
        btn_bank = (Button) findViewById(R.id.button_bank);

        btn_bank.setOnClickListener(this);
        btn_medicine.setOnClickListener(this);
        btn_market.setOnClickListener(this);
        btn_movie.setOnClickListener(this);
        btn_food.setOnClickListener(this);


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    @Override
    protected void onResume() {
        super.onResume();

    }
    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onClick(View v) {

        if (v==btn_food){

        }else if (v==btn_bank){

        }else if (v==btn_market){

        }else if (v==btn_medicine){

        }else if (v==btn_movie){

        }

        Button button = (Button)v;
        String search_condition = String.valueOf(button.getText());
        this.startPoiNearbySearch(search_condition);
    }

    /**
     * 启动百度地图Poi周边检索
     */
    public void startPoiNearbySearch(String search_condition) {
        LatLng ptCenter = new LatLng(mLat1, mLon1); // 天安门
        PoiParaOption para = new PoiParaOption()
                .key(search_condition)
                .center(ptCenter)
                .radius(2000);

        try {
            BaiduMapPoiSearch.openBaiduMapPoiNearbySearch(para, this);
        } catch (Exception e) {
            e.printStackTrace();
            showDialog();
        }

    }

    private void showDialog(){
        Toast.makeText(NearbyActivity.this,"您尚未安装百度地图app或app版本过低",Toast.LENGTH_SHORT).show();
    }
}
