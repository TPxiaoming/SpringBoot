package com.xiaoming.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 监控中心
 * springmvc 的 bean 为单例， 线程不安全
 * @author xiaoming
 * @Date 2019/8/19
 */
@RestController
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
