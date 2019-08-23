package com.xiaoming.controller;

import com.github.pagehelper.PageInfo;
import com.xiaoming.entity.User;
import com.xiaoming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	@Autowired
	private UserService userService;

	@RequestMapping("/findUserList")
	public PageInfo<User> findUserList(int page, int size) {
		return userService.findUserList(page, size);
	}

}
