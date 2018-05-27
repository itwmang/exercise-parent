package com.spring.cloud.authentic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.spring.cloud.authentic.dao")
@RestController
public class AuthenticServiceApplication extends SpringBootServletInitializer {
	@Value("${testbus}")
	private String testbus;

	@GetMapping(value = "test")
	public String test(){
		return "config: "+testbus;
	}
	public static void main(String[] args) {
		SpringApplication.run(AuthenticServiceApplication.class, args);
	}

	/**
	 * Configure the application. Normally all you would need to do is to add sources
	 * (e.g. config classes) because other settings have sensible defaults. You might
	 * choose (for instance) to add default command line arguments, or set an active
	 * Spring profile.
	 *
	 * @param builder a builder for the application context
	 * @return the application builder
	 * @see SpringApplicationBuilder
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return super.configure(builder);
	}
}
