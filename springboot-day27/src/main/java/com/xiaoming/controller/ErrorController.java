package com.xiaoming.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局捕获异常案例
 *
 * 如果都可能会发生异常，每个方法都加上 try
 * 全局捕获异常思路：使用 aop 技术，采用异常通知
 */
@RestController
public class ErrorController {
//    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);
    @RequestMapping("/getUser")
    public String getUser(int i){
        int j = 1/i;
        return "success" + j;
    }

    @RequestMapping("/getMember")
    public String getMember(String name, Integer age){
        //这种打印日志不推荐，因为每个方法都要这样打印，推荐使用aop
//        logger.info("name:" + name +",age:" + age);
        return name;
    }

}
