package com.example.musicstore.service;

import com.example.musicstore.model.bean.SysUser;
import com.example.musicstore.model.bean.dto.UserDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SysUserService {
  /**
   * 搜索记录
   * @return List
   */
  public List<SysUser> search(UserDto requestBean);
  /**
   * 搜索记录(分页)
   * @return PageInfo
   */
  public PageInfo<SysUser> searchPage(UserDto requestBean);
  /**
   * 查询所有记录
   * @return List
   */
  public List<SysUser> getAll();

  /**
   * 根据id获取用户
   * @return
   */
  SysUser getUser(int id);
  /**
   * 根据account获取用户
   * @param account
   * @return
   */
  SysUser getUserByAccount(String account);

  /**
   * 添加用户
   * @param user
   * @return
   */
  int insertUser(SysUser user);
  /**
   * 修改用户
   * @param user
   * @return
   */
  int updateUser(SysUser user);

  public int delete(int id);
}
