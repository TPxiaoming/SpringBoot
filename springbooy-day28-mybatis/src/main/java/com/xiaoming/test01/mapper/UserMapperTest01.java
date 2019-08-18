package com.xiaoming.test01.mapper;

import com.xiaoming.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

//@Mapper 可加可不加 不加在启动类中统一扫包@MapperScan(basePackages = "com.xiaoming.mapper")
public interface UserMapperTest01 {
    /**
     * 查询
     * @param name
     * @return
     */
    @Select("SELECT * FROM USER WHERE NAME = #{name}")
    User findByName(@Param("name") String name);

    /**
     * 添加
     * @param name
     * @param age
     * @return
     */
    @Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);
}
