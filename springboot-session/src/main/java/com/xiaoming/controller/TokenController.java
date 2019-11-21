package com.xiaoming.controller;

import com.xiaoming.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {
	@Autowired
	private TokenService tokenService;
	@Value("${server.port}")
	private String serverPort;

	@RequestMapping("/put")
	public String put(String nameValue) {
		String token = tokenService.put(nameValue);
		return token + "-" + serverPort;
	}
    //生成好的token 移动端存放本地文件钟，浏览器存放在cookie中
    //http

	@RequestMapping("/get")
	public String get(String token) {
		String value = tokenService.get(token);
		return value + "-" + serverPort;
	}

}