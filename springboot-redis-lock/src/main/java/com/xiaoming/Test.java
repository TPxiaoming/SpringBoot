package com.xiaoming;

/**
 * @author xiaoming
 * @Date 2019/12/5
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        LockService lockService = new LockService();
        for (int i = 0; i < 50; i++) {
            Thread thread = new RedisThread(lockService);
            thread.start();
            Thread.sleep(1000);
        }
    }
}
