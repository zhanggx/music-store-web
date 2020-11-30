package com.example.musicstore.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.musicstore.bean.RequestPageBean;
import com.example.musicstore.model.bean.Album;
import com.example.musicstore.model.bean.Music;
import com.example.musicstore.model.bean.Singer;
import com.example.musicstore.model.bean.dto.MusicDto;
import com.example.musicstore.service.AlbumService;
import com.example.musicstore.service.MusicService;
import com.github.pagehelper.PageInfo;
import com.example.musicstore.bean.RequestBean;
import com.example.musicstore.bean.ResultBean;
import com.example.musicstore.bean.ResultPageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

/**
 *HomeWorkController控制类
 */
@RestController
@CrossOrigin
@RequestMapping("/music/")
public class MusicController {
    @Value("${musicstore.music.http.url}")
    private String musicHttpUrl;
    @Value("${musicstore.file.path}")
    private String storeFilePath;
    @Value("${musicstore.music.file.path.prefix}")
    private String musicFilePathPrefix;
    @Autowired
    MusicService musicService;
    @Autowired
    AlbumService albumService;

    @ResponseBody
    @RequestMapping(path = {"/getList"}, method = RequestMethod.GET)
    public ResultBean getAllList(@RequestParam(required=false)  Integer albumId) {
        List<Music> list;
        if (albumId==null||albumId.intValue()==0){
            list= musicService.getAll();
        }else{
            list= musicService.getAlbumMusicList(albumId);
        }

        setMusicInfo(list);

        ResultBean resultBean = ResultBean.success(list);
        return resultBean;
    }
    @ResponseBody
    @RequestMapping(path = {"/getMusic"}, method = RequestMethod.GET)
    public ResultBean getMusic(@RequestParam(required=false)  Integer musicId) {
        if (musicId==null||musicId.intValue()==0){
            List<Music> list= musicService.getAll();
            setMusicInfo(list);
            ResultBean resultBean = ResultBean.success(list);
            return resultBean;
        }
        Music music= musicService.getInfo(musicId);
        if (music!=null) {
            setMusicInfo(music);

            ResultBean resultBean = ResultBean.success(music);
            return resultBean;
        }
        return ResultBean.NOFOUND_RESULTBEAN;
    }
    @ResponseBody
    @RequestMapping(path = {"/list"}, method = RequestMethod.POST)
    public ResultBean list(@RequestBody RequestBean requestBean) {
        List<Music> list;
        if (requestBean.getId()!=null) {
            list = musicService.getAlbumMusicList(requestBean.getId());
        }else{
            list = musicService.getAll();
        }
        setMusicInfo(list);

        ResultBean resultBean = ResultBean.success(list);
        return resultBean;
    }
    @ResponseBody
    @RequestMapping(path = {"/listBySinger"}, method = RequestMethod.POST)
    public ResultBean getListBySinger(@RequestBody RequestPageBean requestBean) {
        if (requestBean.getId()==null||requestBean.getId().intValue()==0){
            return ResultBean.PARAMETER_ERROR_RESULTBEAN;
        }
        if (requestBean.getPage()==null||requestBean.getPage().intValue()==0){
            List<Music> list = musicService.getSingerMusicList(requestBean.getId());
            setMusicInfo(list);
            ResultBean resultBean = ResultBean.success(list);
            return resultBean;
        }
        PageInfo<Music> pageInfo = musicService.getSingerMusicList(requestBean.getId(),requestBean.getPage(),requestBean.getPageSize());
        if (pageInfo!=null){
            setMusicInfo(pageInfo.getList());
        }
        return ResultPageBean.success(pageInfo);
    }

