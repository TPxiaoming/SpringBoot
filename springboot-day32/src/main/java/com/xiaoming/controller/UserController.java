package com.xiaoming.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xiaoming
 * @Date 2019/8/26
 */
@Controller
public class UserController {

    @RequestMapping("/pageIndex")
    public String pageIndex(){
        return "pageIndex";
    }
}
