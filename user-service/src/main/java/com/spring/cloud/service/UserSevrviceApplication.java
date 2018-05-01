package com.spring.cloud.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
@MapperScan("com.spring.cloud.service.dao")
public class UserSevrviceApplication {

	@Value("${druid.url}")
	private String url;
	@Value("${server.port}")
	private String port;

	@RequestMapping(value = "geturl",method = RequestMethod.POST)
	public String geturl(){
		return "config Center return port:["+port+"] url:["+url+"]";
	}

	public static void main(String[] args) {
		SpringApplication.run(UserSevrviceApplication.class, args);
	}
}
