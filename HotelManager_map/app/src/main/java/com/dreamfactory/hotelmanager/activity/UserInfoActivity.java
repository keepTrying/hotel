package com.dreamfactory.hotelmanager.activity;


import android.app.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.provider.MediaStore;
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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.dreamfactory.hotelmanager.R;
import com.dreamfactory.hotelmanager.module.User;
import com.dreamfactory.hotelmanager.tools.SeverManager;
import com.dreamfactory.hotelmanager.tools.UserManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserInfoActivity extends AppCompatActivity {

    // UI references.
    private EditText m_et_nick_name;
    private EditText m_et_years;
    private EditText m_et_email;
    private EditText m_et_phone;
    private EditText m_et_id_num;
    private EditText m_et_name;
    private RadioButton m_rb_male;
    private RadioGroup m_rg;
    private Button m_btn_submit;
//    private NetworkImageView m_img;
    private ImageView m_img2;

    private  int img_position;
//    private Bitmap mBitmap;
    private Context mContext;
    private User me;
    private Uri mOutPutFileUri;
    private int m_gender;
    private final int PHOTO_REQUEST_GALLERY = 0x9901;
    private final int PHOTO_REQUEST_CAREMA = 0x9902;
    private final int PHOTO_REQUEST_CUT = 0x9903;

    private final String imgUrl="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);

        mContext = UserInfoActivity.this;

        m_et_nick_name = (EditText) findViewById(R.id.editText_nick);
        m_et_years = (EditText) findViewById(R.id.editText_years);
        m_et_email = (EditText) findViewById(R.id.editText_email);
        m_et_phone = (EditText) findViewById(R.id.editText_phone);
        m_et_id_num = (EditText) findViewById(R.id.editText_id_num);
        m_et_name = (EditText) findViewById(R.id.editText_name);
        m_rb_male = (RadioButton) findViewById(R.id.radioButton_male);
        m_btn_submit = (Button) findViewById(R.id.button_modify);
//        m_img=(NetworkImageView) findViewById(R.id.image_user);
        m_img2=(ImageView) findViewById(R.id.image_user2);
        m_rg = (RadioGroup) findViewById(R.id.radiogroup);

        m_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==m_rb_male.getId()){
                    m_gender=1;
                }else{
                    m_gender=2;
                }
            }
        });

        me= UserManager.getInstance(UserInfoActivity.this).getUser();

        m_gender=me.getUser_gender();
        img_position=me.getUser_img()-1;

        m_et_nick_name.setText(me.getUser_nick());
        m_et_years.setText(me.getUser_years()+"");
        m_et_email.setText(me.getUser_email());
        m_et_phone.setText(me.getUser_phone()+"");
        m_et_id_num.setText(me.getUser_id_num());
        m_et_name.setText(me.getUser_name());
        m_rg.check(m_gender == 1 ? R.id.radioButton_male : R.id.radioButton_female);
        m_img2.setImageResource(ChoosePic.icons[img_position]);

//        SeverManager.loadImage(mContext,m_img,me.getUser_img(),R.mipmap.ic_launcher,R.mipmap.ic_launcher);
        m_img2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                CharSequence[] items = {"相册", "相机"};
