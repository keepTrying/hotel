package com.dreamfactory.hotelmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.module.RecommendItem;

import java.util.LinkedList;

/**
 * Created by yangpeidong on 16/3/25.
 */
public class RecommendAdapter extends BaseAdapter {

    private Context mContext;
    private LinkedList<RecommendItem> mData;

    public RecommendAdapter(Context mContext, LinkedList<RecommendItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.recommend_item,parent,false);
        TextView tv = (TextView) convertView.findViewById(R.id.tv_type);
        tv.setText(mData.get(position).getType());
        return convertView;
    }
}
