package com.xiaoming.service;

import com.xiaoming.entity.Users;
import com.xiaoming.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaoming
 * @Date 2019/11/13
 * @blame redis 雪崩效应
 */
@Service
public class UserAvalancheService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;

    private Lock lock = new ReentrantLock();

    /**
     * 如果 redis 连接失效，那么都会去访问数据库
     * 会导致数据库崩溃
     *
     * 如果 redis 是集群的话，使用分布式锁
     * @param id
     * @return
     */
    public String getUser(Long id){
        String key = this.getClass().getName() + "-" + Thread.currentThread().getStackTrace()[1].getMethodName() + "-id:" + id;

        //1.查询redis
        String userName = redisService.getString(key);
        if (!StringUtils.isEmpty(userName)){
            return userName;
        }

        String name = null;
        try {
            // 开启本地所=锁
            lock.lock();
            //2.查询数据库
            Users user = userMapper.getUser(id);
            if (user == null){
                return null;
            }
            name = user.getName();
            redisService.setString(key, name, 60L);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放锁
            lock.unlock();
        }
        //3.直接返回
        return name;
    }

}
