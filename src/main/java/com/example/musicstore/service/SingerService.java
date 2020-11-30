package com.example.musicstore.service;

import com.example.musicstore.model.bean.Singer;
import com.example.musicstore.model.bean.dto.SingerDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SingerService {
    /**
     * 搜索记录
     * @return List
     */
    public List<Singer> search(SingerDto requestBean);
    /**
     * 搜索记录(分页)
     * @return PageInfo
     */
    public PageInfo<Singer> searchPage(SingerDto requestBean);

    /**
     * 根据id查询
     * @param id 主键id
     * @return T
     */
    public Singer getInfo(int id);
    /**
     * 查询所有记录
     * @return List
     */
    public List<Singer> getAll();

    public int update(Singer t);

    public int insert(Singer t);

    public int delete(int id);
}
