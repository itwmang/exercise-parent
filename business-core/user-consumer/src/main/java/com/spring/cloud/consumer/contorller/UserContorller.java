package com.spring.cloud.consumer.contorller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yingying on 18-5-1.
 */
@RestController
public class UserContorller {
    @Value("${druid.min-idle}")
    private String test;
    @GetMapping(value = "/user")
    public String userPage() {
        return "user-list";
    }

  @GetMapping(value = "/test")
    public String test() {
        return "configcenter:"+test;
    }

}
