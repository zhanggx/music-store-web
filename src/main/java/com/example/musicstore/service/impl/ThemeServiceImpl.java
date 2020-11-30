package com.example.musicstore.service.impl;

import com.example.musicstore.model.bean.Theme;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.musicstore.model.bean.dto.ThemeDto;
import com.example.musicstore.model.dao.ThemeMapper;
import com.example.musicstore.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private ThemeMapper themeMapper;

    @Override
    public List<Theme> search(ThemeDto requestBean) {
        return themeMapper.search(requestBean);
    }

    @Override
    public PageInfo<Theme> searchPage(ThemeDto requestBean) {
        PageHelper.startPage(requestBean.getPage(), requestBean.getPageSize());
        return new PageInfo<>(themeMapper.search(requestBean));
    }

    @Override
    public Theme getInfo(int id) {
        return themeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Theme> getAll() {
        return themeMapper.selectAll();
    }

    @Override
    public int update(Theme t) {
        return themeMapper.updateByPrimaryKey(t);
    }

    @Override
    public int insert(Theme t) {
        return themeMapper.insert(t);
    }

    @Override
    public int delete(int id) {
        return themeMapper.deleteByPrimaryKey(id);
    }
}
