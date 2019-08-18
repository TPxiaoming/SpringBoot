package com.xiaoming;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mybatis 启动方式
 * 1.可以在 mapper 层不需要加 mapper 注解。但是一定要在启动类的时候加上 @MapperScan
 * 2.在 mybatis 接口加上 @Mapper 注入 mybatis 容器，就不需要在启动类上加上 @MapperScan
 */
@SpringBootApplication
//@MapperScan(basePackages = {"com.xiaoming.mapper"})   //通过反射读取该包下所有的类，装入到容器中
@MapperScan(basePackages = {"com.xiaoming.*.mapper"})   //通过反射读取该包下所有的类，装入到容器中
public class MyBaitsApp {
    public static void main(String[] args) {
        SpringApplication.run(MyBaitsApp.class, args);
    }
}
