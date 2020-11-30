package com.example.musicstore.service.impl;

import com.example.musicstore.bean.ResultBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.musicstore.model.bean.Music;
import com.example.musicstore.model.bean.dto.MusicDto;
import com.example.musicstore.model.dao.MusicMapper;
import com.example.musicstore.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicMapper musicMapper;

    @Override
    public List<Music> search(MusicDto requestBean) {
        return musicMapper.search(requestBean);
    }

    @Override
    public PageInfo<Music> searchPage(MusicDto requestBean) {
        PageHelper.startPage(requestBean.getPage(), requestBean.getPageSize());
        return new PageInfo<>(musicMapper.search(requestBean));
    }

    @Override
    public Music getInfo(int id) {
        return musicMapper.getMusic(id);
    }

    @Override
    public List<Music> getAll() {
        return musicMapper.getList();
    }

    @Override
    public int update(Music t) {
        return musicMapper.updateMusic(t);
    }

    @Override
    public int insert(Music t) {
        return musicMapper.insertMusic(t);
    }

    @Override
    public int delete(int id) {
        return musicMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int getMusicCountByAlbumId(int id) {
        return musicMapper.getMusicCountByAlbumId(id);
    }

    @Override
    public int getMusicCountBySingerId(int id) {
        return musicMapper.getMusicCountBySingerId(id);
    }

    @Override
    public List<Music> getAlbumMusicList(int albumId) {
        return musicMapper.getAlbumMusicList(albumId);
    }

    @Override
    public List<Music> getSingerMusicList(int singerId) {
        return musicMapper.getSingerMusicList(singerId);
    }

    @Override
    public PageInfo<Music> getSingerMusicList(int singerId, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        return new PageInfo<Music>(musicMapper.getSingerMusicList(singerId));
    }

    @Override
    public List<Music> getRecommendList() {
        return musicMapper.getRecommendList();
    }

    @Override
    public int updateRecommendIndex(int id,int recommendIndex) {
        return musicMapper.updateRecommendIndex(id,recommendIndex);
    }

    @Override
    public Integer getMaxRecommendIndex() {
        return musicMapper.getMaxRecommendIndex();
    }

    @Override
    @Transactional
    public ResultBean moveRecommendIndex(int sourceId, int destId) {
        Music musicSource = musicMapper.selectByPrimaryKey(sourceId);
        if (musicSource ==null){
            return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"记录不存在");
        }
        Music musicDest = musicMapper.selectByPrimaryKey(destId);
        if (musicDest ==null){
            return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"记录不存在");
        }
        musicMapper.updateRecommendIndex(destId,musicSource.getRecommendIndex());
        musicMapper.updateRecommendIndex(sourceId,musicDest.getRecommendIndex());

        return ResultBean.success();
    }
}
