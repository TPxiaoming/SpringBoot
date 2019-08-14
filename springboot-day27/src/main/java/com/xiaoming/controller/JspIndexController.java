package com.xiaoming.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspIndexController {
    private static final Logger logger = LoggerFactory.getLogger(JspIndexController.class);
    @RequestMapping("jspIndex")
    public String jspIndex(){
        logger.info("springboot 整合 logger 日志");
        return "jspIndex";
    }
}
