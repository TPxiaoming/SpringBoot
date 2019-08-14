package com.xiaoming.day26;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 第三种启动方法
 * @SpringBootApplication = @ComponentScan + @EnableAutoConfiguration + @Configuration
 * pringBootApplication 扫包范围 是在同级包和子集包下
 */
@SpringBootApplication
/*@ComponentScan("com.xiaoming.day26.controller") //控制扫包范围
@EnableAutoConfiguration*/
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
