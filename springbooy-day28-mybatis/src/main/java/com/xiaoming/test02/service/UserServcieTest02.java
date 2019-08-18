package com.xiaoming.test02.service;

import com.xiaoming.test02.mapper.UserMapperTest02;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserServcieTest02
 */
@Service
@Slf4j
public class UserServcieTest02 {

   @Autowired
   private UserMapperTest02 userMapper;


   @Transactional(transactionManager = "test2TransactionManager")
    public int insertUser(String name, Integer age){
        int result = userMapper.insert(name, age);
       log.info("insertUser result：" + result);
        int i = 1/ age;
        return result;
    }
}
