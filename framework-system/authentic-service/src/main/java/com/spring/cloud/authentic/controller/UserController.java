package com.spring.cloud.authentic.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UserController {

    @GetMapping("/")
    public String index(Authentication authentication) throws IOException {
        return "登录成功";
    }

    /**
     * 获取用户信息
     *
     * @param authentication
     * @throws IOException
     */
    @RequestMapping("/user/info")
    public Object user(Authentication authentication) throws IOException {
        return authentication.getPrincipal();
    }

}
