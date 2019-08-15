package com.xiaoming.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MemberService {

    /**
     * 添加用户的时候，会去发送邮件
     * @return
     */
    @Async  //方法异步调用  相当于该方法重新开启单独的线程进行执行
    public String addMemberAndEmail(){
        log.info("2");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("3");
        return "xiaoming";
    }
}
