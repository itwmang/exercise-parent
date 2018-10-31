package com.spring.cloud.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringCloudApplication
@EnableHystrix
@EnableFeignClients
@RestController
@MapperScan("com.spring.cloud.service.dao")
public class UserSevrviceApplication extends SpringBootServletInitializer{

	@Value("${druid.url}")
	private String url;
	@Value("${server.port}")
	private String port;

	@RequestMapping(value = "geturl",method = RequestMethod.POST)
	public String geturl(){
		return "config Center return port:["+port+"] url:["+url+"]";
	}


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(UserSevrviceApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(UserSevrviceApplication.class, args);
	}
}
