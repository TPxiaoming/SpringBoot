package com.xiaoming.day26.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 第二个Controller
 */
@RestController
public class MemberController {

    @RequestMapping("/getMember")
    public Map<String, Object> getMember(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("error", 200);
        hashMap.put("errorMsg", "xiaoming");
        return hashMap;
    }
}
