package com.example.musicstore.model.bean.dto;


import com.example.musicstore.bean.RequestPageBean;

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