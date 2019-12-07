package com.xiaoming.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaoming
 * @Date 2019/12/7
 */
@RestController
public class IndexCotroller {

    @Value("${name:xiaoming}")
    private String name;

    @RequestMapping("/getName")
    public String getName(){
        return name;
    }
}
