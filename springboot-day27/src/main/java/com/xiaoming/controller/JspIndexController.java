package com.xiaoming.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspIndexController {

    @RequestMapping("jspIndex")
    public String jspIndex(){
        return "jspIndex";
    }
}
