package com.dreamfactory.hotelmanager.tools;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by foreever on 16/5/21.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);


    }


}
