package com.example.musicstore.service;

import com.example.musicstore.model.bean.Album;
import com.example.musicstore.model.bean.dto.AlbumDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AlbumService {
    /**
     * 搜索记录
     * @return List
     */
    public List<Album> search(AlbumDto requestBean);
    /**
     * 搜索记录(分页)
     * @return PageInfo
     */
    public PageInfo<Album> searchPage(AlbumDto requestBean);

    /**
     * 根据id查询
     * @param id 主键id
     * @return T
     */
    public Album getInfo(int id);
    /**
     * 查询所有记录
     * @return List
     */
    public List<Album> getAll();

    public int update(Album t);

    public int insert(Album t);

    public int delete(int id);

    public int getAlbumCountByThemeId(int id);
    public int getAlbumCountBySingerId(int id);

    List<Album> getSingerAlbumList(int singerId);
}
