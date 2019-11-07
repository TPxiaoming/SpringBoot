package com.xiaoming.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author xiaoming
 * @Date 2019/11/7
 * @blame aop 实现 redis 注解事务
 */
@Component
@Aspect
public class AopRedisTransaction {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @AfterThrowing("execution(* com.xiaoming.service.*.*(..))")
    public void afterThrowing(){
        System.out.println("程序已经回滚");
        stringRedisTemplate.discard();
    }

    @Around("execution(* com.xiaoming.service.*.*(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("开启事务");
        //开启事务权限
        stringRedisTemplate.setEnableTransactionSupport(true);
        stringRedisTemplate.multi();
        proceedingJoinPoint.proceed();
        stringRedisTemplate.exec();
        System.out.println("提交事务");
    }
}
