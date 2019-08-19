package com.xiaoming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class JdbcUserServiceImpl {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void createUser(String name, Integer age) {
		jdbcTemplate.update("insert into user values(null,?,?);", name, age);
	}
}
