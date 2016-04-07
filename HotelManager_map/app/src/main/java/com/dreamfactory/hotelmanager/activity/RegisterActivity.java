package com.dreamfactory.hotelmanager.activity;


import android.app.Activity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.tools.SeverManager;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    // UI references.
    private EditText m_et_nick_name;
    private EditText m_et_years;
    private EditText m_et_email;
    private EditText m_et_pw;
    private EditText m_et_cpw;
    private EditText m_et_phone;
    private EditText m_et_id_num;
    private EditText m_et_name;
    private EditText m_et_que;
    private EditText m_et_ans;
    private RadioButton m_rb_male;
    private Button m_btn_submit;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        m_et_nick_name = (EditText) findViewById(R.id.nick_name_tv);
        m_et_years = (EditText) findViewById(R.id.years_tv);
        m_et_email = (EditText) findViewById(R.id.email_tv);
        m_et_pw = (EditText) findViewById(R.id.pw_tv);
        m_et_cpw = (EditText) findViewById(R.id.cpw_tv);
        m_et_phone = (EditText) findViewById(R.id.phone_tv);
        m_et_id_num = (EditText) findViewById(R.id.id_num_tv);
        m_et_name = (EditText) findViewById(R.id.name_tv);
        m_et_que = (EditText) findViewById(R.id.que_tv);
        m_et_ans = (EditText) findViewById(R.id.ans_tv);
        m_rb_male = (RadioButton) findViewById(R.id.radioButton);
        m_btn_submit = (Button) findViewById(R.id.submit);

//        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
//
//        m_et_nick_name.setText(sp.getString("user_nick",null));
//        m_et_years.setText(sp.getString("user_nick",null));
//        m_et_email.setText(sp.getString("user_nick",null));
//        m_et_pw.setText(sp.getString("user_nick",null));
//        m_et_cpw.setText(sp.getString("user_nick",null));
//        m_et_phone.setText(sp.getString("user_nick",null));
//        m_et_id_num.setText(sp.getString("user_nick",null));
//        m_et_name.setText(sp.getString("user_nick",null));
//        m_et_que.setText(sp.getString("user_nick",null));
        
        m_btn_submit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (m_et_nick_name.getText().toString().isEmpty()||m_et_years.getText().toString
                        ().isEmpty()||m_et_email.getText().toString().isEmpty()||m_et_pw.getText
                        ().toString().isEmpty()||m_et_cpw.getText().toString().isEmpty()
                        ||m_et_phone.getText().toString().isEmpty()||m_et_id_num.getText()
                        .toString().isEmpty()||m_et_name.getText().toString().isEmpty()
                        ||m_et_name.getText().toString().isEmpty()||m_et_que.getText().toString()
                        .isEmpty()||m_et_ans.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "请认真填写所有内容！", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (m_et_pw.getText().toString().equals(m_et_cpw.getText().toString())){
                    String male = m_rb_male.isSelected() ? 1 + "" : 0 + "";

                    SeverManager.getInstance(RegisterActivity.this, new SeverManager.Sever_call_back() {
                        @Override
                        public void onResponseSuccess(String obj) {
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onResponseError(int code) {
                            Toast.makeText(RegisterActivity.this, String.format("注册失败，错误码：%d",
                                    code), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onErrorResponse(String volleyError) {
                            Toast.makeText(RegisterActivity.this, String.format("注册失败，原因：%s",
                                    volleyError), Toast.LENGTH_SHORT).show();

                        }
                    }).user_register(RegisterActivity.this, m_et_nick_name.getText().toString(), male, m_et_years.getText().toString(), m_et_email.getText().toString(), m_et_phone.getText().toString(), m_et_id_num.getText().toString(), m_et_name.getText().toString(), m_et_que.getText().toString(), m_et_ans.getText().toString(), m_et_pw.getText().toString());

                } else {
                    Toast.makeText(RegisterActivity.this, "两次密码不一致！", Toast.LENGTH_SHORT).show();

                }
            }
        });
        

    }
}