    @ResponseBody
    @RequestMapping(path = {"/search"}, method = RequestMethod.POST)
    public ResultBean search(@RequestBody MusicDto requestBean) {
        if (requestBean.getPage()==null||requestBean.getPage().intValue()==0){
            List<Music> list = musicService.search(requestBean);
            setMusicInfo(list);
            ResultBean resultBean = ResultBean.success(list);
            return resultBean;
        }
        PageInfo<Music> pageInfo = musicService.searchPage(requestBean);
        if (pageInfo!=null){
            setMusicInfo(pageInfo.getList());
        }
        return ResultPageBean.success(pageInfo);
    }
    @ResponseBody
    @RequestMapping(path = {"/info"}, method = RequestMethod.POST)
    public ResultBean getInfo(@RequestBody RequestBean requestBean) {
        if (requestBean.getId()==null||requestBean.getId().intValue()==0){
            return ResultBean.PARAMETER_ERROR_RESULTBEAN;
        }
        Music music = musicService.getInfo(requestBean.getId());
        if (music ==null){
            return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"记录不存在");
        }
        setMusicInfo(music);
        ResultBean resultBean = ResultBean.success(music);
        return resultBean;
    }
    /**
     * 检查参数的合法性
     */
    private ResultBean checkParameter(Music music) {
        if (StringUtils.isEmpty(music.getName())){
            return ResultBean.PARAMETER_ERROR_RESULTBEAN;
        }
        if(music.getAlbumId()==null||music.getAlbumId().intValue()==0){
            return ResultBean.PARAMETER_ERROR_RESULTBEAN;
        }
        return null;
    }
    @ResponseBody
    @RequestMapping(path = {"/create"}, method = RequestMethod.POST)
    public ResultBean add(@RequestBody JSONObject jsonObject) {
        Music music =jsonObject.toJavaObject(Music.class);

        ResultBean resultBean=checkParameter(music);
        if (resultBean!=null){
            return resultBean;
        }
        Album album= albumService.getInfo(music.getAlbumId());
        if (album==null){
            return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"专辑不存在");
        }
        music.setSingerId(album.getSingerId());
        int result = musicService.insert(music);
        if (result>0){
            resultBean = ResultBean.success();
            return resultBean;
        }
        return ResultBean.fail();
    }
    @ResponseBody
    @RequestMapping(path = {"/saveMusic"}, method = RequestMethod.POST)
    public ResultBean saveMusic(@RequestBody JSONObject jsonObject) {
        Music music =jsonObject.toJavaObject(Music.class);

        ResultBean resultBean=checkParameter(music);
        if (resultBean!=null){
            return resultBean;
        }
        int result=0;
        if (music.getId()>0) {
            Music oldMusic= musicService.getInfo(music.getId());
            if (oldMusic==null){
                return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"记录不存在");
            }
            if (music.getAlbumId().intValue()!=oldMusic.getAlbumId().intValue()){
                Album album= albumService.getInfo(music.getAlbumId());
                if (album==null){
                    return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"专辑不存在");
                }
                music.setSingerId(album.getSingerId());
            }
            result=musicService.update(music);
            if (result>0&&oldMusic.getFilePath()!=null&&oldMusic.getFilePath().equals(music.getFilePath())){
                deleteFile(oldMusic);
            }
        }else{
            Album album= albumService.getInfo(music.getAlbumId());
            if (album==null){
                return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"专辑不存在");
            }
            result=musicService.insert(music);
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
        Music music =jsonObject.toJavaObject(Music.class);
        if (music.getId()==null|| music.getId().intValue()==0){
            return ResultBean.PARAMETER_ERROR_RESULTBEAN;
        }

        ResultBean resultBean=checkParameter(music);
        if (resultBean!=null){
            return resultBean;
        }

        Music oldMusic = musicService.getInfo(music.getId());
        if (oldMusic ==null){
            return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"记录不存在");
        }
        if (music.getAlbumId().intValue()!=oldMusic.getAlbumId().intValue()){
            Album album= albumService.getInfo(music.getAlbumId());
            if (album==null){
                return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"专辑不存在");
            }
            music.setSingerId(album.getSingerId());
        }
        int result = musicService.update(music);
        if (result>0){
            if (oldMusic.getFilePath()!=null&&oldMusic.getFilePath().equals(music.getFilePath())){
                deleteFile(oldMusic);
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
        Music music = musicService.getInfo(requestBean.getId());
        if (music ==null){
            return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"记录不存在");
        }
        int result = musicService.delete(requestBean.getId());
        if (result>0){
            deleteFile(music);
            ResultBean resultBean = ResultBean.success();
            return resultBean;
        }
        return ResultBean.fail();
    }
    private void setMusicInfo(List<Music> musics){
        if (musics==null||musics.isEmpty()){
            return;
        }
        for(Music music:musics){
            setMusicInfo(music);
        }
    }
    private final DecimalFormat df = new DecimalFormat(".00");
    private void setMusicInfo(Music music){
        music.setTimeLengthText(getMusicLength(music.getTimeLength()));

        music.setFileUrl(music.getFilePath());
        if (!StringUtils.isEmpty(music.getFilePath())&& music.getFilePath().startsWith("/")){
            music.setFileUrl(musicHttpUrl + music.getFilePath());
        }
        music.setAlbumPictureUrl(music.getAlbumPicturePath());
        if (!StringUtils.isEmpty(music.getAlbumPicturePath())&& music.getAlbumPicturePath().startsWith("/")){
            music.setAlbumPictureUrl(musicHttpUrl + music.getAlbumPicturePath());
        }
        if (music.getFileSize()!=null){
            int value=music.getFileSize().intValue()/1024;
            if (value<1024){
                music.setFileSizeText(value + "KB");
            }else{
                float m=value/1024F;
                music.setFileSizeText(df.format(m) + "MB");
            }
        }
    }
    public static String getMusicLength(int length){
        if (length<=60){
            return length + "秒";
        }
        int minute=length/60;
        int second=length%60;
        String str=minute + "分钟";
        if (second>0){
            str=str+second + "秒";
        }
        return str;
    }
    private void deleteFile(Music music){
        if (!StringUtils.isEmpty(music.getFilePath())&&music.getFilePath().startsWith(musicFilePathPrefix)){
            String fileName=storeFilePath + music.getFilePath();
            File file=new  File(fileName);
            if (file.exists()){
                file.delete();
            }
        }
    }
}
