package com.spring.cloud.zuul.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
@ConditionalOnExpression("!'${urls}'.isEmpty()")
@ConfigurationProperties(prefix = "urls")
public class FilterUrlsConfig {

    private List<String> collects = new ArrayList<>();

    public List<String> getCollects() {
        return collects;
    }

    public void setCollects(List<String> collects) {
        this.collects = collects;
    }
}
