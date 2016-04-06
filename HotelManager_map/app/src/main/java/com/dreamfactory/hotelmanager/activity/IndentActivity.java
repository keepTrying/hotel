package com.dreamfactory.hotelmanager.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.dreamfactory.hotelmanager.module.Indent;
import com.dreamfactory.hotelmanager.adapter.IndentAdapter;
import com.dreamfactory.hotelmanager.module.IndentHash;
import com.dreamfactory.hotelmanager.R;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IndentActivity extends AppCompatActivity {

    private ExpandableListView elv = null;
    private IndentAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indent);

        List<Indent> indents = new ArrayList<Indent>();
        for(int i=0;i<10;i++){
            Indent indent = new Indent(000001, new Date(23,1,2), Date.valueOf("2013-02-12"),i%5,100001,i%3,"2015年2月3日",20001,108.4f);
            indents.add(indent);
        }
        final HashMap<String,List<Indent>> listHashMap = IndentHash.getData(indents);
        final List<String> titles = new ArrayList<String>(listHashMap.keySet());
        adapter = new IndentAdapter(this,titles,listHashMap);

        elv = (ExpandableListView)findViewById(R.id.indent_list);
        elv.setAdapter(adapter);

        //去掉箭头
//		elv.setGr oupIndicator(null);

        //收缩
        elv.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener()
        {

            @Override
            public void onGroupCollapse(int groupPosition)
            {
                Toast.makeText(IndentActivity.this,titles.get(groupPosition) + " group collapse..... ", Toast.LENGTH_LONG).show();
            }
        });
        //伸展
        elv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener()
        {
            @Override
            public void onGroupExpand(int groupPosition)
            {
                Toast.makeText(IndentActivity.this,titles.get(groupPosition) +" group expand... ", Toast.LENGTH_SHORT).show();
            }
        });
        //子条目点击
        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(IndentActivity.this, listHashMap.get(titles.get(groupPosition)).get(childPosition) + " click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }
}
