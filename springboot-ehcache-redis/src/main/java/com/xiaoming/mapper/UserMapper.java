package com.xiaoming.mapper;

import com.xiaoming.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = "userCache") //缓存名称
public interface UserMapper {
	@Select("SELECT ID ,NAME,AGE FROM user where id=#{id}")
	@Cacheable
		//开启缓存
	List<Users> getUser(@Param("id") Long id);
}