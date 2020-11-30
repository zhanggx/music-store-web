package com.example.musicstore.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.musicstore.bean.RequestBean;
import com.example.musicstore.bean.RequestPageBean;
import com.example.musicstore.bean.ResultBean;
import com.example.musicstore.bean.ResultPageBean;
import com.example.musicstore.model.bean.Album;
import com.example.musicstore.model.bean.Music;
import com.example.musicstore.model.bean.dto.MusicDto;
import com.example.musicstore.service.AlbumService;
import com.example.musicstore.service.MusicService;
import com.example.musicstore.service.SingerService;
import com.example.musicstore.service.ThemeService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.List;

/**
 *RecommendController控制类
 */
@RestController
@CrossOrigin
@RequestMapping("/recommend/")
public class RecommendController {
    @Value("${musicstore.music.http.url}")
    private String musicHttpUrl;
    @Autowired
    MusicService musicService;
    @Autowired
    AlbumService albumService;

    @ResponseBody
    @RequestMapping(path = {"/getList"}, method = RequestMethod.GET)
    public ResultBean getAllList() {
        List<Music> list= musicService.getRecommendList();

        setMusicInfo(list);

        ResultBean resultBean = ResultBean.success(list);
        return resultBean;
    }
    @ResponseBody
    @RequestMapping(path = {"/list"}, method = RequestMethod.POST)
    public ResultBean list(@RequestBody RequestBean requestBean) {
        List<Music> list;
            list = musicService.getRecommendList();
        setMusicInfo(list);

        ResultBean resultBean = ResultBean.success(list);
        return resultBean;
    }
    @ResponseBody
    @RequestMapping(path = {"/move"}, method = RequestMethod.POST)
    public ResultBean cancel(@RequestBody JSONObject object) {
        Integer sourceId=object.getInteger("sourceId");
        Integer destId=object.getInteger("destId");
        if (sourceId==null||destId==0){
            return ResultBean.PARAMETER_ERROR_RESULTBEAN;
        }
        try {
            return musicService.moveRecommendIndex(sourceId, destId);
        }catch(Throwable tr){
            tr.printStackTrace();
        }
        return ResultBean.fail();
    }
    @ResponseBody
    @RequestMapping(path = {"/add"}, method = RequestMethod.POST)
    public ResultBean addRecommend(@RequestBody RequestBean requestBean) {
        if (requestBean.getId()==null||requestBean.getId().intValue()==0){
            return ResultBean.PARAMETER_ERROR_RESULTBEAN;
        }
        Music music = musicService.getInfo(requestBean.getId());
        if (music ==null){
            return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"记录不存在");
        }
        if (music.getRecommendIndex()>0){
            ResultBean resultBean = ResultBean.success();
            return resultBean;
        }
        Integer maxIndex=musicService.getMaxRecommendIndex();
        if (maxIndex==null||maxIndex.intValue()<=0){
            maxIndex=1;
        }else{
            maxIndex++;
        }
        int result = musicService.updateRecommendIndex(requestBean.getId(),maxIndex);
        if (result>0){
            ResultBean resultBean = ResultBean.success();
            return resultBean;
        }
        return ResultBean.fail();
    }
    @ResponseBody
    @RequestMapping(path = {"/cancel"}, method = RequestMethod.POST)
    public ResultBean cancel(@RequestBody RequestBean requestBean) {
        if (requestBean.getId()==null||requestBean.getId().intValue()==0){
            return ResultBean.PARAMETER_ERROR_RESULTBEAN;
        }
        Music music = musicService.getInfo(requestBean.getId());
        if (music ==null){
            return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"记录不存在");
        }

        int result = musicService.updateRecommendIndex(requestBean.getId(),0);
        if (result>0){
            ResultBean resultBean = ResultBean.success();
            return resultBean;
        }
        return ResultBean.fail();
    }
    @ResponseBody
    @RequestMapping(path = {"/update"}, method = RequestMethod.POST)
    public ResultBean update(@RequestBody RequestBean requestBean) {

        if (requestBean.getId()==null|| requestBean.getId().intValue()==0){
            return ResultBean.PARAMETER_ERROR_RESULTBEAN;
        }


        Music music = musicService.getInfo(requestBean.getId());
        if (music ==null){
            return ResultBean.fail(ResultBean.ERROR_CODE_RECORDNOEXISTS,"记录不存在");
        }
        int result;
        if (music.getRecommendIndex()>0){
            music.setRecommendIndex(0);
            result = musicService.updateRecommendIndex(requestBean.getId(),0);
        }else{
            Integer maxIndex=musicService.getMaxRecommendIndex();
            if (maxIndex==null||maxIndex.intValue()<=0){
                maxIndex=1;
            }else{
                maxIndex++;
            }
            result = musicService.updateRecommendIndex(requestBean.getId(),maxIndex);
        }
        if (result>0){
            return ResultBean.success();
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
        music.setTimeLengthText(MusicController.getMusicLength(music.getTimeLength()));

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
}
