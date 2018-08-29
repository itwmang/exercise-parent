package com.spring.cloud.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by yingying on 18-4-29.
 */
@FeignClient("user-service")
public interface UserService {

    @RequestMapping(value = "geturl",method = RequestMethod.POST)
    public String geturl();

    @PostMapping(value = "/user/get/{id}")
    public String getUserById(@PathVariable("id") int id);

    @PostMapping(value = "/user/list")
    public String listUser();
}
