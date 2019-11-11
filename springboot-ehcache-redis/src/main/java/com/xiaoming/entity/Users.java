package com.xiaoming.entity;

import java.io.Serializable;

/**
 * @author xiaoming
 * @Date 2019/11/7
 * @blame Android Team
 */
public class Users implements Serializable {



    private Integer id;

    private String name;

    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
