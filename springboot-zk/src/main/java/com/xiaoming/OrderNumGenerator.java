package com.xiaoming;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生产订单号类
 * @author xiaoming
 * @Date 2019/11/19
 */
public class OrderNumGenerator {

    /**
     * 全局订单 id，区分不同的订单号
     */
    private static int count = 0;

    /**
     * 单台服务器上，多个线程同时生成订单号， 会出现线程安全问题
     * @return
     */
    public String getNumber() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return format.format(new Date()) + "-" + ++count;
    }
}
