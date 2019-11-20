package com.xiaoming.session;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * 初始化Session配置
 * @author xiaoming
 * @Date 2019/11/20
 */
public class SessionInitializer extends AbstractHttpSessionApplicationInitializer {
    public SessionInitializer(){
        super(SessionConfig.class);
    }
}
