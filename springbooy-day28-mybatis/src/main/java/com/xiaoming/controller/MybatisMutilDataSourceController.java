package com.xiaoming.controller;

import com.xiaoming.test01.service.UserServcieTest01;
import com.xiaoming.test02.service.UserServcieTest02;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 多数据源测试
 * @author xiaoming
 */
@RestController
public class MybatisMutilDataSourceController {

    @Autowired
    private UserServcieTest01 userServcieTest01;
    @Autowired
    private UserServcieTest02 userServcieTest02;


    @RequestMapping("/insertUserTest01")
    public int insertUserTest01(String name, Integer age){
        int result = userServcieTest01.insertUser(name, age);
        return result;
    }

    @RequestMapping("/insertUserTest02")
    public int insertUserTest02(String name, Integer age){
        int result = userServcieTest02.insertUser(name, age);
        return result;
    }

    @RequestMapping("/insertUserTest01AndTest02")
    public int insertUserTest01AndTest02(String name, Integer age){
        int result = userServcieTest01.insertUserTest01AndTest02(name, age);
        return result;
    }
}
