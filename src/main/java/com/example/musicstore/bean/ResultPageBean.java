package com.example.musicstore.bean;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 返回数据
 */
public class ResultPageBean extends ResultBean {
    private final int pageIndex;//当前页
    private final long totalCount;//总记录数
    private final int totalPage;//总页数
    private final boolean hasNextPage;//是否有下一页

    public ResultPageBean(PageInfo pageInfo){
        this(pageInfo.getPageNum(),pageInfo.getPages(),pageInfo.getTotal(),pageInfo.isHasNextPage(),pageInfo.getList());
    }
    public ResultPageBean(int pageIndex, int totalPage, long totalCount, boolean hasNextPage, List list) {
        super(list);
        this.pageIndex=pageIndex;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.hasNextPage=hasNextPage;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public static ResultPageBean success(PageInfo pageInfo) {
        return new ResultPageBean(pageInfo);
    }
}
