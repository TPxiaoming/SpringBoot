package com.xiaoming.service;

import com.xiaoming.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

//@Service
@Slf4j
public class UserServcie {

   @Autowired
   private UserMapper userMapper;


   @Transactional
    public int insertUser(String name, Integer age){
        int result = userMapper.insert(name, age);
        int i = 1/ age;
        log.info("insertUser result：" + result);
        return result;
    }
}
