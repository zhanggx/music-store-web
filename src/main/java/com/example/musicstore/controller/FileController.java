package com.example.musicstore.controller;

import com.example.musicstore.bean.ResultBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/file/")
public class FileController {
    @Value("${musicstore.file.path}")
    private String storeFilePath;
    @Value("${musicstore.image.file.path.prefix}")
    private String imageFilePathPrefix;
    @Value("${musicstore.music.file.path.prefix}")
    private String musicFilePathPrefix;

    @PostMapping("/uploadImg")
    @ResponseBody
    public ResultBean upload(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename().toLowerCase();
        int index=fileName.lastIndexOf(".");
        if (index>=0){
            String ext=fileName.substring(index);
            String name=fileName.substring(0,index);
            fileName=name + "_" + System.currentTimeMillis() + ext;
        }
        File dest = new File(storeFilePath + "/" + imageFilePathPrefix + "/" + fileName);
        try {
            file.transferTo(dest);
            return ResultBean.success(imageFilePathPrefix + "/" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultBean.fail();
    }

    @PostMapping("/uploadMusic")
    @ResponseBody
    public ResultBean uploadMusic(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename().toLowerCase();
        int index=fileName.lastIndexOf(".");
        if (index>=0){
            String ext=fileName.substring(index);
            String name=fileName.substring(0,index);
            fileName=name + "_" + System.currentTimeMillis() + ext;
        }
        File dest = new File(storeFilePath + "/" + musicFilePathPrefix + "/" + fileName);
        try {
            file.transferTo(dest);
            return ResultBean.success(musicFilePathPrefix + "/" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultBean.fail();
    }
}
