package com.spring.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ZuulGetewayApplication extends SpringBootServletInitializer{


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ZuulGetewayApplication.class);
	}


//	@Bean
//	public PermissionPreFilter permissionPreFilter(){
//		return new PermissionPreFilter();
//	}
	@Bean
	LoadBalancerInterceptor loadBalancerInterceptor(LoadBalancerClient loadBalance) {
		return new LoadBalancerInterceptor(loadBalance);
	}

	public static void main(String[] args) {
		SpringApplication.run(ZuulGetewayApplication.class, args);
	}
}
