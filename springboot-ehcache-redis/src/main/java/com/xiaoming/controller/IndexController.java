package com.xiaoming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaoming
 * @Date 2019/11/7
 * @blame Android Team
 */
@RestController
public class IndexController {

    @Autowired
    private CacheManager cacheManager;

    @RequestMapping("/remvoKey")
    public void remoKey() {
        cacheManager.getCache("userCache").clear();
    }
}
