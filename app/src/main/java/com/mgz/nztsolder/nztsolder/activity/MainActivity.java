package com.mgz.nztsolder.nztsolder.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mgz.nztsolder.nztsolder.R;
import com.mgz.nztsolder.nztsolder.basictool.BaseActivity;
import com.mgz.nztsolder.nztsolder.network.HttpStringResponseHandler;
import com.mgz.nztsolder.nztsolder.network.reponse.LoginResponse;
import com.mgz.nztsolder.nztsolder.network.request.LoginRequest;
import com.mgz.nztsolder.nztsolder.utils.MyOKHttp;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private EditText phoneNum;
    private EditText password;
    private Button login;
    private ImageButton back;
    private TextView register;
    private TextView findPassword;

    @Override
    public int getRecoureId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        back = (ImageButton) findViewById(R.id.btn_back);
        phoneNum = (EditText) findViewById(R.id.et_phone);
        password = (EditText) findViewById(R.id.et_password);
        login = (Button) findViewById(R.id.btn_login);
        register = (TextView) findViewById(R.id.tv_register);
        findPassword = (TextView) findViewById(R.id.tv_findpwd);
    }

    @Override
    public void setOnClickListner() {
        back.setOnClickListener(this);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        findPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == back) {
            finish();
        } else if (v == login) {
            loginButtonEvent();
        } else if (v == register) {
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            finish();
        }
    }

    private void loginButtonEvent() {
        if (phoneNum.getText().toString().length() != 11) {
            Toast.makeText(getApplicationContext(), "请输入11位手机号码", Toast.LENGTH_LONG).show();
        } else if (password.getText().toString().length() < 6) {
            Toast.makeText(getApplicationContext(), "密码是6位数或以上的数字或字母", Toast.LENGTH_LONG).show();
        } else {
            final LoginRequest loginRequest = new LoginRequest(phoneNum.getText().toString().trim(), password.getText().toString(), 1);
            MyOKHttp.postToBase("solder/login", loginRequest, new HttpStringResponseHandler<LoginResponse>(getApplicationContext(), LoginResponse.class, true) {
                @Override
                public void onSuccess(LoginResponse response) {
                    super.onSuccess(response);
                    if (response.getSuccess() == 1) {

                    } else if (response.getSuccess() == -1) {
                        Toast.makeText(getApplication(), "账号或密码错误，请核对", Toast.LENGTH_LONG).show();
                    } else if (response.getSuccess() == 0) {
                        Toast.makeText(getApplication(), "用户不存在", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
