package com.spring.cloud.authentic.service.impl;

import com.spring.boot.framework.api.utils.MD5Utils;
import com.spring.cloud.authentic.dao.UserMapper;
import com.spring.cloud.authentic.dto.AuthResponse;
import com.spring.cloud.authentic.entity.User;
import com.spring.cloud.authentic.entity.UserExample;
import com.spring.cloud.authentic.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.List;

/**
 * Created by yingying on 18-5-6.
 */
@Service("authService")
public class AuthServiceImpl implements AuthService {
    Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    private UserExample userExample = new UserExample();

    @Override
    public AuthResponse login(String account, String passwd) {
        AuthResponse res = new AuthResponse();
        //验证用户名密码
        userExample.clear();
        userExample.createCriteria().andAccountEqualTo(account);
//        userExample.createCriteria().andPasswdEqualTo(passwd);
        List<User> users = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(users)) {
            if (log.isDebugEnabled()) {
                log.debug(MessageFormat.format("account[{0}]登录接口调用失败,账号不存在.请检查!", account));
            }
            res.setStatus(false);
            res.setMsg("账号不存在.请检查!");
            return res;
        } else {
            if (users.size() > 1) {
                if (log.isDebugEnabled()) {
                    log.debug(MessageFormat.format("account[{0}]登录接口调用失败,账号存在重复信息!", account));
                }
                res.setStatus(false);
                res.setMsg("用户验证失败!");
                return res;
            } else {
                User user = users.get(0);
                String encryPasswd = MD5Utils.encryPasswd(account, passwd);
                if (!encryPasswd.equals(user.getPasswd())) {
                    if (log.isDebugEnabled()) {
                        log.debug(MessageFormat.format("account[{0}]登录验证失败,密码不正确!", account));
                    }
                    res.setStatus(false);
                    res.setMsg("用户验证请求失败!");
                    return res;
                } else {
                    if (log.isDebugEnabled()) {
                        log.debug(MessageFormat.format("account[{0}]登录验证通过!", account));
                    }
                    res.setStatus(true);
                    res.setContent(user);
                    return res;
                }

            }
        }


    }

//    public static void main(String[] args) {
//        String account = "spring", passwd = "1";
//        String encryPasswd = MD5Utils.encry(MD5Utils.encry(account.substring(0, account.length() / 2)).concat(MD5Utils.encry(Base64Utils.encode(passwd))).concat(MD5Utils.encry(account.substring(account.length() / 2, account.length()))));
//        System.out.println(encryPasswd);
//        //D27F4751A21B543D00E428ADFBD75253
//    }
}
