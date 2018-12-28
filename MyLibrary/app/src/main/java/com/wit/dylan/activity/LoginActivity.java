package com.wit.dylan.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.wit.dylan.entity.Users;
import com.wit.dylan.utils.HttpConnection;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private Button btn_login;
    private EditText edt_userName;
    private EditText edt_userPwd;
    private CheckBox rem_pw;
    private Users users = new Users();
    private String url = "http://123.57.222.123:9901/userInfo/login";
    private String param;
    private String json;
    private Intent intent;
    private Bundle bundle;
    private JSONObject jsonObject;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //界面全屏；
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.app_activity_login);

        //控件初始化；
        initView();

        //多选框监听事件；
        rem_pw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rem_pw.isChecked()){
                    System.out.println("------勾选记密码");
                    sp.edit().putBoolean("ISCHECK",true).commit();
                }
                else {
                    System.out.println("------未勾选记住密码");
                    sp.edit().putBoolean("ISCHECK", false).commit();
                }
            }
        });



    }

    //界面初始化
    void initView(){
        btn_login = (Button) findViewById(R.id.btn_login);
        edt_userName = (EditText) findViewById(R.id.edt_userName);
        edt_userPwd = (EditText) findViewById(R.id.edt_userPwd);
        rem_pw = (CheckBox) findViewById(R.id.id_checkbox);
        sp = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);

        //判断多选框状态：若未勾选，置编辑框为空；
        if (sp.getBoolean("ISCHECK",false)){
            rem_pw.setChecked(true);
            edt_userName.setText(sp.getString("USERNAME",""));
            edt_userPwd.setText(sp.getString("PASSWORD",""));
        }
    }



    //Button点击事件；
    void onClick(View view) {
        LoginHandler mHandler = new LoginHandler();
        if (TextUtils.isEmpty(edt_userPwd.getText()) || TextUtils.isEmpty(edt_userName.getText())) {
            Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        } else {

            SharedPreferences.Editor editor = sp.edit();
            editor.putString("USERNAME",edt_userName.getText().toString());
            editor.putString("PASSWORD",edt_userPwd.getText().toString());
            editor.commit();


            users.setUserNum(edt_userName.getText().toString());
            users.setUserPwd(edt_userPwd.getText().toString());
            param = "userNum=" + users.getUserNum() + "&userPwd=" + users.getUserPwd() + "&schoolNum=e55ae886-18a7-4b5e-9692-e62ad5ff7497";
            new HttpConnection(url, param, mHandler).start();
        }
    }


    //线程handler
    class LoginHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            json = (String) msg.obj;
            try {
                jsonObject = new JSONObject(json);
                if (jsonObject.has("object")) {
                    JSONObject jObject = jsonObject.getJSONObject("object");
                    users.setUserInfoId(jObject.getString("userInfoId"));
                    users.setOldPassword(edt_userPwd.getText().toString());
                    users.setUserName(jObject.getString("userName"));
                }
                if (jsonObject.getBoolean("success")) {
                    LoginActivity.this.intent = new Intent(LoginActivity.this, MainActivity.class);
                    bundle = new Bundle();
                    bundle.putSerializable("user", users);
                    LoginActivity.this.intent.putExtras(bundle);
                    startActivity(LoginActivity.this.intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

