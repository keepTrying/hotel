package com.dreamfactory.hotelmanager.tools;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.activity.LoginActivity;
import com.dreamfactory.hotelmanager.module.Indent;
import com.dreamfactory.hotelmanager.module.Room;
import com.dreamfactory.hotelmanager.module.User;
import com.dreamfactory.hotelmanager.module.Status;


import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by yangpeidong on 16/4/1.
 */
public class SeverManager{

    private static final String url_user_login = "http://42.96.148.66/hotel/user/login.php";
    private static final String url_user_register = "http://42.96.148.66/hotel/user/register.php";
    private static final String url_user_query = "http://42.96.148.66/hotel/user/query.php";
    private static final String url_user_alter = "http://42.96.148.66/hotel/user/alter.php";
    private static final String url_security_answer = "http://42.96.148.66/hotel/security/answer.php";
    private static final String url_security_get_question = "http://42.96.148.66/hotel/security/get_question.php";
    private static final String url_security_modify_password = "http://42.96.148.66/hotel/security/modify_password.php";
    private static final String url_room_add = "http://42.96.148.66/hotel/room/add.php";
    private static final String url_room_alter = "http://42.96.148.66/hotel/room/alter.php";
    private static final String url_room_query = "http://42.96.148.66/hotel/room/query.php";
    private static final String url_indent_order = "http://42.96.148.66/hotel/indent/order.php";
    private static final String url_indent_alter = "http://42.96.148.66/hotel/indent/alter.php";
    private static final String url_indent_query = "http://42.96.148.66/hotel/indent/query.php";
    private static final String url_comment_publish = "http://42.96.148.66/hotel/comment/publish.php";
    private static final String url_comment_alter = "http://42.96.148.66/hotel/comment/alter.php";
    private static final String url_comment_query = "http://42.96.148.66/hotel/comment/query.php";
    private static final String url_upload_img = "http://42.96.148.66/hotel/upload_pic.php";

    private AlertDialog dialog;

    public ProgressBar progressbar;
    public Context mContext;


    public interface Sever_call_back{
        void onResponseSuccess(String obj);
        void onResponseError(int code);
        void onErrorResponse(String volleyError);
    }

    private Sever_call_back callback;

    public void setCallback(Sever_call_back callback) {
        this.callback = callback;
    }

    private static SeverManager severManager = null;

    public static SeverManager getInstance(Context context,Sever_call_back callback) {
        return new SeverManager(context,callback);
    }

    private SeverManager(Context context,Sever_call_back callback) {
        this.mContext=context;
        this.showLoading();
        this.callback=callback;
    }

//    *******************************User******************************************************

    public void user_login(Context context, final String phone_num, final String passwd) {
        RequestQueue requestQueue= Volley.newRequestQueue(context);

        StringRequest stringR = new StringRequest( Request.Method.POST,
                url_user_login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String obj) {
//                        Status status = JSON.parseObject(obj, Status.class);
//                        if (status.getStatus()=="200") {
//                            Log.d("log in","success");
//                            User user = JSON.parseObject(obj, User.class);
//                            UserManager.getInstance().setUser(user);
//                        }else {
//                            Log.d("login","fail");
//                        }
                        SeverManager.this.onResponse(obj);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //错误处理
                        dissmissLoading();
                        Log.d("Error Message:", volleyError + "");
                        SeverManager.this.callback.onErrorResponse(volleyError
                                .getLocalizedMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //用来保存post参数
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("user_phone", phone_num);
                params.put("user_password",passwd);
                return params;
            }
        };


            requestQueue.add(stringR);
    }

    public void user_register(Context context, final String user_nick, final String user_gender,
                              final
    String user_years,final String user_email,final String user_phone,final String user_id_num, final
    String user_name,final String user_que,final String user_ans,final String user_password) {
        RequestQueue requestQueue= Volley.newRequestQueue(context);

        StringRequest stringR = new StringRequest( Request.Method.POST,
                url_user_register,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String obj) {

//                        Status status= JSON.parseObject(obj,Status.class);
//                        if (status.getStatus()=="200"){
//                            Log.d("register", "success");
//                        }else{
//                            Log.d("register","fail");
//                        }
                        SeverManager.this.onResponse(obj);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //错误处理
                        dissmissLoading();
                        Log.d("Error Message:",volleyError+"");
                        callback.onErrorResponse(volleyError.getLocalizedMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //用来保存post参数
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("user_nick", user_nick);
                params.put("user_gender",user_gender);
                params.put("user_years", user_years);
                params.put("user_email",user_email);
                params.put("user_phone", user_phone);
                params.put("user_id_num",user_id_num);
                params.put("user_name", user_name);
                params.put("user_que",user_que);
                params.put("user_ans", user_ans);
                params.put("user_password",user_password);
                return params;
            }
        };
        requestQueue.add(stringR);
    }

