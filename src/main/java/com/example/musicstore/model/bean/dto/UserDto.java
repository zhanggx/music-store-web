package com.example.musicstore.model.bean.dto;


import com.example.musicstore.bean.RequestPageBean;

/**
 * @program: musicstore
 * @description: 用户查询类
 * @author: Mr.Wang
 * @create: 2019-07-19 10:51
 **/
public class UserDto extends RequestPageBean {
    private String account;
    private String startTime;
    private String endTime;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}