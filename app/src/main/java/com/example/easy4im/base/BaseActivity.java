package com.example.easy4im.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.easy4im.R;
import com.example.easy4im.ui.login.LoginActivity;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * @Author yang.zhao
 * Date: 2021/1/22
 * Description:
 **/
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView  {
    private AVLoadingIndicatorView avi;
    protected T mPresenter;

    public void showLoading() {
        if (avi == null) {
            Log.i("loading", "初始化loading");
            setLoadingView();
        }
        avi.show();
    }


    public void hideLoading() {
        avi.hide();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
        mPresenter.attachView(this);
    }

    public void setLoadingView() {
        avi = findViewById(getLoadingViewId());
    }

    public int getLoadingViewId() {
        return R.id.avi;
    }


    public abstract T initPresenter();

    //退出时销毁持有Activity
    @Override
    protected void onDestroy() {
        this.mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showToast(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void toLogin() {
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
