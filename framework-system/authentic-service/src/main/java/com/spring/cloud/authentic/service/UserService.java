package com.spring.cloud.authentic.service;

import com.wmang.system.auth.model.AuthUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by yingying on 2018/6/6.
 */
public interface UserService {
    /**
     * 通过用户名查询用户、角色信息
     *
     * @param username
     *            用户名
     */
    @GetMapping("/api/findUserByUsername/{username}")
    AuthUser findUserByUsername(@PathVariable("username") String username);

    /**
     * 通过手机号查询用户、角色信息
     */
    @GetMapping("/api/findUserByMobile/{mobile}")
    AuthUser findUserByMobile(@PathVariable("mobile") String mobile);
}
