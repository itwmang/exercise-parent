package com.spring.boot.framework.redis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yingying on 18-5-22.
 */
@Configuration
@ConfigurationProperties(prefix = "fw.jwt")
public class JwtConfig {
    private String jwtKey;
    private String profix;
    private String jwtAuthUrl;

    public String getJwtKey() {
        return jwtKey;
    }

    public void setJwtKey(String jwtKey) {
        this.jwtKey = jwtKey;
    }

    public String getProfix() {
        return profix;
    }

    public void setProfix(String profix) {
        this.profix = profix;
    }

    public String getJwtAuthUrl() {
        return jwtAuthUrl;
    }

    public void setJwtAuthUrl(String jwtAuthUrl) {
        this.jwtAuthUrl = jwtAuthUrl;
    }
}
