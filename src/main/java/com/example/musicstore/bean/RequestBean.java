package com.example.musicstore.bean;

import java.lang.reflect.Field;

import com.example.musicstore.util.DateTimeUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class RequestBean {

    private Integer id;
    private String key;
    private String name;
    private String account;
    /**
     * 开始日期
     */
    private java.sql.Date startDate;
    /**
     * 结束日期
     */
    private java.sql.Date endDate;

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                builder.append(f.getName(), f.get(this)).append("\n");
            }
        } catch (Exception e) { // Suppress
            builder.append("toString builder encounter an error");
        }
        return builder.toString();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * 名称
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public java.sql.Date getStartDate() {
        return startDate;
    }

    public void setStartDate(java.sql.Date startDate) {
        this.startDate = startDate;
    }

    public java.sql.Date getEndDate() {
        return endDate;
    }

    public void setEndDate(java.sql.Date endDate) {
        this.endDate = endDate;
    }

    public void increaseEndDate() {
        if (endDate!=null){
            endDate= DateTimeUtils.increaseDate(endDate);
        }
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    /*
    @JsonIgnore
    @JSONField(serialize=false)
    public boolean isAdmin() {
        return header != null && PlatformType.Web==header.platform && SystemType.user!=header.system;
    }

    @JsonIgnore
    @JSONField(serialize=false)
    public boolean isApp() {
        return header != null && (PlatformType.Android == header.platform || PlatformType.IOS == header.platform) && SystemType.user == header.system;
    }*/
}