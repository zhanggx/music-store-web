package com.example.musicstore.bean;

/**
 * Created by Liccon on 2/9/18.
 * desc:分页参数 bean
 */
public class RequestPageBean extends RequestBean {
    private Integer page;//查询的页数,第1页为初始值
    private int pageSize;
    private Integer lastId;//上一条id,通常是app的分页请求使用

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public int getPageSize() {
        if (pageSize<=0){
            return 20;
        }
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getLastId() {
        return lastId;
    }

    public void setLastId(Integer lastId) {
        this.lastId = lastId;
    }
}
