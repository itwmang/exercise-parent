package com.spring.cloud.service;

import com.alibaba.fastjson.JSON;
import com.spring.cloud.service.service.UserService;
import com.wmang.system.api.auth.model.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserSevrviceApplicationTests {


	@Autowired
	private UserService userService;

	@Test
	public void testFindUser(){
		String name = "13801233210";
		AuthUser user = userService.findUserByMobile(name);
		log.info(JSON.toJSONString(user));
	}
}
