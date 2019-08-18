package com.xiaoming.entity;

import lombok.Data;

/**
 * 实例类
 */
@Data //@Data = @Setter + @Getter
public class User {

    private Integer id;

    private Integer age;

    private String name;
}
