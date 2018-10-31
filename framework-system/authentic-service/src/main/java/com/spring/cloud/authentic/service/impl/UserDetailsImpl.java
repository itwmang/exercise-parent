package com.spring.cloud.authentic.service.impl;

import com.spring.boot.framework.api.beans.AuthRole;
import com.spring.cloud.framework.utils.constant.SecurityConstant;
import com.wmang.system.api.auth.model.AuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by yingying on 2018/6/6.
 */
public class UserDetailsImpl implements UserDetails {


    public static final Logger log = LoggerFactory.getLogger(UserDetailsImpl.class);

    private String username;
    private String password;
    private int status = 0;
    //角色列表
    private List<AuthRole> roles = new ArrayList<>();

    public UserDetailsImpl(List<AuthRole> roles, String username, String password, int status) {
        this.roles = roles;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public UserDetailsImpl(AuthUser user) {
        this.username = user.getUserName();
        this.password = user.getPassword();
        this.status = user.getStatus();
        this.roles = user.getRoles();
    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (AuthRole role : roles) {
            authorityList.add(new SimpleGrantedAuthority(role.getRoleCode()));
        }
        return authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return SecurityConstant
                .STATUS_LOCK == this.status ? false : true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return SecurityConstant.STATUS_NORMAL == this.status ? true : false;
    }


    public static Logger getLog() {
        return log;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<AuthRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AuthRole> roles) {
        this.roles = roles;
    }
}
