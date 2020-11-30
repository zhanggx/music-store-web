package com.example.musicstore.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.musicstore.model.bean.dto.UserDto;
import com.example.musicstore.model.bean.SysUser;
import com.example.musicstore.model.dao.SysUserMapper;
import com.example.musicstore.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Created by liccon on 2019/07/16.
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;
    /**
     * 搜索记录
     * @return List
     */
    public List<SysUser> search(UserDto requestBean) {
        return userMapper.search(requestBean);
    }
    /**
     * 搜索记录(分页)
     * @return PageInfo
     */
    public PageInfo<SysUser> searchPage(UserDto requestBean){
        PageHelper.startPage(requestBean.getPage(), requestBean.getPageSize());
        return new PageInfo<>(userMapper.search(requestBean));
    }

    @Override
    public List<SysUser> getAll() {
        return userMapper.getList();
    }

    @Override
    public SysUser getUser(int id) {
        SysUser user=userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public SysUser getUserByAccount(String account) {
        SysUser user=userMapper.getUser(account);
        return user;
    }

    @Override
    public int insertUser(SysUser user) {
        return userMapper.insertUser(user);
    }

    @Override
    public int updateUser(SysUser user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int delete(int id) {
        return userMapper.deleteByPrimaryKey(id);
    }
}
