package com.example.musicstore.model.dao;

import com.example.musicstore.model.bean.SysUser;
import com.example.musicstore.model.bean.dto.UserDto;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface SysUserMapper extends Mapper<SysUser> {

    SysUser getUser(@Param("account") String account);

    List<SysUser> search(UserDto requestBean);

    List<SysUser> getList();

    int insertUser(SysUser user);

    int updateUser(SysUser user);
}
