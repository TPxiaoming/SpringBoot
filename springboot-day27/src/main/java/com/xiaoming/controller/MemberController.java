package com.xiaoming.controller;

import com.xiaoming.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * springboot 异步调用技术
 */
@RestController
@Slf4j
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * 项目启动是的时候，初始化，从配置文件中取
     */
    @Value("${name}")
    private String name;

    @Value("${http_url}")
    private String httpUrl;

    @RequestMapping("/addMemberAndEmail")
    public String addMemberAndEmail(){
        log.info("1");
        String result = memberService.addMemberAndEmail();
        log.info("4");
        return result;
    }

    @RequestMapping("/getName")
    public String getName(){
        return name;
    }

    @RequestMapping("/getHttpUrl")
    public String getHttpUrl(){
        return httpUrl;
    }
}
