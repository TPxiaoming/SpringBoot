package com.xiaoming;

import com.xiaoming.lock.ExtLock;
import com.xiaoming.lock.impl.ZookeeperDistrbuteLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用多线程情况模拟生成订单号
 * 多线程生成订单号，线程安全问题解决
 * 使用synchronized或者loca锁
 *
 * @author xiaoming
 * @Date 2019/11/19
 */
public class OrderService implements Runnable {
    private OrderNumGenerator orderNumGenerator = new OrderNumGenerator();

    private ReentrantLock lock =  new ReentrantLock();

    private ExtLock extLock = new ZookeeperDistrbuteLock();

    @Override
    public void run() {
        getNumber();
    }

    /*public synchronized void getNumber() {
        //多个线程共享一个全局 id
        String number = orderNumGenerator.getNumber();
        System.out.println(Thread.currentThread().getName() + ",生成订单ID:" + number);
    }*/

   /* public void getNumber() {
        try {
            lock.lock();
            String number = orderNumGenerator.getNumber();
            System.out.println(Thread.currentThread().getName() + ",生成订单ID:" + number);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }*/

    public void getNumber() {
        try {
            extLock.getLock();
            String number = orderNumGenerator.getNumber();
            System.out.println(Thread.currentThread().getName() + ",生成订单ID:" + number);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            extLock.unLock();
        }
    }

    public static void main(String[] args) {
        System.out.println("####生成唯一订单号###");
        for (int i = 0; i < 100; i++) {
            //模拟分布式锁的场景
            new Thread(new OrderService()).start();
        }

    }
}
