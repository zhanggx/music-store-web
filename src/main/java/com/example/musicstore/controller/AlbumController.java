package com.example.musicstore.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.musicstore.model.bean.Album;
import com.example.musicstore.model.bean.Music;
import com.example.musicstore.model.bean.Singer;
import com.example.musicstore.model.bean.Theme;
import com.example.musicstore.model.bean.dto.AlbumDto;
import com.example.musicstore.service.AlbumService;
import com.example.musicstore.service.MusicService;
import com.example.musicstore.service.SingerService;
import com.example.musicstore.service.ThemeService;
import com.github.pagehelper.PageInfo;
import com.example.musicstore.bean.RequestBean;
import com.example.musicstore.bean.ResultBean;
import com.example.musicstore.bean.ResultPageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

/**
 *AlbumController控制类
 */
@RestController
@CrossOrigin
@RequestMapping("/album/")
public class AlbumController {
    @Value("${musicstore.image.http.url}")
    private String imageHttpUrl;
    @Value("${musicstore.file.path}")
    private String storeFilePath;
    @Value("${musicstore.image.file.path.prefix}")
    private String imageFilePathPrefix;

    @Autowired
    AlbumService albumService;
    @Autowired
    SingerService singerService;
    @Autowired
    ThemeService themeService;
    @Autowired
    MusicService musicService;

