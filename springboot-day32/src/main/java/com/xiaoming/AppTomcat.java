package com.xiaoming;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;

/**
 * 使用 java 内置 Tomcat 运行 SpringMVC 框架
 * 原理：tomcat 加载到 springmvc 注解启动方式，就会创建 springmvc 容器
 * @author xiaoming
 * @Date 2019/8/26
 */
public class AppTomcat {
    public static void main(String[] args) throws ServletException, LifecycleException {
        start();
    }

    public static void start() throws ServletException, LifecycleException {

        // 创建Tomcat容器
        Tomcat tomcatServer = new Tomcat();
        // 端口号设置
        tomcatServer.setPort(9090);
        // 读取项目路径 可以加载到静态资源
        StandardContext ctx = (StandardContext) tomcatServer.addWebapp("/", new File("src/main").getAbsolutePath());
        // 禁止重新载入
        ctx.setReloadable(false);
        // class文件读取地址
        File additionWebInfClasses = new File("target/classes");
        // 创建WebRoot
        WebResourceRoot resources = new StandardRoot(ctx);
        // tomcat内部读取Class执行
        resources.addPreResources(
                new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
        tomcatServer.start();
        // 异步等待请求执行
        tomcatServer.getServer().await();

    }

}
