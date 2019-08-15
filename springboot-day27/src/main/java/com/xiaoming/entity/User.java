package com.xiaoming.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * lombok 减少重复代码
 * lombok 底层使用字节码技术 asm 修改字节码文件，生成 get和 set 方法
 * lombok 一定要在开发工具安装， 在编译的时候修改字节码文件（底层使用字节码技术），线上环境使用编译好的文件
 */

@Slf4j  //等同于打印日志  使用AOP统一处理Web请求日志
@Getter
@Setter
public class User {


    private String name;
    private  Integer age;

    @Override
    public String toString() {
        return "username:" + name + ",age:" + age;
    }

    //需要生成 get 和 set 方法，最终编译的时候还是会生成

    public static void main(String[] args) {
        User user = new User();
        user.setAge(18);
        user.setName("xiaoming");
        log.info(user.toString());
    }
}
