package com.example.musicstore.model.dao;

import com.example.musicstore.model.bean.Theme;
import com.example.musicstore.model.bean.dto.ThemeDto;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface ThemeMapper extends Mapper<Theme> {

    List<Theme> search(ThemeDto requestBean);

}