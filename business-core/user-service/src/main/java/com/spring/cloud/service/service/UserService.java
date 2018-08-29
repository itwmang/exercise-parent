package com.spring.cloud.service.service;

import com.spring.cloud.service.entity.User;

import java.util.List;

/**
 * Created by yingying on 18-4-15.
 */
public interface UserService {

    User getUserById(int id);

    List<User> listUser();

    void insertUser(User user);
}
