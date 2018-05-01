package com.spring.cloud.service.controller;

import com.alibaba.fastjson.JSON;
import com.spring.cloud.service.entity.User;
import com.spring.cloud.service.service.UserService;
import org.apache.commons.lang.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yingying on 18-4-18.
 */
@RestController
//@RequestMapping(value = "/userService")
public class UserController {
    private Logger log = LoggerFactory.getLogger(UserController.class);
    @Value("${server.port}")
    private String port;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/get/{id}")
    public String getUserById(@PathVariable("id") int id) {
        StopWatch sp = new StopWatch();
        sp.start();
        log.info("UserController_getUserById_id:" + id);
        User user = userService.getUserById(id);
        sp.stop();
        log.info("UserController_getUserById_Success,port[" + port + "],time[" + sp.getTime() + "]毫秒");
        return JSON.toJSONString(user);
    }

    @PostMapping(value = "/user/list")
    public String listUser() {
        StopWatch sp = new StopWatch();
        sp.start();
        log.info("UserController_listUser");
        List<User> users = userService.listUser();
        sp.stop();
        log.info("UserController_listUser_Success,port[\"+port+\"],time[" + sp.getTime() + "]毫秒");
        return JSON.toJSONString(users);
    }
}
