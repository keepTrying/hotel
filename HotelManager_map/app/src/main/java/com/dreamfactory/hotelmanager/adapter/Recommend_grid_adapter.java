package com.dreamfactory.hotelmanager.adapter;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;

        import com.android.volley.toolbox.NetworkImageView;
        import com.dreamfactory.hotelmanager.R;
        import com.dreamfactory.hotelmanager.module.RecommendItem;
        import com.dreamfactory.hotelmanager.module.Room;
        import com.dreamfactory.hotelmanager.tools.SeverManager;

        import java.util.ArrayList;
        import java.util.LinkedList;

/**
 * Created by yangpeidong on 16/3/25.
 */
public class Recommend_grid_adapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Room> mData;

    public Recommend_grid_adapter(Context mContext, ArrayList<Room> mData) {
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
        convertView = LayoutInflater.from(mContext).inflate(R.layout.room_grid_item,parent,false);

        NetworkImageView imageView = (NetworkImageView) convertView.findViewById(R.id.room_grid_item_img);
        TextView tv_room_num = (TextView) convertView.findViewById(R.id.room_grid_item_room_num);
        TextView tv_room_price = (TextView) convertView.findViewById(R.id.room_grid_item_price);

        SeverManager.loadImage(mContext,imageView,mData.get(position).getRoom_img(),R.drawable.nopig,R.drawable.nopig);
        tv_room_num.setText(mData.get(position).getRoom_num()+"");
        tv_room_price.setText(mData.get(position).getRoom_cost()+"元/天");

        return convertView;
    }


}