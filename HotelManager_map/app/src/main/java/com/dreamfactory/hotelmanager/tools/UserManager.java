package com.dreamfactory.hotelmanager.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.util.Log;

import com.dreamfactory.hotelmanager.module.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by yangpeidong on 16/4/1.
 */
public class UserManager{
    final static String user_file = "me.dat";
//    private User mUser=null;
    private static UserManager ourInstance = null;
//    private SharedPreferences mPreferences = null;
    private Context mContext=null;
//    SharedPreferences.Editor mEditor=null;

    public static UserManager getInstance(Context context) {
        if (ourInstance==null){
            ourInstance= new UserManager(context);
        }
        return ourInstance;
    }

    private UserManager(Context context) {
//        mPreferences=context.getSharedPreferences("user",Context.MODE_PRIVATE);
//        mEditor=mPreferences.edit();
        mContext=context;

    }
    public User getUser() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = mContext.openFileInput(user_file);
            ois = new ObjectInputStream(fis);
            return (User)ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            //这里是读取文件产生异常
        } finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    //fis流关闭异常
                    e.printStackTrace();
                }
            }
            if (ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    //ois流关闭异常
                    e.printStackTrace();
                }
            }
        }
        //读取产生异常，返回null

        return null;
    }

    public void setUser(User user) {

//        mEditor.putInt("user_id", user.getUser_id());
//        mEditor.putString("user_nick", user.getUser_nick());
//        mEditor.putInt("user_type", user.getUser_type());
//        mEditor.putInt("user_gender", user.getUser_gender());
//        mEditor.putInt("user_years", user.getUser_years());
//        mEditor.putString("user_email", user.getUser_email());
//        mEditor.putString("user_time", user.getUser_time());
//        mEditor.putInt("user_point", user.getUser_point());
//        mEditor.putString("user_id_num", user.getUser_id_num());
//        mEditor.putString("user_name", user.getUser_name());
//
//        mEditor.commit();

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = mContext.openFileOutput(user_file, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(user);
        } catch (Exception e) {
            e.printStackTrace();
            //这里是保存文件产生异常
        } finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    //fos流关闭异常
                    e.printStackTrace();
                }
            }
            if (oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    //oos流关闭异常
                    e.printStackTrace();
                }
            }
        }

    }

    public boolean deleteMyData(){
        File file =new File(user_file);

        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                file.delete(); //删除
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    files[i].delete(); // 把每个文件 用这个方法进行迭代
                }
                file.delete();
            }
            if (file.exists()) return false;
            else return true;

        } else {
            Log.e("delete user data error:","文件不存在!");
            return false;
        }
    }


}