    @ResponseBody
    @RequestMapping(path = {"/getList"}, method = RequestMethod.GET)
    public ResultBean getAllList(@RequestParam(required=false) Integer singerId) {

        List<Album> list;
        if (singerId==null||singerId.intValue()==0){
            list= albumService.getAll();
        }else{
            list= albumService.getSingerAlbumList(singerId);
        }
        updateUrl(list);

        ResultBean resultBean = ResultBean.success(list);
        return resultBean;
    }
    @ResponseBody
    @RequestMapping(path = {"/getAlbum"}, method = RequestMethod.GET)
    public ResultBean getAlbum(@RequestParam(required=false)  Integer albumId) {
        if (albumId==null||albumId.intValue()==0){
            List<Album> list= albumService.getAll();
            updateUrl(list);
            ResultBean resultBean = ResultBean.success(list);
            return resultBean;
        }
        Album album= albumService.getInfo(albumId);
        if (album!=null) {
            updateUrl(album);

            ResultBean resultBean = ResultBean.success(album);
            return resultBean;
        }
        return ResultBean.NOFOUND_RESULTBEAN;
    }
    @ResponseBody
    @RequestMapping(path = {"/list"}, method = RequestMethod.POST)
    public ResultBean list(@RequestBody RequestBean requestBean) {
        List<Album> list = albumService.getAll();
        updateUrl(list);

        ResultBean resultBean = ResultBean.success(list);
        return resultBean;
    }
    @ResponseBody
    @RequestMapping(path = {"/search"}, method = RequestMethod.POST)
    public ResultBean search(@RequestBody AlbumDto requestBean) {
        if (requestBean.getPage()==null||requestBean.getPage().intValue()==0){
            List<Album> list = albumService.search(requestBean);
            updateUrl(list);
            ResultBean resultBean = ResultBean.success(list);
            return resultBean;
        }
        PageInfo<Album> pageInfo = albumService.searchPage(requestBean);
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
        Album album = albumService.getInfo(requestBean.getId());
        if (album ==null){
            return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"记录不存在");
        }
        updateUrl(album);
        ResultBean resultBean = ResultBean.success(album);
        return resultBean;
    }
    private void updateUrl(List<Album> albumList){
        if (albumList !=null&&!albumList.isEmpty()){
            for(Album album : albumList){
                updateUrl(album);
            }
        }
    }
    private void updateUrl(Album album){
        album.setPictureUrl(album.getPicturePath());
        if (!StringUtils.isEmpty(album.getPicturePath())&& album.getPicturePath().startsWith("/")){
            album.setPictureUrl(imageHttpUrl + album.getPicturePath());
        }
    }
    /**
     * 检查参数的合法性
     */
    private ResultBean checkParameter(Album album) {
        if (album.getSingerId()==null||album.getThemeId()==null||album.getSingerId().intValue()==0||album.getThemeId().intValue()==0){
            return ResultBean.PARAMETER_ERROR_RESULTBEAN;
        }
        if (StringUtils.isEmpty(album.getName())){
            return ResultBean.PARAMETER_ERROR_RESULTBEAN;
        }
        return null;
    }
    @ResponseBody
    @RequestMapping(path = {"/create"}, method = RequestMethod.POST)
    public ResultBean add(@RequestBody JSONObject jsonObject) {
        Album album =jsonObject.toJavaObject(Album.class);
        ResultBean resultBean=checkParameter(album);
        if (resultBean!=null){
            return resultBean;
        }
        Singer singer= singerService.getInfo(album.getSingerId());
        if (singer==null){
            return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"歌手记录不存在");
        }
        Theme theme= themeService.getInfo(album.getThemeId());
        if (theme==null){
            return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"类型记录不存在");
        }

        int result = albumService.insert(album);
        if (result>0){
            resultBean = ResultBean.success();
            return resultBean;
        }
        return ResultBean.fail();
    }

    @ResponseBody
    @RequestMapping(path = {"/saveAlbum"}, method = RequestMethod.POST)
    public ResultBean saveAlbum(@RequestBody JSONObject jsonObject) {
        Album album =jsonObject.toJavaObject(Album.class);

        ResultBean resultBean=checkParameter(album);
        if (resultBean!=null){
            return resultBean;
        }
        int result=0;
        if (album.getId()>0) {
            Album oldAlbum= albumService.getInfo(album.getId());
            if (oldAlbum==null){
                return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"记录不存在");
            }
            if (album.getSingerId().intValue()!=oldAlbum.getSingerId().intValue()){
                Singer singer= singerService.getInfo(album.getSingerId());
                if (singer==null){
                    return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"歌手记录不存在");
                }
            }
            if (album.getThemeId().intValue()!=oldAlbum.getThemeId().intValue()) {
                Theme theme = themeService.getInfo(album.getThemeId());
                if (theme == null) {
                    return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS, "类型记录不存在");
                }
            }
            result=albumService.update(album);
            if (result>0&&oldAlbum.getPicturePath()!=null&&oldAlbum.getPicturePath().equals(album.getPicturePath())){
                deleteFile(oldAlbum);
            }
        }else{
            Singer singer= singerService.getInfo(album.getSingerId());
            if (singer==null){
                return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"歌手记录不存在");
            }
            Theme theme= themeService.getInfo(album.getThemeId());
            if (theme==null){
                return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"类型记录不存在");
            }
            result=albumService.insert(album);
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
        Album album =jsonObject.toJavaObject(Album.class);
        if (album.getId()==null||album.getId()==0){
            return ResultBean.PARAMETER_ERROR_RESULTBEAN;
        }

        ResultBean resultBean=checkParameter(album);
        if (resultBean!=null){
            return resultBean;
        }

        Album oldAlbum= albumService.getInfo(album.getId());
        if (oldAlbum==null){
            return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"记录不存在");
        }
        if (album.getSingerId().intValue()!=oldAlbum.getSingerId().intValue()){
            Singer singer= singerService.getInfo(album.getSingerId());
            if (singer==null){
                return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"歌手记录不存在");
            }
        }
        if (album.getThemeId().intValue()!=oldAlbum.getThemeId().intValue()) {
            Theme theme = themeService.getInfo(album.getThemeId());
            if (theme == null) {
                return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS, "类型记录不存在");
            }
        }

        int result = albumService.update(album);
        if (result>0){
            if (oldAlbum.getPicturePath()!=null&&oldAlbum.getPicturePath().equals(album.getPicturePath())){
                deleteFile(oldAlbum);
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
        Album album = albumService.getInfo(requestBean.getId());
        if (album ==null){
            return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"记录不存在");
        }
        int count=musicService.getMusicCountByAlbumId(album.getId());
        if (count>0){
            return ResultBean.fail(ResultBean.ERROR_CODE_DATARELATION,"该专辑已经被关联歌曲");
        }
        int result = albumService.delete(album.getId());
        if (result>0){
            deleteFile(album);
            ResultBean resultBean = ResultBean.success();
            return resultBean;
        }
        return ResultBean.fail();
    }
    private void deleteFile(Album album){
        if (!StringUtils.isEmpty(album.getPicturePath())&&album.getPicturePath().startsWith(imageFilePathPrefix)){
            String fileName=storeFilePath + album.getPicturePath();
            File file=new  File(fileName);
            if (file.exists()){
                file.delete();
            }
        }
    }
}
