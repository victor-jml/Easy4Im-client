package com.example.easy4im.bean.res;

import com.example.easy4im.bean.UserInfo;

/**
 * @Author yang.zhao
 * Date: 2021/1/29
 * Description:
 **/

public class UserResVo {

    private UserInfo userInfo;

    private String password;

    /**
     * login success return token
     */
    private String token;

    /**
     * login success timestamp
     */
    private Long timestamp;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
