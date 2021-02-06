package com.example.easy4im.bean.req;

import java.util.UUID;

/**
 * @Author yang.zhao
 * Date: 2021/1/26
 * Description:
 **/
public class BaseRequestVo {
    /**
     * 请求唯一编号
     */
    private String reqNo;

    /**
     * 请求时间戳
     */
    private Long timestamp;

    public BaseRequestVo(){
        reqNo = UUID.randomUUID().toString();
        timestamp = System.currentTimeMillis();
    }

    public String getReqNo() {
        return reqNo;
    }

    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
