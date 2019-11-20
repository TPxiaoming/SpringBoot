package com.xiaoming.session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 配置 redis 服务器的连接
 * @author xiaoming
 * @Date 2019/11/20
 */
//maxInactiveIntervalInSeconds 为 springsession 的过去时间（单位：秒）
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
public class SessionConfig {

    /**
     * 冒号后的值为没有配置文件时，制动装载的默认值
     */
    @Value("${redis.hostname:localhost}")
    String HostName;
    @Value("${redis.port:6379}")
    int Port;
    @Value("${redis.password:123456}")
    String password;

    @Bean
    public JedisConnectionFactory connectionFactory(){
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setPort(Port);
        connectionFactory.setHostName(HostName);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }
}
