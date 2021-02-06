package com.example.easy4im.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.easy4im.R;
import com.example.easy4im.ui.login.LoginActivity;

/**
 * @Author yang.zhao
 * Date: 2021/1/22
 * Description:
 **/
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IBaseView {
    protected T mPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
        mPresenter.attachView(this);
    }


    public int getLoadingViewId() {
        return R.id.avi;
    }


    public abstract T initPresenter();


    /**
     * 退出时销毁持有Activity
     */
    @Override
    public void onDestroy() {
        this.mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showToast(String message) {
        Toast toast = Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void toLogin() {
        Intent intent=new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}
