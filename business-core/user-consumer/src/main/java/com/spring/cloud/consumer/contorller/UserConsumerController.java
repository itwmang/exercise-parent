package com.spring.cloud.consumer.contorller;

import com.spring.cloud.consumer.service.UserService;
import org.apache.commons.lang.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yingying on 18-4-28.
 */
@RestController
public class UserConsumerController {
    Logger log = LoggerFactory.getLogger(UserConsumerController.class);
    @Autowired
    private UserService userService;

    @PostMapping(value = "/geturl")
    public String getUrl() {
        String user = userService.geturl();
        return user;
    }

//    @GetMapping(value = "/user")
//    public String userPage() {
//        return "user-list";
//    }


    @PostMapping(value = "/user/get/{id}")
    public String getUserById(@PathVariable("id") int id) {
        String user = userService.getUserById(id);
        return user;
    }

    @PostMapping(value = "/user/list")
    public String listUser() {
        return userService.listUser();
    }
}
