package com.example.musicstore.model.dao;

import com.example.musicstore.model.bean.Album;
import com.example.musicstore.model.bean.Singer;
import com.example.musicstore.model.bean.SysUser;
import com.example.musicstore.model.bean.dto.AlbumDto;
import com.example.musicstore.model.bean.dto.SingerDto;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface SingerMapper extends Mapper<Singer> {

    List<Singer> search(SingerDto requestBean);

    int insertSinger(Singer singer);

    int updateSinger(Singer singer);
}