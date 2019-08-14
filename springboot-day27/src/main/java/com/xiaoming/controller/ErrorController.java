package com.xiaoming.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局捕获异常案例
 *
 * 如果都可能会发生异常，每个方法都加上 try
 * 全局捕获异常思路：使用 aop 技术，采用异常通知
 */
@RestController
public class ErrorController {


    @RequestMapping("/getUser")
    public String getUser(int i){
        int j = 1/i;
        return "success" + j;
    }
}
