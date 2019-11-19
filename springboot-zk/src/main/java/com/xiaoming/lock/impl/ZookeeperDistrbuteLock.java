package com.xiaoming.lock.impl;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

/**
 * @author xiaoming
 * @Date 2019/11/19
 */
public class ZookeeperDistrbuteLock extends ZookeeperAbstractLock {

    private String lockPath = "/lock";
    private CountDownLatch countDownLatch = null;

    @Override
    boolean tryLock() {
        try {
            zkClient.createEphemeral(lockPath);
            return true;
        } catch (RuntimeException e) {
//            e.printStackTrace();
            return false;
        }
    }

    @Override
    void waitLock() {
        IZkDataListener iZkDataListener = new IZkDataListener() {
            /**
             * 节点被修改
             *
             * @param s
             * @param o
             * @throws Exception
             */
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            /**
             * 节点被删除
             *
             * @param s
             * @throws Exception
             */
            @Override
            public void handleDataDeleted(String s) throws Exception {
                if (countDownLatch != null) {
                    //计数器一旦为0的情况，await 后面代码继续执行
                    countDownLatch.countDown();
                }
            }
        };
        //监听事件通知
        zkClient.subscribeDataChanges(lockPath, iZkDataListener);

        //如何控制此程序等待
        if (zkClient.exists(lockPath)) {
            countDownLatch = new CountDownLatch(1);
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //为了不影响程序的效率，建议删除该事件监听
        zkClient.unsubscribeDataChanges(lockPath, iZkDataListener);
    }


}
