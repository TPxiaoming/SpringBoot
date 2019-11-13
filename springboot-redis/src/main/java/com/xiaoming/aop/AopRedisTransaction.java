package com.xiaoming.aop;

import com.xiaoming.annotation.ExtRedisTranscation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

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
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        ExtRedisTranscation annotation = method.getAnnotation(ExtRedisTranscation.class);
        if (annotation != null) {
            System.out.println("开启事务");
            //开启事务权限
            stringRedisTemplate.setEnableTransactionSupport(true);
            stringRedisTemplate.multi();
            Object proceed = proceedingJoinPoint.proceed();
            stringRedisTemplate.exec();
            System.out.println("提交事务");
            return proceed;
        }
        return proceedingJoinPoint.proceed();
    }
}
