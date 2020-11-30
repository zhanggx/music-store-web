package com.example.musicstore.model.dao;

import com.example.musicstore.model.bean.Album;
import com.example.musicstore.model.bean.Music;
import com.example.musicstore.model.bean.dto.MusicDto;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface MusicMapper extends Mapper<Music> {
    List<Music> search(MusicDto requestBean);
    List<Music> getList();
    Music getMusic(int id);
    int insertMusic(Music music);

    int updateMusic(Music music);

    int getMusicCountByAlbumId(int id);

    int getMusicCountBySingerId(int id);

    List<Music> getAlbumMusicList(int albumId);

    List<Music> getSingerMusicList(int singerId);

    List<Music> getRecommendList();

    int updateRecommendIndex(@Param("id") int id, @Param("recommendIndex") int recommendIndex);

    Integer getMaxRecommendIndex();
}
