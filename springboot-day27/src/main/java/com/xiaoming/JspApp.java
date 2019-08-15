package com.xiaoming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync  //开启异步调用  扫描加上Async注解的方法，创建线程调用
public class JspApp {

    public static void main(String[] args) {
        SpringApplication.run(JspApp.class, args);
    }
}