    public void user_alter(Context context, final String user_nick, final String user_gender,final
    String user_years,final String user_email,final String user_phone,final String user_id_num, final
                         String user_name) {
        RequestQueue requestQueue= Volley.newRequestQueue(context);

        StringRequest stringR = new StringRequest( Request.Method.POST,
                url_user_alter,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String obj) {

//                        Status status= JSON.parseObject(obj,Status.class);
//                        if (status.getStatus()=="200"){
//                            Log.d("alter","success");
//                        }else{
//                            Log.d("alter","fail");
//                        }
                        SeverManager.this.onResponse(obj);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //错误处理
                        dissmissLoading();
                        Log.d("Error Message:", volleyError + "");
                        callback.onErrorResponse(volleyError.getLocalizedMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //用来保存post参数
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("user_nick", user_nick);
                params.put("user_gender",user_gender);
                params.put("user_years", user_years);
                params.put("user_email",user_email);
                params.put("user_phone", user_phone);
                params.put("user_id_num",user_id_num);
                params.put("user_name", user_name);
                return params;
            }
        };
        requestQueue.add(stringR);
    }

    public void user_query(Context context, final String user_phone,final String user_id_num, final
                          String user_name) {
        RequestQueue requestQueue= Volley.newRequestQueue(context);

        StringRequest stringR = new StringRequest( Request.Method.POST,
                url_user_query,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String obj) {


//                        Status status= JSON.parseObject(obj,Status.class);
//                        if (status.getStatus()=="200"){
//                            Log.d("query","success");
//                            List<User> result=JSON.parseArray(obj,User.class);
//                        }else{
//                            Log.d("query","fail");
//
//                        }
                        SeverManager.this.onResponse(obj);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //错误处理
                        dissmissLoading();
                        Log.d("Error Message:",volleyError+"");
                        callback.onErrorResponse(volleyError.getLocalizedMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //用来保存post参数
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("user_phone", user_phone);
                params.put("user_id_num",user_id_num);
                params.put("user_name", user_name);
                params.put("page",0+"");
                params.put("num_page",999+"");
                return params;
            }
        };
        requestQueue.add(stringR);
    }

    //    *******************************Security
    // ******************************************************

    public void security_answer(Context context, final String user_ans,final String user_id) {
        RequestQueue requestQueue= Volley.newRequestQueue(context);

        StringRequest stringR = new StringRequest( Request.Method.POST,
                url_security_answer,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String obj) {


//                        Status status= JSON.parseObject(obj,Status.class);
//                        if (status.getStatus()=="200"){
//                            Log.d("answer","success");
//
//                        }else{
//                            Log.d("answer","fail");
//
//                        }
                        SeverManager.this.onResponse(obj);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //错误处理
                        dissmissLoading();
                        Log.d("Error Message:",volleyError+"");
                        callback.onErrorResponse(volleyError.getLocalizedMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //用来保存post参数
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("user_id_num",user_id);
                params.put("user_name", user_ans);
                return params;
            }
        };
        requestQueue.add(stringR);
    }

    public void security_get_question(Context context, final String user_phone,final String
            user_id_num,
                                final
                                String user_name) {
        RequestQueue requestQueue= Volley.newRequestQueue(context);

        StringRequest stringR = new StringRequest( Request.Method.POST,
                url_security_get_question,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String obj) {


//                        Status status= JSON.parseObject(obj,Status.class);
//                        if (status.getStatus()=="200"){
//                            Log.d("get_question","success");
//                            User user=JSON.parseObject(obj,User.class);
//                            String question=user.getUser_que();
//                            int id=user.getUser_id();
//
//                        }else{
//                            Log.d("get_question","fail");
//
//                        }
                        SeverManager.this.onResponse(obj);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //错误处理
                        dissmissLoading();
                        Log.d("Error Message:",volleyError+"");
                        callback.onErrorResponse(volleyError.getLocalizedMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //用来保存post参数
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("user_id_num",user_id_num);
                params.put("user_name", user_name);
                params.put("user_name", user_phone);

                return params;
            }
        };
        requestQueue.add(stringR);
    }

