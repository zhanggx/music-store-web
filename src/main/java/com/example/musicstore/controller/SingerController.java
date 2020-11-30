package com.example.musicstore.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.musicstore.bean.RequestBean;
import com.example.musicstore.bean.ResultBean;
import com.example.musicstore.bean.ResultPageBean;
import com.example.musicstore.model.bean.Album;
import com.example.musicstore.model.bean.Singer;
import com.example.musicstore.model.bean.dto.SingerDto;
import com.example.musicstore.service.AlbumService;
import com.example.musicstore.service.MusicService;
import com.example.musicstore.service.SingerService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 *SingerController控制类
 */
@RestController
@CrossOrigin
@RequestMapping("/singer/")
public class SingerController {
    @Value("${musicstore.image.http.url}")
    private String imageHttpUrl;
    @Value("${musicstore.file.path}")
    private String storeFilePath;
    @Value("${musicstore.image.file.path.prefix}")
    private String imageFilePathPrefix;

    @Autowired
    SingerService singerService;
    @Autowired
    MusicService musicService;
    @Autowired
    AlbumService albumService;

    @ResponseBody
    @RequestMapping(path = {"/getList"}, method = RequestMethod.GET)
    public ResultBean getAllList() {
        List<Singer> list = singerService.getAll();
        updateUrl(list);

        ResultBean resultBean = ResultBean.success(list);
        return resultBean;
    }
    @ResponseBody
    @RequestMapping(path = {"/getSinger"}, method = RequestMethod.GET)
    public ResultBean getSinger(@RequestParam(required=false)  Integer singerId) {
        if (singerId==null||singerId.intValue()==0){
            List<Singer> list= singerService.getAll();
            updateUrl(list);
            ResultBean resultBean = ResultBean.success(list);
            return resultBean;
        }
        Singer singer= singerService.getInfo(singerId);
        if (singer!=null) {
            updateUrl(singer);

            ResultBean resultBean = ResultBean.success(singer);
            return resultBean;
        }
        return ResultBean.NOFOUND_RESULTBEAN;
    }
    @ResponseBody
    @RequestMapping(path = {"/list"}, method = RequestMethod.POST)
    public ResultBean list(@RequestBody RequestBean requestBean) {
        List<Singer> list = singerService.getAll();
        updateUrl(list);

        ResultBean resultBean = ResultBean.success(list);
        return resultBean;
    }
    @ResponseBody
    @RequestMapping(path = {"/search"}, method = RequestMethod.POST)
    public ResultBean search(@RequestBody SingerDto requestBean) {
        if (requestBean.getPage()==null||requestBean.getPage().intValue()==0){
            List<Singer> list = singerService.search(requestBean);
            updateUrl(list);
            ResultBean resultBean = ResultBean.success(list);
            return resultBean;
        }
        PageInfo<Singer> pageInfo = singerService.searchPage(requestBean);
        if (pageInfo!=null){
            updateUrl(pageInfo.getList());
        }
        return ResultPageBean.success(pageInfo);
    }
    @ResponseBody
    @RequestMapping(path = {"/info"}, method = RequestMethod.POST)
    public ResultBean getInfo(@RequestBody RequestBean requestBean) {
        if (requestBean.getId()==null||requestBean.getId().intValue()==0){
            return ResultBean.PARAMETER_ERROR_RESULTBEAN;
        }
        Singer singer = singerService.getInfo(requestBean.getId());
        if (singer ==null){
            return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"记录不存在");
        }
        updateUrl(singer);
        ResultBean resultBean = ResultBean.success(singer);
        return resultBean;
    }
    private void updateUrl(List<Singer> singerList){
        if (singerList !=null&&!singerList.isEmpty()){
            for(Singer singer : singerList){
                updateUrl(singer);
            }
        }
    }
    private void updateUrl(Singer singer){
        singer.setPictureUrl(singer.getPicturePath());
        if (!StringUtils.isEmpty(singer.getPicturePath())&& singer.getPicturePath().startsWith("/")){
            singer.setPictureUrl(imageHttpUrl + singer.getPicturePath());
        }
    }
    /**
     * 检查参数的合法性
     */
    private ResultBean checkParameter(Singer singer) {
        if (StringUtils.isEmpty(singer.getName())){
            return ResultBean.PARAMETER_ERROR_RESULTBEAN;
        }
        return null;
    }
    @ResponseBody
    @RequestMapping(path = {"/create"}, method = RequestMethod.POST)
    public ResultBean add(@RequestBody JSONObject jsonObject) {
        Singer singer =jsonObject.toJavaObject(Singer.class);

        ResultBean resultBean=checkParameter(singer);
        if (resultBean!=null){
            return resultBean;
        }

        int result = singerService.insert(singer);
        if (result>0){
            resultBean = ResultBean.success();
            return resultBean;
        }
        return ResultBean.fail();
    }
    @ResponseBody
    @RequestMapping(path = {"/saveSinger"}, method = RequestMethod.POST)
    public ResultBean saveSinger(@RequestBody JSONObject jsonObject) {
        Singer singer =jsonObject.toJavaObject(Singer.class);

        ResultBean resultBean=checkParameter(singer);
        if (resultBean!=null){
            return resultBean;
        }
        int result=0;
        if (singer.getId()>0) {
            Singer oldSinger= singerService.getInfo(singer.getId());
            if (oldSinger==null){
                return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"记录不存在");
            }
            result=singerService.update(singer);
            if (result>0&&oldSinger.getPicturePath()!=null&&oldSinger.getPicturePath().equals(singer.getPicturePath())){
                deleteFile(oldSinger);
            }
        }else{
            result=singerService.insert(singer);
        }
        if (result>0){
            resultBean = ResultBean.success();
            return resultBean;
        }
        return ResultBean.fail();
    }

    @ResponseBody
    @RequestMapping(path = {"/update"}, method = RequestMethod.POST)
    public ResultBean update(@RequestBody JSONObject jsonObject) {
        Singer singer =jsonObject.toJavaObject(Singer.class);
        if (singer.getId()==0){
            return ResultBean.PARAMETER_ERROR_RESULTBEAN;
        }

        ResultBean resultBean=checkParameter(singer);
        if (resultBean!=null){
            return resultBean;
        }

        Singer oldSinger= singerService.getInfo(singer.getId());
        if (oldSinger==null){
            return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"记录不存在");
        }

        int result = singerService.update(singer);
        if (result>0){
            if (oldSinger.getPicturePath()!=null&&oldSinger.getPicturePath().equals(singer.getPicturePath())){
                deleteFile(oldSinger);
            }
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
        Singer singer = singerService.getInfo(requestBean.getId());
        if (singer ==null){
            return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"记录不存在");
        }
        int count=musicService.getMusicCountBySingerId(singer.getId());
        if (count>0){
            return ResultBean.fail(ResultBean.ERROR_CODE_DATARELATION,"该歌手已经被关联歌曲");
        }
        count=albumService.getAlbumCountBySingerId(singer.getId());
        if (count>0){
            return ResultBean.fail(ResultBean.ERROR_CODE_DATARELATION,"该歌手已经被关联专辑");
        }
        int result = singerService.delete(singer.getId());
        if (result>0){
            deleteFile(singer);
            ResultBean resultBean = ResultBean.success();
            return resultBean;
        }
        return ResultBean.fail();
    }
    private void deleteFile(Singer singer){
        if (!StringUtils.isEmpty(singer.getPicturePath())&&singer.getPicturePath().startsWith(imageFilePathPrefix)){
            String fileName=storeFilePath + singer.getPicturePath();
            File file=new  File(fileName);
            if (file.exists()){
                file.delete();
            }
        }
    }

}
