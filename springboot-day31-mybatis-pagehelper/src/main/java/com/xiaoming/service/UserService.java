package com.xiaoming.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoming.entity.User;
import com.xiaoming.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * springboot 整合 pageHelper
 */
@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	/**
	 *
	 * @param page 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public PageInfo<User> findUserList(int page, int pageSize){
		//mysql 分页查询 limit oracle 通过 伪列rownum
		//pageHelper 帮我们生成分页语句  底层实现原理采用改写 sql 语句
		PageHelper.startPage(page, pageSize);
		List<User> userList = userMapper.findUserList();
		//返回给客户端展示
		PageInfo<User> userPageInfo = new PageInfo<>(userList);
		return userPageInfo;
	}

}