//                CharSequence[] items = {"相册"};
//                new AlertDialog.Builder(mContext)
//                        .setTitle("选择图片来源")
//                        .setItems(items, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                if( which == 0 ){
//                                    gallery();
//                                }else{
//                                    camera();
//                                }
//                            }
//                        })
//                        .create().show();
                Intent intent=new Intent(mContext,ChoosePic.class);
                startActivityForResult(intent,ChoosePic.PUT_KEY);
            }
        });

        m_btn_submit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                m_btn_submit.setEnabled(false);
                String text=null;
                if (m_et_nick_name.getText().toString().isEmpty()||m_et_years.getText().toString
                        ().isEmpty()||m_et_email.getText().toString().isEmpty()
                        ||m_et_phone.getText().toString().isEmpty()||m_et_id_num.getText()
                        .toString().isEmpty()||m_et_name.getText().toString().isEmpty()
                        ||m_et_name.getText().toString().isEmpty()){
                   text="请认真填写所有内容！";
                }
                if (m_et_nick_name.getText().toString().length()>40){
                    text="昵称过长，最多允许40个字符！";
                }
                if(Integer.parseInt(m_et_years.getText().toString())<18){
                    text="对不起，用户年龄最小为18周岁！";
                }
                if(Integer.parseInt(m_et_years.getText().toString())>130){
                    text="请核对年龄信息！";
                }

                if (text!=null){
                    Toast.makeText(UserInfoActivity.this, text, Toast.LENGTH_SHORT).show();
                    m_btn_submit.setEnabled(true);
                    return;
                }


                    SeverManager.getInstance(UserInfoActivity.this, new SeverManager.Sever_call_back() {
                        @Override
                        public void onResponseSuccess(String obj) {
                            me.setUser_nick(m_et_nick_name.getText().toString());
                            me.setUser_years(Integer.parseInt(m_et_years.getText().toString()));
                            me.setUser_email(m_et_email.getText().toString());
                            me.setUser_phone(Integer.parseInt(m_et_phone.getText().toString()));
                            me.setUser_id_num(m_et_id_num.getText().toString());
                            me.setUser_name(m_et_name.getText().toString());
                            me.setUser_gender(m_gender);
                            me.setUser_img(img_position+1);

                            UserManager.getInstance(UserInfoActivity.this).setUser(me);

                            Toast.makeText(UserInfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                            m_btn_submit.setEnabled(true);
                        }

                        @Override
                        public void onResponseError(int code) {
                            Toast.makeText(UserInfoActivity.this, String.format("修改失败，错误码：%d",
                                    code), Toast.LENGTH_SHORT).show();
                            m_btn_submit.setEnabled(true);
                        }

                        @Override
                        public void onErrorResponse(String volleyError) {
                            Toast.makeText(UserInfoActivity.this, String.format("修改失败，原因：%s",
                                    volleyError), Toast.LENGTH_SHORT).show();
                            m_btn_submit.setEnabled(true);

                        }
                    }).user_alter(UserInfoActivity.this, m_et_nick_name.getText().toString(),
                            m_gender+"", m_et_years.getText().toString(), m_et_email.getText()
                                    .toString(), m_et_phone.getText().toString(), m_et_id_num
                                    .getText().toString(), m_et_name.getText().toString(),
                            img_position+1+"",me.getUser_point()+"");

                
            }
        });


    }

//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode == RESULT_OK){
//            //选择图片
//            Uri uri = data.getData();
//            ContentResolver cr = this.getContentResolver();
//            try {
//                if(mBitmap != null)//如果不释放的话，不断取图片，将会内存不够
//                    mBitmap.recycle();
//                mBitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
//            } catch (FileNotFoundException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            System.out.println("the mBitmap toString: " + mBitmap);
//            m_img.setImageBitmap(mBitmap);
//        }else{
//            Toast.makeText(mContext, "请重新选择图片", Toast.LENGTH_SHORT).show();
//        }
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }

        }
        else if (requestCode == PHOTO_REQUEST_CAREMA) {


                crop(mOutPutFileUri);



        }
        else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
//                mBitmap = data.getParcelableExtra("data");
//                m_img2.setImageBitmap(mBitmap);

                new AlertDialog.Builder(mContext)
                        .setMessage("确认要上传头像图片吗？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                SeverManager.upload_img(mContext,mBitmap,UserManager.getInstance(mContext).getUser().getUser_img());

                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();

//                Paint p = new Paint();
//                p.setAntiAlias(true); //去锯齿
//                p.setColor(Color.BLACK);
//                p.setStyle(Paint.Style.STROKE);
//                Canvas canvas = new Canvas(bitmap); //bitmap就是我们原来的图,比如头像
//                p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN)); //因为我们先画了图所以DST_IN
//                int radius = bitmap.getWidth(); //假设图片是正方形的
//                canvas.drawCircle(radius, radius, radius, p); //r=radius, 圆心(r,r)


            }
            try {
                // 将临时文件删除
//                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else if(requestCode==ChoosePic.PUT_KEY){
            img_position=data.getIntExtra("image", 0);
            m_img2.setImageResource(ChoosePic.icons[img_position]);

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /*
	 * 剪切图片
	 */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 70);
        intent.putExtra("outputY", 70);
        intent.putExtra("scale", true);//黑边
        intent.putExtra("scaleUpIfNeeded", true);//黑边
        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    /*
	 * 从相册获取
	 */
    public void gallery() {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    /*
     * 从相机获取
     */
    public void camera() {
        // 激活相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //文件夹hotel_user_img
        String path = getFilesDir().toString()+"/hotel_user_img";
        File path1 = new File(path);
        if (!path1.exists()) {
            path1.mkdir();
        }
        File file = new File(path1,System.currentTimeMillis()+".jpg");
        mOutPutFileUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mOutPutFileUri);
        startActivityForResult(intent, PHOTO_REQUEST_CAREMA);

    }


}
