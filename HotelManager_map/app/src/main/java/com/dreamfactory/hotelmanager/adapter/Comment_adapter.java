package com.dreamfactory.hotelmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.module.Comment;

import java.util.LinkedList;

/**
 * Created by yangpeidong on 16/3/24.
 */
public class Comment_adapter extends BaseAdapter {

    private LinkedList<Comment> mData;
    private Context mContext;

    public Comment_adapter(LinkedList<Comment> mData, Context mContext) {
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
        convertView = LayoutInflater.from(mContext).inflate(R.layout.comment_list_item,parent,false);
        ImageView pic = (ImageView) convertView.findViewById(R.id.comment_pic);
        TextView txt_nick = (TextView) convertView.findViewById(R.id.comment_name);
        TextView txt_content = (TextView) convertView.findViewById(R.id.comment_content);
        TextView txt_time = (TextView) convertView.findViewById(R.id.comment_time);
        TextView txt_reply= (TextView) convertView.findViewById(R.id.comment_reply);
        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.comment_rate);

        pic.setBackgroundResource(mData.get(position).getImg());
        txt_nick.setText(mData.get(position).getuName());
        txt_content.setText(mData.get(position).getContent());
        txt_time.setText(mData.get(position).getTime());
        txt_reply.setText((CharSequence) mData.get(position).getReply());
        ratingBar.setRating(mData.get(position).getStar());
        return convertView;
    }
}