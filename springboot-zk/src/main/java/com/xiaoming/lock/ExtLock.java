package com.xiaoming.lock;

/**
 * 基于 Zookeeper 实现分布式锁
 * @author xiaoming
 * @Date 2019/11/19
 */
public interface ExtLock {

    /**
     * 获取锁
     */
    void getLock();

    /**
     * 释放锁
     */
    void unLock();
}
