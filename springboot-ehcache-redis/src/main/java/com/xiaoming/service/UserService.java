package com.xiaoming.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoming.entity.Users;
import com.xiaoming.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaoming
 * @Date 2019/11/11
 * @blame Android Team
 */
@Service
public class UserService {

    @Autowired
    private EhCacheUtils ehCacheUtils;

    private static final String CACHENAME_USERCACHE = "userCache";

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserMapper userMapper;

    public Users getUser(Long id) {
        String key = this.getClass().getName() + "_" + Thread.currentThread().getStackTrace()[1].getMethodName()
                + "-id:" + id;
        //1.先查找一级缓存（本地缓存），如果本地缓存有数据直接返回
        Users ehcacheUsers = (Users) ehCacheUtils.get(CACHENAME_USERCACHE, key);
        if (ehcacheUsers != null) {
            System.out.println("使用key：" + key + "，查询一级缓存 ehcache 获取到 ehcacheUsers：" + JSONObject.toJSONString(ehcacheUsers));
            return ehcacheUsers;
        }
        //2.如果本地缓存没有数据，直接查询二级缓存
        String redisUserJson = redisService.getString(key);
        if (!StringUtils.isEmpty(redisUserJson)) {
            //将 json 转成对象（如果二级缓存中有数据直接返回二级缓存，并且更新一级缓存）
            Users redisUsers = JSON.parseObject(redisUserJson, Users.class);
            //更新一级缓存
            ehCacheUtils.put(CACHENAME_USERCACHE, key, redisUsers);
            System.out.println("使用key：" + key + "，查询二级缓存 redis 获取到 redisUsers：" + JSONObject.toJSONString(redisUsers));
            return redisUsers;
        }
        //3.如果二级缓存 Redis 中也没有数据，查询数据库
        Users user = userMapper.getUser(id);
        if (user == null) {
            return null;
        }

        //更新一级缓存和二级缓存
        String userJson = JSONObject.toJSONString(user);
        ehCacheUtils.put(CACHENAME_USERCACHE, key, user);
        redisService.setString(key, userJson, 60L);
        System.out.println("使用key：" + key + "，查询数据库 获取到 user：" + userJson);
        return user;
    }
}
