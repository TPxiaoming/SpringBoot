package com.xiaoming.controller;

import com.xiaoming.service.JdbcUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JdbcTempateController {

    @Autowired
    private JdbcUserServiceImpl jdbcUserService;

    @RequestMapping("/jdbcCreateUser")
    public String jdbcCreateUser(String name,Integer age){
        jdbcUserService.createUser(name, age);
        return "success";
    }
}
