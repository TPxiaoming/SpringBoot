package com.xiaoming.controller;

import com.xiaoming.dao.UserDao;
import com.xiaoming.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class IndexController {
	@Autowired
	private UserDao userDao;

	@RequestMapping("/jpaFindUser")
	public Object jpaIndex() {

		return userDao.findAll();
	}

}
