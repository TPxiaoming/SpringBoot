package com.xiaoming.controller;

import com.xiaoming.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaoming
 * @Date 2019/10/29
 */
@RestController
public class IndexController {

    @Autowired
    private RedisService redisService;

    @RequestMapping("/setString")
    public String setString(String key, String object){
        redisService.setString(key, object, 60L);
        return "success";
    }

    @RequestMapping("/getString")
    public String getString(String key){
        return redisService.getString(key);
    }
}
