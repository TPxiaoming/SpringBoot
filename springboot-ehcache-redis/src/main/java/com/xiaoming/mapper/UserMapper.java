package com.xiaoming.mapper;

import com.xiaoming.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * UserMapper
 *
 * @blame Android Team
 */ //@CacheConfig(cacheNames = "userCache") //缓存名称
public interface UserMapper {
	@Select("SELECT ID ,NAME,AGE FROM user where id=#{id}")
//	@Cacheable//开启缓存
	Users getUser(@Param("id") Long id);
}