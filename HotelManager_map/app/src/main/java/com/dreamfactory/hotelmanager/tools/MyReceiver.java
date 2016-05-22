package com.dreamfactory.hotelmanager.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.dreamfactory.hotelmanager.activity.TestActivity;

import cn.jpush.android.api.JPushInterface;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
    if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
       //用户点击打开了通知
        Bundle bundle = intent.getExtras();
        //打开自定义的Activity
        Intent i = new Intent(context, TestActivity.class);
        i.putExtras(bundle);
        //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
        context.startActivity(i);

    }
    }
}
