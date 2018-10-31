package com.spring.cloud.service.service.impl;

import com.spring.boot.framework.api.beans.AuthRole;
import com.spring.boot.framework.api.utils.MD5Utils;
import com.spring.cloud.service.cache.UserCacheKey;
import com.spring.cloud.service.dao.UserMapper;
import com.spring.cloud.service.entity.Role;
import com.spring.cloud.service.entity.User;
import com.spring.cloud.service.entity.UserExample;
import com.spring.cloud.service.exception.UserException;
import com.spring.cloud.service.service.RoleService;
import com.spring.cloud.service.service.UserService;
import com.wmang.system.api.auth.model.AuthUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * Created by yingying on 18-4-15.
 */
@Service("userService")
@Cacheable(cacheNames = UserCacheKey.USER_INFO)
public class UserServiceImpl implements UserService {
    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleService roleService;

    @Override
    public User getUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> listUser() {
        UserExample userExample = new UserExample();
        int count = userMapper.countByExample(userExample);
        log.info("===listUser==count:" + count);
        List<User> list = userMapper.selectByExample(userExample);
        return list;
    }

    @Override
    public void insertUser(User user) {
        String account = user.getUsername();
        String passwd = user.getPassword();
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
        user.setPassword(passwd);
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        userMapper.insert(user);
    }

    @Override
    @Cacheable(key = "'user_name_'+#username")
    public AuthUser findUserByUsername(String username) {
        User user = this.getUseryUserName(username);
        List<Role> roleList = this.findRoleListByUserId(user.getUserId());
        return buildAuthUserByUser(user, roleList);
    }

    @Override
    @Cacheable(key = "'user_mobile_'+#mobile")
    public AuthUser findUserByMobile(String mobile) {
        User user = this.getUserByMobile(mobile);
        List<Role> roleList = this.findRoleListByUserId(user.getUserId());
        return buildAuthUserByUser(user, roleList);
    }

    /**
     * 根据手机号查询用户信息
     * @param mobile
     * @return
     */
    private User getUserByMobile(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return null;
        }
        UserExample userExample = new UserExample();
        userExample.createCriteria().andMobileEqualTo(mobile.trim());
        List<User> users = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        return CollectionUtils.isEmpty(users) ? null : users.get(0);
    }

    /**
     * 查询用户信息
     * @param username
     * @return
     */
    private User getUseryUserName(String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username.trim());
        List<User> users = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        return CollectionUtils.isEmpty(users) ? null : users.get(0);

    }

    /**
     * 查询用户所有角色信息
     * @param userId
     * @return
     */
    private List<Role> findRoleListByUserId(Integer userId) {
        if (null == userId || userId > 0) {
            return new ArrayList<>();
        }
        List<Role> roles = roleService.findRoleListByUserId(userId);
        return roles;
    }

    /**
     * 构建包含角色信息的用户对象
     * @param user
     * @param roleList
     * @return
     */
    private AuthUser buildAuthUserByUser(User user, List<Role> roleList) {
        if (null == user) {
            return null;
        }
        AuthUser authUser = new AuthUser();
        authUser.setPictureUrl(user.getPicUrl());
        authUser.setFullName(user.getFullname());
        authUser.setStatus(user.getStatu());
        authUser.setPassword(user.getPassword());
        authUser.setUserId(user.getUserId());
        authUser.setUserName(user.getUsername());

        if (CollectionUtils.isEmpty(roleList)) {
            return authUser;
        }
        List<AuthRole> rList = new ArrayList<>();
        for (Role r : roleList) {
            AuthRole aRole = new AuthRole();
            aRole.setStatus(r.getStatu());
            aRole.setRoleCode(r.getRoleCode());
            aRole.setRoleDesc(r.getRoleDesc());
            aRole.setRoleId(r.getRoleId());
            aRole.setRoleName(r.getRoleName());
            rList.add(aRole);
        }
        authUser.setRoles(rList);

        return authUser;
    }

}