    public void security_modify_password(Context context, final String user_password,final String
            user_id) {
        RequestQueue requestQueue= Volley.newRequestQueue(context);

        StringRequest stringR = new StringRequest( Request.Method.POST,
                url_security_modify_password,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String obj) {


//                        Status status= JSON.parseObject(obj,Status.class);
//                        if (status.getStatus()=="200"){
//                            Log.d("modify password","success");
//
//
//                        }else{
//                            Log.d("modify password","fail");
//
//                        }
                        SeverManager.this.onResponse(obj);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //错误处理
                        dissmissLoading();
                        Log.d("Error Message:",volleyError+"");
                        callback.onErrorResponse(volleyError.getLocalizedMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //用来保存post参数
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("user_id_num",user_id);
                params.put("user_name", user_password);

                return params;
            }
        };
        requestQueue.add(stringR);
    }

    //    *******************************Room******************************************************

    public void room_add(Context context, final String room_num,final String
            room_type,final String
            room_area,final String
                                 room_cost,final String
                                 room_img) {
        RequestQueue requestQueue= Volley.newRequestQueue(context);

        StringRequest stringR = new StringRequest( Request.Method.POST,
                url_room_add,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String obj) {


//                        Status status= JSON.parseObject(obj,Status.class);
//                        if (status.getStatus()=="200"){
//                            Log.d("room add","success");
//
//
//                        }else{
//                            Log.d("room add","fail");
//
//                        }
                        SeverManager.this.onResponse(obj);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //错误处理
                        dissmissLoading();
                        Log.d("Error Message:",volleyError+"");
                        callback.onErrorResponse(volleyError.getLocalizedMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //用来保存post参数
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("room_area",room_area);
                params.put("room_cost", room_cost);
                params.put("room_num",room_num);
                params.put("room_img", room_img);
                params.put("room_type",room_type);

                return params;
            }
        };
        requestQueue.add(stringR);
    }

    public void room_alter(Context context, final String room_num,final String
            room_type,final String
                                 room_area,final String
                                 room_cost,final String
                                 room_img) {
        RequestQueue requestQueue= Volley.newRequestQueue(context);

        StringRequest stringR = new StringRequest( Request.Method.POST,
                url_room_alter,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String obj) {


//                        Status status= JSON.parseObject(obj,Status.class);
//                        if (status.getStatus()=="200"){
//                            Log.d("room alter","success");
//
//
//                        }else{
//                            Log.d("room alter","fail");
//
//                        }
                        SeverManager.this.onResponse(obj);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //错误处理
                        dissmissLoading();
                        Log.d("Error Message:",volleyError+"");
                        callback.onErrorResponse(volleyError.getLocalizedMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //用来保存post参数
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("room_area",room_area);
                params.put("room_cost", room_cost);
                params.put("room_num",room_num);
                params.put("room_img", room_img);
                params.put("room_type",room_type);

                return params;
            }
        };
        requestQueue.add(stringR);
    }

    public void room_query(Context context, final String room_num,final String
            room_type,final String
                                   room_area_min,final String
                                   room_area_max,final String
                                   room_cost_min,final String
            room_cost_max) {
        RequestQueue requestQueue= Volley.newRequestQueue(context);

        StringRequest stringR = new StringRequest( Request.Method.POST,
                url_room_query,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String obj) {


//                        Status status= JSON.parseObject(obj,Status.class);
//                        if (status.getStatus()=="200"){
//                            Log.d("room query","success");
//                            List<Room> rooms = JSON.parseArray(obj,Room.class);
//
//                        }else{
//                            Log.d("room query","fail");
//
//                        }
                        SeverManager.this.onResponse(obj);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //错误处理
                        dissmissLoading();
                        Log.d("Error Message:",volleyError+"");
                        callback.onErrorResponse(volleyError.getLocalizedMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //用来保存post参数
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("room_area_max",room_area_max);
                params.put("room_area_min",room_area_min);
                params.put("room_cost_max", room_cost_max);
                params.put("room_cost_min", room_cost_min);
                params.put("room_num",room_num);
                params.put("room_type",room_type);

                params.put("page",0+"");
                params.put("num_page",999+"");

                return params;
            }
        };
        requestQueue.add(stringR);
    }

    //    *******************************Indent
    // ******************************************************
    public void indent_order(Context context, final String time_begin,final String
            time_end,final String
                                   room_num,final String
                                   user_id,final String
                                   cost,final String
                                   indent_type) {
        RequestQueue requestQueue= Volley.newRequestQueue(context);

        StringRequest stringR = new StringRequest( Request.Method.POST,
                url_indent_order,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String obj) {

//                        Status status= JSON.parseObject(obj,Status.class);
//                        if (status.getStatus()=="200"){
//                            Log.d("indent order","success");
//
//
//                        }else{
//                            Log.d("indent order","fail");
//
//                        }
                        SeverManager.this.onResponse(obj);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //错误处理
                        dissmissLoading();
                        Log.d("Error Message:",volleyError+"");
                        callback.onErrorResponse(volleyError.getLocalizedMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //用来保存post参数
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("time_begin",time_begin);
                params.put("time_end",time_end);
                params.put("room_num", room_num);
                params.put("user_id", user_id);
                params.put("cost",cost);
                params.put("indent_type",indent_type);

                return params;
            }
        };
        requestQueue.add(stringR);
    }

