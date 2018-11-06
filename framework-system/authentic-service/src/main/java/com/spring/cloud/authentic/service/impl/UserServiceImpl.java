package com.spring.cloud.authentic.service.impl;

import com.spring.cloud.authentic.service.UserService;
import com.wmang.system.api.auth.model.AuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by yingying on 2018/6/6.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    public static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 通过用户名查询用户、角色信息
     *
     * @param username 用户名
     */
    @Override
    public AuthUser findUserByUsername(@PathVariable("username") String username) {
        log.error("调用{}异常:{}", "findUserByUsername", username);
        return null;
    }

    /**
     * 通过手机号查询用户、角色信息
     *
     * @param mobile
     */
    @Override
    public AuthUser findUserByMobile(@PathVariable("mobile") String mobile) {
        log.error("调用{}异常:{}", "findUserByMobile", mobile);
        return null;
    }
}
