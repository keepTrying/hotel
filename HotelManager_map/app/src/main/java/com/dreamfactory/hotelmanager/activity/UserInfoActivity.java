package com.dreamfactory.hotelmanager.activity;


import android.app.Activity;

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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.module.User;
import com.dreamfactory.hotelmanager.tools.SeverManager;
import com.dreamfactory.hotelmanager.tools.UserManager;

import java.util.ArrayList;
import java.util.List;

public class UserInfoActivity extends AppCompatActivity {

    // UI references.
    private EditText m_et_nick_name;
    private EditText m_et_years;
    private EditText m_et_email;
    private EditText m_et_phone;
    private EditText m_et_id_num;
    private EditText m_et_name;
    private RadioButton m_rb_male;
    private Button m_btn_submit;

    private User me;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);

        m_et_nick_name = (EditText) findViewById(R.id.editText_name);
        m_et_years = (EditText) findViewById(R.id.editText_years);
        m_et_email = (EditText) findViewById(R.id.editText_email);
        m_et_phone = (EditText) findViewById(R.id.editText_phone);
        m_et_id_num = (EditText) findViewById(R.id.editText_id_num);
        m_et_name = (EditText) findViewById(R.id.editText_name);
        m_rb_male = (RadioButton) findViewById(R.id.radioButton_male);
        m_btn_submit = (Button) findViewById(R.id.button_modify);

        me= UserManager.getInstance(UserInfoActivity.this).getUser();

        m_et_nick_name.setText(me.getUser_nick());
        m_et_years.setText(me.getUser_years());
        m_et_email.setText(me.getUser_email());
        m_et_phone.setText(me.getUser_phone());
        m_et_id_num.setText(me.getUser_id_num());
        m_et_name.setText(me.getUser_name());
        m_rb_male.setSelected(me.getUser_gender()==0?true:false);

        m_btn_submit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                m_btn_submit.setEnabled(false);
                if (m_et_nick_name.getText().toString().isEmpty()||m_et_years.getText().toString
                        ().isEmpty()||m_et_email.getText().toString().isEmpty()
                        ||m_et_phone.getText().toString().isEmpty()||m_et_id_num.getText()
                        .toString().isEmpty()||m_et_name.getText().toString().isEmpty()
                        ||m_et_name.getText().toString().isEmpty()){
                    Toast.makeText(UserInfoActivity.this, "请认真填写所有内容！", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                    final String gender = m_rb_male.isSelected() ? 1 + "" : 0 + "";

                    SeverManager.getInstance(UserInfoActivity.this, new SeverManager.Sever_call_back() {
                        @Override
                        public void onResponseSuccess(String obj) {
                            me.setUser_nick(m_et_nick_name.getText().toString());
                            me.setUser_years(Integer.parseInt(m_et_years.getText().toString()));
                            me.setUser_email(m_et_email.getText().toString());
                            me.setUser_phone(Integer.parseInt(m_et_phone.getText().toString()));
                            me.setUser_id_num(m_et_id_num.getText().toString());
                            me.setUser_name(m_et_name.getText().toString());
                            me.setUser_gender(Integer.parseInt(gender));

                            UserManager.getInstance(UserInfoActivity.this).setUser(me);

                            Toast.makeText(UserInfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                            m_btn_submit.setEnabled(true);
                        }

                        @Override
                        public void onResponseError(int code) {
                            Toast.makeText(UserInfoActivity.this, String.format("修改失败，错误码：%d",
                                    code), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onErrorResponse(String volleyError) {
                            Toast.makeText(UserInfoActivity.this, String.format("修改失败，原因：%s",
                                    volleyError), Toast.LENGTH_SHORT).show();

                        }
                    }).user_alter(UserInfoActivity.this, m_et_nick_name.getText().toString(), gender, m_et_years.getText().toString(), m_et_email.getText().toString(), m_et_phone.getText().toString(), m_et_id_num.getText().toString(), m_et_name.getText().toString());

                
            }
        });


    }
}
