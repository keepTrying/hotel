package com.dreamfactory.hotelmanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

import com.dreamfactory.hotelmanager.R;

import cn.jpush.android.api.JPushInterface;

public class TestActivity extends Activity {

    Button ok;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ok=(Button)findViewById(R.id.button_ok);
        textView=(TextView)findViewById(R.id.textView_message);

        TextView tv = new TextView(this);
        tv.setText("用户自定义打开的Activity");
        Intent intent = getIntent();
        if (null != intent) {
	        Bundle bundle = getIntent().getExtras();
	        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
	        String content = bundle.getString(JPushInterface.EXTRA_ALERT);
//	        tv.setText("Title : " + title + "  " + "Content : " + content);
            textView.setText(content);
        }

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });

    }

}
