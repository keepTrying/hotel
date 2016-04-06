package com.dreamfactory.hotelmanager.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.ScrollView;

import com.dreamfactory.hotelmanager.adapter.Comment_adapter;
import com.dreamfactory.hotelmanager.view.ListViewForScrollView;
import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.module.Comment;

import java.util.LinkedList;
import java.util.List;

public class RoomDetail extends AppCompatActivity {

    private List<Comment> mData = null;
    private Context mContext;
    private Comment_adapter mAdapter = null;
    private ListView list_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);
        ScrollView sv = (ScrollView) findViewById(R.id.scrollView5);
        sv.smoothScrollTo(0, 0);
        list_comment=(ListViewForScrollView)findViewById(R.id.comment_list_view);
        mContext = RoomDetail.this;
        final LayoutInflater inflater = LayoutInflater.from(this);
        mData = new LinkedList<Comment>();
        mData.add(new Comment(100001,"2014年2月2日","当然你也可以在res/values/下另外创建一个ids.xml文件，把上面这段代码贴上去！ 除了这个还有一个要注意的地方，就是这个区分类别的标志要从0开始算，不然会报下面 这样的错误：",000001,999001,2,"当然你也可以在res/values/下另外创建一个ids.xml文件，把上面这段代码贴上去！ 除了这个还有一个要注意的地方，就是这个区分类别的标志要从0开始算，不然会报下面 这样的错误：",R.mipmap.ic_launcher,"小米"));
        mData.add(new Comment(100001,"2014年2月2日","当然你也可以在res/values/下另外创建一个ids.xml文件，把上面这段代码贴上去！ 除了这个还有一个要注意的地方，就是这个区分类别的标志要从0开始算，不然会报下面 这样的错误：",000001,999001,2,"当然你也可以在res/values/下另外创建一个ids.xml文件，把上面这段代码贴上去！ 除了这个还有一个要注意的地方，就是这个区分类别的标志要从0开始算，不然会报下面 这样的错误：",R.mipmap.ic_launcher,"小米"));
        mData.add(new Comment(100001,"2014年2月2日","当然你也可以在res/values/下另外创建一个ids.xml文件，把上面这段代码贴上去！ 除了这个还有一个要注意的地方，就是这个区分类别的标志要从0开始算，不然会报下面 这样的错误：",000001,999001,2,"当然你也可以在res/values/下另外创建一个ids.xml文件，把上面这段代码贴上去！ 除了这个还有一个要注意的地方，就是这个区分类别的标志要从0开始算，不然会报下面 这样的错误：",R.mipmap.ic_launcher,"小米"));
        mData.add(new Comment(100001,"2014年2月2日","当然你也可以在res/values/下另外创建一个ids.xml文件，把上面这段代码贴上去！ 除了这个还有一个要注意的地方，就是这个区分类别的标志要从0开始算，不然会报下面 这样的错误：",000001,999001,2,"当然你也可以在res/values/下另外创建一个ids.xml文件，把上面这段代码贴上去！ 除了这个还有一个要注意的地方，就是这个区分类别的标志要从0开始算，不然会报下面 这样的错误：",R.mipmap.ic_launcher,"小米"));
        mData.add(new Comment(100001,"2014年2月2日","当然你也可以在res/values/下另外创建一个ids.xml文件，把上面这段代码贴上去！ 除了这个还有一个要注意的地方，就是这个区分类别的标志要从0开始算，不然会报下面 这样的错误：",000001,999001,2,"当然你也可以在res/values/下另外创建一个ids.xml文件，把上面这段代码贴上去！ 除了这个还有一个要注意的地方，就是这个区分类别的标志要从0开始算，不然会报下面 这样的错误：",R.mipmap.ic_launcher,"小米"));
        mData.add(new Comment(100001,"2014年2月2日","当然你也可以在res/values/下另外创建一个ids.xml文件，把上面这段代码贴上去！ 除了这个还有一个要注意的地方，就是这个区分类别的标志要从0开始算，不然会报下面 这样的错误：",000001,999001,2,"当然你也可以在res/values/下另外创建一个ids.xml文件，把上面这段代码贴上去！ 除了这个还有一个要注意的地方，就是这个区分类别的标志要从0开始算，不然会报下面 这样的错误：",R.mipmap.ic_launcher,"小米"));
        mAdapter = new Comment_adapter((LinkedList<Comment>) mData, mContext);
        list_comment.setAdapter(mAdapter);

    }

}
