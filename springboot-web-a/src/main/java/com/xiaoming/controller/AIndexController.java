package com.xiaoming.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiaoming.util.HttpClientUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xiaoming
 * @Date 2019/11/25
 */
@Controller
public class AIndexController {

    @RequestMapping("/aIndex")
    public String aIndex(){
        return "aIndex";
    }

    /**
     * 使用HttpClient 进行内部转发
     * @return
     */
    @RequestMapping("/forwardB")
    @ResponseBody
    public JSONObject forwardB(){
        JSONObject result = HttpClientUtils.httpGet("http://b.xiaoming.com:8081/ajaxB");
        System.out.println(result);
        return result;
    }
}
