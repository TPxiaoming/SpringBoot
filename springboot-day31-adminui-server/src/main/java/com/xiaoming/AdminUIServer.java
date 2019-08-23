package com.xiaoming;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaoming
 * @Date 2019/8/20
 */
@Configuration
@EnableAutoConfiguration
@EnableAdminServer
public class AdminUIServer {
    public static void main(String[] args) {
        SpringApplication.run(AdminUIServer.class, args);
    }
}
