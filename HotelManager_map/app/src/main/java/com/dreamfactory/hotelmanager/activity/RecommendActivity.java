package com.dreamfactory.hotelmanager.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.adapter.RecommendAdapter;
import com.dreamfactory.hotelmanager.module.RecommendItem;

import java.util.LinkedList;
import java.util.List;

public class RecommendActivity extends Activity implements AdapterView.OnItemClickListener{

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
        mData.add(new RecommendItem("单人间"));
        mData.add(new RecommendItem("双人间"));
        mData.add(new RecommendItem("三人间"));
        mAdapter = new RecommendAdapter(mContext,(LinkedList<RecommendItem>)mData);
        list_recommend.setAdapter(mAdapter);
        list_recommend.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,RoomGridActivity.class);
        startActivity(intent);
    }
}
