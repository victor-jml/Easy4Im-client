package com.example.easy4im.ui.login;

import com.example.easy4im.base.IBaseView;
import com.example.easy4im.bean.req.UserLoginByPwdReqVo;
import com.example.easy4im.bean.res.UserResVo;

/**
 * @Author yang.zhao
 * Date: 2021/1/29
 * Description:
 **/
public class LoginContract {
    interface  Presenter {
        /**
         * 登录方法
         */
        void login(UserLoginByPwdReqVo loginByPwdReqVo);

    }
    interface View extends IBaseView {
        /**
         * 调转到首页
         */
        void jumpToMain();
        void onLoginError();
        void onLoginSuccess(UserResVo userResVo);
    }
}
