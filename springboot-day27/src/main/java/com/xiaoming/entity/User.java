package com.xiaoming.entity;

import lombok.Getter;
import lombok.Setter;

public class User {

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private  Integer age;

    //需要生成 get 和 set 方法

    public static void main(String[] args) {
        User user = new User();

    }
}
