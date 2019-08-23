package com.xiaoming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 演示 springboot jvm 参数调优
 * @author xiaoming
 * @Date 2019/8/19
 */
@SpringBootApplication
public class SpringBootApp {

    /**
     * 内部启动
     * -XX:+PrintGCDetails -Xmx32M -Xms1M
     *打印详细GC日志 最大堆内存32M 初始堆内存 1M
     * 默认是 4个G
     * 预计会发生多少次回收？特别频繁
     *
     * 外部启动
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }
}
