package com.dreamfactory.hotelmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.module.Room;

import java.util.LinkedList;

/**
 * Created by yangpeidong on 16/3/24.
 */
public class Room_list_adapter extends BaseAdapter {

    private LinkedList<Room> mData;
    private Context mContext;

    public Room_list_adapter(LinkedList<Room> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
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
        convertView = LayoutInflater.from(mContext).inflate(R.layout.room_list_item,parent,false);
        ImageView img_icon = (ImageView) convertView.findViewById(R.id.r_img);
        TextView txt_num = (TextView) convertView.findViewById(R.id.r_num);
        TextView txt_price = (TextView) convertView.findViewById(R.id.r_price);
//        img_icon.setBackgroundResource(mData.get(position).getImg());
        img_icon.setBackgroundResource(R.drawable.hotel);
        txt_num.setText(mData.get(position).getRoom_num()+"");
        txt_price.setText(mData.get(position).getRoom_cost()+"元/天");
        return convertView;
    }
}
