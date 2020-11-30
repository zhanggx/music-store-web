package com.example.musicstore.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.musicstore.model.bean.Album;
import com.example.musicstore.model.bean.Theme;
import com.example.musicstore.model.bean.Singer;
import com.example.musicstore.model.bean.Theme;
import com.example.musicstore.model.bean.dto.ThemeDto;
import com.example.musicstore.service.AlbumService;
import com.example.musicstore.service.ThemeService;
import com.github.pagehelper.PageInfo;
import com.example.musicstore.bean.RequestBean;
import com.example.musicstore.bean.ResultBean;
import com.example.musicstore.bean.ResultPageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *ThemeController控制类
 */
@RestController
@CrossOrigin
@RequestMapping("/theme/")
public class ThemeController {
    @Autowired
    ThemeService themeService;
    @Autowired
    AlbumService albumService;
    @ResponseBody
    @RequestMapping(path = {"/getList"}, method = RequestMethod.GET)
    public ResultBean getAllList() {

        List<Theme> list = themeService.getAll();
        ResultBean resultBean = ResultBean.success(list);
        return resultBean;
    }
    @ResponseBody
    @RequestMapping(path = {"/list"})
    public ResultBean getList() {
        List<Theme> list = themeService.getAll();
        ResultBean resultBean = ResultBean.success(list);
        return resultBean;
    }
    @ResponseBody
    @RequestMapping(path = {"/search"}, method = RequestMethod.POST)
    public ResultBean search(@RequestBody ThemeDto requestBean) {
        if (requestBean.getPage()==null||requestBean.getPage().intValue()==0){
            List<Theme> list = themeService.search(requestBean);
            ResultBean resultBean = ResultBean.success(list);
            return resultBean;
        }
        PageInfo<Theme> pageInfo = themeService.searchPage(requestBean);
        return ResultPageBean.success(pageInfo);
    }
    @ResponseBody
    @RequestMapping(path = {"/info"}, method = RequestMethod.POST)
    public ResultBean getInfo(@RequestBody RequestBean requestBean) {
        if (requestBean.getId()==null||requestBean.getId().intValue()==0){
            return ResultBean.PARAMETER_ERROR_RESULTBEAN;
        }
        Theme theme = themeService.getInfo(requestBean.getId());
        if (theme ==null){
            return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"记录不存在");
        }


        ResultBean resultBean = ResultBean.success(theme);
        return resultBean;
    }
    /**
     * 检查参数的合法性
     */
    private ResultBean checkParameter(Theme theme) {
        if (StringUtils.isEmpty(theme.getName())){
            return ResultBean.PARAMETER_ERROR_RESULTBEAN;
        }
        return null;
    }
    @ResponseBody
    @RequestMapping(path = {"/create"}, method = RequestMethod.POST)
    public ResultBean add(@RequestBody JSONObject jsonObject) {
        Theme theme =jsonObject.toJavaObject(Theme.class);
        ResultBean resultBean=checkParameter(theme);
        if (resultBean!=null){
            return resultBean;
        }

        int result = themeService.insert(theme);
        if (result>0){
            resultBean = ResultBean.success();
            return resultBean;
        }
        return ResultBean.fail();
    }

    @ResponseBody
    @RequestMapping(path = {"/update"}, method = RequestMethod.POST)
    public ResultBean update(@RequestBody JSONObject jsonObject) {
        Theme theme =jsonObject.toJavaObject(Theme.class);
        if (theme.getId()==null||theme.getId()==0){
            return ResultBean.PARAMETER_ERROR_RESULTBEAN;
        }

        ResultBean resultBean=checkParameter(theme);
        if (resultBean!=null){
            return resultBean;
        }

        Theme oldTheme= themeService.getInfo(theme.getId());
        if (oldTheme==null){
            return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"记录不存在");
        }

        int result = themeService.update(theme);
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
        Theme theme = themeService.getInfo(requestBean.getId());
        if (theme ==null){
            return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"记录不存在");
        }
        int count=albumService.getAlbumCountByThemeId(theme.getId());
        if (count>0){
            return ResultBean.fail(ResultBean.ERROR_CODE_DATARELATION,"该类型已经被关联专辑");
        }
        int result = themeService.delete(theme.getId());
        if (result>0){
            ResultBean resultBean = ResultBean.success();
            return resultBean;
        }
        return ResultBean.fail();
    }
}
