package com.xiaoming.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局捕获异常处理器
 * 1.捕获返回 json 格式
 * 2.捕获返回页面
 *
 * @ControllerAdvice springboot 异常切入点
 * @ResponseBody 返回Json 格式
 * @ModelAndView 返回视图页面
 * @ExceptionHandler(RuntimeException.class) 拦截错误   会拦截运行时异常
 */
@ControllerAdvice(basePackages = "com.xiaoming.controller")
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String, Object> errorrResult(){
        //实际开发项目中，怎么做的？ 会将错误记录在日志中。通过定时任务检查出比较重要的错误，存放在mangodb中，在后台发送给运营人员
        HashMap<String, Object> errorResultMap = new HashMap<>();
        errorResultMap.put("errorCode", "500");
        errorResultMap.put("errorMsg", "系统错误");
        return errorResultMap;
    }
}
