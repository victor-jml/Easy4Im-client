package com.example.easy4im.bean.res;

/**
 * @Author yang.zhao
 * Date: 2021/1/29
 * Description:
 **/

public class UserResVo {

    /**
     * login success return token
     */
    private String token;

    /**
     * login success timestamp
     */
    private Long timestamp;

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
