package com.xiaoming.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
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
