package com.example.musicstore.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.musicstore.bean.RequestBean;
import com.example.musicstore.bean.ResultBean;
import com.example.musicstore.model.bean.Album;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/test/")
public class TestController {
    @ResponseBody
    @RequestMapping(path = {"/list"}, method = RequestMethod.POST)
    public ResultBean list(@RequestBody RequestBean requestBean) {
        String json="[{\"id\":\"89938032\",\"code\":\"wxd516f38af3fc123f\",\"supplier\":\"音泰思\",\"engineer\":\"周XX\",\"founder\":\"找XX\",\"type\":\"公司盘点\",\"create_time\":\"2020/9/2 15:45\",\"start_time\":\"2020/9/2\",\"end_time\":\"2020/10/2\",\"status\":\"计划\",\"all_number\":100,\"List\":[{\"id\":\"1\",\"code_number\":\"QC78990390\",\"name\":\"笔记本\",\"use_department\":\"研发中心\",\"check_department \":\"IT系统部\",\"storage_address\":\"新研发大楼/主楼2楼/IS办公区\"},{\"id\":\"2\",\"code_number\":\"QC78990391\",\"name\":\"笔记本\",\"use_department\":\"研发中心\",\"check_department \":\"IT系统部\",\"storage_address\":\"新研发大楼/主楼2楼/IS办公区\"},{\"id\":\"3\",\"code_number\":\"QC78990392\",\"name\":\"笔记本\",\"use_department\":\"研发中心\",\"check_department \":\"IT系统部\",\"storage_address\":\"新研发大楼/主楼2楼/IS办公区\"}]},{\"id\":\"89938033\",\"code\":\"wxd516f38af3fc123f\",\"supplier\":\"音泰思\",\"engineer\":\"周XX\",\"founder\":\"找XX\",\"type\":\"公司盘点\",\"create_time\":\"2020/9/2 15:45\",\"start_time\":\"2020/9/2\",\"end_time\":\"2020/10/2\",\"status\":\"计划\",\"all_number\":100,\"ok_number\":10,\"List\":[{\"id\":\"4\",\"code_number\":\"QC78990390\",\"name\":\"笔记本\",\"use_department\":\"研发中心\",\"check_department \":\"IT系统部\",\"storage_address\":\"新研发大楼/主楼2楼/IS办公区\"},{\"id\":\"5\",\"code_number\":\"QC78990391\",\"name\":\"笔记本\",\"use_department\":\"研发中心\",\"check_department \":\"IT系统部\",\"storage_address\":\"新研发大楼/主楼2楼/IS办公区\"},{\"id\":\"6\",\"code_number\":\"QC78990392\",\"name\":\"笔记本\",\"use_department\":\"研发中心\",\"check_department \":\"IT系统部\",\"storage_address\":\"新研发大楼/主楼2楼/IS办公区\"}]}]";
        JSONArray jsonObject= JSON.parseArray(json);
        ResultBean resultBean = ResultBean.success(jsonObject);
        return resultBean;
    }
    @ResponseBody
    @RequestMapping(path = {"/detail"}, method = RequestMethod.POST)
    public ResultBean getDetail(@RequestBody RequestBean requestBean) {
        String json="{\"id\":\"1\",\"code\":\"wxd516f38af3fc123f\",\"supplier\":\"音泰思\",\"engineer\":\"周XX\",\"type\":\"公司盘点\",\"create_time\":\"2020/9/2 15:45\",\"start_time\":\"2020/9/2\",\"end_time\":\"2020/10/2\",\"status\":\"计划\",\"all_number\":100,\"List\":[{\"id\":1,\"code_number\":\"QC78990390\",\"name\":\"笔记本\",\"use_department\":\"研发中心\",\"check_department \":\"IT系统部\",\"storage_dddress\":\"新研发大楼/主楼2楼/IS办公区\"},{\"id\":2,\"code_number\":\"QC78990391\",\"name\":\"笔记本\",\"use_department\":\"研发中心\",\"check_department \":\"IT系统部\",\"storage_dddress\":\"新研发大楼/主楼2楼/IS办公区\"},{\"id\":3,\"code_number\":\"QC78990392\",\"name\":\"笔记本\",\"use_department\":\"研发中心\",\"check_department \":\"IT系统部\",\"storage_dddress\":\"新研发大楼/主楼2楼/IS办公区\"}]}\n";
        JSONObject jsonObject= JSON.parseObject(json);
        ResultBean resultBean = ResultBean.success(jsonObject);
        return resultBean;
    }
}