    public void indent_alter(Context context, final String time_begin,final String
            time_end,final String
                                     room_num,final String
                                     user_id,final String
                                     cost,final String
                                     indent_type,final String
                                     pay,final String
                                     indent_status,final String
                                     indent_time,final String
                                     indent_id) {
        RequestQueue requestQueue= Volley.newRequestQueue(context);

        StringRequest stringR = new StringRequest( Request.Method.POST,
                url_indent_alter,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String obj) {

//                        Status status= JSON.parseObject(obj,Status.class);
//                        if (status.getStatus()=="200"){
//                            Log.d("indent alter","success");
//
//
//                        }else{
//                            Log.d("indent alter","fail");
//
//                        }
                        SeverManager.this.onResponse(obj);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //错误处理
                        dissmissLoading();
                        Log.d("Error Message:",volleyError+"");
                        callback.onErrorResponse(volleyError.getLocalizedMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //用来保存post参数
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("time_begin",time_begin);
                params.put("time_end",time_end);
                params.put("room_num", room_num);
                params.put("user_id", user_id);
                params.put("cost",cost);
                params.put("indent_time",indent_time);
                params.put("indent_id",indent_id);
                params.put("indent_status",indent_status);
                params.put("indent_type", indent_type);
                params.put("pay", pay);

                return params;
            }
        };
        requestQueue.add(stringR);
    }

    public void indent_query(Context context, final String time_begin,final String
            time_end,final String
                                     room_num,final String
                                     user_id,final String
                                     cost,final String
                                     indent_type,final String
                                     pay,final String
                                     indent_status,final String
                                     indent_time,final String
                                     indent_id) {
        RequestQueue requestQueue= Volley.newRequestQueue(context);

        StringRequest stringR = new StringRequest( Request.Method.POST,
                url_indent_query,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String obj) {

//                        Status status= JSON.parseObject(obj,Status.class);
//                        if (status.getStatus()=="200"){
//                            Log.d("indent query","success");
//                            List<Indent> results = JSON.parseArray(obj,Indent.class);
//
//                        }else{
//                            Log.d("indent query","fail");
//
//                        }
                        SeverManager.this.onResponse(obj);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //错误处理
                        dissmissLoading();
                        Log.d("Error Message:",volleyError+"");
                        callback.onErrorResponse(volleyError.getLocalizedMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //用来保存post参数
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("time_begin",time_begin);
                params.put("time_end",time_end);
                params.put("room_num", room_num);
                params.put("user_id", user_id);
                params.put("cost",cost);
                params.put("indent_time",indent_time);
                params.put("indent_id",indent_id);
                params.put("indent_status",indent_status);
                params.put("indent_type", indent_type);
                params.put("pay", pay);

                params.put("page",0+"");
                params.put("num_page",999+"");

                return params;
            }
        };
        requestQueue.add(stringR);
    }
    //    *******************************Comment
    // ******************************************************
    public void comment_publish(Context context, final String user_id,final String
            comment_text,final String
                                     room_num,final String
                                     comment_star) {
        RequestQueue requestQueue= Volley.newRequestQueue(context);

        StringRequest stringR = new StringRequest( Request.Method.POST,
                url_comment_publish,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String obj) {

//                        Status status= JSON.parseObject(obj,Status.class);
//                        if (status.getStatus()=="200"){
//                            Log.d("comment publish","success");
//
//                        }else{
//                            Log.d("comment publish","fail");
//
//                        }
                        SeverManager.this.onResponse(obj);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //错误处理
                        dissmissLoading();
                        Log.d("Error Message:",volleyError+"");
                        callback.onErrorResponse(volleyError.getLocalizedMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //用来保存post参数
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("comment_star",comment_star);
                params.put("comment_text",comment_text);
                params.put("user_id", user_id);
                params.put("room_num", room_num);

                return params;
            }
        };
        requestQueue.add(stringR);
    }

