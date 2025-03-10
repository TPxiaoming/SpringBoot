package com.xiaoming;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class LockService {
	private static JedisPool pool = null;

	static {
		JedisPoolConfig config = new JedisPoolConfig();
		// 设置最大连接数
		config.setMaxTotal(200);
		// 设置最大空闲数
		config.setMaxIdle(8);
		// 设置最大等待时间
		config.setMaxWaitMillis(1000 * 100);
		// 在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例均是可用的
		config.setTestOnBorrow(true);
		pool = new JedisPool(config, "47.102.136.26", 6379, 3000, "123456", 0);
	}

	/**
	 * 定义一个 redis 锁
	 */
	private LockRedis lockRedis = new LockRedis(pool);

	/**
	 * 演示 redis 实现分布式锁
	 */
	public void seckill(){
		//1.获取锁
		String identifierValue = lockRedis.getRedisLock(5000L, 5000L);
		if (identifierValue == null){
			System.out.println(Thread.currentThread().getName() + "，获取锁失败，原因为获取锁超时");
			return;
		}
		System.out.println(Thread.currentThread().getName() + "，获取锁成功，锁的id：" + identifierValue + "，正常执行业务逻辑");

		//2.释放锁
		lockRedis.unRedisLock(identifierValue);
	}

}
