package com.spring.cloud.service.controller;

import com.alibaba.fastjson.JSON;
import com.spring.boot.framework.api.BaseException;
import com.spring.cloud.service.entity.User;
import com.spring.cloud.service.exception.UserException;
import com.spring.cloud.service.service.UserService;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户操作基础请求
 * Created by yingying on 18-4-18.
 */
@RestController(value = "/user")
public class UserController {
    private Logger log = LoggerFactory.getLogger(UserController.class);
    @Value("${server.port}")
    private String port;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/get/{id}")
    public String getUserById(@PathVariable("id") int id) {
        try {
            StopWatch sp = new StopWatch();
            sp.start();
            log.info("UserController_getUserById_id:" + id);
            User user = userService.getUserById(id);
            sp.stop();
            log.info("UserController_getUserById_Success,port[" + port + "],time[" + sp.getTime() + "]毫秒");
            return JSON.toJSONString(user);
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw UserException.user_get_error.getException(e.getLocalizedMessage());
        }
    }

    @GetMapping(value = "/list")
    public String listUser() {
        try {
            StopWatch sp = new StopWatch();
            sp.start();
            log.info("UserController_listUser");
            List<User> users = userService.listUser();
            sp.stop();
            log.info("UserController_listUser_Success,port[\"+port+\"],time[" + sp.getTime() + "]毫秒");
            return JSON.toJSONString(users);
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw UserException.user_get_error.getException(e.getLocalizedMessage());
        }
    }

    @PostMapping(value = "/insert")
    public String insertUser(@RequestBody User user) {
        try {
            StopWatch sp = new StopWatch();
            sp.start();
            log.info("UserController_insertUser");
            userService.insertUser(user);
            sp.stop();
            log.info("UserController_insertUser_Success,port[\"+port+\"],time[" + sp.getTime() + "]毫秒");
            return JSON.toJSONString(user);
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw UserException.user_insert_error.getException(e.getLocalizedMessage());
        }
    }

//
//    public static void main(String[] args) {
//        User user = new User();
//        user.setUsername("spring");
//        user.setPassword("MQ==");
//        user.setFullname("springcloud测试账号");
//        user.setStatu(CommonConstant.STATUS_DEL.shortValue());
//        System.out.println(JSON.toJSONString(user));
//    }
}
