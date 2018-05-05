package com.spring.cloud.zuul;

import com.spring.cloud.zuul.filters.PermissionPreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class ZuulGetewayApplication extends SpringBootServletInitializer{


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ZuulGetewayApplication.class);
	}


	@Bean
	public PermissionPreFilter permissionPreFilter(){
		return new PermissionPreFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(ZuulGetewayApplication.class, args);
	}
}
