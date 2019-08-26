package com.xiaoming.controller;

import com.xiaoming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController 是 springmvc 提供的
 * @author xiaoming
 * @Date 2019/8/26
 */
@RestController
public class IndexController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index", produces = "text/html;charset=UTF-8")
    public String index(){
        return userService.index();
    }
}
