package com.example.easy4im.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.easy4im.R;
import com.example.easy4im.base.BaseActivity;
import com.example.easy4im.bean.req.UserLoginByPwdReqVo;
import com.example.easy4im.bean.res.UserResVo;
import com.example.easy4im.common.EasyImApplication;
import com.example.easy4im.constant.Constant;
import com.example.easy4im.ui.main.MainActivity;
import com.google.android.material.textfield.TextInputLayout;

/**
 * @Author yang.zhao
 * Date: 2021/1/22
 * Description:
 **/
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    private Button loginBtn;
    private TextView toRegisterText;
    private TextInputLayout passwordText;
    private TextInputLayout phoneText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBtn = findViewById(R.id.loginBtn);
        toRegisterText = findViewById(R.id.toRegisterText);
        phoneText = findViewById(R.id.loginPhoneText);
        passwordText = findViewById(R.id.loginPasswordText);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordText.getEditText().getText().toString();
                String mobilePhone = phoneText.getEditText().getText().toString();


                UserLoginByPwdReqVo userLoginByPwdReqVo = new UserLoginByPwdReqVo();
                userLoginByPwdReqVo.setUserId(mobilePhone);
                userLoginByPwdReqVo.setPassword(password);
                if (!checkInput(userLoginByPwdReqVo)) {
                    return;
                }
                mPresenter.login(userLoginByPwdReqVo);
            }
        });
//        toRegisterText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
//                startActivity(intent);
//            }
//        });
    }
    private boolean checkInput(UserLoginByPwdReqVo userLoginByPwdReqVo) {
        if (TextUtils.isEmpty(userLoginByPwdReqVo.getUserId())) {
            phoneText.setError("用户名不能为空");
            return false;
        } else {
            phoneText.setErrorEnabled(false);
        }
        if (TextUtils.isEmpty(userLoginByPwdReqVo.getPassword())) {
            passwordText.setError("密码不能为空");
            return false;

        } else {
            passwordText.setErrorEnabled(false);
        }
        return true;
    }

    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void jumpToMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLoginError() {
        showToast("登录失败");
    }

    @Override
    public void onLoginSuccess(UserResVo userResVo) {
        ((EasyImApplication)getApplication()).cache(Constant.UserInfo, JSON.toJSONString(userResVo));
    }
}
