package com.xiaoming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 第三种启动方法
 * @SpringBootApplication = @ComponentScan + @EnableAutoConfiguration + @Configuration
 * pringBootApplication 扫包范围 是在同级包和子集包下
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
