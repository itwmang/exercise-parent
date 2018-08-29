package com.spring.cloud.authentic.service.impl;

import com.spring.cloud.authentic.service.UserService;
import com.wmang.system.auth.model.AuthUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.Serializable;

/**
 * Created by yingying on 2018/6/6.
 */
public class UserDetailServiceImpl implements UserDetailsService,Serializable {


    private UserService userService;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StringUtils.isBlank(username)){
            throw new UsernameNotFoundException("用户不存在："+username);
        }
        AuthUser user = userService.findUserByUsername(username);
        if (null == user) { throw new UsernameNotFoundException("用户不存在:" + username); }
        return new UserDetailsImpl(user);
    }
}

