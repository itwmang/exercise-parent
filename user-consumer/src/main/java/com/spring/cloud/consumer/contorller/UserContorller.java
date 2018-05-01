package com.spring.cloud.consumer.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by yingying on 18-5-1.
 */
@Controller
public class UserContorller {
    @GetMapping(value = "/user")
    public String userPage() {
        return "user-list";
    }

}
