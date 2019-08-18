package com.xiaoming.test01.service;

import com.xiaoming.test01.mapper.UserMapperTest01;
import com.xiaoming.test02.mapper.UserMapperTest02;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserServcieTest01
 */
@Service
@Slf4j
public class UserServcieTest01 {

   @Autowired
   private UserMapperTest01 userMapper;

    @Autowired
    private UserMapperTest02 userMapperTest02;

   @Transactional(transactionManager = "test1TransactionManager")
    public int insertUser(String name, Integer age){
       //传统分布式事务解决方案 jta + atomikos 注册到同一个全局事务中
        int result = userMapper.insert(name, age);
       log.info("insertUser result：" + result);
        int i = 1/ age;
        return result;
    }

    @Transactional(transactionManager = "test2TransactionManager")
    public int insertUserTest01AndTest02(String name, Integer age){
        int result = userMapper.insert(name, age);
        int resultTest02 = userMapperTest02.insert(name, age);
        log.info("insertUser result：" + result);
        int i = 1/ age;
        //test01 入库 test02 回滚
        return result + resultTest02;
    }
}
