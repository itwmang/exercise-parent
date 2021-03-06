package com.spring.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
//@EnableEurekaClient
@EnableConfigServer
public class ConfigCenterApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ConfigCenterApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterApplication.class, args);
    }
}
