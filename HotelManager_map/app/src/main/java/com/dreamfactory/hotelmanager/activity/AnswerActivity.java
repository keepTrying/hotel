package com.dreamfactory.hotelmanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.tools.SeverManager;

public class AnswerActivity extends Activity {

    // UI references.
    private Button submit_btn;
    private TextView m_tv_que;
    private EditText m_et_ans;
    private String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        submit_btn = (Button) findViewById(R.id.button_submit);
        m_tv_que = (TextView) findViewById(R.id.phone_tv);
        m_et_ans = (EditText) findViewById(R.id.id_num_tv);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (m_et_ans.getText().toString().isEmpty()) {
                    Toast.makeText(AnswerActivity.this, "请认真填写！", Toast.LENGTH_SHORT).show();
                    return;
                }


                SeverManager.getInstance(AnswerActivity.this, new SeverManager.Sever_call_back() {
                    @Override
                    public void onResponseSuccess(String obj) {
                        Toast.makeText(AnswerActivity.this, "请设置新密码", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AnswerActivity.this, RestorePwActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onResponseError(int code) {
                        Toast.makeText(AnswerActivity.this, String.format("验证失败，错误码：%d", code), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onErrorResponse(String volleyError) {
                        Toast.makeText(AnswerActivity.this, String.format("验证失败，原因：%s", volleyError), Toast.LENGTH_SHORT).show();

                    }
                }).security_answer(AnswerActivity.this, m_et_ans.getText().toString(), user_id);


            }
        });

    }
}
