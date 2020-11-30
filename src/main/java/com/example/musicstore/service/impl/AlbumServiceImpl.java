package com.example.musicstore.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.musicstore.model.bean.Album;
import com.example.musicstore.model.bean.dto.AlbumDto;
import com.example.musicstore.model.dao.AlbumMapper;
import com.example.musicstore.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public List<Album> search(AlbumDto requestBean) {
        return albumMapper.search(requestBean);
    }

    @Override
    public PageInfo<Album> searchPage(AlbumDto requestBean) {
        PageHelper.startPage(requestBean.getPage(), requestBean.getPageSize());
        return new PageInfo<>(albumMapper.search(requestBean));
    }

    @Override
    public Album getInfo(int id) {
        return albumMapper.getAlbum(id);
    }

    @Override
    public List<Album> getAll() {
        return albumMapper.getList();
    }

    @Override
    public int update(Album t) {
        return albumMapper.updateAlbum(t);
    }

    @Override
    public int insert(Album t) {
        return albumMapper.insertAlbum(t);
    }

    @Override
    public int delete(int id) {
        return albumMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int getAlbumCountByThemeId(int id) {
        return albumMapper.getAlbumCountByThemeId(id);
    }

    @Override
    public int getAlbumCountBySingerId(int id) {
        return albumMapper.getAlbumCountBySingerId(id);
    }

    @Override
    public List<Album> getSingerAlbumList(int singerId) {
        return albumMapper.getSingerAlbumList(singerId);
    }
}
