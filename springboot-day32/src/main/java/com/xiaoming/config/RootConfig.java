package com.xiaoming.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 根配置
 *
 * @author xiaoming
 * @Date 2019/8/26
 */
@Configuration
@ComponentScan(basePackages = {"com.xiaoming"})
public class RootConfig {
}
