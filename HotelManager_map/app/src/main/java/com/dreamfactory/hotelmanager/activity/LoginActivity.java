package com.dreamfactory.hotelmanager.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.tools.SeverManager;

import org.json.JSONObject;

import java.util.Map;
import java.util.Set;


public class LoginActivity extends AppCompatActivity implements OnClickListener {

    // UI references.
    private AutoCompleteTextView mEmailView;
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


    }

    @Override
    public void onClick(View v) {
        if (v==login){
            SeverManager.getInstance().login(this,"1332232223","34323432344refee3feeeeeeeeeeefde");
        }else if (v==register){
            Intent intent =new Intent(this,RegisterActivity.class);
            startActivity(intent);
        }else if(v==forget){
            Intent intent =new Intent(this,GetAnsActivity.class);
            startActivity(intent);
        }
    }
}

