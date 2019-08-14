package com.xiaoming.day27.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 整合 freemaker
 */
@Controller
public class FtlIndexController {

    @RequestMapping("/ftlIndex")
    public String ftlIndex(Map<String, Object> map){
        map.put("name", "xiaoming");
        map.put("sex", "0");

        List<String> userlist  = new ArrayList<String>();
        userlist .add("zhangsan");
        userlist .add("lisi");
        userlist .add("xiaoming");
        map.put("userlist", userlist );

        return "ftlIndex";
    }
}
