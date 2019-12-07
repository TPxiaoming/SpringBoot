package com.xiaoming.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoming
 * @Date 2019/11/25
 */
@RestController
public class BIndexController {


    /**
     * 使用设置响应头允许跨域
     * @return
     */
    @RequestMapping("/ajaxB")
    public Map<String ,Object> getBInfo(HttpServletResponse response){
        //* 表示所有的请求都允许跨域访问
        response.setHeader("Access-Control-Allow-Origin", "*");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("code", "200");
        hashMap.put("returnMsg", "登录成功");
        return hashMap;
    }

    /**
     * 使用 jsonp 解决跨域问题
     * @return
     */
    /*@RequestMapping("/ajaxB")
    public void getBInfo(HttpServletResponse response, String jsonCallBack) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "200");
        jsonObject.put("returnMsg", "登录成功");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(jsonCallBack + "("+jsonObject.toString()+")");
        writer.close();
    }*/
}
