package com.xiaoming;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: xiaoming
 * @Date: 16:22 2019/11/18
 * Java 操作Zookeeper
 */
public class Test {
    /**
     * Zookeeper 连接地址
     */
    private static final String CONNECTSTRING = "127.0.0.1:2181";
    /**
     * Zookeeper 超时时间
     */
    private static final int SESSIONTIMEOUT = 5000;
    /**
     * 使用 Java 并发包 信号量技术 控制 Zookeeper 连接成功之后，开始创建节点，否则情况下会等待
     */
    private static final CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = null;
        try {
            //1.Zookeeper 创建了一个连接
            zooKeeper = new ZooKeeper(CONNECTSTRING, SESSIONTIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    //监听节点是否发生变化 连接成功

                    //获取事件状态
                    Event.KeeperState state = watchedEvent.getState();
                    //获取事件类型
                    Event.EventType type = watchedEvent.getType();
                    if (Event.KeeperState.SyncConnected == state){
                        if (Event.EventType.None == type){
                            //调用该方法的时候会减1，如果为0的情况下
                            countDownLatch.countDown();
                            System.out.println("Zookeeper 自动连接成功。。。");
                        }

                        if (Event.EventType.NodeCreated == type){
                            System.out.println("Zookeeper 事件通知 获取当前在创建节点");
                        }
                    }

                }
            });
            //如果计数器为0的情况下，会一直等待
            countDownLatch.await();
            String path = "/test";
            //允许有事件通知
            zooKeeper.exists(path, true);

            //节点类型
            //1.CreateMode.EPHEMERAL 创建一个临时节点
            //2.CreateMode.EPHEMERAL_SEQUENTIAL 如果节点发生重复的情况下，会自动 id 自增保证唯一性
            //3.CreateMode.PERSISTENT 持久类型，永久保存在硬盘上
            //4. CreateMode.PERSISTENT_SEQUENTIAL 如果节点发生重复的情况下，会自动 id 自增保证唯一性
            String nodeResult = zooKeeper.create("/test", "xiaoming".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println("节点名称：" + nodeResult);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (zooKeeper != null){
                Thread.sleep(10 * 1000);
                zooKeeper.close();
            }
        }

    }
}