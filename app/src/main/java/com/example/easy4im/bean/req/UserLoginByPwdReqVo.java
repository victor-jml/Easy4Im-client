package com.example.easy4im.bean.req;

/**
 * @Author yang.zhao
 * Date: 2021/1/26
 * Description:
 **/
public class UserLoginByPwdReqVo extends BaseRequestVo{

    /**
     * Login by userId
     */
    private String userId;

    /**
     * login By Email
     */
    private String email;

    /**
     * login auth proof
     */
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
