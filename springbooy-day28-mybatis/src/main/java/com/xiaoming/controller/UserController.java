package com.xiaoming.controller;

import com.xiaoming.service.UserServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
public class UserController {
    @Autowired
    private UserServcie userServcie;

    @RequestMapping("/insertUser")
    public int insertUser(String name, Integer age){
        int result = userServcie.insertUser(name, age);
        return result;
    }
}
