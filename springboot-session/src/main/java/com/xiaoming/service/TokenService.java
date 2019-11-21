package com.xiaoming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author xiaoming
 * @Date 2019/11/21
 */
@Service
public class TokenService {

    @Autowired
    private RedisService redisService;

    /**
     * 新增返回 token
     * @param object
     * @return
     */
    public String put(Object object){
        String token = getToken();
        redisService.setString(token, object, 600L);
        return token;
    }

    /**
     * 获取信息
     * @param token
     * @return
     */
    public String get(String token){
        String result = redisService.getString(token);
        return result;
    }

    public String getToken(){
        return UUID.randomUUID().toString();
    }
}
