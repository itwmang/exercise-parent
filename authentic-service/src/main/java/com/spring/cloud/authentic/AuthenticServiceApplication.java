package com.spring.cloud.authentic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.spring.cloud.authentic.dao")
@RestController
public class AuthenticServiceApplication {
	@Value("${testbus}")
	private String testbus;

	@GetMapping(value = "test")
	public String test(){
		return "config: "+testbus;
	}
	public static void main(String[] args) {
		SpringApplication.run(AuthenticServiceApplication.class, args);
	}
}
