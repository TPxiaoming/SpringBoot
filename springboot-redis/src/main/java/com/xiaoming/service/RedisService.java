package com.xiaoming.service;

import com.xiaoming.annotation.ExtRedisTranscation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * springboot 2.0 整合redis
 *
 * @author xiaoming
 * @Date 2019/10/29
 * @blame Android Team
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

//    @ExtRedisTranscation
    public void setString(String key, Object object, Long time){
        String value = (String) object;
        stringRedisTemplate.opsForValue().set(key, value);

       /* //开启事务权限
        stringRedisTemplate.setEnableTransactionSupport(true);
        //开启事务
        stringRedisTemplate.multi();
        try {
            String value = (String) object;
            stringRedisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            //事务回滚
            stringRedisTemplate.discard();
        } finally {
            //提交事务
            stringRedisTemplate.exec();
        }*/
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
