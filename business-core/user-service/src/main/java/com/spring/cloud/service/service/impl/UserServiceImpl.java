package com.spring.cloud.service.service.impl;

import com.spring.boot.framework.utils.MD5Utils;
import com.spring.cloud.service.dao.UserMapper;
import com.spring.cloud.service.entity.User;
import com.spring.cloud.service.entity.UserExample;
import com.spring.cloud.service.exception.UserException;
import com.spring.cloud.service.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
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

    @Override
    public void insertUser(User user) {
        String account = user.getAccount();
        String passwd = user.getPasswd();
        if (StringUtils.isBlank(account)) {
            throw UserException.user_insert_not_account_error.getException();
        }
        if (StringUtils.isBlank(passwd)) {
            throw UserException.user_insert_not_passwd_error.getException();
        }
        /**
         * TODO:验证用户名是否重复
         */
        passwd = MD5Utils.encryPasswd(account, new String(Base64.getDecoder().decode(passwd)));
        user.setPasswd(passwd);
        Date date = new Date();
        user.setLastupdatePasswd(date);
        user.setCreater("postman");
        user.setCreateTime(date);
        user.setUpdater("postman");
        user.setUpdateTime(date);
        userMapper.insert(user);
    }

}
