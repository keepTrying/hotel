package com.dreamfactory.hotelmanager.activity;


import android.app.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.tools.SeverManager;

import java.util.ArrayList;
import java.util.List;

public class RestorePwActivity extends AppCompatActivity {

    // UI references.
    private Button submit_btn;
    private EditText m_et_pw;
    private EditText m_et_cpw;
    private String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restore_pw);
        submit_btn = (Button) findViewById(R.id.btn_submit);
        m_et_pw = (EditText) findViewById(R.id.et_pw);
        m_et_cpw = (EditText) findViewById(R.id.et_cpw);

        submit_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (m_et_pw.getText().toString().isEmpty() || m_et_cpw.getText().toString().isEmpty
                        ()) {
                    Toast.makeText(RestorePwActivity.this, "请认真填写所有内容！", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!m_et_pw.getText().toString().equals(m_et_cpw.getText().toString())){
                    Toast.makeText(RestorePwActivity.this, "两次密码不一致！", Toast.LENGTH_SHORT).show();
                    return;
                }


                SeverManager.getInstance(RestorePwActivity.this, new SeverManager.Sever_call_back() {
                    @Override
                    public void onResponseSuccess(String obj) {
                        Toast.makeText(RestorePwActivity.this, "修改密码成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RestorePwActivity.this, RestorePwActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onResponseError(int code) {
                        Toast.makeText(RestorePwActivity.this, String.format("修改密码失败，错误码：%d", code), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onErrorResponse(String volleyError) {
                        Toast.makeText(RestorePwActivity.this, String.format("修改密码失败，原因：%s", volleyError), Toast.LENGTH_SHORT).show();

                    }
                }).security_modify_password(RestorePwActivity.this, m_et_pw.getText().toString(),
                        user_id);


            }
        });

    }
}