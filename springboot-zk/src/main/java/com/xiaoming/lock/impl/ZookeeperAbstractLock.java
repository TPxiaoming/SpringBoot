package com.xiaoming.lock.impl;

import com.xiaoming.lock.ExtLock;
import org.I0Itec.zkclient.ZkClient;

/**
 * 将重复代码抽象到子类中，使用模板方法设计模式
 * @author xiaoming
 * @Date 2019/11/19
 */
public abstract class ZookeeperAbstractLock implements ExtLock {

    /**
     * Zookeeper 连接地址
     */
    private static final String CONNECTSTRING = "127.0.0.1:2181";
    /**
     * Zookeeper 超时时间
     */
    private static final int SESSIONTIMEOUT = 5000;


    protected ZkClient zkClient = new ZkClient(CONNECTSTRING, SESSIONTIMEOUT);

    /**
     * 获取锁
     * 1.连接 zkClient 在 zk 上创建一个 /lock 节点，节点类型为临时节点
     * 2.如果节点创建成功，直接执行业务逻辑，如果节点创建失败，进行等待
     * 3.使用时间通知监听该节点是否被删除，如果被删除的话重新进入获取锁的资源
     */
    @Override
    public void getLock() {
        //1.连接 zkClient 在 zk 上创建一个 /lock 节点，节点类型为临时节点
        //如果节点创建成功，直接执行业务逻辑，如果节点创建失败，进行等待
        if (tryLock()){
            System.out.println("###### 获取锁成功 ######");
        } else {
            //进行等待
            //3.使用时间通知监听该节点是否被删除，如果被删除的话重新进入获取锁的资源
            waitLock();
            getLock();
        }
    }

    /**
     * 如果节点创建成功，直接执行业务逻辑，如果节点创建失败，进行等待
     * @return
     */
    abstract boolean tryLock();

    /**
     * .如果节点创建失败，使用时间通知监听该节点是否被删除，如果被删除的话重新进入获取锁的资源
     * @return
     */
    abstract void waitLock();

    /**
     * 释放锁
     */
    @Override
    public void unLock() {
        if (zkClient != null){
            zkClient.close();
            System.out.println("###### 释放锁成功 ######");
        }
    }
}
