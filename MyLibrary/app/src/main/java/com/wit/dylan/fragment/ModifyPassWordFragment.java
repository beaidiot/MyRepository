package com.wit.dylan.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wit.dylan.activity.R;
import com.wit.dylan.entity.Users;
import com.wit.dylan.utils.HttpConnection;


public class ModifyPassWordFragment extends Fragment {

    private EditText edt_oldPwd;
    private EditText edt_newPwd;
    private EditText edt_newPwdCheck;
    private Button btn_pwdCheck;


    private Users users;
    private String url = "http://123.57.222.123:9901/userInfo/current/password";
    private String param;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_modify_pass_word, container, false);


        init(view);


        btn_pwdCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModifyPwdHandler mHandler = new ModifyPwdHandler();
                if (TextUtils.isEmpty(edt_oldPwd.getText()) || TextUtils.isEmpty(edt_newPwd.getText()) || TextUtils.isEmpty(edt_newPwdCheck.getText())) {
                    Toast.makeText(view.getContext(), "非填字段不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    if (!edt_oldPwd.getText().toString().equals(users.getOldPassword())) {
                        Toast.makeText(view.getContext(), "旧密码输入错误", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (edt_oldPwd.getText().toString().equals(edt_newPwd.getText().toString())) {
                        Toast.makeText(view.getContext(), "新密码与旧密码不能相同", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (edt_newPwd.getText().toString().equals(edt_newPwdCheck.getText().toString())) {
                        param = "userInfoId=" + users.getUserInfoId() + "&oldPassword=" + users.getOldPassword() + "&newPassword=" + edt_newPwd.getText().toString();
                        Toast.makeText(view.getContext(), "修改成功", Toast.LENGTH_SHORT).show();
                        new HttpConnection(url, param, mHandler).start();
                    }

                }
            }
        });
        return view;
    }

    //控件初始化
    void init(View view) {
        users = new Users();
        users = (Users) getArguments().getSerializable("user");
        edt_oldPwd = (EditText) view.findViewById(R.id.edt_oldPwd);
        edt_newPwd = (EditText) view.findViewById(R.id.edt_newPwd);
        edt_newPwdCheck = (EditText) view.findViewById(R.id.edt_newPwdChecked);
        btn_pwdCheck = (Button) view.findViewById(R.id.btn_mofifyPwd);
    }

    class ModifyPwdHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

        }
    }
}
