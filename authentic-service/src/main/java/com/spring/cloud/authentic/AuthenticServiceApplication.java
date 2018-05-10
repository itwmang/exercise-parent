package com.spring.cloud.authentic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.spring.cloud.authentic.dao")
public class AuthenticServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticServiceApplication.class, args);
	}
}
