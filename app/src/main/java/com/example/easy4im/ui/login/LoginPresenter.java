package com.example.easy4im.ui.login;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.easy4im.base.BasePresenter;
import com.example.easy4im.bean.req.UserLoginByPwdReqVo;
import com.example.easy4im.bean.res.Result;
import com.example.easy4im.common.RetrofitManager;
import com.example.easy4im.constant.ResultEnum;
import com.example.easy4im.net.Api;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @Author yang.zhao
 * Date: 2021/1/29
 * Description:
 **/
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {
    @Override
    public void login(UserLoginByPwdReqVo loginByPwdReqVo) {
        //步骤4：创建Retrofit对象
        // 步骤5：创建 网络请求接口 的实例
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(loginByPwdReqVo));
        final Api request = RetrofitManager.getInstance().create(Api.class);

        mView.showLoading();

        // 步骤6：采用Observable<...>形式 对 网络请求 进行封装
        Observable<Result> observable = request.login(requestBody);

        mView.showLoading();

        // 步骤7：发送网络请求
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        Log.i("登录成功", JSON.toJSONString(result));
                        if (mView == null) {
                            return;
                        }
                        mView.hideLoading();
//                        if (ResultEnum.USER_LOGIN_SUCCESS.getCode().equals(result.)) {
//                            Log.i("登录成功", JSON.toJSONString(result));
//                            mView.onLoginSuccess(result.getData());
//                            mView.jumpToMain();
//                        } else {
//                            Log.i("登录失败", JSON.toJSONString(result));
//                            mView.onLoginError();
//                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView == null) {
                            return;
                        }
                        mView.hideLoading();

                        Log.e("登录失败", e.getMessage());
                        mView.onLoginError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
