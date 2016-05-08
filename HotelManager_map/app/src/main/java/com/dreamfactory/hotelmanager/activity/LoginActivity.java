package com.dreamfactory.hotelmanager.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.module.User;
import com.dreamfactory.hotelmanager.tools.SeverManager;
import com.dreamfactory.hotelmanager.tools.UserManager;

import org.json.JSONObject;

import java.util.Map;
import java.util.Set;


public class LoginActivity extends AppCompatActivity implements OnClickListener {

    // UI references.
    private AutoCompleteTextView mPhoneView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button login;
    private Button register;
    private Button forget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=(Button)findViewById(R.id.btn_sign_in);
        register=(Button)findViewById(R.id.btn_register);
        forget=(Button)findViewById(R.id.btn_forget_pw);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        forget.setOnClickListener(this);

        mPhoneView=(AutoCompleteTextView)findViewById(R.id.phone_tv);
        mPasswordView=(EditText)findViewById(R.id.pw_tv);

        mPhoneView.setText("15708445678");
        mPasswordView.setText("123456");


    }

    @Override
    public void onClick(View v) {
        if (v==login){
            SeverManager.getInstance(this,new SeverManager.Sever_call_back(){
                @Override
                public void onResponseSuccess(String obj) {
                    Log.d("log in", "success");
                    User user = JSON.parseObject(obj,User.class);
                    UserManager.getInstance(LoginActivity.this).setUser(user);

                    SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
                    SharedPreferences.Editor mEditor = sp.edit();
                    mEditor.putBoolean("login", true);

                    mEditor.commit();

                    Toast.makeText(LoginActivity.this,"登录成功",Toast
                            .LENGTH_SHORT)
                            .show();
                    finish();
                }

                @Override
                public void onResponseError(int code) {
                    Log.d("login","fail");
                    Toast.makeText(LoginActivity.this,String.format("fail，code：%d",code),Toast
                            .LENGTH_SHORT)
                            .show();
                }

                @Override
                public void onErrorResponse(String volleyError) {
                    Log.d("login","fail");
                    Toast.makeText(LoginActivity.this,volleyError,Toast.LENGTH_SHORT).show();
                }
            }).user_login(this,mPhoneView.getText().toString(),
                    mPasswordView.getText().toString());
        }else if (v==register){
            Intent intent =new Intent(this,RegisterActivity.class);
            startActivity(intent);
        }else if(v==forget){
            Intent intent =new Intent(this,GetAnsActivity.class);
            startActivity(intent);
        }
    }
}

