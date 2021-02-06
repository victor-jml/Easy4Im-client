package com.example.easy4im.base;

/**
 * @Author yang.zhao
 * Date: 2021/1/22
 * Description:
 **/
public interface IBasePresenter <T extends IBaseView>{
    void attachView(T view);

    void detachView();
}
