package com.spring.cloud.service.service.impl;

import com.spring.cloud.service.dao.UserMapper;
import com.spring.cloud.service.entity.User;
import com.spring.cloud.service.entity.UserExample;
import com.spring.cloud.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yingying on 18-4-15.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    private UserExample userExample;

    @Override
    public User getUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> listUser() {
        userExample = new UserExample();
        int count = userMapper.countByExample(userExample);
        log.info("===listUser==count:" + count);
        List<User> list = userMapper.selectByExample(userExample);
        return list;
    }
}
