package com.xiaoming.mapper;

import com.xiaoming.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
	@Select("SELECT * FROM USER ")
	List<User> findUserList();
}
