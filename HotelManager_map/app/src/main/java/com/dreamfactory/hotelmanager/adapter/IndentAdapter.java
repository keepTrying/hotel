package com.dreamfactory.hotelmanager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.module.Indent;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yangpeidong on 16/3/25.
 */
public class IndentAdapter extends BaseExpandableListAdapter{
    private Context mContext;
    private List<String> mTitles;
    private HashMap<String, List<Indent>> mListItems;

    public IndentAdapter(Context mContext, List<String> mTitles, HashMap<String, List<Indent>> mListItems) {
        this.mContext = mContext;
        this.mTitles = mTitles;
        this.mListItems = mListItems;
    }

    @Override
    public int getGroupCount() {
        return mTitles.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mListItems.get(mTitles.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mTitles.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mListItems.get(mTitles.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String data = this.mTitles.get(groupPosition);
        if(convertView == null)
        {
            convertView = View.inflate(mContext, R.layout.indent_group, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.indent_group_title);
        tv.setText(data);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Indent indent = this.mListItems.get(this.mTitles.get(groupPosition)).get(childPosition);
        if (convertView==null){
            convertView=View.inflate(mContext,R.layout.indent_item,null);
        }
        TextView tv_room_num= (TextView) convertView.findViewById(R.id.indent_room_num);
        tv_room_num.setText(indent.getRoom_num()+"");
        TextView tv_cost= (TextView) convertView.findViewById(R.id.indent_cost);
        tv_cost.setText(String.format("%.1f元", indent.getCost()));
        TextView tv_time= (TextView) convertView.findViewById(R.id.indent_time);
        tv_time.setText(indent.getIndent_time());
        TextView tv_type= (TextView) convertView.findViewById(R.id.indent_type);
        String[] types = new String[]{"订房", "早餐", "按摩"};
        tv_type.setText(types[indent.getIndent_type()]);

        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
