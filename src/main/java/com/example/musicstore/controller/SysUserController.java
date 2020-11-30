package com.example.musicstore.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.musicstore.model.bean.SysUser;
import com.example.musicstore.model.bean.dto.UserDto;
import com.example.musicstore.service.SysUserService;
import com.example.musicstore.util.MD5Util;
import com.github.pagehelper.PageInfo;
import com.example.musicstore.bean.RequestBean;
import com.example.musicstore.bean.ResultBean;
import com.example.musicstore.bean.ResultPageBean;
import com.example.musicstore.bean.SystemRequestBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class SysUserController{
    private static final String PASSWORD_PREFIX="youkecloud";
    private static final String PASSWORD_POSTFIX="JustDoIt";
    private static final long TOKEN_EXPIRE_TIMEOUT=48*60*60; //48小时过期
    private static final String LOGIN_USER_CACHE_KEY="youkeyun_login_token_";
    private static final String PHONE_REG = "^(1[3456789])\\d{9}$";
    private static final String PASSWORD_REG ="^[a-zA-Z]\\w{5,17}$";
    @Autowired
    SysUserService userService;

    @RequestMapping(path = {"/login"}, method = RequestMethod.POST)
    public ResultBean login (@RequestBody SystemRequestBean requestBean, HttpServletRequest request){
        if (StringUtils.isEmpty(requestBean.getAccount())||StringUtils.isEmpty(requestBean.getPassword())){
            return ResultBean.PARAMETER_ERROR_RESULTBEAN;
        }
        SysUser user=userService.getUserByAccount(requestBean.getAccount());
        if (user==null){
            return ResultBean.fail(ResultBean.ERROR_CODE_USERNOEXISTS,"用户不存在");
        }
        String pwd= getMd5Password(requestBean.getPassword());
        if (!pwd.equals(user.getPassword())){
            return ResultBean.fail(ResultBean.ERROR_CODE_PASSWORDERROR,"密码不正确");
        }
        String token= UUID.randomUUID().toString();
        user.setToken(token);
        //登录成功，返回token
        return ResultBean.success(user);
    }
    @RequestMapping(path = {"/logout"}, method = RequestMethod.POST)
    public ResultBean logout (HttpServletRequest request){
        /*SysUser user=super.getLoginUser(request);
        if(user!=null){
            redisService.deleteObject(user.getToken());
            redisService.deleteObject(LOGIN_USER_CACHE_KEY + user.getId());
        }*/
        return ResultBean.SUCCESS_RESULTBEAN;
    }


    @ResponseBody
    @RequestMapping(path = {"/list"}, method = RequestMethod.POST)
    public ResultBean list(@RequestBody RequestBean requestBean) {
        List<SysUser> list = userService.getAll();
        ResultBean resultBean = ResultBean.success(list);
        return resultBean;
    }
    @ResponseBody
    @RequestMapping(path = {"/search"}, method = RequestMethod.POST)
    public ResultBean search(@RequestBody UserDto requestBean) {
        if (requestBean.getPage()==null||requestBean.getPage().intValue()==0){
            List<SysUser> list = userService.search(requestBean);
            ResultBean resultBean = ResultBean.success(list);
            return resultBean;
        }
        PageInfo<SysUser> pageInfo = userService.searchPage(requestBean);
        return ResultPageBean.success(pageInfo);
    }
    @ResponseBody
    @RequestMapping(path = {"/info"}, method = RequestMethod.POST)
    public ResultBean getInfo(@RequestBody RequestBean requestBean) {
        if (requestBean.getId()==null||requestBean.getId().intValue()==0){
            return ResultBean.PARAMETER_ERROR_RESULTBEAN;
        }
        SysUser user=userService.getUser(requestBean.getId());
        if (user==null){
            return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"记录不存在");
        }

        ResultBean resultBean = ResultBean.success(user);
        return resultBean;
    }
    /**
     * 检查参数的合法性
     */
    private ResultBean checkParameter(SysUser user) {
        return null;
    }
    @ResponseBody
    @RequestMapping(path = {"/create"}, method = RequestMethod.POST)
    public ResultBean add(@RequestBody JSONObject jsonObject) {
        SysUser user=jsonObject.toJavaObject(SysUser.class);
        ResultBean resultBean=checkParameter(user);
        if (resultBean!=null){
            return resultBean;
        }
        if (StringUtils.isBlank(user.getAccount())){
            return ResultBean.fail(ResultBean.ERROR_CODE_PARAMETERERROR,"账号不能为空");
        }
        if (StringUtils.isBlank(user.getPassword())){
            return ResultBean.fail(ResultBean.ERROR_CODE_PARAMETERERROR,"密码不能为空");
        }
        if(!user.getPassword().matches(PASSWORD_REG)){
            return ResultBean.fail(ResultBean.ERROR_CODE_PARAMETERERROR,"密码以字母开头，长度在6~18之间，只能包含字母、数字和下划线");
        }
        if (!StringUtils.isBlank(user.getMobilePhone())){
            if(!user.getMobilePhone().matches(PHONE_REG)){
                return ResultBean.fail(ResultBean.ERROR_CODE_PARAMETERERROR,"手机号码格式不正确");
            }
        }
        String pwd= getMd5Password(user.getPassword());
        user.setPassword(pwd);
        //判断是否存在该用户
        SysUser userInfo =userService.getUserByAccount(user.getAccount());
        if (userInfo != null){
            return ResultBean.fail(ResultBean.ERROR_CODE_USEREXISTED,"该用户已存在，请设置新的账号");
        }
        int result = userService.insertUser(user);
        if (result>0){
            resultBean = ResultBean.success();
            return resultBean;
        }
        return ResultBean.fail();
    }

    @RequestMapping(path = {"/update"}, method = RequestMethod.POST)
    @ResponseBody
    public ResultBean update(@RequestBody JSONObject jsonObject) {
        SysUser user=jsonObject.toJavaObject(SysUser.class);
        if (user.getId()==null||user.getId().intValue()==0){
            return ResultBean.PARAMETER_ERROR_RESULTBEAN;
        }
        if (StringUtils.isBlank(user.getAccount())){
            return ResultBean.fail(ResultBean.ERROR_CODE_PARAMETERERROR,"账号不能为空");
        }
        if (!StringUtils.isBlank(user.getMobilePhone())){
            if(!user.getMobilePhone().matches(PHONE_REG)){
                return ResultBean.fail(ResultBean.ERROR_CODE_PARAMETERERROR,"手机号码格式不正确");
            }
        }
        ResultBean resultBean=checkParameter(user);
        if (resultBean!=null){
            return resultBean;
        }

        SysUser olduser=userService.getUser(user.getId());
        if (olduser==null){
            return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"记录不存在");
        }
        //判断是否存在该用户
        SysUser userInfo =userService.getUserByAccount(user.getAccount());
        if (userInfo != null&&olduser.getId().intValue()!=userInfo.getId().intValue()){
            return ResultBean.fail(ResultBean.ERROR_CODE_USEREXISTED,"该用户已存在，请设置新的账号");
        }
        int result = userService.updateUser(user);
        if (result>0){
            resultBean = ResultBean.success();
            return resultBean;
        }
        return ResultBean.fail();
    }

    @ResponseBody
    @RequestMapping(path = {"/delete"}, method = RequestMethod.POST)
    public ResultBean delete(@RequestBody RequestBean requestBean) {
        if (requestBean.getId()==null||requestBean.getId().intValue()==0){
            return ResultBean.PARAMETER_ERROR_RESULTBEAN;
        }
        SysUser user=userService.getUser(requestBean.getId());
        if (user==null){
            return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"记录不存在");
        }
        int result = userService.delete(requestBean.getId());
        if (result>0){
            ResultBean resultBean = ResultBean.success();
            return resultBean;
        }
        return ResultBean.fail();
    }
    private static String getMd5Password(String pwd){
        return MD5Util.MD5Encode(PASSWORD_PREFIX + MD5Util.MD5Encode(PASSWORD_PREFIX + pwd + PASSWORD_POSTFIX) + PASSWORD_POSTFIX);
    }
}
