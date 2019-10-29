package com.xiaoming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * springboot 2.0 整合redis
 * @author xiaoming
 * @Date 2019/10/29
 */
@Component
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     *
     * @param key
     * @param object
     * @param time
     */
    public void set(String key, Object object, Long time){
        //使该方法能够支持多种数据类型存放

        //如果存放 String 类型
        if (object instanceof String){
            String value = (String) object;
            stringRedisTemplate.opsForValue().set(key, value);
        }

        //如果存放 set 类型
        if (object instanceof Set){
            Set<String> value = (Set) object;
            for (String string : value) {
                stringRedisTemplate.opsForSet().add(key, string);
            }
        }

        //设置有效期
        if (time != null) {
            stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
        }

    }

    public void setString(String key, Object object, Long time){
        String value = (String) object;
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public void setSet(String key, Object object, Long time){
        Set<String> value = (Set) object;
        for (String string : value) {
            stringRedisTemplate.opsForSet().add(key, string);
        }
    }

    public String getString(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }
}
