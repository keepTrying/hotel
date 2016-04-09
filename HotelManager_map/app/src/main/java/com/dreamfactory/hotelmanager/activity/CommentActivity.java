package com.dreamfactory.hotelmanager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.module.Comment;
import com.dreamfactory.hotelmanager.tools.SeverManager;
import com.dreamfactory.hotelmanager.tools.TimeHelper;
import com.dreamfactory.hotelmanager.tools.UserManager;

import java.net.NetworkInterface;

public class CommentActivity extends Activity {

    private RatingBar ratingBar;
    private NetworkImageView networkImageView;
    private TextView tv_user_name;
    private EditText et_comment_content;
    private TextView tv_comment_time;
    private Button btn_commit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        final String room_num = getIntent().getStringExtra("room_num");

        ratingBar = (RatingBar)findViewById(R.id.comment_rate);
        networkImageView = (NetworkImageView)findViewById(R.id.comment_pic);
        et_comment_content=(EditText)findViewById(R.id.comment_content);
        tv_comment_time=(TextView)findViewById(R.id.comment_time);
        tv_user_name=(TextView)findViewById(R.id.comment_name);

        networkImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        tv_user_name.setText(UserManager.getInstance(CommentActivity.this).getUser().getUser_name());
        tv_comment_time.setText(TimeHelper.getStringDate());

        btn_commit=(Button)findViewById(R.id.button_commit);
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeverManager.getInstance(CommentActivity.this, new SeverManager.Sever_call_back() {
                    @Override
                    public void onResponseSuccess(String obj) {
                        Toast.makeText(CommentActivity.this,"评论成功!",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onResponseError(int code) {
                        Toast.makeText(CommentActivity.this,String.format("评论失败!错误码：%d",code),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onErrorResponse(String volleyError) {
                        Toast.makeText(CommentActivity.this,String.format("评论失败!错误：%s",volleyError),Toast.LENGTH_SHORT).show();
                    }
                }).comment_publish(CommentActivity.this, UserManager.getInstance(CommentActivity.this).getUser().getUser_id()+"",et_comment_content.getText().toString(),room_num,ratingBar.getRating()+"");
            }
        });
    }
}
