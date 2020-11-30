package com.example.musicstore.service;

import com.example.musicstore.model.bean.Theme;
import com.example.musicstore.model.bean.dto.ThemeDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ThemeService {
    /**
     * 搜索记录
     * @return List
     */
    public List<Theme> search(ThemeDto requestBean);
    /**
     * 搜索记录(分页)
     * @return PageInfo
     */
    public PageInfo<Theme> searchPage(ThemeDto requestBean);

    /**
     * 根据id查询
     * @param id 主键id
     * @return T
     */
    public Theme getInfo(int id);
    /**
     * 查询所有记录
     * @return List
     */
    public List<Theme> getAll();


    public int update(Theme t);

    public int insert(Theme t);

    public int delete(int id);
}
