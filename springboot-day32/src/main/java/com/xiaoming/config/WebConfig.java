package com.xiaoming.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * springmvc 配置信息
 * @EnableWebMvc 开启 springmvc 功能
 * @Configuration 加载配置文件
 * @author xiaoming
 * @Date 2019/8/26
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.xiaoming.controller" })
public class WebConfig extends WebMvcConfigurerAdapter {
    /**
     * springboot 整合jsp 最好是war
     * 需要配置转换视图器
     * 创建 SpringMVC 视图解析器
     * @return
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        //可以在JSP页面中通过${}访问beans
        viewResolver.setExposeContextBeansAsAttributes(true);
        return viewResolver;
    }

}
