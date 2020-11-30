package com.example.musicstore.service;

import com.example.musicstore.bean.ResultBean;
import com.example.musicstore.model.bean.Music;
import com.example.musicstore.model.bean.dto.MusicDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MusicService {
    /**
     * 搜索记录
     * @return List
     */
    public List<Music> search(MusicDto requestBean);
    /**
     * 搜索记录(分页)
     * @return PageInfo
     */
    public PageInfo<Music> searchPage(MusicDto requestBean);

    /**
     * 根据id查询
     * @param id 主键id
     * @return T
     */
    public Music getInfo(int id);
    /**
     * 查询所有记录
     * @return List
     */
    public List<Music> getAll();


    public int update(Music t);

    public int insert(Music t);

    public int delete(int id);

    public int getMusicCountByAlbumId(int id);
    public int getMusicCountBySingerId(int id);

    List<Music> getAlbumMusicList(int albumId);

    List<Music> getSingerMusicList(int singerId);

    PageInfo<Music> getSingerMusicList(int singerId, int pageIndex, int pageSize);

    List<Music> getRecommendList();
    int updateRecommendIndex(int id,int recommendIndex);
    Integer getMaxRecommendIndex();

    ResultBean moveRecommendIndex(int sourceId, int destId);
}