    public void comment_alter(Context context, final String user_id,final String
            comment_text,final String
                                        room_num,final String
                                        comment_star,final String
                                      comment_time,final String
                                      comment_id,final String
                                      comment_replay) {
        RequestQueue requestQueue= Volley.newRequestQueue(context);

        StringRequest stringR = new StringRequest( Request.Method.POST,
                url_comment_alter,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String obj) {

//                        Status status= JSON.parseObject(obj,Status.class);
//                        if (status.getStatus()=="200"){
//                            Log.d("comment alter","success");
//
//                        }else{
//                            Log.d("comment alter","fail");
//
//                        }
                        SeverManager.this.onResponse(obj);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //错误处理
                        dissmissLoading();
                        Log.d("Error Message:",volleyError+"");
                        callback.onErrorResponse(volleyError.getLocalizedMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //用来保存post参数
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("comment_star",comment_star);
                params.put("comment_text",comment_text);
                params.put("user_id", user_id);
                params.put("room_num", room_num);
                params.put("comment_id",comment_id);
                params.put("comment_time", comment_time);
                params.put("comment_replay", comment_replay);

                return params;
            }
        };
        requestQueue.add(stringR);
    }
    public void comment_query(Context context, final String user_id,final String
            comment_text,final String
                                      room_num,final String
                                      comment_star,final String
                                      comment_time_start,final String
                                      comment_time_end) {
        RequestQueue requestQueue= Volley.newRequestQueue(context);

        StringRequest stringR = new StringRequest( Request.Method.POST,
                url_comment_query,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String obj) {

//                        Status status= JSON.parseObject(obj,Status.class);
//                        if (status.getStatus()=="200"){
//                            Log.d("comment query","success");
//
//                        }else{
//                            Log.d("comment query","fail");
//
//                        }
                        SeverManager.this.onResponse(obj);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //错误处理
                        dissmissLoading();
                        Log.d("Error Message:",volleyError+"");
                        callback.onErrorResponse(volleyError.getLocalizedMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //用来保存post参数
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("comment_star",comment_star);
                params.put("comment_text",comment_text);
                params.put("user_id", user_id);
                params.put("room_num", room_num);
                params.put("comment_id",comment_time_end);
                params.put("comment_time", comment_time_start);

                params.put("page",0+"");
                params.put("num_page",999+"");

                return params;
            }
        };
        requestQueue.add(stringR);
    }

    //    *******************************Image
    // ******************************************************
    public void upload_img(Context context, final Bitmap bitmap) {
        RequestQueue requestQueue= Volley.newRequestQueue(context);

        StringRequest stringR = new StringRequest( Request.Method.POST,
                url_upload_img,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String obj) {
//                        Status status= JSON.parseObject(obj,Status.class);
//                        if (status.getStatus()=="200"){
//                            Log.d("upload image","success");
//
//                        }else{
//                            Log.d("upload image","fail");
//
//                        }
                        SeverManager.this.onResponse(obj);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //错误处理
                        dissmissLoading();
                        Log.d("Error Message:",volleyError+"");
                        callback.onErrorResponse(volleyError.getLocalizedMessage());
                    }
                }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap <String, String> headers = new HashMap<String,String>();
                headers.put("Content-Type","multipart/form-data;");
                return headers;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return Bitmap2Bytes(bitmap);
            }

            @Override
            public String getBodyContentType() {
                return super.getBodyContentType();

            }
        };
        requestQueue.add(stringR);
    }

    public static void loadImage(Context context,NetworkImageView networkImageView,String url,int
            img_default,int img_fail){
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String s) {
                return null;
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {

            }
        });

        networkImageView.setDefaultImageResId(img_default);
        networkImageView.setErrorImageResId(img_fail);
        networkImageView.setImageUrl(url,imageLoader);
    }


    public byte[] Bitmap2Bytes(Bitmap bm) {
                 ByteArrayOutputStream baos = new ByteArrayOutputStream();
                 bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
                 return baos.toByteArray();
             }


    public void onResponse(String obj){

        Object atr2=JSON.parse(obj);
        com.alibaba.fastjson.JSONObject status2 = JSON.parseObject(obj);
        Status status= JSON.parseObject(obj,Status.class);
        if (status.getStatus().equals("200")){
            callback.onResponseSuccess(status.getData());
            dissmissLoading();
        }else{

            callback.onResponseError(Integer.valueOf(status.getStatus()).intValue());
            dissmissLoading();
            
        }
    }

    public void showLoading(){
        dialog = new AlertDialog.Builder(mContext).setCancelable(false).setView(R.layout
                .loading).show();

//        WindowManager.LayoutParams layoutParams=new WindowManager.LayoutParams();
//        layoutParams.=0.5f;
//        dialog.getWindow().setAttributes(layoutParams);
    }

    public void dissmissLoading(){
        dialog.dismiss();
    }
}
