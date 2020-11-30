package com.example.musicstore.model.dao;

import com.example.musicstore.model.bean.Album;
import com.example.musicstore.model.bean.Music;
import com.example.musicstore.model.bean.Singer;
import com.example.musicstore.model.bean.dto.AlbumDto;
import com.example.musicstore.model.bean.dto.MusicDto;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface AlbumMapper extends Mapper<Album> {
    List<Album> search(AlbumDto requestBean);
    List<Album> getList();
    Album getAlbum(int id);
    int insertAlbum(Album album);

    int updateAlbum(Album album);

    int getAlbumCountByThemeId(@Param("themeId") int themeId);

    int getAlbumCountBySingerId(@Param("singerId") int singerId);

    List<Album> getSingerAlbumList(@Param("singerId") int singerId);
}