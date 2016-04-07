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
import com.dreamfactory.hotelmanager.module.Indent;
import com.dreamfactory.hotelmanager.tools.SeverManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A login screen that offers login via email/password.
 */
public class GetAnsActivity extends AppCompatActivity {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */


    // UI references.
    private Button submit_btn;
    private EditText m_et_phone;
    private EditText m_et_id_num;
    private EditText m_et_name;
   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_answer);
        submit_btn = (Button) findViewById(R.id.button_submit);
        m_et_phone = (EditText) findViewById(R.id.phone_tv);
        m_et_id_num = (EditText) findViewById(R.id.id_num_tv);
        m_et_name = (EditText) findViewById(R.id.name_tv);
        
        submit_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (m_et_phone.getText().toString().isEmpty()||m_et_id_num.getText().toString
                        ().isEmpty()){
                    Toast.makeText(GetAnsActivity.this, "请认真填写所有内容！", Toast.LENGTH_SHORT).show();
                    return;
                }


                SeverManager.getInstance(GetAnsActivity.this, new SeverManager.Sever_call_back() {
                    @Override
                    public void onResponseSuccess(String obj) {
                        Toast.makeText(GetAnsActivity.this, "请输入问题答案", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(GetAnsActivity.this,AnswerActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onResponseError(int code) {
                        Toast.makeText(GetAnsActivity.this, String.format("验证失败，错误码：%d", code),
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onErrorResponse(String volleyError) {
                        Toast.makeText(GetAnsActivity.this, String.format("验证失败，原因：%s", volleyError), Toast.LENGTH_SHORT).show();

                    }
                }).security_get_question(GetAnsActivity.this,m_et_phone.getText().toString(),
                        m_et_id_num.getText().toString(),m_et_name.getText().toString());

            
            }
        });

    }
}
