package com.dreamfactory.hotelmanager.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.module.Question;
import com.dreamfactory.hotelmanager.tools.SeverManager;

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

                        Question question=JSON.parseObject(obj, Question.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("user_id", question.getUser_id());
                        bundle.putString("user_que", question.getUser_que());
                        Intent intent = new Intent(GetAnsActivity.this,AnswerActivity.class);
                        intent.putExtras(bundle);
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
                }).security_get_question(GetAnsActivity.this, m_et_phone.getText().toString(),
                        m_et_id_num.getText().toString(), m_et_name.getText().toString());

            
            }
        });

    }
}
