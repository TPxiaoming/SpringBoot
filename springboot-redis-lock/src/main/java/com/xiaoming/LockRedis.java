package com.xiaoming;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

/**
 * 基于 redis 实现分布式锁代码思路
 * 核心方法：获取锁、释放锁
 * <p>
 * redis 以 key（REDIS_LOCK_KEY）和value（随机不能重复的数字——锁id） 方式进行存储
 * <p>
 * redis 实现分布式锁 有两个超时时间问题：
 * 1.在获取锁之前的超时时间——在尝试获取锁的时候，如果在规定的时间内还没有获取到锁，直接放弃
 * 2.在获取锁之后的超时时间——当获取锁成功之后，对应的 key 有对应的有效期，对应的 key 在规定时间内进行失效
 *
 * @author xiaoming
 * @Date 2019/12/5
 */
public class LockRedis {
    /**
     * redis 线程池
     */
    private JedisPool jedisPool;
    /**
     * 同时在 Redis 上创建相同的一个 key ,相同 key 的名称
     */
    private static final String REDIS_LOCK_KEY = "redis_lock";

    public LockRedis(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 获取锁
     * 1.建立 reids 连接
     * 2.定义 redis 对应 key 的 value 值（暂定 uuid），value作用：释放锁
     * 3.定义获取锁之前的超时时间
     * 4.定义获取锁之后的超时时间
     * 5.使用循环机制，保证重复进行尝试获取锁（乐观锁）
     * 6.使用 setnx 命令插入对应的 REDIS_LOCK_KEY，如果返回为 1 表示成功获取锁
     *
     * @param acquireTimeout 在获取锁之前的超时时间
     * @param timeOut        在获取锁之后的超时时间 毫秒转成秒
     */
    public String getRedisLock(Long acquireTimeout, Long timeOut) {
        Jedis conn = null;
        try {
            //1.建立 reids 连接
            conn = jedisPool.getResource();
            //2.定义 redis 对应 key 的 value 值（暂定 uuid），value作用：释放锁
            String identifierValue = UUID.randomUUID().toString();
            //4.定义获取锁之后的超时时间 毫秒转成秒
            int expireLock = (int) (timeOut / 1000);

            //3.定义获取锁之前的超时时间
            //5.使用循环机制，如果没有获取到锁，要在规定的 aquireTimeout 时间 ，保证重复进行尝试获取锁（乐观锁）
            Long endTime = System.currentTimeMillis() + acquireTimeout;
            if (System.currentTimeMillis() < endTime) {
                //获取锁
                //使用 setnx 命令插入对应的 REDIS_LOCK_KEY，如果返回为 1 表示成功获取锁
                if (conn.setnx(REDIS_LOCK_KEY, identifierValue) == 1) {
                    //设置对应 key 的有效期
                    conn.expire(REDIS_LOCK_KEY, expireLock);
                    return identifierValue;
                }
            }

        } catch (Exception e) {

        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }


    /**
     * 释放锁 有两种方式
     * 1.key 有有效期
     * 2.整个程序执行完毕
     * <p>
     * 如果直接使用 del 删除 key 释放锁，可能会产生什么问题
     * a 获取到锁之后，b给删除了
     * 需要保证对应是自己创建的 REDIS_LOCK_KEY 删除对应自己的。
     *
     * @param identifierValue
     */
    public void unRedisLock(String identifierValue) {
        Jedis conn = null;
        try {
            //1.建立 reids 连接
            conn = jedisPool.getResource();

            //如果该锁的 id 等于 identifierValue
            if (conn.get(REDIS_LOCK_KEY).equals(identifierValue)) {
                System.out.println(Thread.currentThread().getName() + "，释放锁，identifierValue：" + identifierValue);
                //释放锁
                conn.del(REDIS_LOCK_KEY);
            }
        } catch (Exception e) {

        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
