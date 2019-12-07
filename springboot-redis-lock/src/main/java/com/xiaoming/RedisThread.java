package com.xiaoming;

/**
 * @author xiaoming
 * @Date 2019/12/5
 */
public class RedisThread extends Thread {
    private LockService lockService;

    public RedisThread(LockService lockService){
        this.lockService = lockService;
    }
    @Override
    public void run() {
        lockService.seckill();
    }
}
