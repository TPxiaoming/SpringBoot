package com.xiaoming.controller;

import com.xiaoming.entity.Users;
import com.xiaoming.service.UserService;
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

    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    public Users getUser(Long id){
        return userService.getUser(id);
    }

    @RequestMapping("/remvoKey")
    public void remoKey() {
        cacheManager.getCache("userCache").clear();
    }
}
