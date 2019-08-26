package com.xiaoming.tomcat;

import com.xiaoming.servlet.IndexServlet;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

/**
 * 使用 Java 语言创建 Tomcat 服务器
 * Tomcat 底层执行程序 servlet
 * SpringMVC 底层使用 Servlet 包装
 * @author xiaoming
 * @Date 2019/8/26
 */
public class Test001 {

    public static int PORT = 8080;
    public static String CONTEXT_PATH = "/xiaoming";
    public static String SERVLET_NAME = "inxdexServlet";

    public static void main(String[] args) throws InterruptedException, LifecycleException {
        //创建 Tomcat 服务器
        Tomcat tomcatServer = new Tomcat();
        //指定端口号
        tomcatServer.setPort(PORT);
        //是否设置自动部署
        tomcatServer.getHost().setAutoDeploy(false);
        //创建上下文
        StandardContext standardContext = new StandardContext();
        standardContext.setPath(CONTEXT_PATH);
        //监听上下文
        standardContext.addLifecycleListener(new Tomcat.FixContextListener());
        // tomcat容器添加standardContext
        tomcatServer.getHost().addChild(standardContext);

        //创建 servlet
        tomcatServer.addServlet(CONTEXT_PATH, SERVLET_NAME, new IndexServlet());
        //servlet url 映射
        standardContext.addServletMappingDecoded("/index",  SERVLET_NAME);
        tomcatServer.start();
        System.out.println("tomcat 容器启动成功...");
        //异步进行接收请求
        tomcatServer.getServer().await();
    }
}
