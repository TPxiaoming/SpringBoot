package com.xiaoming.day26.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 第一个 SpringBoot 项目
 *
 * 在微服务项目中，基本都在类上加上 @RestController
 * @RestController = @Controller + @ResposeBody 联合注解
 * RestController 修饰的类下所有的方法都是返回 json 格式，这样的话不要在方法上加上 @ResposeBody
 *
 * 思考：如何启动? 使用 main 启动
 * @EnableAutoConfiguration 让 Spring Boot   根据应用所声明的依赖来对 Spring 框架进行自动配置
 */
@RestController
//@EnableAutoConfiguration
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "xiaoming  这是我的一个 SpringBoot项目。分布式微服务相关知识";
    }


    /*public static void main(String[] args) {
        //程序入口 默认端口 8080
        SpringApplication.run(IndexController.class, args);
    }*/
}
