package com.example.musicstore.service.impl;

import com.example.musicstore.model.bean.Singer;
import com.example.musicstore.model.bean.dto.SingerDto;
import com.example.musicstore.model.dao.SingerMapper;
import com.example.musicstore.service.SingerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerMapper singerMapper;

    @Override
    public List<Singer> search(SingerDto requestBean) {
        return singerMapper.search(requestBean);
    }

    @Override
    public PageInfo<Singer> searchPage(SingerDto requestBean) {
        PageHelper.startPage(requestBean.getPage(), requestBean.getPageSize());
        return new PageInfo<>(singerMapper.search(requestBean));
    }

    @Override
    public Singer getInfo(int id) {
        return singerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Singer> getAll() {
        return singerMapper.selectAll();
    }

    @Override
    public int update(Singer t) {
        return singerMapper.updateSinger(t);
    }

    @Override
    public int insert(Singer t) {
        return singerMapper.insertSinger(t);
    }

    @Override
    public int delete(int id) {
        return singerMapper.deleteByPrimaryKey(id);
    }
}
