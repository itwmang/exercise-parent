package com.spring.cloud.authentic.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yingying on 18-5-22.
 */
@Configuration
@ConfigurationProperties(prefix = "fw.jwt")
public class JwtConfig {
    private String jwtKey;

    public String getJwtKey() {
        return jwtKey;
    }

    public void setJwtKey(String jwtKey) {
        this.jwtKey = jwtKey;
    }
}
